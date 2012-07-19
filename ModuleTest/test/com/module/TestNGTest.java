/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.module;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author kete
 */
public class TestNGTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked before this test starts
    }

    @Test
    public static void main(String... args) {



        BufferedReader read = null;
        BufferedWriter write = null;
        String dateStart = "2012-06-25 08:00";
        String dateEnd = "2012-06-25 09:00";
        String target = "2012-06-25 ";
        String subString = "0001680764";

        try {

            File file = new File("/home/kete/wms.log");
            File nFile = null;
            String nFileName = "web_log";
            File files = new File(nFileName + "_" + 0 + ".log");
            if (files != null && files.getAbsoluteFile().getParentFile() != null && files.getAbsoluteFile().getParentFile().isDirectory()) {
                for (File tf : files.getAbsoluteFile().getParentFile().listFiles(new FileFilter() {

                    public boolean accept(File dir) {
                        return dir.isFile() && dir.getName().endsWith(".log");
                    }
                })) {
                    tf.delete();
                }
            }

            read = new BufferedReader(new FileReader(file));
            String line = null;
            String charString = null;
            int idx = 0;
            boolean isStart = false;
            while ((line = read.readLine()) != null) {
                if (!line.startsWith(target)) {
                    continue;
                }

                if (line.contains(subString)) {
                    isStart = true;
                }

                if (!isStart) {
                    continue;
                }

                charString = line.substring(0, dateStart.length());
                if (charString.compareTo(dateStart) < 0) {
                    continue;
                }
                if (nFile == null) {
                    nFile = new File(nFileName + "_" + idx + ".log");
                }
                if (!nFile.exists()) {
                    write = new BufferedWriter(new FileWriter(nFile, true));
                }
                write.write(line);
                write.newLine();
                charString = line.substring(0, dateEnd.length());
                if (charString.compareTo(dateEnd) > 0) {
                    break;
                }
                if (nFile.length() >= 1024 * 1024 * 100) {
                    idx++;
                    write.flush();
                    if (write != null) {
                        write.close();
                    }
                    write = null;
                    nFile = null;
                }

            }
            write.flush();

        } catch (IOException ex) {
            Logger.getLogger(TestNGTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (read != null) {
                    read.close();
                }
                if (write != null) {
                    write.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(TestNGTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
