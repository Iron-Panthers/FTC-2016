package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 2/10/17.
 */
public class TurnDoubleSensor implements Loopable {

    private DcMotor leftM, rightM;

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
        leftM.setPower(0);
        rightM.setPower(0);
    }
}
