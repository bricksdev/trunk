/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import byd.mvc.context.ActionContext;
import byd.wms.task.BussinessRelationServeice;
import byd.wms.task.formbeans.TaskIPKFormBean;
import cn.com.annotations.parser.AnnotationParser;
import cn.com.exceptions.AppException;
import cn.com.factory.OperatFactory;
import cn.com.manager.domains.Task;
import cn.com.template.ContentTemplate;
import cn.com.utils.FormParameterUtil;
import cn.com.wapps.style.ApplicationStyle;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xy
 */
public class Index {

    public void onLoad(Map request, Map session) {
        String userId = null;

        List<Task> tasks = OperatFactory.getServiceObject(BussinessRelationServeice.class).getTasksByUserId(userId);
        TaskIPKFormBean taskIPK = new TaskIPKFormBean();
        taskIPK.setTasks(tasks);
        request.put("tasks", taskIPK);
    }

    public void queryByCondition(Map request, Map session) {
        try {
            String userId = FormParameterUtil.getString("userId", request);
            List<Task> tasks = OperatFactory.getServiceObject(BussinessRelationServeice.class).getTasksByUserId(userId);
            TaskIPKFormBean content = new TaskIPKFormBean();
            content.setTasks(tasks);
            AnnotationParser parser = new AnnotationParser(content, "ajax");
            parser.setCurrentComponents("table");
            ContentTemplate contentTemplate = (ContentTemplate) ApplicationStyle.getCurrentStyle().getTemplate(ContentTemplate.class);
            contentTemplate.setParser(parser);
            contentTemplate.setPermission(null);
            String content1 = contentTemplate.content();
            ActionContext.getResponse().getWriter().print(content1);
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AppException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ActionContext.getResponse().getWriter().close();
            } catch (IOException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
