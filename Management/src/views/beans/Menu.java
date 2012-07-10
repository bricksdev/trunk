/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.beans;

import java.util.List;

/**
 *
 * @author kete
 */
public class Menu {

    private String id = null;
    private String label = null;
    private List<Menu> submenus = null;
    private String url = null;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the submenus
     */
    public List<Menu> getSubmenus() {
        return submenus;
    }

    /**
     * @param submenus the submenus to set
     */
    public void setSubmenus(List<Menu> submenus) {
        this.submenus = submenus;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
