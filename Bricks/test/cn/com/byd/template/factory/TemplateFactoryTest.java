/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.byd.template.factory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author kete
 */
public class TemplateFactoryTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked before this test starts
    }

    @Test(threadPoolSize = 1, invocationCount = 1)
    public void aTest() {
//        Story story = new Story();
//        story.setId("1245");
//        story.setName("TTTTT1245");
//        List<Context> list = new ArrayList<Context>(2);
//        Context c = new Context();
//        c.setId("23");
//        c.setContext("测试Context");
//        list.add(c);
//        story.setContexts(list);
//        AnnotationParser parser = new AnnotationParser(story, "");
//        String content = TemplateFactory.parser(parser.getForm().template()).content(parser);
//        System.out.println(content);


//        String className = MockInstanceCreator.instance(StoryService.class).getClass().toString();
//        System.out.println(((StoryService) MockInstanceCreator.instance(StoryService.class)).create("52"));

//        Mockery content = new Mockery();
//        final StoryService service = content.mock(StoryService.class);
////        Publisher publisher = new Publisher();
////        publisher.add(service);
//        final String VALUE = "33";
//        content.checking(new Expectations(){
//            {
//                oneOf(((StoryService)service).create("33"));will(returnValue(VALUE));
//            }
//        });
//        content.assertIsSatisfied();
//        System.out.println(VALUE);
//        StoryService.class.newInstance()
    }

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
