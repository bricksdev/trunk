/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service.process.impl;

import cn.com.manager.domains.DataDetails;
import cn.com.manager.process.AbstractProcess;
import java.util.List;

/**
 *
 * @author xy
 */
public class TestSubProcess extends AbstractProcess {

    @Override
    public boolean backProcess(List datas) {
        return true;
    }

    @Override
    public <T> String process(T data) {
        System.out.println("正在执行子任务...");
        return null;
    }

    @Override
    public List<DataDetails> getTaskDetails(String taskid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
