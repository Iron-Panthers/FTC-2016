package org.firstinspires.ftc.team7316.util.input;

/**
 * A wrapper for a single joystick axis.
 */
public class AxisWrapper {

    private GamepadAxis inputName;
    private GamepadWrapper gpWrapper;
    private double power;

    public AxisWrapper(GamepadAxis inputName, GamepadWrapper gpWrapper, double power) {
        this.inputName = inputName;
        this.gpWrapper = gpWrapper;
        this.power = power;
    }

    public AxisWrapper(GamepadAxis inputName, GamepadWrapper gpWrapper) {
        this(inputName, gpWrapper, 1);
    }

    public float value() {
        float input = gpWrapper.axisValue(inputName);
        float value = (float) Math.abs(Math.pow(input, power));
        return input > 0 ? value : -value;
    }

    public String name() {
        return String.valueOf(this.inputName);
    }
}
