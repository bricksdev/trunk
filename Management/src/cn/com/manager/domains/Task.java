/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.domains;

import java.util.List;

/**
 *
 * @author kete
 */
public class Task {

    private String node = null;
    private String nodeDesc = null;
    private String content = null;
    private String url = null;
    private String taskid = null;
    private String parameter = null;

    private List<Task> tasks = null;

    /**
     * @return the type
     */
    public String getType() {
        return node;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.node = type;
    }

    /**
     * @return the typeDesc
     */
    public String getTypeDesc() {
        return nodeDesc;
    }

    /**
     * @param typeDesc the typeDesc to set
     */
    public void setTypeDesc(String typeDesc) {
        this.nodeDesc = typeDesc;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the taskid
     */
    public String getTaskid() {
        return taskid;
    }

    /**
     * @param taskid the taskid to set
     */
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    /**
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the parameter
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * @param parameter the parameter to set
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
