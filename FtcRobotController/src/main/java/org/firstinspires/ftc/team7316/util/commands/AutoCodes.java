package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.robotcore.external.matrices.ColumnMajorMatrixF;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.conditions.ButtonCondition;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.commands.conditions.DistanceSensorThreshold;
import org.firstinspires.ftc.team7316.util.commands.conditions.OpticalDistanceSensorThreshold;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 11/2/16.
 * All the sequential commands to run
 */
public class AutoCodes {

    public static SimultaneousCommands robotDriveDistanceAccurate(double distance, double power) {
        DriveDistanceAccurate leftMotor = new DriveDistanceAccurate(distance, power, Hardware.instance.leftDriveMotor);
        DriveDistanceAccurate rightMotor = new DriveDistanceAccurate(distance, power, Hardware.instance.rightDriveMotor);
        Loopable[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
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

    public static CommandSequence closeBeaconFarStartBlue() {

        SimultaneousCommands driveToLine = AutoCodes.robotDriveTime(2.35, 0.5);

        Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);
        Loopable setLeftServoPosition = new SetServoPosition(Hardware.instance.leftBeaconServo, Constants.LEFT_OFF);
        Loopable setRightServoPosition = new SetServoPosition(Hardware.instance.rightBeaconServo, Constants.RIGHT_OFF);

        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        Loopable turnToLine = new TurnGyro(40, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        Conditional buttonCondition = new ButtonCondition(Hardware.instance.touchSensor);
        Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.15, buttonCondition);

        Loopable wait = new Wait(Constants.COLOR_SENSOR_DELAY);

        Loopable pressBeacon = new PressBeacon(Alliance.BLUE, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo);

        Loopable[] cmds = {driveToLine, setServoPosition, setLeftServoPosition, setRightServoPosition, armCatapult, turnToLine, followLine, wait, pressBeacon};

        return new CommandSequence(cmds);
    }

    public static CommandSequence midStartRedBothBeacons() {

        CommandSequence toBlueFirst = AutoCodes.closeBeaconFarStartRed();

        SimultaneousCommands backwards = AutoCodes.robotDriveTime(0.55, -0.3);

        Loopable turnTowardsOtherLine = new TurnGyro(90, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        SimultaneousCommands driveToOtherLine = AutoCodes.robotDriveTime(2.45, 0.3);

        Loopable turnBack = new TurnGyro(100, -0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        Loopable[] cmds = {toBlueFirst, backwards, turnTowardsOtherLine, driveToOtherLine, turnBack, AutoCodes.followLineAndBeacon(Alliance.RED)};

        return new CommandSequence(cmds);
    }

    public static CommandSequence closeBeaconFarStartRed() {

        SimultaneousCommands driveToLine = AutoCodes.robotDriveTime(2.2, 0.5);

        Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);
        Loopable setLeftServoPosition = new SetServoPosition(Hardware.instance.leftBeaconServo, Constants.LEFT_OFF);
        Loopable setRightServoPosition = new SetServoPosition(Hardware.instance.rightBeaconServo, Constants.RIGHT_OFF);

        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        Loopable turnToLine = new TurnGyro(40, -0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        Conditional buttonCondition = new ButtonCondition(Hardware.instance.touchSensor);
        Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.15, buttonCondition);

        Loopable wait = new Wait(Constants.COLOR_SENSOR_DELAY);

        Loopable pressBeacon = new PressBeacon(Alliance.RED, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo);

        Loopable[] cmds = {driveToLine, setServoPosition, setLeftServoPosition, setRightServoPosition, armCatapult, turnToLine, followLine, wait, pressBeacon};

        return new CommandSequence(cmds);
    }

    public static CommandSequence followLineAndBeacon(Alliance alliance) {
        Conditional buttonCondition = new ButtonCondition(Hardware.instance.touchSensor);
        Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.15, buttonCondition);

        Loopable wait = new Wait(Constants.COLOR_SENSOR_DELAY);

        Loopable pressBeacon = new PressBeacon(alliance, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo);

        Loopable[] cmds = {followLine, wait, pressBeacon};

        return new CommandSequence(cmds);
    }

    public static CommandSequence closeBeaconFarStartRedNoBeacon() {

        Loopable turnToLine = new TurnGyro(45, 0.25, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        Loopable[] cmds = {turnToLine};

        return new CommandSequence(cmds);
    }

    public static CommandSequence simpleShoot() {

        SimultaneousCommands driveToLine = AutoCodes.robotDriveTime(0.4, 0.3);

        Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);

        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        Loopable shootCatapult = new RunMotorForTime(Hardware.instance.catapultMotor, 1, 1);

        SimultaneousCommands driveToBall = AutoCodes.robotDriveTime(2.7, 0.5);

        Loopable[] cmds = {driveToLine, setServoPosition, armCatapult, shootCatapult, driveToBall};

        return new CommandSequence(cmds);

    }

    public static CommandSequence doubleShoot() {

        SimultaneousCommands driveToLine = AutoCodes.robotDriveTime(0.4, 0.3);

        Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);

        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        Loopable shootCatapult = new RunMotorForTime(Hardware.instance.catapultMotor, 1, 1);

        Loopable runIntake = new RunMotorForTime(Hardware.instance.intakeMotor, 0.5, 3);

        SimultaneousCommands driveToBall = AutoCodes.robotDriveTime(2.7, 0.5);

        Loopable[] cmds = {driveToLine, setServoPosition, armCatapult, shootCatapult, armCatapult, runIntake, shootCatapult, driveToBall};

        return new CommandSequence(cmds);

    }

    public static CommandSequence singleShootAndBeaconFromRed() {

        CommandSequence shootAndCap = simpleShoot();

        TurnGyro turnLeft = new TurnGyro(45, -0.25, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        SimultaneousCommands driveNearLine = AutoCodes.robotDriveTime(1.5, 0.5);

        CommandSequence followLineAndBeacon = followLineAndBeacon(Alliance.RED);

        Loopable[] cmds = {shootAndCap, turnLeft, driveNearLine, followLineAndBeacon};

        return new CommandSequence(cmds);
    }

    public static CommandSequence beaconPressTest() {

        Loopable setIntakePosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);
        Loopable setLeftPresser = new SetServoPosition(Hardware.instance.leftBeaconServo, Constants.LEFT_OFF);
        Loopable setRightPresser = new SetServoPosition(Hardware.instance.rightBeaconServo, Constants.RIGHT_OFF);

        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false);
        Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

        Conditional buttonCondition = new ButtonCondition(Hardware.instance.touchSensor);
        Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.15, buttonCondition);

        Loopable wait = new Wait(Constants.COLOR_SENSOR_DELAY);

        Loopable pressBeacon = new PressBeacon(Alliance.RED, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo);

        Loopable[] cmds = {setIntakePosition, setLeftPresser, setRightPresser, armCatapult, followLine, wait, pressBeacon};

        return new CommandSequence(cmds);

    }

}
