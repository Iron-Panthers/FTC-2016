package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.TwoButtonToggleWrapper;

/**
 * Created by Maxim on 11/18/2016.
 */
public class IntakeDrive extends DcMotorThreeStateWrapper {

    private Conditional canIntake;

    /**
     * Create a new IntakeDrive object.
     * @param motor The motor to drive
     * @param forwardPower The power when in forward mode
     * @param neutralPower The power when in neutral mode
     * @param reversePower The power when in reverse mode
     * @param buttons The buttons to use
     * @param canIntake If canRun.shouldRemove() is true, run, otherwise nothing will work
     */
    public IntakeDrive(DcMotor motor, double forwardPower, double neutralPower, double reversePower, TwoButtonToggleWrapper buttons, Conditional canIntake) {
        super(motor, forwardPower, neutralPower, reversePower, buttons);
        this.canIntake = canIntake;
    }

    @Override
    public void loop() {
        TwoButtonToggleWrapper.TwoButtonToggleState state = buttons.buttonsState();
        switch (state) {
            case NEUTRAL:
                motor.setPower(neutralPower);
                break;
            case FORWARD:
                if (canIntake.shouldRemove()) {
                    motor.setPower(neutralPower);
                } else {
                    motor.setPower(forwardPower);
                }
                break;
            case BACKWARD:
                motor.setPower(reversePower);
                break;
        }
    }


}
