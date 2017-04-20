package ru.ange.gui;

import javax.swing.*;

/**
 * Created by fedor-m on 4/20/17.
 */
public class TimerPanel extends JPanel {

    private JButton startTimer;
    private JButton stopTimer;

    private static final String START_BTT_NAME = "Start";
    private static final String STOPP_BTT_NAME = "Stop";

    public TimerPanel() {
        super();
        this.startTimer = new JButton(START_BTT_NAME);
        this.stopTimer = new JButton(STOPP_BTT_NAME);

        this.add(startTimer);
        this.add(stopTimer);
    }

}
