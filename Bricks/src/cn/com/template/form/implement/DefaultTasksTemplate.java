/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.form.implement;

import cn.com.factory.OperatFactory;
import cn.com.manager.services.TaskService;
import cn.com.tags.AbstractHtmlElementTag;
import cn.com.tags.TagWriter;
import cn.com.tags.form.containstag.LIElementTag;
import cn.com.tags.form.containstag.ULElementTag;
import cn.com.tags.form.singletag.LinkElementTag;
import cn.com.template.form.TasksTemplate;
import cn.com.utils.DataProviderConfig.DataProviderModel;
import cn.com.utils.StringUtil;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import cn.com.manager.domains.Task;

/**
 *
 * @author kete
 */
public class DefaultTasksTemplate implements TasksTemplate {

    private int count = 0;
    @Override
    public String content() {

        List<Task> tasks = OperatFactory.getServiceObject(TaskService.class, DataProviderModel.FILE).getTasks();

        AbstractHtmlElementTag ulElement = createTasks(recreateTasks(tasks), count);
        TagWriter writer = new TagWriter(new CharArrayWriter());
//        ContainHTMLElementTag taskDiv = new DivElementTag("taskBar", "task");
//        taskDiv.addChildrenTag(ulElement);
        try {
            ulElement.doTag(writer);
        } catch (JspException ex) {
            Logger.getLogger(DefaultMenuTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DefaultMenuTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return writer.getWritedString();
    }

    /**
     * 设定下列表
     * @param tasks
     * @return
     */
    private List<Task> recreateTasks(List<Task> tasks){
        List<Task> nTasks = new ArrayList<Task>(1);

        Task t = new Task();
        t.setTasks(tasks);
        t.setTypeDesc("My Tasks");
        nTasks.add(t);
        return nTasks;
    }
    /**
     * 生成菜单
     * @param tasks
     * @param parentElement
     */
    private AbstractHtmlElementTag createTasks(List<Task> tasks, int levelCount) {
        if (tasks != null) {
            ULElementTag ulElement = new ULElementTag();
            LIElementTag liElement = null;
            LinkElementTag linkElement = null;
            String cssClass = (levelCount == 0 ? "hide" : "");
            AbstractHtmlElementTag childElement = null;
            int idxCount = 0;
            for (Task m : tasks) {
                liElement = new LIElementTag(m.getTaskid());
                linkElement = new LinkElementTag();
                linkElement.setHref(StringUtil.isEmpty(m.getUrl()) ? "#" : getLinkHref(m.getUrl(), m.getParameter(), m.getContent()));

                linkElement.setValue(levelCount > 1 ? m.getContent() : m.getTypeDesc() + "(" + (m.getTasks() == null ? 0 : m.getTasks().size()) + ")");
                linkElement.setCssClass(cssClass);
                liElement.addChildrenTag(linkElement);
                childElement = this.createTasks(m.getTasks(), ++count);
                if (childElement != null) {
                    liElement.addChildrenTag(childElement);
                }
                if (idxCount++ == 10) {
                    liElement = new LIElementTag(m.getTaskid());
                    linkElement = new LinkElementTag();
                    linkElement.setHref("#");
                    linkElement.setValue("more than 10 task.");
                    linkElement.setCssClass(cssClass);
                    liElement.addChildrenTag(linkElement);
                    ulElement.addChildrenTag(liElement);
                    break;
                }
                    ulElement.addChildrenTag(liElement);

            }

            return ulElement;

        }

        return null;
    }

    private String getLinkHref(String url, String parameter, String content) {

        StringBuilder builder = new StringBuilder(url);
        if (!StringUtil.isEmpty(parameter)) {
            builder.append("?").append(parameter).append("=").append(content);
        }
        return builder.toString();
    }
}
