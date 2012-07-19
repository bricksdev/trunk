/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.views.inspect;

import cn.com.exceptions.AppException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author kete
 */
public class InspectTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked before this test starts
    }

    @Test(invocationCount = 5000, threadPoolSize = 50)
    public void aTest() throws AppException {
//        Inspect inspect = new Inspect();
//        Map request = new HashMap(3);
//        inspect.onLoad(request, null);
//        Assert.assertNotNull(request.get("inspect"));
//        ApplicationStyle.setCurrentStyle(ApplicationStyle.WMS);
//        AnnotationParser parser = new AnnotationParser(OperatFactory.getServiceObject(InspectModules.class).queryInspect(""), null);
////        parser.setCurrentComponents("table");
//        ContentTemplate contentTemplate = (ContentTemplate) ApplicationStyle.getCurrentStyle().getTemplate(ContentTemplate.class);
//        contentTemplate.setParser(parser);
//        contentTemplate.setPermission(null);
//        String content = contentTemplate.content();
//        System.out.println(content);
    }

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
