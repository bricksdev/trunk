/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.implement;

import cn.com.exceptions.AppException;
import cn.com.manager.CurrentUser;
import cn.com.manager.domains.DataDetails;
import cn.com.elements.AbstractHtmlElementTag;
import cn.com.elements.TagWriter;
import cn.com.elements.containstag.ul.LIElementTag;
import cn.com.elements.containstag.ul.ULElementTag;
import cn.com.elements.singletag.link.LinkElementTag;
import cn.com.template.TasksTemplate;
import cn.com.utils.StringUtil;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import cn.com.manager.domains.Task;
import cn.com.manager.process.ProcessFactory;
import cn.com.manager.tasks.TasksController;
import cn.com.national.BricksNationality;
import java.util.ArrayList;

/**
 *
 * @author kete
 */
public class DefaultTasksTemplate implements TasksTemplate {

    private static final String _BLANK_CSS = "";
    private static final String _CONTENT_HINT_FONT = "(";
    private static final String _CONTENT_HINT_FOOT = ")";
    private static final String _CURRENT_PAGE_HREF = "#";
    private static final String _MORE_THAN_10_TASK_HINIT = BricksNationality.getValue("MoreThan10Task");
    private static final String _MY_TASKS = BricksNationality.getValue("MyTasks");
    private static final String _UL_HIDE_CSS = "hide";
    private static final String _PARAMETER_EQUAL_CHAR = "=";
    private static final String _PARAMETER_SIGN_CHAR = "?";

    @Override
    public String content() throws AppException {

        List<Task> tasks = TasksController.userTask(CurrentUser.getUserID(), CurrentUser.getEffectNodes());

        AbstractHtmlElementTag ulElement = null;
        TagWriter writer = null;
        try {
            ulElement = this.mainElement(createTaskTag(tasks));

            writer = new TagWriter(new CharArrayWriter());

            ulElement.doTag(writer);
            return writer.getWritedString();
        } catch (JspException ex) {
            Logger.getLogger(DefaultTasksTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DefaultTasksTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AppException ex) {
            Logger.getLogger(DefaultTasksTemplate.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return null;
    }

    /**
     * 设定下列表
     * @param tasks
     * @return
     */
    private AbstractHtmlElementTag mainElement(AbstractHtmlElementTag childElement) {
        ULElementTag currentElement = new ULElementTag();
        LIElementTag liElement = null;
        LinkElementTag linkElement = null;
        String cssClass = _UL_HIDE_CSS;
        liElement = new LIElementTag();
        linkElement = new LinkElementTag();
        linkElement.setHref(_CURRENT_PAGE_HREF);

        linkElement.setValue(_MY_TASKS);
        linkElement.setCssClass(cssClass);
        liElement.addChildrenTag(linkElement);
        liElement.addChildrenTag(childElement);
        currentElement.addChildrenTag(liElement);
        return currentElement;
    }

    /**
     * 生成任务菜单
     * @param tasks
     * @param parentElement
     */
    private AbstractHtmlElementTag createTaskTag(List<Task> tasks) throws AppException {
        if (tasks != null) {
            ULElementTag ulElement = new ULElementTag();
            LIElementTag liElement = null;
            LinkElementTag linkElement = null;
            String cssClass = _BLANK_CSS;
            AbstractHtmlElementTag childElement = null;
            int nodeCount = 0;
            Task lastNode = null;
            List<Task> nodeTasks = new ArrayList<Task>(3);
            for (Task m : tasks) {
                if (lastNode == null) {
                    lastNode = m;
                }

                if (!lastNode.getNode().equals(m.getNode())) {

                    liElement = new LIElementTag();
                    linkElement = new LinkElementTag();
                    linkElement.setHref(StringUtil.isEmpty(lastNode.getUrl()) ? _CURRENT_PAGE_HREF : lastNode.getUrl());

                    linkElement.setValue(lastNode.getDisplay() + _CONTENT_HINT_FONT + nodeCount + _CONTENT_HINT_FOOT);
                    linkElement.setCssClass(cssClass);
                    liElement.addChildrenTag(linkElement);
                    childElement = this.createTasksTag(nodeTasks);
                    if (childElement != null) {
                        liElement.addChildrenTag(childElement);
                    }

                    ulElement.addChildrenTag(liElement);
                    nodeCount = 0;
                    nodeTasks.clear();
                    lastNode = m;
                }
                nodeCount++;
                nodeTasks.add(m);

            }
            if (lastNode != null) {

                liElement = new LIElementTag();
                linkElement = new LinkElementTag();
                linkElement.setHref(StringUtil.isEmpty(lastNode.getUrl()) ? _CURRENT_PAGE_HREF : lastNode.getUrl());

                linkElement.setValue(lastNode.getDisplay() + _CONTENT_HINT_FONT + nodeCount + _CONTENT_HINT_FOOT);
                linkElement.setCssClass(cssClass);
                liElement.addChildrenTag(linkElement);
                childElement = this.createTasksTag(nodeTasks);
                if (childElement != null) {
                    liElement.addChildrenTag(childElement);
                }

                ulElement.addChildrenTag(liElement);
            }
            return ulElement;

        }

        return null;
    }

    /**
     * 生成任务菜单列表
     * @param tasks
     * @param parentElement
     */
    private AbstractHtmlElementTag createTasksTag(List<Task> tasks) throws AppException {
        if (tasks != null) {
            ULElementTag ulElement = new ULElementTag();
            LIElementTag liElement = null;
            LinkElementTag linkElement = null;
            String cssClass = _BLANK_CSS;
            AbstractHtmlElementTag childElement = null;
            int idxCount = 0;
            for (Task m : tasks) {
                liElement = new LIElementTag();
                m.setDetails(ProcessFactory.getProcess(m.getNode()).getTaskDetails(m.getTaskid()));
                linkElement = new LinkElementTag();
                linkElement.setHref(StringUtil.isEmpty(m.getUrl()) ? _CURRENT_PAGE_HREF : m.getUrl());

                linkElement.setValue(m.getTaskid() + _CONTENT_HINT_FONT + m.getDetails().size() + _CONTENT_HINT_FOOT);
                linkElement.setCssClass(cssClass);
                liElement.addChildrenTag(linkElement);
                childElement = this.createTaskDetailsTag(m.getDetails());
                if (childElement != null) {
                    liElement.addChildrenTag(childElement);
                }
                if (idxCount++ == 10) {
                    liElement = new LIElementTag();
                    linkElement = new LinkElementTag();
                    linkElement.setHref(_CURRENT_PAGE_HREF);
                    linkElement.setValue(_MORE_THAN_10_TASK_HINIT);
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

    private AbstractHtmlElementTag createTaskDetailsTag(List<DataDetails> details) {
        if (details != null) {
            ULElementTag ulElement = new ULElementTag();
            LIElementTag liElement = null;
            LinkElementTag linkElement = null;
            String cssClass = _BLANK_CSS;
            int idxCount = 0;
            for (DataDetails m : details) {
                liElement = new LIElementTag();
                linkElement = new LinkElementTag();
                linkElement.setHref(StringUtil.isEmpty(m.getUrl()) ? _CURRENT_PAGE_HREF : getLinkHref(m.getUrl(), m.getParameter(), m.getContent()));

                linkElement.setValue(m.getContent());
                linkElement.setCssClass(cssClass);
                liElement.addChildrenTag(linkElement);
                if (idxCount++ == 10) {
                    liElement = new LIElementTag();
                    linkElement = new LinkElementTag();
                    linkElement.setHref(m.getUrl());
                    linkElement.setValue(_MORE_THAN_10_TASK_HINIT);
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

            if (builder.indexOf(_PARAMETER_SIGN_CHAR) == -1) {
                builder.append(_PARAMETER_SIGN_CHAR);
            }

            builder.append(parameter).append(_PARAMETER_EQUAL_CHAR).append(content);
        }
        return builder.toString();
    }
}
