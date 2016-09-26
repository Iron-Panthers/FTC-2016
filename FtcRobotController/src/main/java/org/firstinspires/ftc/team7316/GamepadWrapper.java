package org.firstinspires.ftc.team7316;

import android.widget.Button;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by andrew on 9/15/16.
 */
public class GamepadWrapper {

    private Gamepad gamepad;

    public AxisWrapper left_stick_y;
    public AxisWrapper right_stick_y;

    public ToggleButtonWrapper aButtonWrapper;
    public ButtonWrapper bButtonWrapper, xButtonWrapper, yButtonWrapper;
    public AxisThresholdWrapper leftTriggerWrapper;

    public GamepadWrapper(Gamepad gamepad) {
        this.gamepad = gamepad;

        this.left_stick_y = new AxisWrapper(GamepadAxis.L_STICK_Y, this);
        this.right_stick_y = new AxisWrapper(GamepadAxis.R_STICK_Y, this);
        this.aButtonWrapper = new ToggleButtonWrapper(GamepadButton.A_BUTTON, this);
        this.bButtonWrapper = new ButtonWrapper(GamepadButton.B_BUTTON, this);
        this.xButtonWrapper = new ButtonWrapper(GamepadButton.X_BUTTON, this);
        this.yButtonWrapper = new ButtonWrapper(GamepadButton.Y_BUTTON, this);
        this.leftTriggerWrapper = new AxisThresholdWrapper(GamepadAxis.L_TRIGGER, this);
    }

    public Boolean buttonState(GamepadButton buttonIndex) {
        if (buttonIndex == GamepadButton.A_BUTTON) {
            return gamepad.a;
        } else if (buttonIndex == GamepadButton.B_BUTTON) {
            return gamepad.b;
        } else if (buttonIndex == GamepadButton.X_BUTTON) {
            return gamepad.x;
        } else if (buttonIndex == GamepadButton.Y_BUTTON) {
            return gamepad.y;
        } else if (buttonIndex == GamepadButton.R_BUMPER) {
            return gamepad.right_bumper;
        } else if (buttonIndex == GamepadButton.L_BUMPER) {
            return gamepad.left_bumper;
        } else {
            throw new IllegalAccessError();
        }
    }

    public float axisValue(GamepadAxis axisIndex) {
        if (axisIndex == GamepadAxis.L_STICK_X) {
            return gamepad.left_stick_x;
        } else if (axisIndex == GamepadAxis.L_STICK_Y) {
            return gamepad.left_stick_y;
        } else if (axisIndex == GamepadAxis.R_STICK_X) {
            return gamepad.right_stick_x;
        } else if (axisIndex == GamepadAxis.R_STICK_Y) {
            return gamepad.right_stick_y;
        } else if (axisIndex == GamepadAxis.R_TRIGGER) {
            return gamepad.right_trigger;
        } else if (axisIndex == GamepadAxis.L_TRIGGER) {
            return gamepad.left_trigger;
        } else {
            throw new IllegalAccessError();
        }
    }

}
