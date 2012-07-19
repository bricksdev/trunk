/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.tasks;

import cn.com.factory.OperatFactory;
import cn.com.manager.domains.Node;
import cn.com.manager.domains.Task;
import cn.com.manager.domains.TaskNode;
import cn.com.manager.process.ProcessFactory;
import cn.com.manager.services.NodeService;
import cn.com.manager.services.TaskService;
import cn.com.manager.tasks.node.ProcessNode;
import cn.com.utils.DataProviderConfig.DataProviderModel;
import cn.com.utils.StringUtil;
import java.util.List;

/**
 *
 * @author kete
 */
public class InitiationTaskInfo {

    public static void init() {
        List<Node> nodes = OperatFactory.getServiceObject(NodeService.class, DataProviderModel.FILE).getNodes();
        for (Node node : nodes) {
            ProcessFactory.register(node);
        }

        List<Task> tasks = OperatFactory.getServiceObject(TaskService.class, DataProviderModel.FILE).getTasks();
        for (Task task : tasks) {

            TasksController.registTask(task);
        }
    }

    /**
     * 初始化业务节点
     *
     * @param nodeName
     *
     * @return
     */
    public static void initNextProcessNode(ProcessNode processNode) {
        if (processNode != null) {
            TaskNode currentNode = OperatFactory.getServiceObject(TaskService.class, DataProviderModel.FILE).getTaskNode(processNode.getNodeName());
            Node node = null;
            // 依次设定下一节点
            String nodeName = currentNode.getNextNode();
            if (!StringUtil.isEmpty(nodeName) ) {
                node = OperatFactory.getServiceObject(NodeService.class, DataProviderModel.FILE).getNode(nodeName);
                ProcessNode subNode = new ProcessNode();
                subNode.setAutoFlag(node.isAutoFlag());
                subNode.setNodeName(node.getNodeName());
                subNode.setNodeDesc(node.getNodeDesc());
                subNode.setNodeStatus(node.getNodeStatus());
                processNode.setNext(subNode);
            }
            nodeName = currentNode.getParentNode();
            // 依次设定父节点
            if (!StringUtil.isEmpty(nodeName)) {
                node = OperatFactory.getServiceObject(NodeService.class, DataProviderModel.FILE).getNode(nodeName);
                ProcessNode parentNode = new ProcessNode();
                parentNode.setAutoFlag(node.isAutoFlag());
                parentNode.setNodeName(node.getNodeName());
                parentNode.setNodeDesc(node.getNodeDesc());
                parentNode.setNodeStatus(node.getNodeStatus());
                processNode.setParent(parentNode);
            }
        }
    }
}
