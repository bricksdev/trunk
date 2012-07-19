/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

/**
 *
 * @author xy
 */
public class Result {

    private String nodeName = null;
    private String taskid = null;
    private String message;
    private Throwable exception;
    private boolean successed = true;

    public Result(String returnedValue, String nodeName,String taskid, Throwable exception) {
        this.message = returnedValue;
        this.nodeName = nodeName;
        this.exception = exception;
        this.taskid = taskid;
        if (exception != null) {
            this.successed = false;
        }
    }

    /**
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName the nodeName to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the exception
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * @param exception the exception to set
     */
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    /**
     * @return the successed
     */
    public boolean isSuccessed() {
        return successed;
    }

    /**
     * @param successed the successed to set
     */
    public void setSuccessed(boolean successed) {
        this.successed = successed;
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
}
