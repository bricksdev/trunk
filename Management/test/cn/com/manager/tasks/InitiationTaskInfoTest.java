/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.tasks;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author kete
 */
public class InitiationTaskInfoTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked before this test starts
    }

    @Test
    public void aTest() throws UnsupportedEncodingException, IOException {
        InitiationTaskInfo.init();
        String target = "货,,哦看\n";
        char[] chars = target.toCharArray();
        System.out.println(System.getProperties());
        for (char c : chars) {
            System.out.println(Character.toTitleCase(c));
        }

        byte[] bytes = target.getBytes("UTF-8");
        byte[] nbs = new byte[128];
        int idx = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        Writer w = null;
        byte[] first3bytes = new byte[3];
        for (byte b : bytes) {
            nbs[idx++] = b;
            if (idx < 3) {
                first3bytes[idx - 1] = b;
            }
            if (b != '\n' && b != ',') {
                continue;
            }


            String charset = Charset.defaultCharset().name();
            if (first3bytes[0] == -17 && first3bytes[1] == -69 && first3bytes[2] == -65) {// utf-8

                charset = "utf-8";

            } else {

                charset = "GBK";
            }
            System.out.println(charset);
            w = new BufferedWriter(new OutputStreamWriter(out, charset));
            w.write(new String(Arrays.copyOf(nbs, idx - 1), charset).toCharArray());
            w.write("\n");
            w.flush();
            System.out.println(out.toString());
            out.reset();
//            System.out.println(new String(target.getBytes("UTF-8")).toCharArray());
//            System.out.println(new String(Arrays.copyOf(nbs, idx - 1), "GBK"));
            nbs = new byte[50];
            idx = 0;
        }

//        System.out.println(new String(Arrays.copyOf(nbs, idx - 1), "GBK"));
    }

//    public static String getFileCharacterEnding(String filePath) {
//        File file = new File(filePath);
//        return getFileCharacterEnding(file);
//    }
    /**
     * Try to get file character ending. </p> <strong>Warning: </strong>use
     * cpDetector to detect file's encoding.
     *
     * @param file
     * @return
     */
//    public static String getFileCharacterEnding(File file) {
//        String fileCharacterEnding = "UTF-8";
//        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//        detector.add(JChardetFacade.getInstance());
//        Charset charset = null;
//// File f = new File(filePath);
//        try {
//            charset = detector.detectCodepage(file.toURL());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (charset != null) {
//            fileCharacterEnding = charset.name();
//        }
//        return fileCharacterEnding;
//    }
    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
