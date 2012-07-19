/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.exceptions;

import java.util.Date;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author kete
 */
public class AppExceptionTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked before this test starts
    }

    @Test
    public void aTest() {
        System.out.println(
            new AppException("E00002", new Object[]{new Date()}).getMessage());
    }

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
