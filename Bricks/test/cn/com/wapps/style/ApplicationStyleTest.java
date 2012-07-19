/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.style;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author kete
 */
public class ApplicationStyleTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked before this test starts
    }

    @Test
    public void aTest() {
        ApplicationStyle t = ApplicationStyle.DEFAULT;
    }

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
