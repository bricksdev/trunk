/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.exceptions.AppException;
import cn.com.manager.domains.DataDetails;
import cn.com.manager.domains.Task;
import java.util.List;

/**
 * 定义流程节点处理类
 *
 * @author kete
 */
public interface Process {

    /**
     * 执行
     * @param datas  执行所需要的数据
     * @return
     */
     String execute(ProcessData datas) throws AppException;

    /**
     * 回退
     * @param taskId  流程Id
     * @return
     */
    boolean back(Task task) throws AppException;

    /**
     * 获取当前业务节点的具体数据
     * @param taskid
     * @return
     */
    List<DataDetails> getTaskDetails(String taskid) throws AppException;
}
