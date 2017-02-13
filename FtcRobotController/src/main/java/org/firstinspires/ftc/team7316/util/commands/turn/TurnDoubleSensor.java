package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 2/10/17.
 */
public class TurnDoubleSensor implements Loopable {

    public enum Direction {
        LEFT, RIGHT
    }

    enum State {
        DRIVING, TURNING_RIGHT, TURNING_LEFT, DONE
    }

    private DcMotor leftM, rightM;
    private OpticalDistanceSensor front, back;
    private State state = State.DRIVING;
    private int cycles = 0;
    private Direction direction;

    private static final double DRIVING_POWER = Constants.DRIVER_MOTOR_DEADZONE;
    private static final double WANTED_THRESHOLD = 0.02;
    private static int MAX_CYCLES = 3;
    private static final double TURN_PERCENT_BOOST = 1.65;
    private static final double DRIVING_BIAS = 0.5;
    private static final double MIN_LIGHT = 0.08;

    public TurnDoubleSensor(DcMotor leftM, DcMotor rightM, OpticalDistanceSensor front, OpticalDistanceSensor back, Direction direction) {
        this.leftM = leftM;
        this.rightM = rightM;
        this.front = front;
        this.back = back;
        this.direction = direction;
    }

    public TurnDoubleSensor(DcMotor leftM, DcMotor rightM, OpticalDistanceSensor front, OpticalDistanceSensor back, Direction direction, int cycles) {
        this.leftM = leftM;
        this.rightM = rightM;
        this.front = front;
        this.back = back;
        this.direction = direction;
        MAX_CYCLES = cycles;
    }

    @Override
    public void init() {
        this.state = State.DRIVING;
        this.cycles = 0;
        this.front.enableLed(true);
        this.back.enableLed(true);
    }

    @Override
    public void loop() {
        Hardware.log("front", front.getLightDetected());
        Hardware.log("back", back.getLightDetected());
        Hardware.log("cycles", cycles);
        Hardware.log("state", state.toString());

        switch (state) {

            case DRIVING:
                this.leftM.setPower(DRIVING_POWER);
                this.rightM.setPower(DRIVING_POWER);

                if (Math.abs(this.back.getLightDetected()) > Constants.WANTED_LIGHT) {
                    this.leftM.setPower(0);
                    this.rightM.setPower(0);
                    this.state = State.TURNING_RIGHT;
                    cycles++;
                }

                break;

            case TURNING_RIGHT:

                if (this.direction == Direction.LEFT) {
                    this.leftM.setPower(-DRIVING_POWER * TURN_PERCENT_BOOST);
                    this.rightM.setPower(DRIVING_POWER * TURN_PERCENT_BOOST);
                } else if (this.direction == Direction.RIGHT) {
                    this.leftM.setPower(DRIVING_POWER * TURN_PERCENT_BOOST);
                    this.rightM.setPower(-DRIVING_POWER * TURN_PERCENT_BOOST);
                }

                if (cycles > MAX_CYCLES || (Math.abs(this.front.getLightDetected()) > MIN_LIGHT && Math.abs(this.back.getLightDetected()) > MIN_LIGHT)) {
                    this.state = State.DONE;
                } else if (Math.abs(this.front.getLightDetected()) > Constants.WANTED_LIGHT) {
                    this.leftM.setPower(0);
                    this.rightM.setPower(0);
                    this.state = State.TURNING_LEFT;
                    cycles++;

                }

                /*if (Math.abs(this.averageError()) < WANTED_THRESHOLD || this.cycles > MAX_CYCLES) {
                    this.state = State.DONE;
                } else {
                    this.state = State.DRIVING;
                }*/

                break;

            case TURNING_LEFT:
                if (this.direction == Direction.LEFT) {
                    this.leftM.setPower(DRIVING_POWER * TURN_PERCENT_BOOST);
                    this.rightM.setPower(-DRIVING_POWER * TURN_PERCENT_BOOST);
                } else if (this.direction == Direction.RIGHT) {
                    this.leftM.setPower(-DRIVING_POWER * TURN_PERCENT_BOOST);
                    this.rightM.setPower(DRIVING_POWER * TURN_PERCENT_BOOST);
                }

                if (cycles > MAX_CYCLES || (Math.abs(this.front.getLightDetected()) > MIN_LIGHT && Math.abs(this.back.getLightDetected()) > MIN_LIGHT)) {
                    this.state = State.DONE;
                } else if (Math.abs(this.back.getLightDetected()) > Constants.WANTED_LIGHT) {
                    this.leftM.setPower(0);
                    this.rightM.setPower(0);
                    this.state = State.TURNING_RIGHT;
                    this.cycles++;

                }

                /*if (Math.abs(this.averageError()) < WANTED_THRESHOLD || this.cycles > MAX_CYCLES) {
                    this.state = State.DONE;
                } else {
                    this.state = State.DRIVING;
                }*/

                break;

            default:
                Hardware.memel0rd();
                break;
        }
    }

    @Override
    public boolean shouldRemove() {
        return this.state == State.DONE;
    }

    @Override
    public void terminate() {
        leftM.setPower(0);
        rightM.setPower(0);
    }

    public double frontError() {
        return front.getLightDetected() - Constants.WANTED_LIGHT;
    }

    public double backError() {
        return back.getLightDetected() - Constants.WANTED_LIGHT;
    }

    public double averageError() {
        return (1-DRIVING_BIAS)*frontError() + DRIVING_BIAS*backError();
    }
}
