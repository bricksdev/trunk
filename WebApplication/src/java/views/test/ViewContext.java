/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.test;

import business.test.Context;
import java.util.Map;

/**
 *
 * @author kete
 */
public class ViewContext {

    public void onLoad(Map request, Map ression){

        String contextId = String.valueOf(request.get("id"));

        Context context = new Context();
        context.setId("123546");
        context.setContext("TEST Context");
        context.setDescription("Test View Context.");

        request.put("context", context);

    }

}
