package com.diasparsoftware.htmlunitx;

import java.io.*;
import java.net.URL;

public class FileSystemWebResponse extends InputStreamWebResponse {
    public FileSystemWebResponse(URL url, File contentFile)
        throws FileNotFoundException {
            
        super(url, new FileInputStream(contentFile));
    }
}
