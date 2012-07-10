/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service.process;

/**
 *
 * @author kete
 */
public interface Process {

   <T> T execute(String taskid, T beans);

   <T> T back(String taskid);

}
