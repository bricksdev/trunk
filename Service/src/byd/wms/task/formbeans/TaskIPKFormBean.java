/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byd.wms.task.formbeans;

import cn.com.annotations.Element;
import cn.com.annotations.Form;
import cn.com.annotations.Grid;
import cn.com.annotations.Group;
import cn.com.annotations.Groups;
import cn.com.annotations.Option;
import cn.com.annotations.Title;
import cn.com.annotations.enums.ElementEnum;
import cn.com.manager.domains.Task;
import java.util.List;

/**
 * 任务看板
 *
 * @author xy
 */
@Groups(groups = {
    @Group(name = "ajax", grids =
    @Grid(id = "tasks", contextClass = Task.class, isMultiple = true,
          columns = {
        @Element(id = "plant", label = "工厂", readonly = true),
        @Element(id = "taskid", label = "任务ID", readonly = true),
        @Element(id = "display", label = "任务描述", readonly = true),
        @Element(id = "node", label = "类型", readonly = true),
        @Element(id = "status", label = "任务状态", readonly = true),
        @Element(id = "operator", label = "操作人", readonly = true)
    }))
})
@Title(title = "WMS")
@Form(label = "任务列表", id = "TaskIPK")
public class TaskIPKFormBean {

    @Element(id = "userId", label = "用户ID",
             onblur = "AjaxExecute('queryByCondition',null,'userId','table_container_div',null,'XML')")
    private String userId = null;
    @Element(id = "nodeName", label = "任务类型", type = ElementEnum.SELECT, readonly = true, source =
    @Option(display = {"来料", "质检", "交接", "进仓"}, value = {"1", "2", "3", "4"}))
    private String nodeName = null;
    @Grid(id = "tasks", contextClass = Task.class, isMultiple = true,
          columns = {
        @Element(id = "plant", label = "工厂", readonly = true),
        @Element(id = "taskid", label = "任务ID", readonly = true),
        @Element(id = "display", label = "任务描述", readonly = true),
        @Element(id = "node", label = "类型", readonly = true),
        @Element(id = "status", label = "任务状态", readonly = true),
        @Element(id = "operator", label = "操作人", readonly = true)
    })
    private List<Task> tasks = null;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
