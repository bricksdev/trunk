/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import cn.com.annotations.Action;
import cn.com.annotations.Element;
import cn.com.annotations.Form;
import cn.com.annotations.Grid;
import cn.com.annotations.Group;
import cn.com.annotations.Groups;
import cn.com.annotations.SelectOption;
import cn.com.annotations.enums.ElementTagType;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kete
 */
@Groups(groups = {
    @Group(name = "group1", elements = {
        @Element(label = "ID", id = "id", required = true),
        @Element(label = "FILE.TEMPLATE", id = "template", type= ElementTagType.FILE, href="Template.jsp?t=1")
    },
    grids = {
        @Grid(id = "contexts", addabled = true, contextClass = Context.class, columns = {
            @Element(label = "ID", id = "id", href = "EditStory.jsp", type = ElementTagType.LINK),
            @Element(label = "DESC", id = "description")
        })}),
    @Group(name = "group2", elements = {
        @Element(label = "ID", id = "id", required = true),
        @Element(label = "NAME", id = "name"),
        @Element(label = "SELECT", id = "select", type = ElementTagType.SELECT, source =
        @SelectOption(display = {"TEST1", "TEST2"}, value = {"1", "2"}))
    },
    grids = {
        @Grid(id = "contexts", contextClass = Context.class, addabled=true, deletabled= true,columns = {
            @Element(label = "ID", id = "id"),
            @Element(label = "CONTEXT", id = "context")
        })})
})
@Form(id = "story",action="Story.jsp")
public class Story {

    @Element(label = "ID", id="id")
    private String id;
    @Element(label = "NAME",id="name")
    private String name;
    @Element(label = "SELECT",id="select", type= ElementTagType.SELECT,source =
    @SelectOption(display = {"TEST1", "TEST2"}, value = {"1", "2"}))
    private String select;
    @Grid(id = "contexts", contextClass = Context.class, columns = {
        @Element(label = "ID", id = "id", readonly=true),
        @Element(label = "CONTEXT", id = "context")
    })
    private List<Context> contexts = null;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contexts
     */
    public List<Context> getContexts() {
        return contexts;
    }

    /**
     * @param contexts the contexts to set
     */
    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    @Action(label="SAVE", id="save", onclick="this.form.submit();")
    public void save(Map request, Map session) {
        //TODO save operat
    }

    /**
     * @return the select
     */
    public String getSelect() {
        return select;
    }

    /**
     * @param select the select to set
     */
    public void setSelect(String select) {
        this.select = select;
    }
}
