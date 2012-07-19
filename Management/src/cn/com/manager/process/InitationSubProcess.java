/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.manager.domains.Task;
import java.util.List;

/**
 *创建子任务
 * @author xy
 */
public interface InitationSubProcess {
    
    List<Task> createSubTask(Task parentTask);
}
