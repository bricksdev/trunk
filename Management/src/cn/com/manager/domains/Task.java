/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.domains;

import cn.com.manager.process.TaskListener;
import cn.com.manager.tasks.TaskStateManager;
import cn.com.manager.tasks.node.ProcessNode;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author kete
 */
public class Task {

    private final Lock lock = new ReentrantLock();

    private Task parentTask = null;

    private String display = null;

    private String url = null;

    private String taskid = null;

    private String node = null;

    private String status = null;

    private String operatFlag = null;

    private String operator = null;

    private String plant = null;

    private TaskStateManager statemanager = new TaskStateManager(this);

    private ProcessNode processNode = new ProcessNode();

    private List<DataDetails> details = null;

    private TaskListener listener = null;

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
     * @return the statemanager
     */
    public TaskStateManager getStatemanager() {
        return statemanager;
    }

    /**
     * @return the processNode
     */
    public ProcessNode getProcessNode() {
        return processNode;
    }

    /**
     * @param processNode the processNode to set
     */
    public void setProcessNode(ProcessNode processNode) {
        this.processNode = processNode;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the operatFlag
     */
    public String getOperatFlag() {
        return operatFlag;
    }

    /**
     * @param operatFlag the operatFlag to set
     */
    public void setOperatFlag(String operatFlag) {
        this.operatFlag = operatFlag;
    }

    /**
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return the plant
     */
    public String getPlant() {
        return plant;
    }

    /**
     * @param plant the plant to set
     */
    public void setPlant(String plant) {
        this.plant = plant;
    }

    /**
     * @return the display
     */
    public String getDisplay() {
        return display;
    }

    /**
     * @param display the display to set
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    /**
     * @return the taskType
     */
    public String getNode() {
        return node;
    }

    /**
     * @param taskType the taskType to set
     */
    public void setNode(String taskType) {
        this.node = taskType;
    }

    /**
     * @return the details
     */
    public List<DataDetails> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<DataDetails> details) {
        this.details = details;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public TaskListener getListener() {
        return listener;
    }

    public void setListener(TaskListener listener) {
        this.listener = listener;
    }

    public boolean tryLock(){
        return this.lock.tryLock();
    }
    
    public void lock(){
        this.lock.lock();
    }
    
    public void unLock(){
        this.lock.unlock();
    }
}
