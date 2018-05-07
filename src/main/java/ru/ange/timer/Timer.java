package ru.ange.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Timer {

    private int defTime;
    private int currentTime;

    private Timeline timeline;

    private List<Subscriber> subscribers;

    public Timer(int sec) {
        this.subscribers = new ArrayList<Subscriber>();
        this.defTime = sec;
        this.currentTime = sec;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        System.out.println("run timeReducer");
                        currentTime--;
                        notifySubscribers(currentTime);
                    }
                }));
        timeline.setCycleCount(defTime);
    }

    public void subscribe(Subscriber subscr) {
        this.subscribers.add(subscr);
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
       // this.timerTask.cancel();
        this.timeline.stop();
    }

    public void reset() {
        this.timeline.stop();
        this.currentTime = this.defTime;
        this.notifySubscribers(this.currentTime);
    }

    private void notifySubscribers(int time) {
        for (Subscriber subscriber : subscribers) {
            subscriber.notify(currentTime);
        }
    }
}
