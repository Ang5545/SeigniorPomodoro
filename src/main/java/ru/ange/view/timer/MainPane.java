package ru.ange.view.timer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ru.ange.controller.timer.MainPaneController;
import ru.ange.timer.Subscriber;

public class MainPane extends StackPane implements Subscriber {

    private MainPaneController mpc;

    private Button startBtt;
    private Button stopBtt;
    private Button pauseBtt;

    private Button pomodoroBtt;
    private Button shorBreakBtt;
    private Button longBreakBtt;

    private TimerLabel timerLabel;

    public MainPane (final MainPaneController mpc) {
        this.mpc = mpc;
        mpc.getTimer().subscribe(this);

        // -- init components --
        this.startBtt = createBtt("Start", new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.startTimer();
            }
        });
        this.stopBtt = createBtt("Stop", new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.stopTimer();
            }
        });
        this.pauseBtt = createBtt("Pause", new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.resetTimer();
            }
        });

        HBox bottomButtons = createBttsPane();
        bottomButtons.getChildren().addAll(startBtt, stopBtt, pauseBtt);

        this.timerLabel = new TimerLabel();

        this.pomodoroBtt = createBtt("Pause", new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Pomodoro");
            }
        });
        this.shorBreakBtt = createBtt("Pause", new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("shorBreakBtt");
            }
        });
        this.longBreakBtt = createBtt("Pause", new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("longBreakBtt");
            }
        });

        HBox topButtons = createBttsPane();
        topButtons.getChildren().addAll(pomodoroBtt, shorBreakBtt, longBreakBtt);

        BorderPane border = new BorderPane();
        border.setTop(topButtons);
        border.setCenter(timerLabel);
        border.setBottom(bottomButtons);

        this.getChildren().add(border);
    }

    public void notify(int time) {
        this.timerLabel.setTime(time);
    }


    private Button createBtt(String name, EventHandler<ActionEvent> event) {
        Button btt = new Button(name);
        btt.setOnAction(event);
        return btt;
    }

    private HBox createBttsPane() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        return hbox;
    }
}
