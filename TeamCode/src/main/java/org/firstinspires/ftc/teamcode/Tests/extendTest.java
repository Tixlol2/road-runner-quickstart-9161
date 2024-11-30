package org.firstinspires.ftc.teamcode.Tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp
public class extendTest extends LinearOpMode {

    DcMotorEx upMotor;
    DcMotorEx downMotor;
    double power;

    @Override
    public void runOpMode() throws InterruptedException {

        upMotor = hardwareMap.get(DcMotorEx.class, "armExtendUp");
        downMotor = hardwareMap.get(DcMotorEx.class, "armExtendDown");


        while (opModeInInit()){




        }

        while(opModeIsActive()){

            power = gamepad1.left_stick_y;

            upMotor.setPower(-power);
            downMotor.setPower(-power);

        }






    }
}
