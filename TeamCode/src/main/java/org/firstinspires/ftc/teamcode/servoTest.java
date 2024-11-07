package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="servoTest", group = "Drive")
public class servoTest extends LinearOpMode {

    //Class def


    Servo servo;


    @Override
    public void runOpMode() throws InterruptedException {

        servo = hardwareMap.get(Servo.class, "clawAngle");
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());




        waitForStart();
        //During Initialization:


        while (opModeIsActive()) {

            if (gamepad2.b) {
                servo.setPosition(0);
            } else if (gamepad2.a) {
                servo.setPosition(1);
            }
            else if (gamepad2.x){
                servo.setPosition(.5);
            }
        }
    }
}
