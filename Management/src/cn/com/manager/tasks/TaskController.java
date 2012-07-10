/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.tasks;

import cn.com.manager.domains.Task;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kete
 */
public class TaskController {

    private final static List<Task> _TASKS = new ArrayList<Task>(15);

    public static void registerTask(Task task) {
        _TASKS.add(task);
    }

    public static List<Task> userTask(String userId, String node) {

        // TODO 获取给定节点的任务列表
        List<Task> currentNodeTasks = new ArrayList<Task>(5);
        // TODO 过滤当前处理人的任务
        // TODO 将当前节点任务没有指定处理人的加入到可处理的列表

        return currentNodeTasks;
    }

    //TODO
    public static Task getTask(String taskid){
        return _TASKS.get(0);
    }
}
