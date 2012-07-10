/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.test;

import business.test.Context;
import cn.com.factory.OperatFactory;
import cn.com.service.StoryService;
import java.util.Map;

/**
 *
 * @author kete
 */
public class EditContext {

    public void onLoad(Map request, Map session) {

        String contextId = (String) request.get("id");

        request.put("context", OperatFactory.getServiceObject(StoryService.class).getContext(contextId));

    }

    public void save(Map request, Map session) {
        request.put("info", "save OK!");
//
//        Context context = new Context();
//        context.setId("5879");
//        context.setContext("TEST Context");
//        context.setDescription("Test Edit Context.");

        request.put("context", OperatFactory.getServiceObject(StoryService.class).saveContext(new Context()));
    }
}
