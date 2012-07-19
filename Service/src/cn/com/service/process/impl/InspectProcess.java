/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service.process.impl;

import cn.com.manager.domains.DataDetails;
import cn.com.manager.domains.Task;
import cn.com.manager.process.AbstractProcess;
import cn.com.manager.process.InitationSubProcess;
import cn.com.manager.process.TaskListener;
import cn.com.manager.process.TestTaskListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 质检
 *
 * @author xy
 */
public class InspectProcess extends AbstractProcess implements InitationSubProcess {

    @Override
    public boolean backProcess(List datas) {
        return true;
    }

    @Override
    public <T> String process(T data) {
        System.out.println("正在质检...");
        //修改并保存业务记录
        return null;
    }

    @Override
    public List<DataDetails> getTaskDetails(String taskid) {
        List<DataDetails> details = new ArrayList<DataDetails>(3);
        DataDetails detail = new DataDetails();
        detail.setContent("2012020001");
        detail.setParameter("batch");
        detail.setUrl("iqc.jsp");
        details.add(detail);
        return details;
    }

    @Override
    public List<Task> createSubTask(Task parentTask) {
        //根据规则 产生子任务
        List<Task> subTasks = new ArrayList<Task>();
        Task task1 = new Task();
        Task task2 = new Task();
        task1.setTaskid("sub1");
        task1.setNode("sub");
        task1.setParentTask(parentTask);
        task2.setTaskid("sub2");
        task2.setNode("sub");
        task2.setParentTask(parentTask);
        subTasks.add(task1);
        subTasks.add(task2);
        //添加对子任务的监听
        TaskListener testTaskListener = new TestTaskListener(subTasks);
        parentTask.setListener(testTaskListener);
        return subTasks;
    }
}
