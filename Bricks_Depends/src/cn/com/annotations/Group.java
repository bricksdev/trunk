/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 生成自定义标签，设定页面与beans的实际对应关系
 * </p>
 * @author jhq1022749
 */
@Target(ElementType.TYPE)
public @interface Group {

    final static String __DEFAULT_GROUP_NAME = "_default";

    class DEFAULT implements Group {

        private Form form = null;
        private String title = Title._DEFAULT_TITLE;
        private String template = null;
        private List<Element> elements = new ArrayList<Element>(1);
        private List<Grid> grids = new ArrayList<Grid>(1);

        public DEFAULT() {
        }

        public DEFAULT(Form form, List<Element> elements, List<Grid> grids, String title, String template) {
            this.elements = elements;
            this.form = form;
            this.grids = grids;
            this.template = template;
            this.title = title;
        }

        @Override
        public Form form() {
            return form;
        }

        @Override
        public String name() {
            return __DEFAULT_GROUP_NAME;
        }

        @Override
        public Element[] elements() {
            return Collections.unmodifiableCollection(elements).toArray(new Element[0]);
        }

        @Override
        public Grid[] grids() {
            return Collections.unmodifiableCollection(grids).toArray(new Grid[0]);
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Group.class;
        }

        @Override
        public Title title() {
            return new Title.DEFAULT(title);
        }

        @Override
        public Template template() {
            return new Template.DEFAULT(template);
        }
    }

    /**
     * 组ID
     * @return
     */
    String name();

    /**
     * 元素列表
     * @return
     */
    Element[] elements() default {};

    /**
     * 表格列表
     * @return
     */
    Grid[] grids() default {};

    /**
     * 模版
     * @return
     */
    Template template() default @Template;

    /**
     * 默认表单
     * @return
     */
    Form form() default @Form;

    /**
     * 标题
     * @return
     */
    Title title() default @Title;
}
