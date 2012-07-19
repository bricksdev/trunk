/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.tasks;

import cn.com.manager.domains.Task;
import cn.com.manager.tasks.node.ProcessNode;

/**
 *任务状态管理类
 * @author kete
 */
public class TaskStateManager {

    public final static String _STATUS_END = "finished";
    private Task task = null;

    public TaskStateManager(Task task) {
        this.task = task;
    }

    /**
     * 任务节点的流转
     * @param state
     */
    public void change() {
        // 保存Task操作记录
        ProcessNode current = task.getProcessNode();
        if (current.getNext() == null) {
            task.setStatus(_STATUS_END);
            // 应该清空缓存中的任务
            TasksController.removeTask(task);
            task.setProcessNode(null);
            return;
        }
        task.setDisplay(current.getNext().getNodeDesc());
        task.setStatus(current.getNext().getNodeStatus());
        task.setProcessNode(current.getNext());
        task.setNode(current.getNext().getNodeName());
        InitiationTaskInfo.initNextProcessNode(current.getNext());
    }
    
    /**
     * 更新任务的状态 但任务节点并未流转
     * @param stateName 
     */
    public void changeTaskStateName(String stateName){
        this.task.setStatus(stateName);
    }

    /**
     * 给任务打标记
     * @param flag 
     */
    public void setTaskFlag(String flag) {
        task.setOperatFlag(flag);
    }
    
    
}
