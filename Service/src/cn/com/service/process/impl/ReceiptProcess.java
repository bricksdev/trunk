/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service.process.impl;

import cn.com.manager.tasks.TaskController;
import cn.com.service.process.AbstractProcess;
import cn.com.service.process.InitiationTaskProcess;
import java.util.List;

/**
 *
 * @author kete
 */
public class ReceiptProcess extends AbstractProcess implements InitiationTaskProcess {

    @Override
    public <T> T process(T beans) {
        return null;
    }

    @Override
    public <T> String createTask(T taskcontent) {
        // TODO
        TaskController.registerTask(null);
        return null;
    }

    @Override
    public void backOperat(List<?> datas) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
