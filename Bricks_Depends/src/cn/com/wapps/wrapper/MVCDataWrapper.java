/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.wrapper;

import cn.com.convert.BricksConverter;
import cn.com.refects.InstanceCreator;
import cn.com.refects.TypeContentRefecter;
import cn.com.refects.TypeInitialization;
import cn.com.utils.FormParameterUtil;
import cn.com.utils.StringUtil;
import cn.com.wrapper.DataWrapper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public class MVCDataWrapper implements DataWrapper {

    private static final String _BOUND_SYMBOL = ".";
    private static final String _SELECT_INPUT_ID = "selectInput";
    private Map parameters = null;
    private boolean checkedBound = false;
    private String partten = null;

    /**
     * 页面封装的内容
     * @param parameters
     */
    public MVCDataWrapper(Map parameters) {
        this.parameters = parameters;
    }

    /**
     *
     * @param parameters 页面封装的内容
     * @param checkedBound 设定是否为列表被选中才进行数据绑定
     */
    public MVCDataWrapper(Map parameters, boolean checkedBound) {
        this.parameters = parameters;
        this.checkedBound = checkedBound;
    }

    /**
     *
     * @param parameters 页面封装的内容
     * @param checkedBound 设定是否为列表被选中才进行数据绑定
     */
    public MVCDataWrapper(Map parameters, boolean checkedBound, String datePartten) {
        this.parameters = parameters;
        this.checkedBound = checkedBound;
        this.partten = datePartten;
    }

    @Override
    public <T> T execute(Class<T> clazz) {
        try {
            return (T) invoke(clazz, null, parameters, 0);
        } catch (Throwable ex) {
            Logger.getLogger(MVCDataWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 模拟返回的数据
     */
    private Object invoke(Class<?> clazz, String parentFieldName, Map<String, Object> parameters, int idx) throws Throwable {

        Object value = InstanceCreator.getInstance(clazz.getName());
        // 如果为空将不进行处理
        if (value == null) {
            return null;
        }

        List<Field> fields = TypeContentRefecter.getAllFields(clazz);
        // 封装数据
        for (Field field : fields) {
            String parameterKey = StringUtil.joinString(_BOUND_SYMBOL, parentFieldName, field.getName());
            // 返回通用类
            if (TypeInitialization.isCommonType(field.getType())) {
                TypeContentRefecter.setValue(value, field.getName(), new Class[]{field.getType()}, FormParameterUtil.getParam(parameterKey, field.getType(), parameters, idx, partten));
                continue;
            }

            // 处理数组类型
            if (field.getType().isArray()) {
                TypeContentRefecter.setValue(value, field.getName(), new Class[]{field.getType()}, FormParameterUtil.getParamArray(parameterKey, field.getType().getComponentType(), parameters, partten));
                continue;
            }

            // 处理列表类
            if ((field.getType().isInterface() && field.getType().equals(List.class))) {

                List<Object> contents = new ArrayList<Object>();
                Class<?> contentClass = TypeContentRefecter.getGenericClass(field.getGenericType());
                Object[] contentObjects = null;
                Object[] checkedObjects = null;
                String startChar = StringUtil.joinString(_BOUND_SYMBOL, field.getName(), "");
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    if (entry.getKey().startsWith(startChar)) {
                        contentObjects = (Object[]) entry.getValue();
                    }
                    if (entry.getKey().equals(_SELECT_INPUT_ID)) {
                        checkedObjects = (Object[]) entry.getValue();
                    }

                    if (contentObjects != null && checkedObjects != null) {
                        break;
                    }
                }
                // 判定是否获取checked的数据,针对表格提交数据
                if (checkedBound) {
                    if (checkedObjects != null && contentObjects != null) {

                        for (int idx1 = 0; idx1 < checkedObjects.length; idx1++) {
                            contents.add(invoke(contentClass, field.getName(), parameters, BricksConverter.getInstance().convertStringToInteger((String) checkedObjects[idx1])));
                        }
                    }

                } else if ((!checkedBound || checkedObjects == null) && contentObjects != null) {
                    for (int idx1 = 0; idx1 < contentObjects.length; idx1++) {
                        contents.add(invoke(contentClass, field.getName(), parameters, idx1));
                    }
                }

                TypeContentRefecter.setValue(value, field.getName(), new Class[]{field.getType()}, contents);
                continue;
            }

            // 处理一般类型
            if (!field.getType().isInterface()) {
                invoke(field.getType(), field.getName(), parameters, 0);
                continue;
            }
            // 验证可处理的返回类型
            boolean isMaybe = true;
            // 不处理映射类型
            if (field.getType().isInterface() && field.getType().equals(Map.class)) {
                isMaybe = false;
            }

            if (!isMaybe) {
                throw new RuntimeException("don't operat field type :" + field.getType().getName());
            }
        }

        return value;
    }
}
