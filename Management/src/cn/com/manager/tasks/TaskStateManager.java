/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.tasks;

/**
 *
 * @author kete
 */
public class TaskStateManager {

    public static void change(String taskid, String state) {
        // TODO 更新任务节点状态
        TaskController.getTask(taskid);
        // TODO 保存Task操作记录
    }
}
