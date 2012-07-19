/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.tasks;

import cn.com.cache.BricksCacheManager;
import cn.com.factory.OperatFactory;
import cn.com.manager.domains.Node;
import cn.com.manager.domains.Task;
import cn.com.manager.domains.TaskNode;
import cn.com.manager.services.NodeService;
import cn.com.manager.services.TaskService;
import cn.com.manager.tasks.node.ProcessNode;
import cn.com.utils.DataProviderConfig.DataProviderModel;
import cn.com.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *任务管理类
 * @author kete
 */
public class TasksController {

    private static final String _CACHE_NAME = "TASK_CACHE";

    static {
        InitiationTaskInfo.init();
    }

    /**
     * 注册任务
     * @param task
     */
    public static void registTask(Task task) {
        TaskNode currentNode = OperatFactory.getServiceObject(TaskService.class, DataProviderModel.FILE).getTaskNode(task.getNode());
        String nodeName = currentNode.getCurrentNode();
        ProcessNode processNode = new ProcessNode();
        Node n = OperatFactory.getServiceObject(NodeService.class, DataProviderModel.FILE).getNode(nodeName);
        processNode.setAutoFlag(n.isAutoFlag());
        processNode.setNodeName(n.getNodeName());
        processNode.setNodeDesc(n.getNodeDesc());
        processNode.setNodeStatus(n.getNodeStatus());
        task.setProcessNode(processNode);
        task.setDisplay(processNode.getNodeDesc());
        task.setStatus(processNode.getNodeStatus());
        InitiationTaskInfo.initNextProcessNode(processNode);
        BricksCacheManager.INSTANCE.addCacheElement(_CACHE_NAME, task.getTaskid(), task);
    }

    /**
     * 删除任务
     * @param task 
     */
    public static void removeTask(Task task){
        BricksCacheManager.INSTANCE.removeCacheElement(_CACHE_NAME, task.getTaskid());
    }
    
    public static List<Task> userTask(String userId, List<String> nodes) {

        List<Task> operatTasks = new ArrayList<Task>(5);
        List<Task> nonDispatchTasks = new ArrayList<Task>(5);
        List<Task> currentNodeTasks = new ArrayList<Task>(5);
        //  获取给定节点的任务列表
        List<Task> tasks = BricksCacheManager.INSTANCE.getValues(_CACHE_NAME);
        for (Task task : tasks) {
            if (nodes.contains(task.getNode())) {
                // 过滤当前处理人的任务
                if (!StringUtil.isEmpty(userId) && userId.equals(task.getOperator())) {
                    operatTasks.add(task);
                } else if (!StringUtil.isEmpty(userId) && StringUtil.isEmpty(task.getOperator())) {
                    nonDispatchTasks.add(task);
                } else if (StringUtil.isEmpty(userId)) {
                    currentNodeTasks.add(task);
                }
            }
        }
        // 将当前节点任务没有指定处理人的加入到可处理的列表
        if (!StringUtil.isEmpty(userId)) {
            operatTasks.addAll(nonDispatchTasks);
        }

        return StringUtil.isEmpty(userId) ? currentNodeTasks : operatTasks;
    }

    /**
     * 获取指定任务ID的当前任务
     * @param taskid
     * @return
     */
    public static Task getTask(String taskid) {
        return (Task) BricksCacheManager.INSTANCE.getValue(_CACHE_NAME, taskid);
    }
}
