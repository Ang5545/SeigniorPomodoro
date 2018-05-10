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

    public static String getResourcePath(String relativePath) {
        URL url = FileLoader.class.getClassLoader().getResource(relativePath);
        try {
            return url.toURI().toString();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static String getResourceFile(String relativePath) {
        URL url = FileLoader.class.getClassLoader().getResource(relativePath);
        return url.getFile();

    }
}


