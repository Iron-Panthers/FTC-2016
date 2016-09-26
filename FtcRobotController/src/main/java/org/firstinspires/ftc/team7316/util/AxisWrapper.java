package org.firstinspires.ftc.team7316.util;

import android.util.Log;

import org.firstinspires.ftc.team7316.Hardware;

/**
 * Created by andrew on 9/15/16.
 */
public class AxisWrapper {

    private GamepadAxis inputName;
    private GamepadWrapper gpWrapper;

    public AxisWrapper (GamepadAxis inputName, GamepadWrapper gpWrapper) {
        this.inputName = inputName;
        this.gpWrapper = gpWrapper;

        Log.d(Hardware.tag, "AxisWrapper created, " + this.inputName);
    }

    public float value() {
        return gpWrapper.axisValue(inputName);
    }

    public String name() {
        return String.valueOf(this.inputName);
    }
}
