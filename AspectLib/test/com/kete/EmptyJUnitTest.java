/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kete;

import cn.com.byd.compose.utils.TypeConversion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kete
 */
public class EmptyJUnitTest {
    
    public EmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         System.out.println(TypeConversion.converObject(String.class, "123"));
         
     }
}
