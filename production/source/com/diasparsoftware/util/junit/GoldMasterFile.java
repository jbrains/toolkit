package com.diasparsoftware.util.junit;

import java.io.*;

import junit.framework.Assert;

/***
 * Use a gold master file when verifying content that is
 * not worth parsing.
 *
 */
public class GoldMasterFile extends Assert {
    private File file;

    public GoldMasterFile(String directory, String file) {
        this(new File(directory, file));
    }

    public GoldMasterFile(File file) {
        this.file = file;
    }

    /***
     * Create gold master content, writing it to file.
     * 
     * @param content  The gold master content
     * @throws IOException
     */
    public void write(String content) throws IOException {
        file.getParentFile().mkdirs();
        FileWriter goldMasterWriter = new FileWriter(file);
        goldMasterWriter.write(content);
        goldMasterWriter.close();
    }

    /***
     * Check content against the gold master.
     * 
     * @param expectedContent
     * @throws IOException
     */
    public void check(String actualContent)
        throws IOException {

        assertTrue(
            "Gold master [" + file.getAbsolutePath() + "] not found.",
            file.exists());

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        BufferedReader goldMasterReader =
            new BufferedReader(new FileReader(file));
        while (true) {
            String line = goldMasterReader.readLine();
            if (line == null)
                break;
            printWriter.println(line);
        }

        assertEquals(stringWriter.toString(), actualContent);
    }
}
