/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.exceptions.AppException;
import cn.com.manager.domains.Task;
import cn.com.manager.tasks.TasksController;
import cn.com.manager.tasks.node.ProcessNode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 任务处理组件
 *
 * @author kete
 */
public class ProcessWrapper {
    /**
     * 初始化任务
     *
     * @param <T>
 * param nodeName 初始节点
     * @param data 数据
     *
     * @return
     */
    public static <T> Result InitProcess(String nodeName, T data) {
        ProcessData processData = new ProcessData(null, data);
        Result result = null;
        Throwable exception = null;
        String returnValue = null;
        try {
            //通过节点名得到该节点的处理类
            Process nodeProcess = ProcessFactory.getProcess(nodeName);
            if (!(nodeProcess instanceof InitiationProcess)) {
                throw new AppException("E1200000", nodeName);
            }
            // 创建任务
            Task task = ((InitiationProcess) nodeProcess).createTask();
            processData.setTask(task);
            //任务注册到缓存中
            TasksController.registTask(task);
            returnValue = nodeProcess.execute(processData);
        } catch (AppException ex) {
            exception = ex;
            Logger.getLogger(ProcessWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = new Result(returnValue, nodeName, (processData.getTask() != null) ? processData.getTask().getTaskid() : null, exception);
        return result;
    }

    /**
     * 任务向下流转
     *
     * @param <T>
 * param taskId 任务Id
     * @param data 数据
     *
     * @return
     */
    public static <T> List<Result> approveProcess(String taskId, T data) {
        Task task = TasksController.getTask(taskId);
        ProcessNode node = task.getProcessNode();
        List<Result> rs = new ArrayList<Result>(3);
        String returnedValue = null;
        Throwable exception = null;
        ProcessData datas = new ProcessData(task, data);
        String nodeName = null;
        do {
            try {
                if (node != null) {
                    nodeName = node.getNodeName();
                    //节点处理类
                    Process nodeProcess = ProcessFactory.getProcess(nodeName);
                    returnedValue = nodeProcess.execute(datas);
                }
                node = task.getProcessNode();
            } catch (AppException ex) {
                exception = ex;
                node = null;
            }
            rs.add(new Result(returnedValue, nodeName, taskId, exception));
            //自动流转节点系统自动完成
            if (node!=null &&!node.isAutoFlag()) {
                break;
            }
        } while (node != null);
        return rs;
    }
}
