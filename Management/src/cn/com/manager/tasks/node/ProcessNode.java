/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.tasks.node;

/**
 *
 * @author kete
 */
public class ProcessNode {

    private ProcessNode parent = null;
    private ProcessNode next = null;
    private String nodeName = null;
    private String nodeDesc = null;
    private boolean autoFlag = false;
    private String nodeStatus = null;

    /**
     * @return the parent
     */
    public ProcessNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(ProcessNode parent) {
        this.parent = parent;
    }

    /**
     * @return the next
     */
    public ProcessNode getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(ProcessNode next) {
        this.next = next;
    }

    /**
     * @return the type
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodename the type to set
     */
    public void setNodeName(String nodename) {
        this.nodeName = nodename;
    }

    /**
     * @return the nodeDesc
     */
    public String getNodeDesc() {
        return nodeDesc;
    }

    /**
     * @param nodeDesc the nodeDesc to set
     */
    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    /**
     * @return the autoFlag
     */
    public boolean isAutoFlag() {
        return autoFlag;
    }

    /**
     * @param autoFlag the autoFlag to set
     */
    public void setAutoFlag(boolean autoFlag) {
        this.autoFlag = autoFlag;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

}
