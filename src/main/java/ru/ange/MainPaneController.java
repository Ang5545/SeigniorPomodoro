package ru.ange;

import ru.ange.timer.Timer;

public class MainPaneController {

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
}
