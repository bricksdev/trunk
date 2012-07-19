/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations.parser;

import cn.com.annotations.Action;
import cn.com.annotations.Element;
import cn.com.annotations.Form;
import cn.com.annotations.Formatter;
import cn.com.annotations.Grid;
import cn.com.annotations.Group;
import cn.com.annotations.Groups;
import cn.com.annotations.Template;
import cn.com.annotations.Title;
import cn.com.annotations.enums.FormatType;
import cn.com.convert.BricksConverter;
import cn.com.exceptions.AppException;
import cn.com.refects.InstanceCreator;
import cn.com.refects.TypeContentRefecter;
import cn.com.utils.StringUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kete
 */
public class AnnotationParser {

    private Class clazz = null;
    private Object bundleObj = null;
    private Form formAnnotation = null;
    private List<Group> groups = new ArrayList<Group>(15);
    private Map<String, Object> elementValue = new HashMap<String, Object>(5);
    private String groupName = null;
    private Group currentGroup = null;
    private Title title = null;
    private Template template = null;
    private final static ValueFormatter _FORMATTER = new ValueFormatter();
    private final static ElementValidator _VALIDATOR = new ElementValidator();
    private final static BricksConverter _CONVERT = BricksConverter.getInstance();
    private String[] currentComponentName = null;

    public AnnotationParser(Object bundleObj, String groupName) throws AppException {
        this.clazz = bundleObj.getClass();
        this.bundleObj = bundleObj;
        this.groupName = groupName;
        this.formAnnotation = (Form) this.clazz.getAnnotation(Form.class);

//        // 设定如果没有设定FORM将给定默认的FORM
//        if (formAnnotation == null) {
//            formAnnotation = new Form.NULL() {
//
//                @Override
//                public String action() {
//                    return clazz.getSimpleName() + __DEFAULT_SUFFIX;
//                }
//            };
//        }
        this.title = (Title) this.clazz.getAnnotation(Title.class);
        this.template = (Template) this.clazz.getAnnotation(Template.class);

        Groups groupsAnnotation = (Groups) this.clazz.getAnnotation(Groups.class);
        if (groupsAnnotation != null) {
            // 保存显示定义的组
            groups.addAll(Arrays.asList(groupsAnnotation.groups()));
        }
        // 保存默认组
        groups.add(getDefaultGroup());

        // 设定当前组
        currentGroup = this.getCurrentGroup();
    }

    /**
     * 获取默认组列表
     * @return
     */
    private Group getDefaultGroup() throws AppException {
        final List<Element> elements = new ArrayList<Element>(20);
        final List<Grid> grids = new ArrayList<Grid>(3);
        final List<Field> fields = TypeContentRefecter.getAllFields(this.clazz);
        Element element = null;
        Grid grid = null;
        Object value = null;
        for (Field field : fields) {
            element = field.getAnnotation(Element.class);
            grid = field.getAnnotation(Grid.class);
            if (element != null) {

                elements.add(element);
            }
            if (grid != null) {
                grids.add(grid);
            }
            value = this.getElementValue(field.getName());
            if (value != null) {

                // 保存元素内容
                elementValue.put(field.getName(), value);
            } else {
                // 列表数据初始化为1个表格长度
                if (field.getType().equals(List.class)) {
                    List values = new ArrayList(1);
                    values.add(InstanceCreator.getInstance(TypeContentRefecter.getGenericClass(field.getGenericType()).getName()));
                    elementValue.put(field.getName(), values);
                }
            }

        }
        final List<Action> actions = new ArrayList<Action>(5);
        final List<Method> methods = TypeContentRefecter.getAllMethods(this.clazz);
        Action action = null;
        // 获取操作标注
        for (Method m : methods) {
            action = m.getAnnotation(Action.class);
            if (action != null) {
                actions.add(action);
            }
        }

        // 默认组
        Group group = new Group.DEFAULT(formAnnotation, elements, grids, this.title != null ? title.title() : Title._DEFAULT_TITLE, this.template != null ? this.template.template() : Template._DEFAULT_TEMPLATE);

        return group;
    }

    /**
     * 设定元素的默认值
     */
    public void settingDefaultValue() throws AppException {
        final List<Field> fields = TypeContentRefecter.getAllFields(this.clazz);
        Element element = null;
        for (Field field : fields) {
            element = field.getAnnotation(Element.class);
            if (element != null && !StringUtil.isEmpty(element.defaultValue())) {
                _VALIDATOR.validate(element);
                // 保存元素内容
                elementValue.put(field.getName(), _FORMATTER.converObject(element.format(), element.defaultValue()));

            }
        }
    }

    /**
     * 获取当前绑定类名称
     * @return
     */
    public String getBundleClassName() {
        return this.clazz.getName();
    }

    /**
     * 获取当前属性的内容，转换为字符后的值
     * @param field
     * @return
     */
    private Object getElementValue(String fieldName) {

        return TypeContentRefecter.invokeValue(bundleObj, fieldName);
    }

    /**
     * 获取对象的值
     * @param obj
     * @param field 属性ID
     * @return
     */
    public String invokeValue(Object obj, String field) throws AppException {
        Object value = TypeContentRefecter.invokeValue(obj, field);

        if (value != null) {
            return String.valueOf(value);
        }

        return null;
    }

    /**
     * 获取对象的值
     * @param obj
     * @param element
     * @return
     */
    public String invokeValue(Object obj, Element element) throws AppException {
        this.validate(element);
        Object value = TypeContentRefecter.invokeValue(obj, element.id());

        if (value != null) {
            return _FORMATTER.converString(element.format(), value);
        }

        return null;
    }
    /**
     * 元素内容
     * @param element
     * @return
     */
    public String getValue(Element element) throws AppException {

        this.validate(element);

        return elementValue.containsKey(element.id()) ? _FORMATTER.converString(element.format(), elementValue.get(element.id())) : null;
    }

    /**
     * 元素内容
     * @param elementId
     * @param formatter
     * @return
     */
    public String getValue(String elementId, Formatter formatter) throws AppException {
        return elementValue.containsKey(elementId) ? _FORMATTER.converString(formatter, elementValue.get(elementId)) : null;
    }

    /**
     * 获取FORM模板标注
     * @return
     */
    public Form getForm() {
        return this.currentGroup.form();
    }

    /**
     * 获取当前
     * @return
     */
    public String getTitle() {

        return title == null ? "WEB" : title.title();
    }

    public List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }


    /**
     * 表格内容
     * @param gridId
     * @return
     */
    public List<?> getGridValue(String gridId) {
        return (List<?>) elementValue.get(gridId);
    }

    /**
     * 获取
     * @param groupName
     * @return
     */
    private Group getCurrentGroup() {
        // 如果组为空将返回默认最后组
        for (Group t : groups) {
            if (t.name().equals(groupName)) {

                return t;
            }
        }
        return groups.isEmpty() ? null : groups.get(groups.size() - 1);
    }

    /**
     * 获取
     * @param groupName
     * @return
     */
    public Group getGroup() {
        return this.currentGroup;
    }

    /**
     * 验证元素配置是否正确并抛出对应的异常
     * @param element
     * @throws AppException
     */
    public void validate(Element element) throws AppException {

        _VALIDATOR.validate(element);
    }

    /**
     * @return the currentComponentName
     */
    public String[] getCurrentComponents() {
        return currentComponentName;
    }

    /**
     * @param currentComponentName the currentComponentName to set
     */
    public void setCurrentComponents(String... currentComponentName) {
        this.currentComponentName = currentComponentName;
    }

    /**
     * 内容格式化处理类
     */
    private static class ValueFormatter {

        /**
         * 转化为字符
         * @param formatter
         * @param value
         * @return
         * @throws AppException
         */
        public String converString(Formatter formatter, Object value) throws AppException {

            if (value == null) {
                return null;
            }
            String rValue = null;

            if (formatter != null) {
                switch (formatter.type()) {
                    case DATE:
                        rValue = _CONVERT.convertDateToString((Date) value, formatter.patten());
                        break;
                    case TIME:
                        rValue = _CONVERT.convertDateToString((Date) value, formatter.patten());
                        break;
                    case LOWER:
                        rValue = String.valueOf(value).toLowerCase();
                        break;
                    case UPPER:
                        rValue = String.valueOf(value).toUpperCase();
                        break;
                    case NORMAL:
                        rValue = String.valueOf(value);
                        break;
                    default:
                        rValue = String.valueOf(value);
                        break;
                }

                return rValue;

            }
            return String.valueOf(value);
        }

        /**
         * 转化为对象
         * @param formatter
         * @param value
         * @return
         */
        public Object converObject(Formatter formatter, String value) throws AppException {
            try {
                Object rValue = null;
                switch (formatter.type()) {
                    case DATE:
                        rValue = _CONVERT.convertStringToDate(value, formatter.patten());
                        break;
                    case TIME:
                        rValue = _CONVERT.convertStringToDate(value, formatter.patten());
                        break;
                    case LOWER:
                        rValue = String.valueOf(value).toLowerCase();
                        break;
                    case UPPER:
                        rValue = String.valueOf(value).toUpperCase();
                        break;
                    case NORMAL:
                        rValue = String.valueOf(value);
                        break;
                    default:
                        rValue = String.valueOf(value);
                        break;
                }

                return rValue;
            } catch (ParseException ex) {
                throw new AppException("E00005", ex);
            }
        }
    }

    /**
     * 元素验证处理
     */
    private static class ElementValidator {

        public void validate(Element element) throws AppException {

            if (element != null) {
                switch (element.type()) {
                    case DATE:
                        if (element.format().type() != FormatType.DATE) {
                            throw new AppException("E00001");
                        }

                        break;
                    case DATE_TIME:
                        if (element.format().type() != FormatType.TIME) {
                            throw new AppException("E00001");
                        }
                        break;
                    case LINK:
                        if (element.link().url() == null) {
                            throw new AppException("E00004");
                        }
                        break;
                    case SELECT:
                        if (element.source() == null) {
                            throw new AppException("E00003");
                        }
                        break;
                    default:
                        break;


                }
            }
        }
    }
}
