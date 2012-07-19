package cn.com.byd.support;

public interface ObjectCreator {
    /**
     * 获取指定类文件实例
     * @param className
     * @return
     */
    Object findObject(String className);
}
