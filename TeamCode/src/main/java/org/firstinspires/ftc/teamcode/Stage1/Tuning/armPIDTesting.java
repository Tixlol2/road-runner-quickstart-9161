package org.firstinspires.ftc.teamcode.Stage1.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Disabled
@Config
@TeleOp
public class armPIDTesting extends LinearOpMode {

    private PIDController angleController;

    public static double p = 0.0035, i = 0.05, d = 0.0003;
    public static double fAngle = .25;

    public static int angleTarget = 0;

    private final double ticks_in_degree = (751.8 * 3) / 360;

    private final double ticks_in_inch = ticks_in_degree / 4.409;

    private DcMotorEx angleMotor;


    @Override
    public void runOpMode() throws InterruptedException {

        angleController = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        angleMotor = hardwareMap.get(DcMotorEx.class, "armAng");
        angleMotor.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();

        while(!isStopRequested()){

            double armAngle = angleMotor.getCurrentPosition();
            angleController.setPID(p,i,d);



            // CLamping

            angleTarget = Math.max(10, Math.min(550, angleTarget));

            //Angle motor
            double anglePIDFpower = angleController.calculate(armAngle, angleTarget);
            double anglefeedForward = Math.cos(Math.toRadians(-armAngle / ticks_in_degree)) * fAngle;
            double anglePower = Math.max(-0.8, Math.min(0.8, anglePIDFpower + anglefeedForward));

            angleMotor.setPower(anglePower);


            telemetry.addData("Current Pos: ", armAngle);
            telemetry.addData("Current Target: ", angleTarget);
            telemetry.addData("Power", anglePower);

            telemetry.update();



        }



    }
}
