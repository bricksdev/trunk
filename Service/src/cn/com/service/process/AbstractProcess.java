/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service.process;

import cn.com.manager.tasks.TaskStateManager;
import java.util.List;

/**
 *
 * @author kete
 */
public abstract class AbstractProcess implements Process {

    private String status = null;

    public AbstractProcess() {
    }

    @Override
    public <T> T execute(String taskid, T beans) {
        T result = process(beans);
        TaskStateManager.change(taskid, status);

        return result;
    }

    @Override
    public <T> T back(String taskid) {
        backOperat(findTaskContent(taskid));

        TaskStateManager.change(taskid, status);

        return null;
    }

    /**
     * 获取任务记录中保存的操作数据内容
     * @param taskid
     * @return
     */
    public List<?> findTaskContent(String taskid) {

        return null;
    }

    public abstract void backOperat(List<?> datas);

    public abstract <T> T process(T beans);

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
