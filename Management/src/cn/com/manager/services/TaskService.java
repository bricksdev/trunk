/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.services;

import java.util.List;
import cn.com.manager.domains.Task;

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

}
