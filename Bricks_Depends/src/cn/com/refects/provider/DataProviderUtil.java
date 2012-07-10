/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.refects.provider;

import cn.com.convert.LocalConvert;
import cn.com.refects.InstanceCreator;
import cn.com.refects.TypeContentRefecter;
import cn.com.refects.TypeInitialization;
import cn.com.utils.DataProviderConfig;
import cn.com.utils.StringUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public enum DataProviderUtil {

    /**
     * 实类化当前类
     */
    INSTACE;
    private static final String _FILE_TYPE_CVS = "cvs";
    private String basePath = DataProviderConfig.INSTANCE.getBasePath();
    private String spliter = DataProviderConfig.INSTANCE.getSpliter();
    private String parentPath = null;

    /**
     * 设定父文件夹路径
     * @param path
     */
    public void setParentPath(String path) {
        this.parentPath = path;
    }

    /**
     * 获取测试文件的内容
     * @param <T> 目标数据类型，支持bean的测试数据
     * @param childPath 测试内容保存的路径
     * @param clazz 目标数据类
     * @return 测试数据列表
     */
    public <T> List<T> getData(String childPath, Class<T> clazz) {

        // 判断是否为空
        assert (clazz != null);
        File[] files = this.getFiles(childPath);
        List<T> datas = new ArrayList<T>(15);

        for (File file : files) {
            this.getFileData(file, datas, clazz);
        }

        return datas;
    }

    private File[] getFiles(String childPath) {
        // 判断文件是否存在文件夹路径
        assert (new File(this.basePath + childPath).exists());

        File[] files = new File(this.basePath + childPath).listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.toLowerCase().contains(_FILE_TYPE_CVS)) {
                    return true;
                }
                return false;
            }
        });

        return files;

    }

    @SuppressWarnings("unchecked")
    private <T> void getFileData(File file, List<T> lists, Class<T> clazz) {
        assert (file != null && file.isFile() && lists != null);

        BufferedReader reader = null;
        FileReader fileStream = null;

        try {
            fileStream = new FileReader(file);
            reader = new BufferedReader(fileStream);

            // 跳过第一行列头数据
            int idx = 0;
            String lineString = null;
            String[] headers = null;
            while ((lineString = reader.readLine()) != null) {

                if(StringUtil.isEmpty(lineString)){
                    continue;
                }
                if (idx++ > 0) {
                    // 开始实际的数据封装
                    lists.add(this.convertToBean(lineString, clazz, spliter, headers));
                } else {
                    // 获取抬头设定，主要是封装bean数据用
                    headers = lineString.split(spliter);
                }

            }


        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {

                try {
//                    // 设定总是从第一行开始便利
//                    reader.reset();
                    reader.close();
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }

            if (fileStream != null) {
                try {
                    fileStream.close();
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    private <T> T convertToBean(String lineData, Class<T> clazz, String spliter, String[] header) {

        if (TypeInitialization.isCommonType(clazz)) {

            return (T) LocalConvert.getInstance().convertStringToObject(lineData, clazz, "yyyy-MM-dd");
        } else {

            List<Field> fields = TypeContentRefecter.getAllFields(clazz);
            Object value = null;
            try {
                value = InstanceCreator.getInstance(clazz.getName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataProviderUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            // 处理初始异常情况
            if (value == null) {
                return null;
            }
            // 处理分割符后面没有内容导致数组溢出
            String[] contents = (lineData+", ").split(spliter);
            for (Field field : fields) {
                int idx = 0;
                boolean isExists = false;
                for (String f : header) {

                    if (field.getName().equalsIgnoreCase(f)) {
                        isExists = true;
                        break;
                    }
                    idx++;
                }
                // 文件中存在数据映射才进行处理
                if (isExists) {

                    if (TypeInitialization.isCommonType(field.getType())) {
                        TypeContentRefecter.setValue(value, field.getName(), new Class[]{field.getType()}, LocalConvert.getInstance().convertStringToObject(contents[idx], field.getType(), "yyyy-MM-dd"));
                    } else {
                        String path = StringUtil.joinString(File.separator, parentPath, field.getName());

                        Object parameterValue = null;
                        if (field.getType().isArray()) {
                            List results = getData(path, field.getType().getComponentType());
                            parameterValue = results.toArray();
                        } else if (field.getType().isInterface() && field.getType().equals(List.class)) {
                            if (idx < contents.length && !StringUtil.isEmpty(contents[idx])) {
                                List results = getData(path + contents[idx], TypeContentRefecter.getGenericClass(field.getGenericType()));
                                parameterValue = results;
                            }
                        } else if (!field.getType().isInterface()) {
                            List results = getData(path, field.getType());
                            parameterValue = results.get(0);
                        } else {
                            continue;
                        }
                        TypeContentRefecter.setValue(value, field.getName(), new Class[]{field.getType()}, parameterValue);
                    }
                }
            }
            return (T) value;
        }

    }
    private static final Logger LOG = Logger.getLogger(DataProviderUtil.class.getName());
}
