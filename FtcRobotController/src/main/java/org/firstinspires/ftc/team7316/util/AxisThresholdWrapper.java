package org.firstinspires.ftc.team7316.util;

/**
 * Created by andrew on 9/16/16.
 */
public class AxisThresholdWrapper extends ButtonWrapper {

    private boolean value = false;
    private AxisWrapper axisWrapper;
    private static final float threshold = (float) 0.8;

    public AxisThresholdWrapper(GamepadAxis inputName, GamepadWrapper gpWrapper) {
        super(null, gpWrapper);
        this.axisWrapper = new AxisWrapper(inputName, gpWrapper);
    }

    @Override
    public boolean state() {
        if (this.axisWrapper.value() > threshold) {
            return true;
        } else {
            return false;
        }
    }
}
