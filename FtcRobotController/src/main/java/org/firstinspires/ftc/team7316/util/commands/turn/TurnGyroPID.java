package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Turn the robot a specific distance using PID. Stops when the correction speed is under a threshold and
 * the robot's distance from the correct angle is also under a threshold.
 */
public class TurnGyroPID implements Loopable {

    public static final float P = 0.015f, I = 0, D = 0;
    public static final float ERROR_THRESHOLD = 10, DELTA_THRESHOLD = 10;
    private int turnAngle;

    private DcMotor left, right;
    private GyroSensor gyro;
    private ElapsedTime timer;
    private int target;
    public float sumError, lastError, deltaError;

    /**
     *
     * @param left
     * @param right
     * @param gyro
     * @param turnAngle the amount to turn
     */
    public TurnGyroPID(DcMotor left, DcMotor right, GyroSensor gyro, int turnAngle) {
        this.left = left;
        this.right = right;
        this.gyro = gyro;
        this.turnAngle = turnAngle;
        this.timer = new ElapsedTime();
    }

    @Override
    public void init() {
        sumError = 0;
        lastError = error();
        deltaError = 0;
        timer.reset();
        target = gyro.getHeading() + turnAngle;
    }

    @Override
    public void loop() {
        float deltaTime = (float) timer.time();
        float error = error();
        deltaError = (error - lastError) / deltaTime;
        sumError += deltaError;

        double power = P*error + I*sumError + D*deltaError;
        left.setPower(-power);
        right.setPower(power);

        lastError = error;
        timer.reset();
    }

    @Override
    public boolean shouldRemove() {
        return Math.abs(error()) <= ERROR_THRESHOLD && Math.abs(deltaError) <= DELTA_THRESHOLD;
    }

    @Override
    public void terminate() {
        left.setPower(0);
        right.setPower(0);
    }

    /**
     * The angle distance from the targeted heading.
     * Positive angles mean to the right, while negative angles mean to the left.
     * @return the error
     */
        private int error() {
            return (gyro.getHeading() + 540 - target) % 360 - 180;
        }

}
