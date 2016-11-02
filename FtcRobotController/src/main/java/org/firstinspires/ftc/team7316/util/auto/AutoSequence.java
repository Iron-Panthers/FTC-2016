package org.firstinspires.ftc.team7316.util.auto;

import org.firstinspires.ftc.team7316.util.Loopable;

import java.util.ArrayList;

/**
 * Created by andrew on 10/28/16.
 */
public class AutoSequence implements Loopable {
    private Loopable[] cmds;
    private int index = 0;

    public void addSequential(Loopable[] cmds) {
        this.cmds = cmds;
    }

    @Override
    public void init() {
        cmds[0].init();
    }

    @Override
    public void loop() {
        cmds[index].loop();

        if (cmds[index].shouldRemove()) {
            cmds[index].terminate();
            index++;
            if (index < cmds.length) {
                cmds[index].init();
            }
        }
    }

    @Override
    public boolean shouldRemove() {
        return index >= cmds.length;
    }

    @Override
    public void terminate() {

    }
}
