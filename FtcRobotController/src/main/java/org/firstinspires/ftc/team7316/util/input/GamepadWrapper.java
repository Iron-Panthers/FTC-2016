package org.firstinspires.ftc.team7316.util.input;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by wayne on 9/15/16.
 */
public class GamepadWrapper implements Loopable {

    private Gamepad gamepad;

    private float multLeft = 1f;
    private float multRight = 1f;

    public JoystickWrapper left_stick, right_stick;
    public AxisWrapper left_axis_y, right_axis_y;
    public AxisWrapper r_trigger;

    public ButtonWrapper a_button, b_button, x_button, y_button;
    public ButtonWrapper dp_up, dp_down, dp_right;
    public ButtonWrapper left_bumper, right_bumper;
    public TriggerWrapper leftTriggerWrapper, rightTriggerWrapper;
    public ToggleButtonWrapper dpLeftWrapper;

    private static final double STICK_DEADZONE = 0.1;

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

        this.dpLeftWrapper = new ToggleButtonWrapper(GamepadButton.DPAD_LEFT, this);
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
        Scheduler.instance.addTask(dpLeftWrapper);
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
            case L_STICK_Y: return deadzone(-gamepad.left_stick_y, multLeft);
            case R_STICK_X: return gamepad.right_stick_x;
            case R_STICK_Y: return deadzone(-gamepad.right_stick_y, multRight);
            case L_TRIGGER: return gamepad.left_trigger;
            case R_TRIGGER: return gamepad.right_trigger;
        }
        throw new IllegalArgumentException();
    }

    private float squared(float value) {
        float result = (float) Math.abs(Math.pow(value, 2));
        return value > 0 ? result : -result;
    }

    private float deadzone(float value, float mult) {
        if (Math.abs(value) < Constants.JOYSTICK_DRIVE_DEADZONE) {
            return 0;
        } else {
            float slope = (1 - Constants.DRIVER_MOTOR_DEADZONE) / (1 - Constants.JOYSTICK_DRIVE_DEADZONE) * mult;
            float preval = slope * (Math.abs(value) - Constants.JOYSTICK_DRIVE_DEADZONE) + Constants.DRIVER_MOTOR_DEADZONE;

            if (value > 0) {
                return preval;
            } else {
                return -preval;
            }

        }
    }

    @Override
    public void init() {
        multLeft = 1;
        multRight = 1;
    }

    @Override
    public void loop() {
        if (this.right_bumper.state()) {
            multRight = 0.1f;
        } else {
            multRight = 1;
        }

        if (this.left_bumper.state()) {
            multLeft = 0.1f;
        } else {
            multLeft = 1;
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
    }
}
