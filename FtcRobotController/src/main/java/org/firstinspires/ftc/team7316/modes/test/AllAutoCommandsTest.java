package org.firstinspires.ftc.team7316.modes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.BaseOpMode;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;
import org.firstinspires.ftc.team7316.util.input.GamepadButton;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;

/**
 * Created by Maxim on 2/19/2017.
 */
@TeleOp(name = "Test all auto commands")
public class AllAutoCommandsTest extends BaseOpMode {

    /**
     * run after initialization
     * @return
     */
    public static Loopable[] generateCommands() {
        return new Loopable[] {
                AutoCodes.robotDriveDistanceAccurate(1, Constants.DRIVER_MOTOR_DEADZONE),
                AutoCodes.robotDriveDistanceAccurate(3, Constants.DRIVER_MOTOR_DEADZONE),
                AutoCodes.robotDriveDistanceAccurate(-4, Constants.DRIVER_MOTOR_DEADZONE),
                new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, 45),
                new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, -90),
                new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, 180),
                new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, -45),
                AutoCodes.followLineThenBeacon(Alliance.BLUE)
        };
    };

    private Loopable[] commands;
    private int index;
    private GamepadWrapper gpwrapper;

    @Override
    public void onInit() {
        commands = generateCommands();
        index = 0;
        gpwrapper = new GamepadWrapper(gamepad1);
        gpwrapper.a_button.addListener(new ButtonListener() {
            @Override
            public void onPressed() {
                index = (index + 1) % commands.length;
                Scheduler.instance.addTask(commands[index]);
            }

            @Override
            public void onReleased() {

            }
        });
    }

    @Override
    public void onLoop() {
        Hardware.log("Current command", index);
    }
}
