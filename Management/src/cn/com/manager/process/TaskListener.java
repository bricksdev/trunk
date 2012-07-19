/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.manager.domains.Task;

/**
 *
 * @author xy
 */
public interface TaskListener {

    void listen(Task parentTask);
}
