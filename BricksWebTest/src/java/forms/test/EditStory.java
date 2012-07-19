/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.test;

import business.test.Story;
import byd.mvc.context.ActionContext;
import byd.mvc.render.FormStatics;
import cn.com.exceptions.AppException;
import cn.com.factory.OperatFactory;
import cn.com.manager.PermissionManager;
import cn.com.service.StoryService;
import cn.com.utils.JSONObjectUtils;
import cn.com.wrapper.DataWrapper;
import cn.com.wapps.wrapper.MVCDataWrapper;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public class EditStory {

    public void save(Map request, Map session) {

        request.put("info", "saved OK!");

        Story story = OperatFactory.getServiceObject(StoryService.class).save("OK");
        request.put("story", story);
        request.put("group", "viewStory");



    }

    public String view(Map request, Map session) {
        try {
            PermissionManager.getUserPermission().check(Story.class, "view");

            DataWrapper wrapper = new MVCDataWrapper(request, false, "yyyy-MM-dd");
            Story story = wrapper.execute(Story.class);
            request.put("story", story);
        } catch (AppException ex) {
            Logger.getLogger(EditStory.class.getName()).log(Level.SEVERE, null, ex);
            request.put("error", ex.getMessage());
            return null;
        }
        return FormStatics.ACTIONRETURN_FORWARD + "ViewStory.jsp";
    }

    public void settingId(Map request, Map session) {
        try {
            Story story = OperatFactory.getServiceObject(StoryService.class).save("OK");
            ActionContext.getResponse().getWriter().println(JSONObjectUtils.wrapperJSON(story));
        } catch (IOException ex) {
            Logger.getLogger(EditStory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ActionContext.getResponse().getWriter().close();
            } catch (IOException ex) {
                Logger.getLogger(EditStory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void settingDesc(Map request, Map session) {
        try {
            Story story = OperatFactory.getServiceObject(StoryService.class).save("OK");
            ActionContext.getResponse().getWriter().println("{\"contexts.description\":12233}");
        } catch (Exception ex) {
            Logger.getLogger(EditStory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ActionContext.getResponse().getWriter().close();
            } catch (IOException ex) {
                Logger.getLogger(EditStory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
