package ru.ange;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ange.gui.mainpane.MainPane;


public class App extends Application {

    public static final String APP_NAME = "Seignior Pomodoro";
    public static final int DEF_WIDTH = 300;
    public static final int DEF_HEIGHT = 250;




    public void start(Stage primaryStage) throws Exception {

        MainPaneController mpc = new MainPaneController();
        MainPane mainPane = new MainPane(mpc);

        Scene scene = new Scene(mainPane, DEF_WIDTH, DEF_HEIGHT);
        primaryStage.setTitle(APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
