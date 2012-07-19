/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.exceptions.AppException;
import cn.com.manager.domains.Task;
import cn.com.manager.tasks.TaskStateManager;
import cn.com.manager.tasks.TasksController;
import java.util.List;

/**
 * 流程节点处理模板操作类
 *
 * @author kete
 */
public abstract class AbstractProcess implements Process {

    @Override
    public String execute(ProcessData datas) throws AppException {
        Task task = datas.getTask();
        String result = null;
        boolean capured = false;
        try {
            capured = task.tryLock();
            if (!capured) {
                throw new AppException("E1200001",task.getTaskid());
            }
            //业务处理
            result = this.process(datas.getData());
            //生成子任务
            if (this instanceof InitationSubProcess) {
                List<Task> subTasks = ((InitationSubProcess) this).createSubTask(task);
                for (Task sub : subTasks) {
                    TasksController.registTask(sub);
                }
            }
            //任务流转
            task.getStatemanager().change();
            //获取父任务的监听器
            if (task.getParentTask() != null) {
                if (!TaskStateManager._STATUS_END.equalsIgnoreCase(task.getParentTask().getStatus())) {
                    TaskListener listener = task.getParentTask().getListener();
                    if (listener != null) {
                        listener.listen(task.getParentTask());
                    }
                } else {
                    task.setParentTask(null);
                }
            }
            return result;
        } finally {
            if (capured) {
                task.unLock();
            }
        }

    }

    @Override
    public boolean back(Task task) throws AppException {
        //业务处理
        boolean isSuccessed = backProcess(findTask(task.getTaskid()));
        return isSuccessed;
    }

    /**
     * 获取任务记录中保存的操作数据内容
     *
     * @return
     */
    public List findTask(String taskId) {
        return null;
    }

    /**
     * 业务处理
     *
     * @param datas
     *
     * @return
     */
    public abstract boolean backProcess(List datas);

    /**
     * 业务处理
     *
     * @param <T>
* param data
     *
     * @return
     */
    public abstract <T> String process(T data);
}
