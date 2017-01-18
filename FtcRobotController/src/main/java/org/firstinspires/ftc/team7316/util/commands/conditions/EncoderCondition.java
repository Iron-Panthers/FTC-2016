package org.firstinspires.ftc.team7316.util.commands.conditions;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Will return true if the encoder's current position is greater than (or less than) the given value
 */
public class EncoderCondition implements Conditional {

    private DcMotor encoder;
    private int position;
    private boolean greaterThan;

    public EncoderCondition(DcMotor encoder, int position, boolean greaterThan) {
        this.encoder = encoder;
        this.position = position;
        this.greaterThan = greaterThan;
    }

    @Override
    public boolean state() {
        if (greaterThan) {
            return encoder.getCurrentPosition() > position;
        }
        return encoder.getCurrentPosition() < position;
    }
}
