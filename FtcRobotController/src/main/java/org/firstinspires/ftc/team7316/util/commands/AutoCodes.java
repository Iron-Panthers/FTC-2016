package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.conditions.ButtonCondition;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.commands.conditions.OpticalDistanceSensorThreshold;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistanceAccurate;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveUntilCondition;
import org.firstinspires.ftc.team7316.util.commands.drive.LineFollowUntilCondition;
import org.firstinspires.ftc.team7316.util.commands.drive.RunMotorForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.RunMotorUntilConditional;
import org.firstinspires.ftc.team7316.util.commands.flow.CommandSequence;
import org.firstinspires.ftc.team7316.util.commands.flow.SimultaneousCommands;
import org.firstinspires.ftc.team7316.util.commands.flow.Wait;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnGyro;
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

    public static CommandSequence resetServos() {
        Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);
        Loopable setLeftServoPosition = new SetServoPosition(Hardware.instance.leftBeaconServo, Constants.LEFT_OFF);
        Loopable setRightServoPosition = new SetServoPosition(Hardware.instance.rightBeaconServo, Constants.RIGHT_OFF);

        Loopable[] cmds = {setServoPosition, setLeftServoPosition, setRightServoPosition};

        return new CommandSequence(cmds);
    }

    public static CommandSequence followLineThenBeacon(Alliance alliance) {
        Conditional buttonCondition = new ButtonCondition(Hardware.instance.touchSensor);
        Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.15, buttonCondition);

        Loopable wait = new Wait(Constants.COLOR_SENSOR_DELAY);

        Loopable pressBeacon = new PressBeacon(alliance, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo);

        Loopable[] cmds = {followLine, wait, pressBeacon};

        return new CommandSequence(cmds);
    }

    public static Loopable armCatapult() {
        Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false);
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

        Loopable[] cmds = {driveToLine, setServoPosition, armCatapult, shootCatapult, armCatapult, runIntake, shootCatapult};

        return new CommandSequence(cmds);

    }

    public static CommandSequence doubleShootAndCap() {
        CommandSequence doubleShoot = doubleShoot();

        SimultaneousCommands driveToBall = AutoCodes.robotDriveTime(2.7, 0.5);

        return new CommandSequence(doubleShoot, driveToBall);
    }

    public static CommandSequence redDoubleShootDoubleBeacon() {
        //double shoot

        //reset servos

        TurnGyro turnTowardsLine = new TurnGyro(17, -0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        SimultaneousCommands driveToLine = AutoCodes.robotDriveTime(3.6, 0.4);

        TurnGyro turnAlongLine = new TurnGyro(16, -0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        //arm capatult

        //follow line and press

        SimultaneousCommands backwards = AutoCodes.robotDriveTime(0.55, -0.3);

        Loopable turnTowardsOtherLine = new TurnGyro(90, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        SimultaneousCommands driveToOtherLine = AutoCodes.robotDriveTime(2.45, 0.3);

        Loopable turnBack = new TurnGyro(100, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

        //follow line and press

        Loopable[] cmds = {AutoCodes.doubleShoot(), AutoCodes.resetServos(), turnTowardsLine, driveToLine, AutoCodes.armCatapult(), turnAlongLine, AutoCodes.followLineThenBeacon(Alliance.RED), backwards, turnTowardsOtherLine, driveToOtherLine, turnBack, AutoCodes.followLineThenBeacon(Alliance.RED)};

        return new CommandSequence(cmds);
    }

}
