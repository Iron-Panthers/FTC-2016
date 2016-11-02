package org.firstinspires.ftc.team7316.util.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 11/1/16.
 */
public class SetBearingGyro extends TurnGyro {
    private GyroSensor gyro;
    private float wantedBearing;

    public SetBearingGyro(float wantedBearing, double power, DcMotor leftMotor, DcMotor rightMotor, GyroSensor gyro) {
        super(0, power, leftMotor, rightMotor, gyro); //deltaBearing will be set in the init() function
        this.gyro = gyro;
        this.wantedBearing = wantedBearing;
    }

    @Override
    public void init() {
        this.startBearing = this.gyro.getHeading();
        float deltaBearing = this.wantedBearing - this.startBearing;
        /*
        if wanted is 20 and current is 10 then:
            delta = 10
        therefore
            rotate 10 degrees cw

        if wanted is 10 and current is 20 then:
            delta = -10
        therefore
            rotate 10 degrees ccw

        if wanted is 10 and current is 350 then:
            delta = -340
        therefore
            rotate 20 degrees cw

        if wanted is 350 and current is 10 then:
            delta = 340
        therefore
            rotate 20 degrees ccw

        to compare deltas, first take the absolute value of them.
        the rule is that deltas under 180 are correct, but ccw if the original delta was negative.
        if the delta is more than 180, do 360 - delta, but ccw if the original delta was positive.
         */
        if (Math.abs(deltaBearing) < 180) {
            this.deltaBearing = Math.abs(deltaBearing);
            if (deltaBearing < 0) {
                this.power *= -1;
            }

        } else {
            this.deltaBearing = 360 - Math.abs(deltaBearing);
            if (deltaBearing > 0) {
                this.power *= -1;
            }
        }
    }

}
