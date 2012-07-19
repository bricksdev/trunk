/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service.impl;

import cn.com.service.StoryService;
import java.util.List;
import business.test.Context;
import business.test.Story;
import java.util.ArrayList;

/**
 *
 * @author kete
 */
public class StoryServiceImpl implements StoryService{

    @Override
    public Story save(String message) {
        Story story = new Story();
        story.setId("PRO_test111");
        return story;
    }

    @Override
    public Context getContext(String string) {
        Context context = new Context();
        context.setId("PRO_1233");
        return context;
    }

    @Override
    public Context saveContext(Context context) {
        Context context1 = new Context();
        context.setId("PRO_saveContext");
        return context1;
    }

    @Override
    public List<Story> test() {
        Story story = new Story();
        story.setId("PRO_test111");
        List<Story> result = new ArrayList<Story>(2);
        result.add(story);
        return result;
    }

}
