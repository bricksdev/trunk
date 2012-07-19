/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service.process.impl;

import cn.com.manager.domains.DataDetails;
import cn.com.manager.process.AbstractProcess;
import java.util.List;

/**
 * 进仓 入库
 *
 * @author xy
 */
public class StorageProcess extends AbstractProcess {

    @Override
    public boolean backProcess(List datas) {
        return true;
    }

    @Override
    public <T> String process(T data) {
        System.out.println("正在进仓...");
        return null;
    }

    @Override
    public List<DataDetails> getTaskDetails(String taskid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
