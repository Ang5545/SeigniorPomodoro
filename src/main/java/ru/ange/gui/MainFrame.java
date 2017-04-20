package ru.ange.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fedor-m on 4/20/17.
 */
public class MainFrame extends JFrame {

    public static final String FRAME_NAME = "Seignior Pomodoro";

    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 300;

    public MainFrame() {
        super(FRAME_NAME);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        this.initComponenets();
    }

    public void showFrame() {
        this.setVisible(true);
    }

    public void hideFrame() {
        this.setVisible(false);
    }

    private void initComponenets() {
        TimerPanel tp = new TimerPanel();
        this.add(tp);
    }
}
