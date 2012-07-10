/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.test;

import cn.com.annotations.Action;
import cn.com.annotations.Element;
import cn.com.annotations.Form;
import cn.com.annotations.Group;
import cn.com.annotations.Groups;
import cn.com.annotations.enums.SubmitType;

/**
 *
 * @author kete
 */
@Groups(groups = {
    @Group(name = "group1", elements = {
        @Element(label = "ID", id = "id"),
        @Element(label = "DESC", id = "description")
    }),
    @Group(name = "viewContext", form =
    @Form(action = "ViewContext.jsp"),
    actions = {
        @Action(label = "CLOSE", id = "close", submitType = SubmitType.BUTTON, onclick = "confirm('Are you sure exist this.');")
            ,
        @Action(id="back",label="BACK",onclick="history.go(-1);",submitType= SubmitType.BUTTON)
    },
    elements = {
        @Element(label = "ID", id = "id", readonly = true),
        @Element(label = "CONTEXT", id = "context", readonly = true),
        @Element(label = "DESC", id = "description", readonly = true)
    })
})
@Form()
public class Context {

    @Element(label = "ID", id = "id")
    private String id;
    @Element(label = "DESC", id = "description")
    private String description;
    @Element(label = "CONTEXT", id = "context")
    private String context;
    @Element(label="TEST", id="test1")
    private String test1=null;

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

    @Action(label = "SAVE", id = "save", submitType = SubmitType.SUBMIT)
    public void save() {
    }

    @Action(id = "back", label = "BACK", submitType = SubmitType.BUTTON, onclick = "history.go(-1);")
    public void back() {
    }

    /**
     * @return the test1
     */
    public String getTest1() {
        return test1;
    }

    /**
     * @param test1 the test1 to set
     */
    public void setTest1(String test1) {
        this.test1 = test1;
    }
}
