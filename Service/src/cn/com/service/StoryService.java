/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service;

import java.util.List;
import business.test.Context;
import business.test.Story;

/**
 *
 * @author kete
 */
public interface StoryService {

    Story save(String message);

    Context getContext(String string);

     Context saveContext(Context context);

     List<Story> test();

}
