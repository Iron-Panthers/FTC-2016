package org.firstinspires.ftc.team7316.util.commands.conditions;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Maxim on 11/18/2016.
 */
public class ServoPositionConditional implements Conditional {

    private Servo servo;
    private double threshold;
    private boolean wantedLess;

    public ServoPositionConditional(Servo servo, double threshold, boolean wantedLess) {
        this.servo = servo;
        this.threshold = threshold;
        this.wantedLess = wantedLess;
    }

    @Override
    public boolean shouldRemove() {
        if (this.wantedLess) {
            return servo.getPosition() <= this.threshold;
        } else {
            return servo.getPosition() >= this.threshold;
        }
    }
}
