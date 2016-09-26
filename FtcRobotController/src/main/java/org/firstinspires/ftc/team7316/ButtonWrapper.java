package org.firstinspires.ftc.team7316;

/**
 * Created by andrew on 9/15/16.
 */
public class ButtonWrapper {

    private GamepadButton gamepadInput;
    private GamepadWrapper gpSource;

    public ButtonWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        this.gamepadInput = gamepadInput;
        this.gpSource  = gpSource;
    }

    public boolean state() {
        return gpSource.buttonState(gamepadInput);
    }

}
