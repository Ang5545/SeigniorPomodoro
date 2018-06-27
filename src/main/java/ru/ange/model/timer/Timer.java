package ru.ange.model.timer;

public class Timer {

    private String endMsg;
    private String endMsgDesc;

    private int defTime;
    private int currentTime;
    private boolean run;

    public Timer(int sec, String endMsg, String endMsgDesc) {
        this.defTime = sec;
        this.currentTime = sec;
        this.endMsg = endMsg;
        this.endMsgDesc = endMsgDesc;
        this.run = false;
    }

    public void reset() {
        this.currentTime = this.defTime;
        this.run = false;
    }
    public int getDefTime() {
        return defTime;
    }

    public void setDefTime(int defTime) {
        this.defTime = defTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public void stepReduce() {
        this.currentTime--;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public String getEndMsg() {
        return endMsg;
    }

    public void setEndMsg(String endMsg) {
        this.endMsg = endMsg;
    }

    public String getEndMsgDesc() {
        return endMsgDesc;
    }

    public void setEndMsgDesc(String endMsgDesc) {
        this.endMsgDesc = endMsgDesc;
    }
}
