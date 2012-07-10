/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations.parser;

import cn.com.annotations.enums.FormMethodModel;
import cn.com.annotations.enums.FormTemplateType;
import cn.com.annotations.Action;
import cn.com.annotations.Element;
import cn.com.annotations.Form;
import cn.com.annotations.Grid;
import cn.com.annotations.Group;
import cn.com.annotations.Groups;
import cn.com.convert.LocalConvert;
import cn.com.refects.InstanceCreator;
import cn.com.refects.TypeContentRefecter;
import cn.com.utils.StringUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public AnnotationParser(Object bundleObj, String groupName) {
        this.clazz = bundleObj.getClass();
        this.bundleObj = bundleObj;
        this.groupName = groupName;
        formAnnotation = (Form) this.clazz.getAnnotation(Form.class);

        // 设定如果没有设定FORM将给定默认的FORM
        if (formAnnotation == null) {
            formAnnotation = new Form() {

                @Override
                public String id() {
                    return Form.__DEFAULT_FORM_NAME;
                }

                @Override
                public String cssClass() {
                    return Form.__DEFAULT_CSS;
                }

                @Override
                public String action() {
                    return clazz.getSimpleName() + Form.__DEFAULT_SUFFIX;
                }

                @Override
                public FormTemplateType template() {
                    return FormTemplateType.DEFAULT;
                }

                @Override
                public String enctype() {
                    return Form.__DEFAULT_ENCTYPE;
                }

                @Override
                public FormMethodModel method() {
                    return FormMethodModel.POST;
                }

                @Override
                public String pageTitle() {
                    return Form.__DEFAULT_TITLE;
                }

                @Override
                public Class<? extends Annotation> annotationType() {
                    return Form.class;
                }

                @Override
                public String label() {
                    return __DEFAULT_ACTION;
                }
            };
        }

        Groups groupsAnnotation = (Groups) this.clazz.getAnnotation(Groups.class);
        // 保存显示定义的组
        groups.addAll(Arrays.asList(groupsAnnotation.groups()));
        // 保存默认组
        groups.add(getDefaultGroup());

        // 设定当前组
        currentGroup = this.getCurrentGroup();
    }

    /**
     * 获取默认组列表
     * @return
     */
    private Group getDefaultGroup() {
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
                // 列表数据初始化
                if (field.getType().equals(List.class)) {
                    List values = new ArrayList(1);
                    try {
                        values.add(InstanceCreator.getInstance(TypeContentRefecter.getGenericClass(field.getGenericType()).getName()));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AnnotationParser.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        Group group = new Group() {

            @Override
            public String name() {
                return Group.__DEFAULT_GROUP_NAME;
            }

            @Override
            public Element[] elements() {
                return elements.toArray(new Element[0]);
            }

            @Override
            public Grid[] grids() {
                return grids.toArray(new Grid[0]);
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Group.class;
            }

            @Override
            public Action[] actions() {
                return actions.toArray(new Action[0]);
            }

            @Override
            public Form form() {
                return formAnnotation;
            }
        };

        return group;
    }

    /**
     * 设定元素的默认值
     */
    public void settingDefaultValue() {
        final List<Field> fields = TypeContentRefecter.getAllFields(this.clazz);
        Object value = null;
        Element element = null;
        for (Field field : fields) {
            element = field.getAnnotation(Element.class);
            if (element != null && !StringUtil.isEmpty(element.defaultValue())) {
                value = element.defaultValue();
                if (field.getType().equals(Date.class)) {
                    value = LocalConvert.getInstance().convertStringToDate((String) value, element.formate());
                }
                // 保存元素内容
                elementValue.put(field.getName(), value);

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
     * @param fieldName
     * @return
     */
    public String invokeValue(Object obj, String fieldName) {
        Object value = TypeContentRefecter.invokeValue(obj, fieldName);

        if (value != null) {
            return String.valueOf(value);
        }

        return null;
    }

    /**
     * 获取FORM模板标注
     * @return
     */
    public Form getForm() {
        return this.currentGroup.form();
    }

    public List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    /**
     * 元素内容
     * @param element
     * @return
     */
    public String getValue(String elementId, String... partten) {
        return elementValue.containsKey(elementId) ? elementValue.get(elementId).getClass().equals(Date.class) ? LocalConvert.getInstance().convertDateToString((Date) elementValue.get(elementId), (partten == null || partten.length == 0) ? null : partten[0]) : String.valueOf(elementValue.get(elementId)) : null;
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

                // 保存默认FORM
                if (t.form() == null) {
                    // TODO
                }

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
}
