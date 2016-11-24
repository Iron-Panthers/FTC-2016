package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.LineFollowUntilCondition;
import org.firstinspires.ftc.team7316.util.commands.conditions.ButtonCondition;
import org.firstinspires.ftc.team7316.util.commands.conditions.ButtonPressCondition;
import org.firstinspires.ftc.team7316.util.commands.conditions.CatapultPositionConditional;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.commands.conditions.OpticalDistanceSensorThreshold;
import org.firstinspires.ftc.team7316.util.commands.conditions.ServoPositionConditional;
import org.firstinspires.ftc.team7316.util.hardware.CatapultWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorThreeStateWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapperWithConditional;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.IntakeDrive;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.input.RunCommandOnPress;
import org.firstinspires.ftc.team7316.util.input.TwoButtonToggleWrapper;

/*
gamepad1:
-left stick y = left motor power √
-right stick y = right motor power √

gamepad2:
-left bumper: left beacon servo √
-right bumper: right beacon servo √
-right trigger (with threshold) = catapult next movement in cycle (either go to sensor or go past sensor)
-left trigger (with threshold) = move catapult backwards
-a button = run intake inward (toggle) √
-b button = run intake outward (toggle) √
 */
@TeleOp(name = "PantherDrive")
public class DriveMode extends OpMode {

    private GamepadWrapper gpWrapperDriver;
    private GamepadWrapper gpWrapperNotDriver;

    private DcMotorWrapperWithConditional leftDrive, rightDrive;
    private DcMotorThreeStateWrapper intakeDrive;
    private CatapultWrapper catapultDrive;

    private ServoWrapper leftPusher, rightPusher, intakeRelease;

    private TwoButtonToggleWrapper aAndBToggle;

    private ServoPositionConditional servoPositionConditional;
    private CatapultPositionConditional catapultPositionConditional;

    private RunCommandOnPress runLineFollow;

    @Override
    public void init() {
        Scheduler.instance.clear();

        gpWrapperDriver = new GamepadWrapper(gamepad1);
        gpWrapperNotDriver = new GamepadWrapper(gamepad2);

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        ButtonPressCondition xDown = new ButtonPressCondition(gpWrapperDriver.x_button);

        leftDrive = new DcMotorWrapperWithConditional(Hardware.instance.leftDriveMotor, gpWrapperDriver.left_axis_y, xDown);
        rightDrive = new DcMotorWrapperWithConditional(Hardware.instance.rightDriveMotor, gpWrapperDriver.right_axis_y, xDown );

        Conditional buttonTriggered = new ButtonCondition(Hardware.instance.touchSensor);
        LineFollowUntilCondition lineFollowCommand = new LineFollowUntilCondition(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensor, 0.15, buttonTriggered);
        runLineFollow = new RunCommandOnPress(gpWrapperDriver.x_button, lineFollowCommand);

        aAndBToggle = new TwoButtonToggleWrapper(gpWrapperNotDriver.a_button, gpWrapperNotDriver.b_button);

        catapultDrive = new CatapultWrapper(
                Hardware.instance.catapultMotor,
                new OpticalDistanceSensorThreshold(Hardware.instance.catapultSensor, 0.14, false)
        );

        servoPositionConditional = new ServoPositionConditional(Hardware.instance.intakeUpServo, Constants.INTAKE_SERVO_RELEASE, true);
        catapultPositionConditional = new CatapultPositionConditional(catapultDrive, true);
        intakeDrive = new IntakeDrive(Hardware.instance.intakeMotor, 0.5, 0, -1.0, aAndBToggle, catapultPositionConditional, servoPositionConditional);

        rightPusher = new ServoWrapper(Hardware.instance.rightBeaconServo, gpWrapperNotDriver.right_bumper, Constants.RIGHT_ON, Constants.RIGHT_OFF);
        leftPusher = new ServoWrapper(Hardware.instance.leftBeaconServo, gpWrapperNotDriver.left_bumper, Constants.LEFT_ON, Constants.LEFT_OFF);
        intakeRelease = new ServoWrapper(Hardware.instance.intakeUpServo, gpWrapperNotDriver.dpLeftWrapper, Constants.INTAKE_SERVO_RELEASE, Constants.INTAKE_SERVO_LOCKED);

        Scheduler.instance.addTask(leftDrive);
        Scheduler.instance.addTask(rightDrive);
        Scheduler.instance.addTask(aAndBToggle);
        Scheduler.instance.addTask(intakeDrive);
        Scheduler.instance.addTask(catapultDrive);
        Scheduler.instance.addTask(rightPusher);
        Scheduler.instance.addTask(leftPusher);
        Scheduler.instance.addTask(intakeRelease);
        Scheduler.instance.addTask(catapultDrive);
        Scheduler.instance.addTask(runLineFollow);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
