/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.test;

import business.test.Story;
import business.test.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kete
 */
public class ViewStory {

    public void onLoad(Map request, Map session) {
        Story story = new Story();
        story.setId("1245");
        story.setName("TTTTT1245");
        List<Context> list = new ArrayList<Context>(2);
        Context c = new Context();
        c.setId("23");
        c.setContext("测试Context");
        list.add(c);

        c = new Context();
        c.setId("43");
        c.setContext("测试Context__23");
        list.add(c);
        story.setContexts(list);

        request.put("story", story);
    }
}
