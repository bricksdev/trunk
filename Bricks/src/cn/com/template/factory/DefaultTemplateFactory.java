/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.factory;

import cn.com.template.form.MenuTemplate;
import cn.com.template.form.TasksTemplate;
import cn.com.template.form.implement.DefaultMenuTemplate;
import cn.com.template.form.implement.DefaultTasksTemplate;

/**
 *
 * @author kete
 */
public class DefaultTemplateFactory {

//    public static FormTemplate getFormTemplate(){
//
//        return new DefaultFormTemplate();
//    }

    public static MenuTemplate getMenuTemplate(){
        return new DefaultMenuTemplate();
    }

    public static TasksTemplate getTasksTemplate(){

        return new DefaultTasksTemplate();
    }

}
