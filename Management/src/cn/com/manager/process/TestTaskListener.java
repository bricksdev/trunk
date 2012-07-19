/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.manager.domains.Task;
import java.util.List;

/**
 *
 * @author xy
 */
public class TestTaskListener implements TaskListener {

    private List<Task> subTasks = null;

    public TestTaskListener() {
    }

    public TestTaskListener(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public void listen(Task parentTask) {
        //当所有子任务完成  更新父任务状态
        boolean flag = true;
        for (Task task : subTasks) {
            if (!"finished".equalsIgnoreCase(task.getStatus())) {
                flag = false;
                break;
            }
        }
        if(flag){
            parentTask.getStatemanager().change();
        }
    }
    
    
}
