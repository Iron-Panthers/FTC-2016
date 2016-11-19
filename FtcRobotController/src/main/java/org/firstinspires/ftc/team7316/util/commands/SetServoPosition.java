package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;

/**
 * Created by andrew on 11/19/16.
 */
public class SetServoPosition implements Loopable {

    private double wantedPower;
    private Servo servo;

    public SetServoPosition(Servo servo, double wantedPower) {
        this.wantedPower = wantedPower;
        this.servo = servo;
    }

    @Override
    public void init() {
        servo.setPosition(this.wantedPower); //just in case
    }

    @Override
    public void loop() {
        servo.setPosition(this.wantedPower);
    }

    @Override
    public boolean shouldRemove() {
        return true;
    }

    @Override
    public void terminate() {

    }
}
