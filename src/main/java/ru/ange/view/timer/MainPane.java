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
import ru.ange.controller.timer.Subscriber;

public class MainPane extends StackPane implements Subscriber {

    private static final String START_BTT_TEXT = "Play";
    private static final String PAUSE_BTT_TEXT = "Pause";
    private static final String STOP_BTT_TEXT = "Stop";

    private static final String POMODORO_BTT_TEXT = "Pomodoro";
    private static final String SHORT_BREAK_BTT_TEXT = "Short Break";
    private static final String LONG_BREAK_BTT_TEXT = "Long break";


    private MainPaneController mpc;

    private Button startBtt;
    private Button stopBtt;

    private Button pomodoroBtt;
    private Button shorBreakBtt;
    private Button longBreakBtt;

    private TimerLabel timerLabel;

    public MainPane (final MainPaneController mpc) {
        this.mpc = mpc;
        this.mpc.subscribe(this);

        // -- init components --
        this.startBtt = createBtt(START_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (mpc.getTimer().isRun()) {
                    mpc.pauseTimer();
                    startBtt.setText(START_BTT_TEXT);
                } else {
                    mpc.startTimer();
                    startBtt.setText(PAUSE_BTT_TEXT);
                }
            }
        });
        this.stopBtt = createBtt(STOP_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.stopTimer();
            }
        });


        HBox bottomButtons = createBttsPane();
        bottomButtons.getChildren().addAll(startBtt, stopBtt);

        this.timerLabel = new TimerLabel();
        this.timerLabel.setTime(mpc.getTimer().getCurrentTime());

        this.pomodoroBtt = createBtt(POMODORO_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.switchToPomodoro();
                mpc.startTimer();
            }
        });
        this.shorBreakBtt = createBtt(SHORT_BREAK_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.switchToShortBreak();
                mpc.startTimer();
            }
        });
        this.longBreakBtt = createBtt(LONG_BREAK_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.switchToLongBreak();
                mpc.startTimer();
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
