/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.loader.weaving;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public class TestOnlyClassLoader extends ClassLoader {

    private List<String> _PATHS = new ArrayList<String>();
    private Map<String, JarFile> _JARFILES = new HashMap<String, JarFile>();
    private WeakHashMap<String, Class<?>> _CLASS_CACHE = new WeakHashMap<String, Class<?>>();

    public TestOnlyClassLoader(ClassLoader parent){
        
        super(parent);
        init();
    }
    
    public void init() {
        URLClassLoader urlClassLoader = null;
        //  + ":" + System.getProperty("sun.boot.class.path")
        String classpath = System.getProperty("java.class.path");
        if (classpath != null && !classpath.trim().isEmpty()) {
            String[] paths = classpath.split(System.getProperty("path.separator"));
            for (String p : paths) {
                try {
                    if (p.endsWith(".jar")) {

                        JarFile jarfile = new JarFile(p);
                        _JARFILES.put(p, jarfile);
                    }
                    _PATHS.add(p);

                } catch (Exception ex) {
                    Logger.getLogger(TestOnlyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }
    
    public synchronized void addResouse(String classPath){
        _PATHS.add(classPath);
    }

    @Override
    public synchronized Class<?> loadClass(String name) throws ClassNotFoundException {
        // 如果当前class路径下存在类，将返回
        if (_CLASS_CACHE.containsKey(name)) {
            return _CLASS_CACHE.get(name);
        }
        Class localClazz = null;
        try {
            localClazz = this.findClass(name);
            if (localClazz != null) {
                return localClazz;
            }
        } catch (ClassNotFoundException ex) {
            // 当前路径下不存在将由父类查找
            ClassLoader cl = this.getParent();

            while (cl != null) {

                localClazz = cl.loadClass(name);
                if (localClazz != null) {
                    break;
                }

                cl = cl.getParent();
            }
            if (localClazz == null) {
                throw ex;
            }

            if (localClazz.getProtectionDomain().getCodeSource() != null) {
                String parentClassPath = localClazz.getProtectionDomain().getCodeSource().getLocation().getPath();
                _PATHS.add(parentClassPath);
                localClazz = this.findClass(name);
                if (localClazz == null) {
                    throw ex;
                }
            }
            return localClazz;
        }
        // 保存缓存
        _CLASS_CACHE.put(name, localClazz);

        return localClazz;
    }

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        // 如果当前class路径下存在类，将返回
        if (_CLASS_CACHE.containsKey(name)) {
            return _CLASS_CACHE.get(name);
        }

        byte[] data = loadClassData(name);
        Class localClazz = defineClass(name, data, 0, data.length);
        if (localClazz == null) {
            localClazz = this.loadClass(name);
        }

        if (localClazz == null) {
            throw new ClassNotFoundException(name);
        }
        // 保存缓存
        _CLASS_CACHE.put(name, localClazz);
        return localClazz;
    }

    /**
     * 生成指定字节流的实类
     *
     * @param <T>
     * @param name
     * @param classbytes
     * @return
     */
    public Class<?> getInstance(String name, byte[] classbytes) {
//        try {
//            FileOutputStream oupt = new FileOutputStream("/media/WinD/softdev/kopnew/sourcecodes/wms/branch/dev/wms-v1.2/wms_util/build/classes/TU.class");
//            System.out.println(name);
//            oupt.write(classbytes);
//            oupt.close();
//        } catch (Exception ex) {
//            Logger.getLogger(TestOnlyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Class localClazz = super.defineClass(name, classbytes, 0, classbytes.length);

        return localClazz;
    }

    /**
     * 转换成字节码
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public byte[] loadClassData(String name) throws ClassNotFoundException {
        InputStream fis = null;
        byte[] data = null;
        try {
            Stack<String> filepaths = new Stack<String>();
            filepaths.addAll(_PATHS);
            fis = this.getClassInputStream(filepaths, name);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch = 0;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();

        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
        return data;
    }

    /**
     * 获取字节流，递归当前classpath下的所有文件，优先线程加载器
     *
     * @param filesStacks
     * @param fileName
     * @return
     * @throws ClassNotFoundException
     */
    private InputStream getClassInputStream(Stack<String> filesStacks, String fileName) throws ClassNotFoundException {

        if (filesStacks.empty()) {
            throw new ClassNotFoundException(fileName);
        }
        String filepath = filesStacks.pop();
        String clazzPath = fileName.replaceAll("[.]", "/") + ".class";
        // 处理jar包中的文件
        if (filepath.endsWith(".jar")) {
            JarFile jarFile = _JARFILES.get(filepath);
            JarEntry entry = jarFile.getJarEntry(clazzPath);
            if (entry != null) {
                try {
                    return jarFile.getInputStream(entry);
                } catch (IOException ex) {
                    Logger.getLogger(TestOnlyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ClassNotFoundException(fileName);

                }
            }
            // 获取classpath下的路径文件
        } else {
            StringBuilder sb = new StringBuilder(filepath).append("/");
            sb.append(clazzPath);
            File file = new File(sb.toString());
            if (file.isFile() && file.exists()) {
                try {
                    return new FileInputStream(file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TestOnlyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ClassNotFoundException(fileName);
                }
            }

        }
        return getClassInputStream(filesStacks, fileName);
    }
}
