package ru.ange.model.timer;

public class LongBreakTimer extends Timer {

    public static final int TIME = 60 * 15;

    public static final String END_MSG_TITLE = "Перерыв окончен";

    public static final String[] END_MSGS = {
            "Перерыв окончен",
            "Пора возвращаться к работе",
            "Ну вот отдохнули, пора и поработать",
            "Вы помните, что это всего лишь перерыв?",
            "Возобновите работу"
    };

    public LongBreakTimer() {
        super(TIME, END_MSG_TITLE, END_MSGS[0 + (int) (Math.random() * ((END_MSGS.length - 0) + 1))]);
    }

}
