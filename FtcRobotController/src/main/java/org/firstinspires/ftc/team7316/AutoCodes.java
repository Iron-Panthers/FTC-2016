package org.firstinspires.ftc.team7316;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.auto.CommandSequence;
import org.firstinspires.ftc.team7316.util.auto.DriveDistance;
import org.firstinspires.ftc.team7316.util.auto.DriveDistanceAccurate;
import org.firstinspires.ftc.team7316.util.auto.LineFollow;
import org.firstinspires.ftc.team7316.util.auto.LineFollowUntilCondition;
import org.firstinspires.ftc.team7316.util.auto.PressBeacon;
import org.firstinspires.ftc.team7316.util.auto.SimultaneousCommands;
import org.firstinspires.ftc.team7316.util.auto.TurnGyro;
import org.firstinspires.ftc.team7316.util.auto.conditions.DistanceSensorThreshold;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

//THIS COMMENT IS A GIT TEST


/**
 * Created by andrew on 11/2/16.
 */
public class AutoCodes {
    private static CommandSequence closeBeaconCloseStartRed;

    public static SimultaneousCommands robotDriveDistanceAccurate(double distance, double power) {
        DriveDistanceAccurate leftMotor = new DriveDistanceAccurate(distance, power, Hardware.instance.leftDriveMotor);
        DriveDistanceAccurate rightMotor = new DriveDistanceAccurate(distance, power, Hardware.instance.rightDriveMotor);
        Loopable[] both = {leftMotor, rightMotor};

        SimultaneousCommands bothDrive = new SimultaneousCommands(both);
        return bothDrive;
    }

    public static CommandSequence closeBeaconCloseStartRed() {
        if (AutoCodes.closeBeaconCloseStartRed == null) {

            SimultaneousCommands driveToLine = AutoCodes.robotDriveDistanceAccurate(11287, 0.5);

            Loopable turnToLine = new TurnGyro(0, 0.2, Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor);

            Loopable distanceCondition = new DistanceSensorThreshold(Hardware.instance.distanceSensor, 1.5, true);
            Loopable followLine = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.1, distanceCondition);

            Loopable pressBeacon = new PressBeacon(Alliance.RED, Hardware.instance.colorSensor, Hardware.instance.leftBeaconServo, Hardware.instance.rightBeaconServo, true);

            Loopable[] cmds = {turnToLine, followLine, pressBeacon};

            AutoCodes.closeBeaconCloseStartRed =  new CommandSequence(cmds);
        }

        return AutoCodes.closeBeaconCloseStartRed;
    }

}
