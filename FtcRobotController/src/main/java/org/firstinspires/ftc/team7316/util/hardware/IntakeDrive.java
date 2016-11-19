package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.TwoButtonToggleWrapper;

/**
 * Created by Maxim on 11/18/2016.
 */
public class IntakeDrive extends DcMotorThreeStateWrapper {

    private Conditional canRun;

    /**
     * Create a new IntakeDrive object.
     * @param motor The motor to drive
     * @param forwardPower The power when in forward mode
     * @param neutralPower The power when in neutral mode
     * @param reversePower The power when in reverse mode
     * @param buttons The buttons to use
     * @param canRun If canRun.shouldRemove() is true, run, otherwise nothing will work
     */
    public IntakeDrive(DcMotor motor, double forwardPower, double neutralPower, double reversePower, TwoButtonToggleWrapper buttons, Conditional canRun) {
        super(motor, forwardPower, neutralPower, reversePower, buttons);
        this.canRun = canRun;
    }

    @Override
    public void loop() {
        if (canRun.shouldRemove()) {
            super.loop();
        } else {
            motor.setPower(neutralPower);
        }
    }


}
