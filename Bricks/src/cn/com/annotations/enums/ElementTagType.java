/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations.enums;

/**
 *
 * @author kete
 */
public enum ElementTagType {

    TEXT, SELECT, CHECKBOX, RADIO, BUTTON, LINK, OPTION, FILE, SUBMIT, HIDDEN, DATE, DATE_TIME;

    /**
     * 判断是否input标签
     * @param type
     * @return
     */
    @Deprecated
    public boolean isInputType(ElementTagType type) {
        boolean isinput = Boolean.FALSE;
        switch (type) {
            case TEXT:
                isinput = Boolean.TRUE;
                break;
            case CHECKBOX:
                isinput = Boolean.TRUE;
                break;
            case RADIO:
                isinput = Boolean.TRUE;
                break;
            case BUTTON:
                isinput = Boolean.TRUE;
                break;
            case FILE:
                isinput = Boolean.TRUE;
                break;
            default:
                isinput = Boolean.FALSE;
                break;

        }
        return isinput;
    }
}
