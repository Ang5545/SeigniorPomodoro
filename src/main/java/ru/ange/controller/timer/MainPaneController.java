package ru.ange.controller.timer;

import ru.ange.App;
import ru.ange.timer.Timer;

public class MainPaneController {

    // Reference to the main application.
    private App app;

    private Timer timer;

    public MainPaneController() {
        timer = new Timer(10);
    }


    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void resetTimer() {
        timer.reset();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }


    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
