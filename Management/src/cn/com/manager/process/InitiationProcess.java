/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.manager.domains.Task;

/**
 *定义初始化流程
 * @author kete
 */
public interface InitiationProcess {

    /**
     * 创建任务
     * @param bean  创建任务所需要数据
     * @return
     */
      Task createTask();
}
