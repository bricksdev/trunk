package cn.com.byd.compose.beans;

import java.io.Serializable;

import java.util.Map;

public class ParameterType implements Serializable {

    /**
     * ID属性
     */
    private String id = null;
    /**
     * 类名称
     */
    private String typeName = null;
    /**
     * 类
     */
    private Class clazz = null;
    /**
     * 对应的值 ID
     */
    private String referenceValue = null;
    /**
     * 当前参数对象的 对应动态属性值 实际内容从methodContext中取值
     */
    private Map<String, String> beanFieldMap = null;
    /**
     * 保存当前对象中属性值的类型
     */
    private Map<String, Class> beanFieldTypeMap = null;

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setBeanFieldMap(Map<String, String> beanFieldMap) {
        this.beanFieldMap = beanFieldMap;
    }

    public Map<String, String> getBeanFieldMap() {
        return beanFieldMap;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setReferenceValue(String referenceValue) {
        this.referenceValue = referenceValue;
    }

    public String getReferenceValue() {
        return referenceValue;
    }

    /**
     * 保存当前对象中属性值的类型
     * @return the beanFieldTypeMap
     */
    public Map<String, Class> getBeanFieldTypeMap() {
        return beanFieldTypeMap;
    }

    /**
     * 保存当前对象中属性值的类型
     * @param beanFieldTypeMap the beanFieldTypeMap to set
     */
    public void setBeanFieldTypeMap(Map<String, Class> beanFieldTypeMap) {
        this.beanFieldTypeMap = beanFieldTypeMap;
    }
}
