/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.manager.domains.Task;

/**
 *
 * @author xy
 */
public class ProcessData<T> {

    private Task task = null;
    private T data = null;

    public ProcessData(Task task, T data) {
        this.task = task;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }

}
