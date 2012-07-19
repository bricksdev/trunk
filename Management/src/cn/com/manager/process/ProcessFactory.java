/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.process;

import cn.com.cache.BricksCacheManager;
import cn.com.exceptions.AppException;
import cn.com.manager.domains.Node;
import cn.com.refects.InstanceCreator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xy
 */
public class ProcessFactory {

    private static final String _CACHE_NAME = "PROCESS_CACHE";

    /**
     * 初始化处理节点
     * @param node
     * @throws AppException
     */
    public static void register(Node node) {
        try {
            Process process = (Process) InstanceCreator.getInstance(node.getClassName());
            BricksCacheManager.INSTANCE.addCacheElement(_CACHE_NAME, node.getNodeName(), process);
        } catch (AppException ex) {
            Logger.getLogger(ProcessFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 获取处理节点
     * @param node
     * @return
     * @throws AppException
     */
    public static Process getProcess(String node) throws AppException {

        if (!BricksCacheManager.INSTANCE.containsKeyInCache(_CACHE_NAME, node)) {
            throw new AppException("E00008", node);
        }

        return (Process) BricksCacheManager.INSTANCE.getValue(_CACHE_NAME, node);
    }
}
