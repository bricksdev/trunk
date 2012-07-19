/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.services;

import cn.com.manager.domains.Node;
import java.util.List;

/**
 *
 * @author kete
 */
public interface NodeService {

    /**
     * 获取所有的节点
     * @return
     */
    List<Node> getNodes();

    Node getNode(String node);
}
