package ru.ange.view.timer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ru.ange.controller.timer.MainPaneController;
import ru.ange.controller.timer.Subscriber;
import ru.ange.utils.FileLoader;

public class MainPane extends StackPane implements Subscriber {

    private static final String START_BTT_TEXT = "Play";
    private static final String PAUSE_BTT_TEXT = "Pause";
    private static final String STOP_BTT_TEXT = "Stop";

    private static final String POMODORO_BTT_TEXT = "Pomodoro";
    private static final String SHORT_BREAK_BTT_TEXT = "Short Break";
    private static final String LONG_BREAK_BTT_TEXT = "Long break";

    private static final Image START_IMAGE = new Image(FileLoader.getResourceAsStream("images/buttons/play.png"));
    private static final Image STOP_IMAGE = new Image(FileLoader.getResourceAsStream("images/buttons/stop.png"));
    private static final Image PAUSE_IMAGE = new Image(FileLoader.getResourceAsStream("images/buttons/pause.png"));

    private static final Image TOMATO_IMAGE = new Image(FileLoader.getResourceAsStream("images/buttons/tomato.png"));
    private static final Image CARROT_IMAGE = new Image(FileLoader.getResourceAsStream("images/buttons/carrot.png"));
    private static final Image CUCUMBER_IMAGE = new Image(FileLoader.getResourceAsStream("images/buttons/cucumber.png"));

    private MainPaneController mpc;

    private Button startBtt;
    private Button stopBtt;

    private ToggleButton pomodoroBtt;
    private ToggleButton shorBreakBtt;
    private ToggleButton longBreakBtt;

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
                    startBtt.setGraphic(getBttImageView(START_IMAGE));
                } else {
                    mpc.startTimer();
                    startBtt.setText(PAUSE_BTT_TEXT);
                    startBtt.setGraphic(getBttImageView(PAUSE_IMAGE));
                }

            }
        }, START_IMAGE,80);

        this.stopBtt = createBtt(STOP_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.stopTimer();
            }
        }, STOP_IMAGE,80);


        HBox bottomButtons = createBttsPane();
        bottomButtons.getChildren().addAll(startBtt, stopBtt);

        this.timerLabel = new TimerLabel();
        this.timerLabel.setTime(mpc.getTimer().getCurrentTime());


        ToggleGroup group = new PersistentButtonToggleGroup();


        this.pomodoroBtt = createToggleBtt(POMODORO_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.switchToPomodoro();
                mpc.startTimer();
            }
        }, TOMATO_IMAGE, 110, group);
        pomodoroBtt.setSelected(true);

        this.shorBreakBtt = createToggleBtt(SHORT_BREAK_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.switchToShortBreak();
                mpc.startTimer();
            }
        }, CUCUMBER_IMAGE, 110, group);

        this.longBreakBtt = createToggleBtt(LONG_BREAK_BTT_TEXT, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mpc.switchToLongBreak();
                mpc.startTimer();
            }
        }, CARROT_IMAGE, 110, group);

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


    private Button createBtt(String name, EventHandler<ActionEvent> event, Image image) {
        Button btt = new Button(name, getBttImageView(image));
        btt.setOnAction(event);
        return btt;
    }

    private Button createBtt(String name, EventHandler<ActionEvent> event, Image image, int width) {
        Button btt = createBtt(name, event, image);
        btt.setMaxWidth(width);
        btt.setMinWidth(width);
        return btt;
    }

    public ImageView getBttImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        return imageView;
    }


    private HBox createBttsPane() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: rgb(223, 75, 75);");
        return hbox;
    }

    public ToggleButton createToggleBtt(String name, EventHandler<ActionEvent> event, Image image, int width, ToggleGroup group) {
        ToggleButton tbtt = new ToggleButton(name, getBttImageView(image));
        tbtt.setOnAction(event);
        tbtt.setMaxWidth(width);
        tbtt.setMinWidth(width);
        tbtt.setToggleGroup(group);
        return tbtt;
    }
}
