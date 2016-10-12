package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.ColorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.GyroWrapper;

/**
 * Created by andrew on 10/11/16.
 */
@TeleOp(name = "SensorTest123")
public class SensorTest extends OpMode {

    GyroWrapper gyroWrapper;
    ColorWrapper colorWrapper;

    @Override
    public void init() {
        GyroSensor gyroSensor = hardwareMap.gyroSensor.get("gyro");
        gyroWrapper = new GyroWrapper(gyroSensor);
        Scheduler.instance.addTask(gyroWrapper);

        ColorSensor colorSensor = hardwareMap.colorSensor.get("color");
        colorWrapper = new ColorWrapper(colorSensor);
        Scheduler.instance.addTask(colorWrapper);

        gyroWrapper.calibrate();
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        telemetry.addData("gyro_z", gyroWrapper.getHeading());

        telemetry.addData("color_r", colorWrapper.sumR());
        telemetry.addData("color_g", colorWrapper.sumG());
        telemetry.addData("color_b", colorWrapper.sumB());
    }
}
