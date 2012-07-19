/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byd.wms.task;

import byd.wms.task.formbeans.TaskRelationFormBean;
import cn.com.manager.domains.Task;
import java.util.List;

/**
 *
 * @author kete
 */
public interface BussinessRelationServeice {

    List<TaskRelationFormBean> getBussinessRelations(String taskid);

    List<Task> getTasksByUserId(String userId);
}
