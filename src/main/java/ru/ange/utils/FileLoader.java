package ru.ange.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class FileLoader {

    static public String getAppPath() {
        return new File("").getAbsolutePath();
    }


    public static URL getResourceURL(String relativePath) {
        return FileLoader.class.getClassLoader().getResource(relativePath);
    }

    public static String getResourcePath(String relativePath) {
        URL url = getResourceURL(relativePath);
        try {
            return url.toURI().toString();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static File getResourceFile(String relativePath) {
        URL url = getResourceURL(relativePath);
        return new File(url.getFile());
    }

    public static InputStream getResourceAsStream(String relativePath) {
        return FileLoader.class.getClassLoader().getResourceAsStream(relativePath);
    }
}



