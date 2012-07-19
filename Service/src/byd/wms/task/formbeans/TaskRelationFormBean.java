/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byd.wms.task.formbeans;

/**
 *
 * @author kete
 */
public class TaskRelationFormBean {

    /**
     * 任务ID
     */
    private String taskid;
    /**
     * 关联内容
     */
    private String content;
    /**
     * 关联内容状态
     */
    private String status;
    /**
     * 关联类型，决定当前的数据内容从何处进行获取，及如何进行关联
     */
    private String relationType;

    public TaskRelationFormBean() {
    }

    public TaskRelationFormBean(String taskid, String content, String status, String relationType) {
        this.taskid = taskid;
        this.content = content;
        this.status = status;
        this.relationType = relationType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskRelationFormBean other = (TaskRelationFormBean) obj;
        if ((this.taskid == null) ? (other.taskid != null) : !this.taskid.equals(other.taskid)) {
            return false;
        }
        if ((this.content == null) ? (other.content != null) : !this.content.equals(other.content)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if ((this.relationType == null) ? (other.relationType != null) : !this.relationType.equals(other.relationType)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.taskid != null ? this.taskid.hashCode() : 0);
        hash = 47 * hash + (this.content != null ? this.content.hashCode() : 0);
        hash = 47 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 47 * hash + (this.relationType != null ? this.relationType.hashCode() : 0);
        return hash;
    }
}
