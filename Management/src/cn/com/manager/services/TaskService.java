/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.services;

import java.util.List;
import cn.com.manager.domains.Task;
import cn.com.manager.domains.TaskNode;

/**
 *
 * @author kete
 */
public interface TaskService {

    /**
     * 获取任务列表
     * @return
     */
    List<Task> getTasks();

    /**
     * 获取任务节点
     * @param node
     * @return
     */
    TaskNode getTaskNode(String node);

    /**
     * 生成任务序列
     * @return
     */
    String generatingSequence();
}
