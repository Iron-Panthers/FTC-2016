package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper for a button.
 */
public class ButtonWrapper implements Loopable {

    private List<ButtonListener> listeners;
    private GamepadButton gamepadInput;
    private GamepadWrapper gpSource;

    boolean lastValue; // for detecting rising/falling edges

    public ButtonWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        this.gamepadInput = gamepadInput;
        this.gpSource  = gpSource;
        this.listeners = new ArrayList<>();
        Scheduler.instance.addTask(this);
    }

    public void addListener(ButtonListener listener) {
        this.listeners.add(listener);
    }

    public boolean isPressed() {
        return gpSource.buttonState(gamepadInput);
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        boolean currentValue = isPressed();
        if (currentValue && !lastValue) { // Rising edge
            for (ButtonListener listener: listeners) {
                listener.onPressed();
            }
        } else if (!currentValue && lastValue) { // Falling edge
            for (ButtonListener listener: listeners) {
                listener.onReleased();
            }
        }
        lastValue = currentValue;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
