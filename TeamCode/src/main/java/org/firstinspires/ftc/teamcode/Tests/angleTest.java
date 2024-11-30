package org.firstinspires.ftc.teamcode.Tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp
public class angleTest extends LinearOpMode {

    DcMotorEx leftMotor;
    DcMotorEx rightMotor;
    double power;

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.get(DcMotorEx.class, "armAngleLeft");
        rightMotor = hardwareMap.get(DcMotorEx.class, "armAngleRight");


        while (opModeInInit()){




        }

        while(opModeIsActive()){

            power = gamepad1.left_stick_y;

            leftMotor.setPower(power);
            rightMotor.setPower(-power);

        }






    }
}
