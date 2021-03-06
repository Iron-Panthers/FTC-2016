package org.firstinspires.ftc.team7316.util.commands.flow;

import android.util.Log;

import com.qualcomm.ftccommon.DbgLog;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 10/28/16.
 */
public class CommandSequence implements Loopable {
    private Loopable[] cmds;
    private int index = 0;

    public CommandSequence(Loopable... cmds) {
        this.cmds = cmds;
    }

    @Override
    public void init() {
        index = 0;
        cmds[0].init();
    }

    @Override
    public void loop() {
        Loopable cmd = cmds[index];
        cmd.loop();
        Log.i(Hardware.tag, String.valueOf(cmd.getClass()));
        Log.i(Hardware.tag, String.valueOf(index));

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
