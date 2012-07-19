/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.domains;

/**
 *
 * @author kete
 */
public class TaskNode {

    private String nextNode;
    private String parentNode;
    private String currentNode;

    /**
     * @return the nextNode
     */
    public String getNextNode() {
        return nextNode;
    }

    /**
     * @param nextNode the nextNode to set
     */
    public void setNextNode(String nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * @return the parentNode
     */
    public String getParentNode() {
        return parentNode;
    }

    /**
     * @param parentNode the parentNode to set
     */
    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * @return the currentNode
     */
    public String getCurrentNode() {
        return currentNode;
    }

    /**
     * @param currentNode the currentNode to set
     */
    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }
}
