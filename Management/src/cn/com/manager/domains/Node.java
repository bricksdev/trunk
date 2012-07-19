/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.domains;

/**
 *
 * @author kete
 */
public class Node {

    private String nodeName;
    private String nodeDesc;
    private boolean autoFlag;
    private String className;
    private String nodeStatus;

    /**
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName the nodeName to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the nodeStatus
     */
    public String getNodeStatus() {
        return nodeStatus;
    }

    /**
     * @param nodeStatus the nodeStatus to set
     */
    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }
}
