/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.ContextLoader;

/**
 * Web application lifecycle listener.
 *
 * @author kete
 */
public class SpringInjectListener implements ServletContextListener {
    
    private ContextLoader springContextLoader = new ContextLoader();
    
    public SpringInjectListener() {
        
//        try {
////            ClassLoader currentLoader = LoaderUtil.getClassLoader();
//            Class contextLoaderClass = Class.forName("org.springframework.web.context.ContextLoader");
//
//            springContextLoader = contextLoaderClass.newInstance();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        try {
//
////            File resourcefile = (File)sce.getServletContext().getAttribute("javax.servlet.context.tempdir");
////            currentLoader.addResouse(resourcefile.getPath());
//
//            //initWebApplicationContext
//            Method method = springContextLoader.getClass().getDeclaredMethod("initWebApplicationContext", new Class[]{ServletContext.class});
//            method.invoke(springContextLoader, sce.getServletContext());
//
//        } catch (IllegalArgumentException ex) {
//            Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvocationTargetException ex) {
//            Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchMethodException ex) {
//            Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SecurityException ex) {
//            Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
        springContextLoader.initWebApplicationContext(sce.getServletContext());
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (springContextLoader != null) {
//            try {
//                Method method = springContextLoader.getClass().getDeclaredMethod("closeWebApplicationContext", new Class[]{ServletContext.class});
//                method.invoke(springContextLoader, sce.getServletContext());
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalArgumentException ex) {
//                Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (InvocationTargetException ex) {
//                Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (NoSuchMethodException ex) {
//                Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SecurityException ex) {
//                Logger.getLogger(SpringInjectListener.class.getName()).log(Level.SEVERE, null, ex);
//            }
            springContextLoader.closeWebApplicationContext(sce.getServletContext());
        }
    }
}
