/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving.xml.utils;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 转换操作类
 *
 * @author Kete
 */
public class TranslateXMLOperator {

    private static final String SERIALVERSIONUID = "serialVersionUID";
    /**
     * 通用类型初始化
     */
    private static final List<Class<?>> COMMON_OBJECT = Collections.unmodifiableList(new ArrayList<Class<?>>() {
        {
            add(Integer.TYPE);
            add(Long.TYPE);
            add(Double.TYPE);
            add(Short.TYPE);
            add(Character.TYPE);
            add(Float.TYPE);
            add(char.class);
            add(int.class);
            add(long.class);
            add(double.class);
            add(float.class);
            add(boolean.class);
            add(Boolean.class);
            add(String.class);
            add(BigDecimal.class);
            add(Date.class);
            add(StringBuilder.class);
            add(StringBuffer.class);
            add(Class.class);
            add(Object.class);
        }
    });

    /**
     * 创建元素
     *
     * @param elementName
     * @param document
     * @return
     */
    public static Element createElement(String elementName, Document document) {

        return document.createElement(elementName);
    }

    /**
     * 创建元素, 并追加对应的属性
     *
     * @param elementName
     * @param document
     * @param attributeIds
     * @param attributeValues
     * @return
     */
    public static Element createElement(String elementName, Document document, String[] attributeIds, String[] attributeValues) {

        Element element = createElement(elementName, document);

        if (attributeIds == null || attributeValues == null || attributeIds.length != attributeValues.length) {
            throw new RuntimeException("创建元素属性不对应");
        }

        for (int idx = 0; idx < attributeIds.length; idx++) {
            element.setAttribute(attributeIds[idx], attributeValues[idx]);
        }

        return element;
    }

    /**
     * 追加子元素
     *
     * @param element
     * @param childElement
     */
    public static void appendChildren(Element element, Element childElement) {

        element.appendChild(childElement);
    }

    /**
     * 追加元素
     *
     * @param element
     * @param childElementName
     * @param content
     * @return
     */
    public static Element appendChildElement(Element element, String childElementName, String content) {
        Element childElement = appendChildElement(element, childElementName);
        childElement.setTextContent(content);
        return childElement;
    }

    /**
     * 追加元素
     *
     * @param element
     * @param childElementName
     * @param childElementId
     * @param content
     * @return
     */
    public static Element appendChildElement(Element element, String childElementName, String childElementId, String content) {
        Element childElement = appendChildElement(element, childElementName);
        childElement.setAttribute("id", childElementId);
        childElement.setTextContent(content);
        return childElement;
    }

    /**
     * 追加元素
     *
     * @param element
     * @param childElementName
     * @return
     */
    public static Element appendChildElement(Element element, String childElementName) {
        Element childElement = element.getOwnerDocument().createElement(childElementName);
        appendChildren(element, childElement);
        return childElement;
    }

    /**
     * 追加元素
     *
     * @param element
     * @param childElementName
     * @param attributeIds
     * @param attributeValues
     * @return
     */
    public static Element appendChildElement(Element element, String childElementName, String[] attributeIds, String[] attributeValues) {
        Element childElement = appendChildElement(element, childElementName);
        if (attributeIds == null || attributeValues == null || attributeIds.length != attributeValues.length) {
            throw new RuntimeException("创建元素属性不对应");
        }

        for (int idx = 0; idx < attributeIds.length; idx++) {
            childElement.setAttribute(attributeIds[idx], attributeValues[idx]);
        }

        return childElement;
    }

    /**
     * 创建元素
     *
     * @param elementName
     * @param document
     * @param elementId
     * @return
     */
    public static Element createElement(String elementName, Document document, String elementId) {

        Element element = createElement(elementName, document);
        element.setAttribute("id", elementId);
        return element;
    }

    /**
     * 查找元素通过元素 ID
     *
     * @param elementId
     * @param document
     * @return
     */
    public static Element findElementById(String elementId, Document document) {
        return document.getElementById(elementId);
    }

    /**
     * 输出输入输出参数信息
     *
     * @param output
     * @param document
     */
    public static synchronized void output(OutputStream output, Document document) {
        try {
            TransformerFactory transFactory = TransformerFactory.newInstance();     //开始把Document映射到文件
            Transformer transFormer = transFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);    //设置输出结果

            StreamResult xmlResult = new StreamResult(output);            //设置输入源  

            transFormer.transform(domSource, xmlResult);              //输出xml文件   


        } catch (TransformerException ex) {
            Logger.getLogger(TranslateXMLOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 通过元素名获得节点
     *
     * @param elementName
     * @param document
     * @param idx
     * @return
     */
    public static Node findElementByName(String elementName, Document document, int idx) {

        NodeList elements = document.getElementsByTagName(elementName);
        if (elements == null) {
            throw new RuntimeException("文档中没有对应的元素:" + elementName);
        }

        if (elements.getLength() < idx) {
            throw new RuntimeException("文档中对应的元素:" + elementName + "越界.");
        }

        return elements.item(idx);
    }

    /**
     * 创建DOM
     *
     * @return
     */
    public static Document createDocument() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return documentBuilder.newDocument();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TranslateXMLOperator.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

    }

    /**
     * 验证对象是否为空
     *
     * @param obj
     * @param element
     */
    private static boolean validate(Object obj, Element element) {
        boolean b = false;
        if (element == null) {
            throw new RuntimeException("文档节点为空!");
//            throw new RuntimeException(LanguageNational.getValue("M6000488"));
        }
        if (obj == null) {
            b = true;
        }
        return b;
    }

    private static Field[] getFields(Class clazz) {
        Class tmpClass = clazz;
        List<Field> fields = new ArrayList<Field>();
        Field[] tFields = null;
        do {
            tFields = tmpClass.getDeclaredFields();
            if (tFields.length > 0) {
                fields.addAll(Arrays.asList(tFields));
            }
            tmpClass = tmpClass.getSuperclass();
        } while (tmpClass != Object.class);
        return fields.toArray(new Field[0]);
    }

    /**
     * 装换对象成文档节点
     *
     * @param obj
     * @param element
     */
    public static void turnObjectToElement(Object obj, Element element) {
        if (validate(obj, element)) {
            return;
        }

        // 获取对象类
        Class clzz = obj.getClass();
        Field[] fields = getFields(clzz);

        if (fields != null && fields.length > 0) {
            try {

                Element child = null;
//                Element valueNode = null;
                Element subTable = null;
                Element thChild = appendChildElement(element, "tr");
                Element trChild = appendChildElement(element, "tr");
                appendChildElement(thChild, "td", clzz.getName());

                child = appendChildElement(trChild, "td");
                for (Field f : fields) {
                    // 去除不必要的序列字段
                    if (f.getName().equals(SERIALVERSIONUID)) {
                        continue;
                    }
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    appendChildElement(thChild, "td", String.valueOf(f.getName()));
                    
                    if (f.getType().isPrimitive() || isNoTurnType(f.getType()) || f.getType().isEnum()) {
                        // 有数据时才处理
//                        if (f.get(obj) != null) {
//                            child = element.getOwnerDocument().createElement(upperFirstChar(f.getName()));
//                            child.setTextContent(f.get(obj).toString());
//                            element.appendChild(child);
//                        }
                        appendChildElement(child, "td", String.valueOf(f.get(obj)));

                    } else if (obj instanceof Collection) {
                        subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
                        turnCollectionToElement((Collection) f.get(obj), subTable);
//                        element.appendChild(child);
                    } else if (obj instanceof Map) {
                        subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
                        turnMapToElement((Map) f.get(obj), subTable);
                    } else if (f.getType().isArray()) {
                        Object[] objs = null;
                        if (obj instanceof int[]) {
                            int[] t = (int[]) f.get(obj);
                            objs = new Object[t.length];
                            for (int k = 0; k < t.length; k++) {
                                objs[k] = Integer.valueOf(t[k]);
                            }
                        } else if (obj instanceof double[]) {
                            double[] t = (double[]) f.get(obj);
                            objs = new Object[t.length];
                            for (int k = 0; k < t.length; k++) {
                                objs[k] = Double.valueOf(t[k]);
                            }
                        } else if (obj instanceof float[]) {
                            float[] t = (float[]) f.get(obj);
                            objs = new Object[t.length];
                            for (int k = 0; k < t.length; k++) {
                                objs[k] = Float.valueOf(t[k]);
                            }
                        } else if (obj instanceof byte[]) {
                            byte[] t = (byte[]) f.get(obj);
                            objs = new Object[t.length];
                            for (int k = 0; k < t.length; k++) {
                                objs[k] = Byte.valueOf(t[k]);
                            }
                        } else {
                            objs = (Object[]) f.get(obj);
                        }
                        subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
//                        for (int i = 0; i < objs.length; i++) {
//                            valueNode = element.getOwnerDocument().createElement("value");
//
//                            valueNode.setTextContent(String.valueOf(objs[i]));
//                            child.appendChild(valueNode);
//                        }
                        turnCollectionToElement(Arrays.asList(objs), subTable);
//                        element.appendChild(child);
                    } else {
                        // 有数据时才处理
                        if (f.get(obj) != null) {
                            subTable = appendChildElement(child, "table");
                            turnObjectToElement(f.get(obj), subTable);
//                            element.appendChild(child);
                        }
                    }


                }

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(TranslateXMLOperator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TranslateXMLOperator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 是否为不用转换的类型
     *
     * @param clazz
     * @return
     */
    private static boolean isNoTurnType(Class clazz) {
        return COMMON_OBJECT.contains(clazz);
    }

    /**
     * 转换列表对象为文档节点
     *
     * @param collection
     * @param element
     */
    public static void turnCollectionToElement(Collection collection, Element element) {
        if (validate(collection, element)) {
            return;
        }

        if (collection.isEmpty()) {
            return;
        }
        Object obj = null;
        Element child = null;
        Element subTable = null;
        Element thChild = appendChildElement(element, "tr");
        Element trChild = appendChildElement(element, "tr");
        for (Iterator it = collection.iterator(); it.hasNext();) {

            obj = it.next();
            if (obj != null) {
                appendChildElement(thChild, "td", obj.getClass().getName());
            } else {
                appendChildElement(thChild, "td", "Null Object");
            }

            child = appendChildElement(trChild, "td");
            if (obj != null && obj instanceof Map) {
                subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
                turnMapToElement((Map) obj, subTable);
            } else if (obj != null && obj instanceof List) {
                subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
                turnCollectionToElement((List) obj, subTable);
            } else if (obj != null && isNotJavaObject(obj)) {
                subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
                turnObjectToElement(obj, subTable);
            } else {
                child.setTextContent(String.valueOf(obj));
            }
//            element.appendChild(child);

        }
    }

    /**
     * 转换集合对象为文档节点
     *
     * @param map
     * @param element
     */
    public static void turnMapToElement(Map map, Element element) {
        if (validate(map, element)) {
            return;
        }

        if (map.isEmpty()) {
            return;
        }
        Element child = null;
        String key = null;
        Object obj = null;
        Element subTable = null;
        Element thChild = appendChildElement(element, "tr");
        Element trChild = appendChildElement(element, "tr");
        for (Iterator it = map.keySet().iterator(); it.hasNext();) {
            key = (String) it.next();
            obj = map.get(key);
//            if (obj != null) {
                appendChildElement(thChild, "td", key);
//            } else {
//                appendChildElement(thChild, "td", "Null");
//            }

            child = appendChildElement(trChild, "td");
            if (obj != null && obj instanceof List) {
                subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
                turnCollectionToElement((List) obj, subTable);
            } else if (obj != null && obj instanceof Map) {
                subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
                turnMapToElement((Map) obj, subTable);
            } else if (obj != null && isNotJavaObject(obj)) {
                subTable = appendChildElement(child, "table", new String[]{"border"}, new String[]{"1"});
                turnObjectToElement(obj, subTable);
            } else {
                child.setTextContent(String.valueOf(obj));
            }
//            element.appendChild(child);
        }
    }

    /**
     * 是否为对象类型
     *
     * @param obj
     * @return
     */
    private static boolean isNotJavaObject(Object obj) {
        boolean b = false;
        if (obj != null) {
            Class clazz = obj.getClass();
            if (clazz.isPrimitive() || isNoTurnType(clazz)) {
                return b;
            } else {

                b = true;
            }
        }
        return b;
    }
}
