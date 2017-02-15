package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistanceLameOrConditional;
import org.firstinspires.ftc.team7316.modes.CommandAuto;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.conditions.ButtonCondition;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.commands.conditions.MultipleCondition;
import org.firstinspires.ftc.team7316.util.commands.conditions.OpticalDistanceSensorThreshold;
import org.firstinspires.ftc.team7316.util.commands.conditions.ServoPositionConditional;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistance;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistanceAccurate;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistanceOrCondition;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveUntilCondition;
import org.firstinspires.ftc.team7316.util.commands.drive.LineFollowUntilCondition;
import org.firstinspires.ftc.team7316.util.commands.drive.RunMotorForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.RunMotorUntilConditional;
import org.firstinspires.ftc.team7316.util.commands.flow.CommandSequence;
import org.firstinspires.ftc.team7316.util.commands.flow.SimultaneousCommands;
import org.firstinspires.ftc.team7316.util.commands.flow.Wait;
import org.firstinspires.ftc.team7316.util.commands.turn.LeftHoldTurnUntilConditional;
import org.firstinspires.ftc.team7316.util.commands.turn.RightHoldTurnUntilConditional;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnAccurate;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnDoubleSensor;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnGyro;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnUntilConditional;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.input.CapballDropper;

/**
 * Created by andrew on 11/2/16.
 * All the sequential commands to run
 */
public class AutoCodes {

    public static SimultaneousCommands robotDriveDistanceAccurate(double distance, double power) {
        DriveDistanceAccurate leftMotor = new DriveDistanceAccurate(Constants.distanceToTicks(distance), power, Hardware.instance.leftDriveMotor);
        DriveDistanceAccurate rightMotor = new DriveDistanceAccurate(Constants.distanceToTicks(distance), power, Hardware.instance.rightDriveMotor);
        Loopable[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }

    public static SimultaneousCommands robotDriveDistanceAccurateOrConditonal(double distance, double power, Conditional conditional) {
        DriveDistanceAccurate leftMotor = new DriveDistanceOrCondition(Constants.distanceToTicks(distance), power, Hardware.instance.leftDriveMotor, conditional);
        DriveDistanceAccurate rightMotor = new DriveDistanceOrCondition(Constants.distanceToTicks(distance), power, Hardware.instance.rightDriveMotor, conditional);
        Loopable[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }

    public static SimultaneousCommands robotDriveDistanceOrConditonal(double distance, double power, Conditional conditional) {
        DriveDistanceLameOrConditional leftMotor = new DriveDistanceLameOrConditional((float) Constants.distanceToTicks(distance), power, Hardware.instance.leftDriveMotor, conditional);
        DriveDistanceLameOrConditional rightMotor = new DriveDistanceLameOrConditional((float) Constants.distanceToTicks(distance), power, Hardware.instance.rightDriveMotor, conditional);
        Loopable[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }

    public static Conditional servoPositionRange(Servo servo, double lowVal, double highVal) {
        Conditional lowerThreshold = new ServoPositionConditional(servo, lowVal, false);
        Conditional upperThreshold = new ServoPositionConditional(servo, highVal, true);

        Conditional both = new MultipleCondition(true, lowerThreshold, upperThreshold);
        return both;
    }

    public static SimultaneousCommands robotDriveTime(double time, double power) {
        RunMotorForTime leftMotor = new RunMotorForTime(Hardware.instance.leftDriveMotor, power, time);
        RunMotorForTime rightMotor = new RunMotorForTime(Hardware.instance.rightDriveMotor, power, time);
        Loopable[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }

    public static SimultaneousCommands robotDriveUntilCondition(Conditional conditional, double power) {
        DriveUntilCondition leftMotor = new DriveUntilCondition(power, Hardware.instance.leftDriveMotor, conditional);
        DriveUntilCondition rightMotor = new DriveUntilCondition(power, Hardware.instance.rightDriveMotor, conditional);
        Loopable[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }

    public static CommandSequence resetServos() {
        Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_DONT_STORE);
        Loopable setLeftServoPosition = new SetServoPosition(Hardware.instance.leftBeaconServo, Constants.LEFT_OFF);
        Loopable setRightServoPosition = new SetServoPosition(Hardware.instance.rightBeaconServo, Constants.RIGHT_OFF);
        Loopable setBeaconServoPosition = new SetServoPosition(Hardware.instance.beaconWheelServo, 0.35);

        Loopable[] cmds = {setServoPosition, setLeftServoPosition, setRightServoPosition};

        return new CommandSequence(cmds);
    }

    public static CommandSequence followLineThenBeacon(Alliance alliance) {
        Conditional buttonCondition = new ButtonCondition(Hardware.instance.touchSensor);
        Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensorLeft, 0.15, buttonCondition, alliance);

        Loopable wait = new Wait(Constants.COLOR_SENSOR_DELAY);

        Loopable pressBeacon = new PressBeacon(alliance, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo);

        Loopable[] cmds = {followLine, wait, pressBeacon};

        return new CommandSequence(cmds);
    }

    public static Loopable armCatapult() {
        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, Constants.CAP_THRESHOLD, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        return armCatapult;
    }

    public static CommandSequence closeBeaconFarStartBlue() {

        SimultaneousCommands driveToLine = AutoCodes.robotDriveTime(2.35, 0.5);

        Loopable turnToLine = new TurnGyro(40, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        Loopable[] cmds = {driveToLine, AutoCodes.resetServos(), AutoCodes.armCatapult(), turnToLine, AutoCodes.followLineThenBeacon(Alliance.BLUE)};

        return new CommandSequence(cmds);
    }

    public static CommandSequence closeBeaconFarStartRed() {

        SimultaneousCommands driveToLine = AutoCodes.robotDriveTime(2.35, 0.5);

        Loopable turnToLine = new TurnGyro(40, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        Loopable[] cmds = {driveToLine, AutoCodes.resetServos(), AutoCodes.armCatapult(), turnToLine, AutoCodes.followLineThenBeacon(Alliance.RED)};

        return new CommandSequence(cmds);
    }

    public static CommandSequence redBothBeaconsCloseStart() {

        SimultaneousCommands backwards = AutoCodes.robotDriveTime(0.55, -0.3);

        Loopable turnTowardsOtherLine = new TurnGyro(90, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        SimultaneousCommands driveToOtherLine = AutoCodes.robotDriveTime(2.45, 0.3);

        Loopable turnBack = new TurnGyro(100, -0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        Loopable[] cmds = {AutoCodes.closeBeaconFarStartRed(), backwards, turnTowardsOtherLine, driveToOtherLine, turnBack, AutoCodes.followLineThenBeacon(Alliance.RED)};

        return new CommandSequence(cmds);
    }

    public static CommandSequence blueBothBeaconsCloseStart() {

        SimultaneousCommands backwards = AutoCodes.robotDriveTime(0.55, -0.3);

        Loopable turnTowardsOtherLine = new TurnGyro(90, -0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        SimultaneousCommands driveToOtherLine = AutoCodes.robotDriveTime(2.45, 0.3);

        Loopable turnBack = new TurnGyro(100, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        Loopable[] cmds = {AutoCodes.closeBeaconFarStartBlue(), backwards, turnTowardsOtherLine, driveToOtherLine, turnBack, AutoCodes.followLineThenBeacon(Alliance.BLUE)};

        return new CommandSequence(cmds);
    }

    public static CommandSequence simpleShoot() {

        SimultaneousCommands driveToLine = AutoCodes.robotDriveTime(0.4, 0.3);

        Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);

        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, Constants.CAP_THRESHOLD, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        Loopable shootCatapult = new RunMotorForTime(Hardware.instance.catapultMotor, 1, 1);

        SimultaneousCommands driveToBall = AutoCodes.robotDriveTime(2.7, 0.5);

        Loopable[] cmds = {AutoCodes.resetServos(), driveToLine, setServoPosition, armCatapult, shootCatapult, driveToBall};

        return new CommandSequence(cmds);

    }

    /**
     * Release the intake, arm and fire, intake, and fire again.
     */
    public static CommandSequence doubleShoot() {

        Loopable setServoPosition1 = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);
        Loopable wait = new Wait(0.4);
        Loopable setServoPosition2 = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_DONT_STORE);

        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, Constants.CAP_THRESHOLD, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        Loopable shootCatapult = new RunMotorForTime(Hardware.instance.catapultMotor, 1, 1);

        Loopable runIntake = new RunMotorForTime(Hardware.instance.intakeMotor, Constants.INTAKE_IN_SPEED, 3);

        Loopable[] cmds = {AutoCodes.resetServos(), setServoPosition1, wait, setServoPosition2, wait, armCatapult, shootCatapult, armCatapult, runIntake, shootCatapult};

        return new CommandSequence(cmds);

    }

    public static CommandSequence doubleShootAndCap() {
        SimultaneousCommands driveToBall = AutoCodes.robotDriveDistanceAccurate(4, 0.9);

        return new CommandSequence(AutoCodes.doubleShoot(), driveToBall);
    }

    public static CommandSequence doubleShootAndCap(double time) {
        Wait waitToDrive = new Wait(time);
        SimultaneousCommands driveToBall = AutoCodes.robotDriveDistanceAccurate(4, 0.9);

        return new CommandSequence(AutoCodes.doubleShoot(), waitToDrive, driveToBall);
    }

    public static CommandSequence redDoubleShootDoubleBeacon() {
        //double shoot

        TurnGyroPID toLine = new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, -37);

        //double beacon blue

        SimultaneousCommands driveBackABit = AutoCodes.robotDriveTime(0.3, -0.5);
        TurnGyroPID pointButtTowardsCenter = new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, 40);
        SimultaneousCommands driveBackFast = AutoCodes.robotDriveDistanceAccurate(4.5, -1);

        Loopable[] cmds = {AutoCodes.doubleShootFull(), toLine, AutoCodes.redDoubleBeacon(), driveBackABit, pointButtTowardsCenter, driveBackFast};

        return new CommandSequence(cmds);
    }

    public static CommandSequence blueDoubleShootDoubleBeacon() {
        //double shoot

        TurnGyroPID toLine = new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, 36);

        //double beacon blue

        SimultaneousCommands driveBackABit = AutoCodes.robotDriveTime(0.3, -0.5);
        TurnGyroPID pointButtTowardsCenter = new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, -40);
        SimultaneousCommands driveBackFast = AutoCodes.robotDriveDistanceAccurate(4.5, -1);

        Loopable[] cmds = {AutoCodes.doubleShootFull(), toLine, AutoCodes.blueDoubleBeacon(), driveBackABit, pointButtTowardsCenter, driveBackFast};

        return new CommandSequence(cmds);
    }

    public static CommandSequence blueDoubleBeaconDoubleShoot(boolean cap) {
        //double blue beacon

        SimultaneousCommands backUp = AutoCodes.robotDriveDistanceAccurate(1, -0.25);
        Loopable turnTowardsCenter = new TurnAccurate(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, 123, 0.4);
        SimultaneousCommands moveTowardsCenter = AutoCodes.robotDriveDistanceAccurate(0.75, 0.25);

        //double shoot

        if (cap) {
            Loopable[] cmds = {AutoCodes.blueDoubleBeacon(), backUp, turnTowardsCenter, moveTowardsCenter, AutoCodes.doubleShootAndCap()};

            return new CommandSequence(cmds);
        } else {
            Loopable[] cmds = {AutoCodes.blueDoubleBeacon(), backUp, turnTowardsCenter, moveTowardsCenter, AutoCodes.doubleShoot()};

            return new CommandSequence(cmds);
        }

    }

    public static CommandSequence redDoubleBeaconDoubleShoot(boolean cap) {
        //double beacon red

        SimultaneousCommands backUp = AutoCodes.robotDriveDistanceAccurate(1, -0.25);
        Loopable turnTowardsCenter = new TurnAccurate(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, -125, 0.4);
        SimultaneousCommands moveTowardsCenter = AutoCodes.robotDriveDistanceAccurate(0.75, 0.25);

        //double shoot

        if (cap) {
            Loopable[] cmds = {AutoCodes.redDoubleBeacon(), backUp, turnTowardsCenter, moveTowardsCenter, AutoCodes.doubleShootAndCap()};

            return new CommandSequence(cmds);
        } else {
            Loopable[] cmds = {AutoCodes.redDoubleBeacon(), backUp, turnTowardsCenter, moveTowardsCenter, AutoCodes.doubleShoot()};

            return new CommandSequence(cmds);
        }
    }

    public static CommandSequence waitAndDrive(double time, float dist, double power) {

        Wait wait = new Wait(time);
        SimultaneousCommands drive = robotDriveDistanceAccurate(dist, power);
        return new CommandSequence(wait, drive);
    }

    public static CommandSequence blueDoubleBeacon() {
        Conditional hitLine = new OpticalDistanceSensorThreshold(Hardware.instance.lightSensorLeft, 0.3, false);
        SimultaneousCommands driveToLine = AutoCodes.robotDriveDistanceAccurate(3.5, 0.4);

        TurnDoubleSensor turnDouble = new TurnDoubleSensor(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensorLeft, Hardware.instance.lightSensorRight, TurnDoubleSensor.Direction.RIGHT, 1);

        //arm catapult
        //reset servos

        /*Conditional offLine = new OpticalDistanceSensorThreshold(Hardware.instance.lightSensorLeft, 0.08, true);
        Loopable rotateALittle = new TurnUntilConditional(30, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, offLine);*/
        //Loopable rotateUntilLine = new TurnUntilConditional(50, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, hitLine);

        //follow line and press

        SimultaneousCommands backwards = AutoCodes.robotDriveDistanceAccurate(0.63, -0.3);

        Loopable turnTowardsOtherLine = new TurnAccurate(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, -90, 0.28);

        SimultaneousCommands driveToOtherLine = AutoCodes.robotDriveDistanceAccurate(2.75, 0.4);
        SimultaneousCommands driveToLineCarefully = AutoCodes.robotDriveDistanceAccurateOrConditonal(1, 0.15, hitLine);

        TurnDoubleSensor turnDoubleSecondDouble = new TurnDoubleSensor(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensorLeft, Hardware.instance.lightSensorRight, TurnDoubleSensor.Direction.RIGHT, 1);

        //follow line and press

        Loopable[] cmds = {driveToLine, AutoCodes.armCatapult(), AutoCodes.resetServos(), turnDouble, AutoCodes.followLineThenBeacon(Alliance.BLUE), backwards, turnTowardsOtherLine, driveToOtherLine, driveToLineCarefully, turnDoubleSecondDouble, AutoCodes.followLineThenBeacon(Alliance.BLUE)};

        return new CommandSequence(cmds);
    }

    public static CommandSequence redDoubleBeacon() {
        Conditional hitLine = new OpticalDistanceSensorThreshold(Hardware.instance.lightSensorLeft, 0.3, false);
        SimultaneousCommands driveToLine = AutoCodes.robotDriveDistanceAccurate(3.5, 0.4);

        TurnDoubleSensor turnDouble = new TurnDoubleSensor(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensorLeft, Hardware.instance.lightSensorRight, TurnDoubleSensor.Direction.LEFT, 1);

        //arm catapult
        //reset servos

        /*Conditional offLine = new OpticalDistanceSensorThreshold(Hardware.instance.lightSensorLeft, 0.08, true);
        Loopable rotateALittle = new TurnUntilConditional(30, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, offLine);*/
        //Loopable rotateUntilLine = new TurnUntilConditional(50, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, hitLine);

        //follow line and press

        SimultaneousCommands backwards = AutoCodes.robotDriveDistanceAccurate(0.63, -0.3);

        Loopable turnTowardsOtherLine = new TurnAccurate(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, 90, 0.28);

        SimultaneousCommands driveToOtherLine = AutoCodes.robotDriveDistanceAccurate(2.75, 0.4);
        SimultaneousCommands driveToLineCarefully = AutoCodes.robotDriveDistanceAccurateOrConditonal(1, 0.15, hitLine);

        TurnDoubleSensor turnDoubleSecondDouble = new TurnDoubleSensor(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensorLeft, Hardware.instance.lightSensorRight, TurnDoubleSensor.Direction.LEFT, 1);

        //follow line and press

        Loopable[] cmds = {driveToLine, AutoCodes.armCatapult(), AutoCodes.resetServos(), turnDouble, AutoCodes.followLineThenBeacon(Alliance.RED), backwards, turnTowardsOtherLine, driveToOtherLine, driveToLineCarefully, turnDoubleSecondDouble, AutoCodes.followLineThenBeacon(Alliance.RED)};

        return new CommandSequence(cmds);
    }

    public static CommandSequence waitAndDriveToCenter(double time) {
        Wait wait = new Wait(time);
        SimultaneousCommands driveToBall = AutoCodes.robotDriveDistanceAccurate(4, 0.9);

        Loopable[] cmds = {AutoCodes.resetServos(), wait, driveToBall};

        return new CommandSequence(cmds);
    }

    public static CommandSequence doubleShootFull() {

        Loopable setServoPosition1 = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);
        Loopable wait = new Wait(0.4);
        Loopable setServoPosition2 = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_DONT_STORE);

        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, Constants.CAP_THRESHOLD, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        Loopable shootCatapult = new RunMotorForTime(Hardware.instance.catapultMotor, 1, 1);

        Loopable runIntake = new RunMotorForTime(Hardware.instance.intakeMotor, Constants.INTAKE_IN_SPEED, 3);

        Loopable[] cmds = {AutoCodes.resetServos(), AutoCodes.robotDriveTime(0.7, 0.2), setServoPosition1, wait, setServoPosition2, wait, armCatapult, shootCatapult, armCatapult, runIntake, shootCatapult};

        return new CommandSequence(cmds);
    }

    public static CommandSequence doubleShootAndParkFull() {
        SimultaneousCommands driveToBall = AutoCodes.robotDriveDistanceAccurate(4, 0.9);

        return new CommandSequence(AutoCodes.doubleShootFull(), driveToBall);
    }

    public static CommandSequence redDoubleShootAndRamp(double time) {
        Loopable wait = new Wait(time);
        Loopable turnTowardsRamp = new TurnAccurate(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, -45, 0.5);
        SimultaneousCommands driveToRamp = AutoCodes.robotDriveDistanceAccurate(6, 0.75);

        return new CommandSequence(AutoCodes.doubleShootFull(), wait, turnTowardsRamp, driveToRamp);
    }

    public static CommandSequence blueDoubleShootAndRamp(double time) {
        Loopable wait = new Wait(time);
        Loopable turnTowardsRamp = new TurnAccurate(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, 45, 0.5);
        SimultaneousCommands driveToRamp = AutoCodes.robotDriveDistanceAccurate(6, 0.75);

        return new CommandSequence(AutoCodes.doubleShootFull(), wait, turnTowardsRamp, driveToRamp);
    }

    public static CommandSequence redBeaconDefense() {
        return new CommandSequence();
    }

}
