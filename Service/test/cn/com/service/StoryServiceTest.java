/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.service;

import cn.com.factory.OperatFactory;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import business.test.Story;
import cn.com.global.Model;
import cn.com.global.MotionModel;

/**
 *
 * @author kete
 */
public class StoryServiceTest {

    @BeforeClass
    public void setUp() {
        MotionModel.setModel(Model.TEST);
        // code that will be invoked before this test starts
    }

    @Test
    public void aTest() {

        List<Story> storys = OperatFactory.getServiceObject(StoryService.class).test();

        int idx = 0;
        for (Story story : storys) {
            System.out.println("list : "+(idx++)+" > "+story);
        }
    }

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
