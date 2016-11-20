package org.firstinspires.ftc.team7316.util.commands.conditions;

import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by andrew on 11/17/16.
 */
public class ButtonCondition implements Conditional {
    private TouchSensor sensor;

    public ButtonCondition(TouchSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean shouldRemove() {
        return this.sensor.isPressed();
    }
}
