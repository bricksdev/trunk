/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.loader.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public abstract class DynamicDeployUtil {

    private final static String _PATH_KEY = "deploypath";
    private final static String _PATH_VERSION_BACKUP = "version_bakup";
    private final static String _CONFIG_NAME = "config/hotdeployconfig.properties";
    private final static Properties _CONFIG_CONTENT;
    private final static Map<String, ResourceEntry> _DEPLOY_RESOURCES = new HashMap<String, ResourceEntry>();
    private final static ScheduledExecutorService _SCHEDULE_EXECUTOR = Executors.newSingleThreadScheduledExecutor();
    private static final String CLASS_TYPE_NAME = ".class";
    private volatile static AtomicBoolean _IS_RELOAD = new AtomicBoolean();

    static {
        // 重启后备份已部署的文件
        bakup();
        _CONFIG_CONTENT = new Properties();
        try {
            _CONFIG_CONTENT.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(_CONFIG_NAME));
        } catch (IOException ex) {
            Logger.getLogger(DynamicDeployUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshDeployPath();
        _SCHEDULE_EXECUTOR.scheduleAtFixedRate(new RefreshThread(), 0, 3, TimeUnit.SECONDS);
    }

    /**
     * 获取热部署路径
     *
     * @return
     */
    public static String getHotDeployPath() {
        return getDefaultPath();
    }

    /**
     * 获取备份的后缀字符
     *
     * @return
     */
    public static String getBackupChar() {
        return _CONFIG_CONTENT.getProperty(_PATH_VERSION_BACKUP);
    }

    /**
     * 设定是否重载标记
     *
     * @param bool
     */
    public static synchronized void setLoaderFlag(boolean bool) {
        _IS_RELOAD.set(bool);
    }

    /**
     * 判断线程是否重载入
     *
     * @return
     */
    public static boolean isReload() {

        return _IS_RELOAD.get();
    }

    /**
     * 获取默认的部署路径，为当前文件部署的父路径
     *
     * @return
     */
    protected static String getDefaultPath() {
        return _CONFIG_CONTENT.getProperty(_PATH_KEY);
    }

    /**
     * 备份部署的文件
     */
    protected static void bakup() {
    }

    /**
     * 刷新部署路径
     */
    protected static void refreshDeployPath() {
        String folder = getDefaultPath();
        File files = new File(folder);
        if (files.exists()) {
            getClassFile(files);
        }
    }

    /**
     * 获取部署文件清单
     *
     * @param file
     */
    private static void getClassFile(File file) {
        if (file.isDirectory()) {
            // 过滤文件只获取非备份的文件列表
            File[] files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return !name.contains(_CONFIG_CONTENT.getProperty(_PATH_VERSION_BACKUP));
                }
            });
            for (File f : files) {
                // 处理非隐藏且文件类型为.class的文件
                if (f != null && f.isFile() && !f.isHidden()) {
                    // 类文件名去掉.class后缀及部署的路径
                    String fileName = f.getPath().replace(_CONFIG_CONTENT.getProperty(_PATH_KEY), "");
                    fileName = fileName.endsWith(CLASS_TYPE_NAME) ? fileName.replace(CLASS_TYPE_NAME, "").replaceAll("/", ".") : fileName;

                    // 当部署的文件清单中不包含对应的文件或包含文件但修改时间不一致
                    if (!_DEPLOY_RESOURCES.containsKey(fileName) || (_DEPLOY_RESOURCES.containsKey(fileName) && _DEPLOY_RESOURCES.get(fileName).lastModitiedTimes != f.lastModified())) {
                        getRecourseContent(f);
                    }
                }
                if (f != null && f.isDirectory()) {
                    getClassFile(f);
                }
            }

        }


    }

    /**
     * 获取类字节
     *
     * @return
     */
    private static void getRecourseContent(File resourseFile) {

        if (resourseFile != null && resourseFile.exists()) {
            InputStream input = null;
            ByteArrayOutputStream output = null;
            try {
                output = new ByteArrayOutputStream();
                input = new FileInputStream(resourseFile);
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = input.read(buffer)) != -1) {
                    output.write(buffer, 0, length);
                }
                // 设定类对象内容
                ResourceEntry entry = new ResourceEntry();
                entry.content = output.toByteArray();
                entry.lastModitiedTimes = resourseFile.lastModified();
                String resourceName = resourseFile.getPath().replace(_CONFIG_CONTENT.getProperty(_PATH_KEY), "");
                // 如果为类文件即修正为类路径的表达方式其他资源不进行变更
                entry.resourceName = resourceName.endsWith(CLASS_TYPE_NAME) ? resourceName.replace(CLASS_TYPE_NAME, "").replaceAll("/", ".") : resourceName;
                // 设定当前文件是否应该被重新加载
                entry.isReload = true;
                
                setLoaderFlag(true);
                
                _DEPLOY_RESOURCES.put(entry.resourceName, entry);
            } catch (IOException ex) {
                Logger.getLogger(DynamicDeployUtil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DynamicDeployUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
                input = null;
                output = null;
            }

        }
    }

    /**
     * 判断当前资源是否需要热加载
     *
     * @param resourceName
     * @return
     */
    public static boolean isReloadResource(String resourceName) {
        return _DEPLOY_RESOURCES.containsKey(resourceName) && _DEPLOY_RESOURCES.get(resourceName).isReload;
    }

    /**
     * 获取当前资源的二进制字节流
     *
     * @param resourceName
     * @return
     */
    public static byte[] findResource(String resourceName) {
        return _DEPLOY_RESOURCES.containsKey(resourceName) ? _DEPLOY_RESOURCES.get(resourceName).content : null;
    }

    private static class RefreshThread extends Thread {

        @Override
        public void run() {
            DynamicDeployUtil.refreshDeployPath();
        }

        public RefreshThread() {
            super("Refresh hot deploy Thread");
            super.setDaemon(true);
        }
    }

    /**
     * 资源文件对象
     */
    private static class ResourceEntry {

        private byte[] content;
        private long lastModitiedTimes;
        private String resourceName;
        private boolean isReload;
    }
}
