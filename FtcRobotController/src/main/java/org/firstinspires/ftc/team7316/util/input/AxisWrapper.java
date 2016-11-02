package org.firstinspires.ftc.team7316.util.input;

/**
 * A wrapper for a single joystick axis.
 */
public class AxisWrapper {

    private GamepadAxis inputName;
    private GamepadWrapper gpWrapper;

    public AxisWrapper (GamepadAxis inputName, GamepadWrapper gpWrapper) {
        this.inputName = inputName;
        this.gpWrapper = gpWrapper;
    }

    public float value() {
        return gpWrapper.axisValue(inputName);
    }

    public String name() {
        return String.valueOf(this.inputName);
    }
}
