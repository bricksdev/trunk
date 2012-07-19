/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements;

import cn.com.exceptions.AppException;
import cn.com.refects.InstanceCreator;

/**
 * 获取元素的工厂
 * @author kete
 */
@Deprecated
public abstract class ElementsFactory {

    /**
     * 获取对应元素实类
     * @param <T>
     * @param element
     * @param id
     * @param cssClass
     * @param cssStyle
     * @return 对应的元素实类
     * @throws AppException
     */
    public static <T> T findElement(HTMLElement element, String id, String cssClass, String cssStyle) throws AppException {
        AbstractHtmlElementTag elementTag = findElement(element);
        elementTag.setCssClass(cssClass);
        elementTag.setId(id);
        elementTag.setCssStyle(cssStyle);
        return (T) elementTag;
    }

    /**
     * 获取对应元素实类
     * @param <T>
     * @param element
     * @return
     * @throws AppException
     */
    public static <T> T findElement(HTMLElement element) throws AppException {
        return (T) InstanceCreator.getInstance(element.getElement());

    }
}
