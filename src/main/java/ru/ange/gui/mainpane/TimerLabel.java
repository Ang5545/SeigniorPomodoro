package ru.ange.gui.mainpane;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class TimerLabel extends Label {

    private static final int UUR = 3600;
    private static final int MIN = 60;

    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE = 60;

    private static final String TIME_PATT = "%s:%s";

    public TimerLabel() {
        super("00:00");
        this.setFont(new Font(FONT_NAME, FONT_SIZE));
    }

    public void setTime (int sec) {
        int uren = sec / UUR;
        int mm = (sec - uren * UUR) / MIN;
        int ss = sec - uren * UUR - mm * MIN;

        this.setText(String.format(TIME_PATT, getStrNumber(mm), getStrNumber(ss)));
    }

    private String getStrNumber(int val) {
        if (val >= 10) {
            return String.valueOf(val);
        } else {
            return "0" + String.valueOf(val);
        }
    }

}
