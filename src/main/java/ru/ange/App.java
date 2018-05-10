package ru.ange;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.ange.conf.Path;
import ru.ange.controller.timer.MainPaneController;
import ru.ange.utils.AwtTrayConfigurator;
import ru.ange.utils.FileLoader;
import ru.ange.view.timer.MainPane;
import sun.misc.ClassLoaderUtil;

import javax.swing.*;
import java.io.File;
import java.net.URL;


public class App extends Application {

    private static final String APP_NAME = "Seignior Pomodoro";
    private static final String LOGO_FILE_NAME = "logo.png";

    private static final int DEF_WIDTH = 300;
    private static final int DEF_HEIGHT = 250;

    public void start(Stage stage) throws Exception {
        MainPaneController mpc = new MainPaneController(this);
        MainPane mainPane = new MainPane(mpc);

        if (java.awt.SystemTray.isSupported()) {
            // suported awt tray methods
            // configurate view via awt
            AwtTrayConfigurator atc = new AwtTrayConfigurator();
            atc.addMacDocIco(Path.RESOURCES_IMAGES_DIR + LOGO_FILE_NAME);
            //atc.addTrayIcon(Path.RESOURCES_IMAGES_DIR + LOGO_FILE_NAME);
        } else {

            // NOT suported awt
            // only add image ico
            Image appIcon = new Image(FileLoader.getResourcePath(Path.RESOURCES_IMAGES_DIR + LOGO_FILE_NAME));
            stage.getIcons().add(appIcon);
        }

        Scene scene = new Scene(mainPane, DEF_WIDTH, DEF_HEIGHT);
        stage.setTitle(APP_NAME);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }




}
