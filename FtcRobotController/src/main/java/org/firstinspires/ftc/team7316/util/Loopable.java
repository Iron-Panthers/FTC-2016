package org.firstinspires.ftc.team7316.util;

/**
 * Created by andrew on 9/15/16.
 */
public interface Loopable {

    void init();
    void loop();
    boolean shouldRemove();
    void terminate();

}
