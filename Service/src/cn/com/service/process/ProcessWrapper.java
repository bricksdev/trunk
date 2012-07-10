/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service.process;

import cn.com.factory.OperatFactory;

/**
 *
 * @author kete
 */
public class ProcessWrapper {

    public static <T> T process(String node, String task, T content) {
//        Class<?> clazz = node;
        // TODO 需要管理process的类进行集中处理及返回对应节点的处理类
        Process process = OperatFactory.getServiceObject(Process.class);
        String taskid = task;
        if (process instanceof InitiationTaskProcess) {
            // 初始任务
            taskid = ((InitiationTaskProcess) process).createTask(content);

        }
        return process.execute(taskid, content);
    }
}
