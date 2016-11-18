package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.commands.conditions.DistanceSensorThreshold;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 11/2/16.
 */
public class AutoCodes {
    private static CommandSequence closeBeaconCloseStartRed;
    private static CommandSequence darrionHouseTest;

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

            SimultaneousCommands driveToLine = AutoCodes.robotDriveDistanceAccurate(11287, 0.5);

            Loopable turnToLine = new SetBearingGyro(0, 0.2, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

            Conditional distanceCondition = new DistanceSensorThreshold(Hardware.instance.distanceSensor, 1.5, true);
            Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.1, distanceCondition);

            Loopable pressBeacon = new PressBeacon(Alliance.RED, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo, true);

            Loopable[] cmds = {turnToLine, followLine, pressBeacon};

            AutoCodes.closeBeaconCloseStartRed =  new CommandSequence(cmds);
        }

        return AutoCodes.closeBeaconCloseStartRed;
    }

    public static CommandSequence darrionHouseTest() {
        if (AutoCodes.darrionHouseTest == null) {
            SimultaneousCommands forward = AutoCodes.robotDriveDistanceAccurate(10000, 0.6);

            Loopable turn = new TurnGyro(90, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

            Conditional condition = new DistanceSensorThreshold(Hardware.instance.distanceSensor, 10, true);
            SimultaneousCommands driveUntilWall = AutoCodes.robotDriveUntilCondition(condition, 0.3);

            SimultaneousCommands back = AutoCodes.robotDriveDistanceAccurate(7000, -0.5);

            Loopable turnBack = new TurnGyro(-90, 0.3, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

            Loopable[] cmds = {forward, turn, driveUntilWall, back, forward};

            AutoCodes.darrionHouseTest =  new CommandSequence(cmds);
        }

        return AutoCodes.darrionHouseTest;
    }

}
