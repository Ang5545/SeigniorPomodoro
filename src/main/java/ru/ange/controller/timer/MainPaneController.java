package ru.ange.controller.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import ru.ange.App;
import ru.ange.model.timer.LongBreakTimer;
import ru.ange.model.timer.PomodoroTimer;
import ru.ange.model.timer.ShortBreakTimer;
import ru.ange.model.timer.Timer;
import ru.ange.utils.Notificator;

import java.util.ArrayList;
import java.util.List;

public class MainPaneController {

    // Reference to the main application.
    private App app;

    // Model data
    private Timer pomodo = new PomodoroTimer();
    private Timer shortBreak = new ShortBreakTimer();
    private Timer longBreak = new LongBreakTimer();

    private Notificator notificator;

    private Timer timer;

    // Timer objects
    private Timeline timeline;
    private List<Subscriber> timerSubscribers;


    public MainPaneController(App app) {
        this.app = app;
        this.timerSubscribers = new ArrayList<Subscriber>();
        this.notificator = new Notificator();

        this.timer = pomodo;
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        timer.stepReduce();
                        notifySubscribers(timer.getCurrentTime());
                    }
                }));
        this.timeline.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                notificator.notice(timer.getEndMsg(), timer.getEndMsgDesc());
            }
        });
        this.timeline.setCycleCount(timer.getDefTime());
    }

    public Timer getTimer() {
        return timer;
    }
    public void setTimer(Timer timer) {
        this.timer = timer;
    }


    public void startTimer() {
        this.timeline.play();
        this.timer.setRun(true);
    }

    public void stopTimer() {
        this.timeline.stop();
        this.timer.reset();
        notifySubscribers(timer.getCurrentTime());
    }

    public void pauseTimer() {
        if (timer.isRun()) {
            this.timeline.stop();
            this.timer.setRun(false);
        } else {
            this.timeline.play();
            this.timer.setRun(true);
        }
    }

    private void notifySubscribers(int time) {
        for (Subscriber subscriber : timerSubscribers) {
            subscriber.notify(time);
        }
    }

    public void subscribe(Subscriber subscr) {
        this.timerSubscribers.add(subscr);
    }

    public void switchToPomodoro() {
        this.timer = pomodo;
        refreshTimer();
    }

    public void switchToShortBreak() {
        this.timer = shortBreak;
        refreshTimer();
    }

    public void switchToLongBreak() {
        this.timer = longBreak;
        refreshTimer();
    }

    private void refreshTimer() {
        this.timer.reset();
        this.timeline.stop();
        this.timeline.setCycleCount(timer.getDefTime());
        notifySubscribers(timer.getDefTime());
    }

}
