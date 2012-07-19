/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.listener;

import cn.com.global.Model;
import cn.com.global.MotionModel;
import cn.com.utils.StringUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * @author kete
 */
public class ModelServletListener implements ServletContextListener {
    private static final String _CONFIG_MODEL_KEY = "model";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String model = (String) sce.getServletContext().getInitParameter(_CONFIG_MODEL_KEY);
        if (!StringUtil.isEmpty(model)) {
            // 设定运行模式
            MotionModel.setModel(Model.valueOf(model.toUpperCase()));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 设定默认的运行模式
        MotionModel.setModel(Model.TEST);
    }
}
