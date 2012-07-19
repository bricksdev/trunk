/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service;

import cn.com.factory.OperatFactory;
import cn.com.manager.domains.Task;
import cn.com.manager.process.ProcessWrapper;
import cn.com.manager.tasks.InitiationTaskInfo;
import cn.com.manager.tasks.TasksController;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author xy
 */
public class TaskTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked before this test starts
    }

    @Test
    public void aTest() {
        InitiationTaskInfo.init();
//        Asn asn = OperatFactory.getServiceObject(ReceiptModules.class).queryAsn("123456");
            //初始化 收货
//            ProcessWrapper.InitProcess("receipt", asn);
            //得到任务
            Task task =TasksController.getTask("test data");
            
            //质检
//            ProcessWrapper.approveProcess(task.getTaskid(), asn);
            
//            System.out.println("=====:"+TasksController.getTask(task.getTaskid()).getStatus());
//            System.out.println(System.getProperty("file.encoding"));
            System.out.println("====================");
            Task sub1 = TasksController.getTask("sub1");
            Task sub2 = TasksController.getTask("sub2");
            
            ProcessWrapper.approveProcess(sub1.getTaskid(), null);
            ProcessWrapper.approveProcess(sub2.getTaskid(), null);
            
            System.out.println("=====:"+TasksController.getTask("sub1"));
    }

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
