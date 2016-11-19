package org.firstinspires.ftc.team7316.util.commands;

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
 */
public class AutoCodes {
    private static CommandSequence closeBeaconCloseStartRed;
    private static CommandSequence beaconPressTest;

    public static SimultaneousCommands robotDriveDistanceAccurate(double distance, double power) {
        DriveDistanceAccurate leftMotor = new DriveDistanceAccurate(distance, power, Hardware.instance.leftDriveMotor);
        DriveDistanceAccurate rightMotor = new DriveDistanceAccurate(distance, power, Hardware.instance.rightDriveMotor);
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

    public static CommandSequence closeBeaconCloseStartRed() {
        if (AutoCodes.closeBeaconCloseStartRed == null) {

            Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);

            Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false);
            Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

            SimultaneousCommands driveToLine = AutoCodes.robotDriveDistanceAccurate(11287, 0.5);

            Loopable turnToLine = new SetBearingGyro(0, 0.2, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

            Conditional buttonCondition = new ButtonCondition(Hardware.instance.touchSensor);
            Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.1, buttonCondition);

            Loopable pressBeacon = new PressBeacon(Alliance.RED, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo);

            Loopable[] cmds = {setServoPosition, armCatapult, driveToLine, turnToLine, followLine, pressBeacon};

            AutoCodes.closeBeaconCloseStartRed =  new CommandSequence(cmds);
        }

        return AutoCodes.closeBeaconCloseStartRed;
    }

    public static CommandSequence beaconPressTest() {
        if (AutoCodes.beaconPressTest == null) {

            Loopable setServoPosition = new SetServoPosition(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE);

            Conditional odsCondition = new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false);
            Loopable armCatapult = new RunMotorUntilConditional(Hardware.instance.catapultMotor, odsCondition, 1);

            Conditional buttonCondition = new ButtonCondition(Hardware.instance.touchSensor);
            Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.1, buttonCondition);

            Loopable pressBeacon = new PressBeacon(Alliance.RED, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo);

            Loopable[] cmds = {setServoPosition, armCatapult, followLine, pressBeacon};

            AutoCodes.beaconPressTest =  new CommandSequence(cmds);
        }

        return AutoCodes.beaconPressTest;
    }

}
