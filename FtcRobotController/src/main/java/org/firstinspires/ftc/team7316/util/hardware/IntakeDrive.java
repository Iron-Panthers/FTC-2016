package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.TwoButtonToggleWrapper;

/**
 * Created by Maxim on 11/18/2016.
 */
public class IntakeDrive extends DcMotorThreeStateWrapper {

    private Conditional catapultDown;
    private Conditional intakeDown;

    /**
     * Create a new IntakeDrive object.
     * @param motor The motor to drive
     * @param forwardPower The power when in forward mode
     * @param neutralPower The power when in neutral mode
     * @param reversePower The power when in reverse mode
     * @param buttons The buttons to use
     * @param catapultDown If catapultDown.shouldRemove() (and intake down) is true then we can intake
     * @param intakeDown If intakeDown.shouldRemove() is true then we can outtake
     */
    public IntakeDrive(DcMotor motor, double forwardPower, double neutralPower, double reversePower, TwoButtonToggleWrapper buttons, Conditional catapultDown, Conditional intakeDown) {
        super(motor, forwardPower, neutralPower, reversePower, buttons);
        this.catapultDown = catapultDown;
        this.intakeDown = intakeDown;
    }

    @Override
    public void loop() {
        TwoButtonToggleWrapper.TwoButtonToggleState state = buttons.buttonsState();
        switch (state) {
            case NEUTRAL:
                motor.setPower(neutralPower);
                break;
            case FORWARD:
                if (catapultDown.shouldRemove() && intakeDown.shouldRemove()) {
                    motor.setPower(forwardPower);
                } else {
                    motor.setPower(neutralPower);
                }
                break;
            case BACKWARD:
                if (intakeDown.shouldRemove()) {
                    motor.setPower(reversePower);
                } else {
                    motor.setPower(neutralPower);
                }
                break;
        }
    }


}
