package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Intake.ClawSubsystemRoadRunner;


@TeleOp(name="servoTest", group = "Drive")

public class servoTest extends LinearOpMode {

    //Class def


    Servo servo;


    @Override
    public void runOpMode() throws InterruptedException {

        servo = hardwareMap.get(Servo.class, "clawAngle");
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        ClawSubsystemRoadRunner clawSubsystem = new ClawSubsystemRoadRunner(hardwareMap);



        waitForStart();
        //During Initialization:


        while (opModeIsActive()) {

            if (gamepad2.b) {

                Actions.runBlocking(clawSubsystem.setAngle(0));
            } else if (gamepad2.a) {
                Actions.runBlocking(clawSubsystem.setAngle(1));

            }
            else if (gamepad2.x){
                Actions.runBlocking(clawSubsystem.setAngle(.5));
            }


        }
    }
}
