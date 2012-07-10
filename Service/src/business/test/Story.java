/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.test;

import cn.com.annotations.Action;
import cn.com.annotations.Element;
import cn.com.annotations.Form;
import cn.com.annotations.Grid;
import cn.com.annotations.Group;
import cn.com.annotations.Groups;
import cn.com.annotations.Link;
import cn.com.annotations.SelectOption;
import cn.com.annotations.enums.ElementTagType;
import cn.com.annotations.enums.SubmitType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kete
 */
@Groups(groups = {
    @Group(name = "group1", form =
    @Form(action = "Story.jsp"), elements = {
        @Element(label = "ID", id = "id", required = true),
        @Element(label = "FILE.TEMPLATE", id = "template", type = ElementTagType.FILE, link =
        @Link(url = "UploadTemplate.jsp?t=1"))
    },
    actions = {
        @Action(id = "save", label = "SAVE")},
    grids = {
        @Grid(id = "contexts", addabled = true, contextClass = Context.class, columns = {
            @Element(label = "ID", id = "id", link =
            @Link(url = "EditStory.jsp", parameters = {"id"}), type = ElementTagType.LINK),
            @Element(label = "DESC", id = "description")
        })}),
    @Group(name = "viewStory",
    form =
    @Form(action = "ViewStory.jsp"),
    elements = {
        @Element(label = "ID", id = "id", readonly = true),
        @Element(label = "NAME", id = "name", readonly = true),
        @Element(label = "SELECT", id = "selectStatus", type = ElementTagType.SELECT, readonly = true, source =
        @SelectOption(display = {"TEST1", "TEST2"}, value = {"1", "2"}))
    },
    actions = {
        @Action(id = "back", label = "BACK", onclick = "history.go(-1);", submitType = SubmitType.BUTTON)},
    grids = {
        @Grid(id = "contexts", contextClass = Context.class, columns = {
            @Element(label = "ID", id = "id", type = ElementTagType.LINK, link =
            @Link(url = "ViewContext.jsp", parameters = {"id"})),
            @Element(label = "CONTEXT", id = "context", readonly = true),
            @Element(label = "DESCRIPTION", id = "description", readonly = true)
        })})
})
@Form(id = "story", action = "EditStory.jsp", label="TEST FORM")
public class Story {

    @Element(label = "ID", id = "id", required = true)
    private String id;
    @Element(label = "NAME", id = "name", onblur = "AjaxExecute('settingId',null,'','id');")
    private String name;
    @Element(label = "TEST", id = "test", required = true)
    private String test;

    @Element(label = "DATE", id = "date", type = ElementTagType.DATE, formate = "yyyy-MM-dd", defaultValue = "2012-1-1")
    private Date date;
    @Element(label = "SELECT", id = "selectStatus", defaultValue = "2", type = ElementTagType.SELECT, source =
    @SelectOption(display = {"TEST1", "TEST2"}, value = {"1", "2"}))
    private String selectStatus;
    @Grid(id = "contexts", contextClass = Context.class, isMultiple = true, copyabled = true, addabled = true, deletabled = true, columns = {
        @Element(label = "ID", id = "id", required=true),
        @Element(label = "CONTEXT", id = "context", onblur = "AjaxExecute('settingDesc',null,'','contexts.description',this);"),
        @Element(label = "DESC", id = "description")
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

    @Action(label = "SAVE", id = "save", onclick = "return checkInputRequired();")
    public void save() {
    }

    @Action(label = "VIEW", id = "view")
    public void view() {
    }

    /**
     * @return the selectStatus
     */
    public String getSelectStatus() {
        return selectStatus;
    }

    /**
     * @param selectStatus the selectStatus to set
     */
    public void setSelectStatus(String select) {
        this.selectStatus = select;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName()).append(":");

        builder.append(getId()).append("\t").append(getName()).append("\t").append(getSelectStatus()).append("\t").append(getDate()).append("\t").append("\n");
        if (getContexts() != null) {
            builder.append(Context.class.getSimpleName()).append(":");
            for (Context context : getContexts()) {

                builder.append(context.getId()).append("\t").append(context.getContext()).append("\t").append(context.getDescription()).append("\n");
            }
        }
        return builder.toString();


    }

    /**
     * @return the test
     */
    public String getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(String test) {
        this.test = test;
    }
}
