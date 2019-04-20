
package ui.pushNotification;

import util.Interface.Sound_Path;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class Notification {

    public static int type = -1;

    public static int SUCCESS = 0;
    public static int FAILURE = 1;
    public static int WARNING = 2;
    public static int INFORM = 3;

    
    public static void PlaySound(String SoundName) {
	new MediaPlayer(new Media(new File(SoundName).toURI().toString())).play();
    }
    
    
    public static void push(String title, String details, int notifyType) {
        push(title, details, notifyType, Pos.BOTTOM_RIGHT);
    }

    public static void push(String title, String details, int notifyType,Pos pos) {
        if (notifyType == SUCCESS) {
            PlaySound(Sound_Path.CONFIRM);
            Notifications.create().darkStyle().title(title).position(pos)
                    .text(details).hideAfter(Duration.seconds(5)).showConfirm();
        } else if (notifyType == FAILURE) {
            PlaySound(Sound_Path.ERROR);
            Notifications.create().darkStyle().title(title).position(pos)
                    .text(details).hideAfter(Duration.seconds(5)).showError();
        } else if (notifyType == WARNING) {
            PlaySound(Sound_Path.ERROR);
            Notifications.create().darkStyle().title(title).position(pos)
                    .text(details).hideAfter(Duration.seconds(5)).showWarning();
        } else {
            PlaySound(Sound_Path.ERROR);
            Notifications.create().darkStyle().title(title).position(pos)
                    .text(details).hideAfter(Duration.seconds(5)).showInformation();
        }
    }

}
