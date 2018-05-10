package ru.ange.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileLoader {

    static public String getAppPath() {
        return new File("").getAbsolutePath();
    }

    static public File getFile(String relativePath) {
        String path = getAppPath() + relativePath;
        return new File(path);
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

    public static String getResourceFile(String relativePath) {
        URL url = getResourceURL(relativePath);
        return url.getFile();

    }
}



