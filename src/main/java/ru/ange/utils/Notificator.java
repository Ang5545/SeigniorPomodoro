package ru.ange.utils;

import eu.hansolo.enzo.notification.Notification;
import eu.hansolo.enzo.notification.Notification.Notifier;
import ru.ange.conf.Path;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Notificator {

    public static final String END_ALARM_FILE_NAME = "ringtone.wav";

    private File endAlarm;

    public Notificator() {
        this.endAlarm = FileLoader.getFile(Path.RESOURCES_AUDIO_DIR + END_ALARM_FILE_NAME);
    }

    public void notice(String title, String msg) {

        // -- show notification --
        Notification not = new Notification(title, msg);
        Notifier.INSTANCE.notify(not);

        // -- play sound --
        this.alarm(endAlarm);

    }

    public Clip alarm(File sound) {
        try {
//            String path = new File("").getAbsolutePath() + END_ALARM_PATH;
//            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
//            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
//            Clip clip = (Clip) AudioSystem.getLine(info);
//            clip.open(ais);
//            clip.start();
//
//
//            File end
            AudioInputStream stream = AudioSystem.getAudioInputStream(endAlarm);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

            return clip;

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            return null;
        }
    }

}
