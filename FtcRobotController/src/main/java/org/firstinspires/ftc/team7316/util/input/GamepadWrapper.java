package org.firstinspires.ftc.team7316.util.input;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Created by andrew on 9/15/16.
 */
public class GamepadWrapper {

    private Gamepad gamepad;

    public JoystickWrapper left_stick, right_stick;
    public AxisWrapper left_axis_y, right_axis_y;
    public AxisWrapper r_trigger;

    public ButtonWrapper a_button, b_button, x_button, y_button;
    public ButtonWrapper dp_up, dp_down, dp_right, dp_left;
    public ButtonWrapper left_bumper, right_bumper;
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

        this.dp_left = new ButtonWrapper(GamepadButton.DPAD_LEFT, this);
        this.dp_right = new ButtonWrapper(GamepadButton.DPAD_RIGHT, this);
        this.dp_down = new ButtonWrapper(GamepadButton.DPAD_DOWN, this);
        this.dp_up = new ButtonWrapper(GamepadButton.DPAD_UP, this);

        this.left_bumper = new ButtonWrapper(GamepadButton.L_BUMPER, this);
        this.right_bumper = new ButtonWrapper(GamepadButton.R_BUMPER, this);

        this.leftTriggerWrapper = new TriggerWrapper(GamepadAxis.L_TRIGGER, this);
        this.rightTriggerWrapper = new TriggerWrapper(GamepadAxis.R_TRIGGER, this);

        this.r_trigger = new AxisWrapper(GamepadAxis.L_TRIGGER, this);

        Scheduler.instance.addTask(a_button);
        Scheduler.instance.addTask(b_button);
        Scheduler.instance.addTask(x_button);
        Scheduler.instance.addTask(y_button);
        Scheduler.instance.addTask(dp_left);
        Scheduler.instance.addTask(dp_right);
        Scheduler.instance.addTask(dp_down);
        Scheduler.instance.addTask(dp_up);
        Scheduler.instance.addTask(left_bumper);
        Scheduler.instance.addTask(right_bumper);
        Scheduler.instance.addTask(leftTriggerWrapper);
        Scheduler.instance.addTask(rightTriggerWrapper);
    }

    public boolean buttonState(GamepadButton buttonIndex) {
        switch (buttonIndex) {
            case A_BUTTON: return gamepad.a;
            case B_BUTTON: return gamepad.b;
            case X_BUTTON: return gamepad.x;
            case Y_BUTTON: return gamepad.y;
            case DPAD_UP: return gamepad.dpad_up;
            case DPAD_DOWN: return gamepad.dpad_down;
            case DPAD_LEFT: return gamepad.dpad_left;
            case DPAD_RIGHT: return gamepad.dpad_right;
            case R_BUMPER: return gamepad.right_bumper;
            case L_BUMPER: return gamepad.left_bumper;
        }
        throw new IllegalArgumentException();
    }

    public float axisValue(GamepadAxis axisIndex) {
        switch (axisIndex) {
            case L_STICK_X: return gamepad.left_stick_x;
            case L_STICK_Y: return squared(-gamepad.left_stick_y);
            case R_STICK_X: return gamepad.right_stick_x;
            case R_STICK_Y: return squared(-gamepad.right_stick_y);
            case L_TRIGGER: return gamepad.left_trigger;
            case R_TRIGGER: return gamepad.left_trigger;
        }
        throw new IllegalArgumentException();
    }

    private float squared(float value) {
        float result = (float) Math.abs(Math.pow(value, 2));
        return value > 0 ? result : -result;
    }

}