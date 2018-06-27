package ru.ange.utils;

import fr.jcgay.notification.Application;
import fr.jcgay.notification.Icon;
import fr.jcgay.notification.Notifier;
import fr.jcgay.notification.SendNotification;
import ru.ange.conf.Path;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class Notificator {

    public static final String END_ALARM_FILE_NAME = "ringtone.wav";

    private File endAlarm;
    private Notifier notifier;

    public Notificator() {
        this.endAlarm = FileLoader.getResourceFile(Path.RESOURCES_AUDIO_DIR + END_ALARM_FILE_NAME);

        URL icon = FileLoader.getResourceURL("images/dialog-clean.png");

        Application application = Application.builder()
                .id("notify-send-example")
                .name("Notify Send Example")
                .icon(Icon.create(icon, "app"))
                .timeout(TimeUnit.SECONDS.toMillis(3))
                .build();


        this.notifier = new SendNotification()
                .setApplication(application)
                //.setChosenNotifier("notifysend")
                .initNotifier();
    }

    public void notice(String title, String msg) {
        // -- show notification --
//        Notification not = new Notification(title, msg, Notification.INFO_ICON);
//        Notifier.INSTANCE.notify(not);

//        URL icoUrl = FileLoader.getResourceURL("images/buttons/carrot.png");
//        System.out.println("icoUrl = " + icoUrl);
//        Notification notification = Notification.builder()
//                .title(title)
//                .message(msg)
//                .icon(Icon.create(icoUrl, "ok"))
//                .level(Notification.Level.INFO)
//                .build();
//
//        notifier.send(notification);

        NotificationFacade nf = new NotificationFacade();
        nf.notoficate();

        // -- play sound --
        this.alarm(endAlarm);
    }

    public void alarm(File sound) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(sound);
            AudioFormat format = stream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
