/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import cn.com.annotations.Element;
import cn.com.annotations.Group;
import cn.com.annotations.Groups;

/**
 *
 * @author kete
 */
@Groups(groups = {
    @Group(name = "group1", elements = {
        @Element(label = "ID", id = "id", href = "EditStory.jsp"),
        @Element(label = "DESC", id = "description")
    }),
    @Group(name = "group2", elements = {
        @Element(label = "ID", id = "id"),
        @Element(label = "CONTEXT", id = "context")
    })
})
public class Context {

    @Element(label = "ID", id="id")
    private String id;
    @Element(label = "DESC",id="description")
    private String description;
    @Element(label = "CONTEXT",id="context")
    private String context;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
    }
}
