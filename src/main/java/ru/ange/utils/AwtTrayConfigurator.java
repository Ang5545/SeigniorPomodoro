package ru.ange.utils;

import com.apple.eawt.Application;
import ru.ange.conf.Path;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class AwtTrayConfigurator {

    public AwtTrayConfigurator() {
        java.awt.Toolkit.getDefaultToolkit();
    }

    public void addTrayIcon(String icoPath) {

        // set up a system tray icon.
        SystemTray tray = SystemTray.getSystemTray();

        try {
            System.out.println("add tray ico");
            Image image = ImageIO.read(FileLoader.getResourceURL(icoPath));
            TrayIcon trayIcon = new java.awt.TrayIcon(image);
            tray.add(trayIcon);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    public void addMacDocIco(String imgPath) {
        URL url = FileLoader.getResourceURL(imgPath);
        Image image = new ImageIcon(url).getImage();
        Application.getApplication().setDockIconImage(image);
    }
}
