package ru.ange.model.timer;

import java.util.List;
import java.util.Random;

public class PomodoroTimer extends Timer {

    public static final int TIME = 60 * 25;

    public static final String END_MSG_TITLE = "Время вышло";

    public static final String[] END_MSGS = {
            "Пора сделать перерыв",
            "Работа не волк",
            "Надо немного отдохнуть",
            "Отдохните",
            "Пока хватит работы",
            "Необходимо преваться",
            "Настало время востановить силы",
    };


    public PomodoroTimer() {
        super(TIME, END_MSG_TITLE, END_MSGS[0 + (int) (Math.random() * ((END_MSGS.length - 0) + 1))]);
    }

}
