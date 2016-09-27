package org.firstinspires.ftc.team7316.util.input;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by andrew on 9/15/16.
 */
public class GamepadWrapper {

    private Gamepad gamepad;

    public JoystickWrapper left_stick, right_stick;
    public AxisWrapper left_axis_y, right_axis_y;

    public ButtonWrapper a_button, b_button, x_button, y_button;
    public TriggerWrapper leftTriggerWrapper, rightTriggerWrapper;

    public GamepadWrapper(Gamepad gamepad) {
        this.gamepad = gamepad;

        this.left_stick = new JoystickWrapper(JoystickWrapper.Joystick.LEFT, this);
        this.right_stick = new JoystickWrapper(JoystickWrapper.Joystick.RIGHT, this);
        this.left_axis_y = new AxisWrapper(GamepadAxis.L_STICK_Y, this);
        this.right_axis_y = new AxisWrapper(GamepadAxis.R_STICK_Y, this);

        this.a_button = new ButtonWrapper(GamepadButton.A_BUTTON, this);
        this.b_button = new ButtonWrapper(GamepadButton.B_BUTTON, this);
        this.x_button = new ButtonWrapper(GamepadButton.X_BUTTON, this);
        this.y_button = new ButtonWrapper(GamepadButton.Y_BUTTON, this);
        this.leftTriggerWrapper = new TriggerWrapper(GamepadAxis.L_TRIGGER, this);
        this.rightTriggerWrapper = new TriggerWrapper(GamepadAxis.R_TRIGGER, this);
    }

    public boolean buttonState(GamepadButton buttonIndex) {
        switch (buttonIndex) {
            case A_BUTTON: return gamepad.a;
            case B_BUTTON: return gamepad.b;
            case X_BUTTON: return gamepad.x;
            case Y_BUTTON: return gamepad.y;
            case R_BUMPER: return gamepad.right_bumper;
            case L_BUMPER: return gamepad.left_bumper;
        }
        throw new IllegalArgumentException();
    }

    public float axisValue(GamepadAxis axisIndex) {
        switch (axisIndex) {
            case L_STICK_X: return gamepad.left_stick_x;
            case R_STICK_X: return gamepad.left_stick_y;
            case L_STICK_Y: return gamepad.right_stick_x;
            case R_STICK_Y: return gamepad.right_stick_y;
            case L_TRIGGER: return gamepad.left_trigger;
            case R_TRIGGER: return gamepad.left_trigger;
        }
        throw new IllegalArgumentException();
    }

}
