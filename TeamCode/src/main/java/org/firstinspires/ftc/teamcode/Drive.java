package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Stage1.Commands.armPIDFCommand;
import org.firstinspires.ftc.teamcode.Stage1.armSubsystem;
import org.firstinspires.ftc.teamcode.Intake.clawSubsystem;


@TeleOp(name="Pedro Drive", group = "Drive")
public class Drive extends LinearOpMode {

    //Class def


    double deflator;
    double deflator2;

    int angleTarget = 0;
    int extendTarget = 0;
    double clawTarget = 0;



    //Limelight3A ll3a;
    //Follower follower;
    boolean driveCentric;
    //CommandScheduler commandScheduler;

    private PIDController angleController;
    private PIDController extendController;




    public  double pAngle = .0028, iAngle = 0, dAngle = 0.000, fAngle = -0.01;
    public static double pExtend = 0.008, iExtend = 0, dExtend = 0;
    private final double ticks_in_degree = (751.8 * 4) / 360;
    private final double ticks_in_inch = (537.7 / 112) / 25.4;

    private int armAngle;
    private int extendPos;
    private double anglePower;
    private double extendPower;
    private double anglePIDFpower;
    private double anglefeedForward;

    private double armX;
    private double armY;



    @Override
    public void runOpMode() throws InterruptedException {



        angleController = new PIDController(pAngle, iAngle, dAngle);
        extendController = new PIDController(pExtend, iExtend, dExtend);
        waitForStart();
        //During Initialization:
        //ll3a = hardwareMap.get(Limelight3A.class, "LL3a");



        //commandScheduler = CommandScheduler.getInstance();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        //hMap, name of servo used for claw
        clawSubsystem clawSubsystem = new clawSubsystem(hardwareMap, "clawAngle", "clawDriver");
        //hMap, name of motor used to change the EXTENSION HEIGHT of the arm/slides
        armSubsystem armSubsystem = new armSubsystem(hardwareMap, "armExt", "armAng");
        armPIDFCommand armPIDFCommand = new armPIDFCommand(armSubsystem, 0,0 );







//        follower = new Follower(hardwareMap);
//
//
//
//        follower.startTeleopDrive();



        while (opModeIsActive()) {
            //-----------------------------
            //Input
            // ----------------------------

            deflator = gamepad2.left_bumper && gamepad2.right_bumper ? 0.5 : gamepad2.left_bumper ? 0.7 : 1;
            deflator2 = gamepad2.left_bumper && gamepad2.right_bumper ? 0.5 : gamepad2.left_bumper ? 0.7 : 1;

            if (gamepad1.b) {
                driveCentric = false;
            } else if (gamepad1.a) {
                driveCentric = true;
            }
            //Testing clawSubsystem
            if (gamepad2.b) {
                clawSubsystem.close();
            } else if (gamepad2.a) {
                clawSubsystem.open();
            }
            if (gamepad2.x) {
                clawTarget = 1;
            } else if (gamepad2.y) {
                clawTarget = 0;

            }

            //Testing armSubsystem
            clawTarget += (Math.pow(gamepad2.left_trigger + -gamepad2.right_trigger,3) * 0.1 * deflator);
            angleTarget += (int) (Math.pow(gamepad2.left_stick_y, 3) * 4 *deflator);
            extendTarget += (int) (Math.pow(gamepad2.right_stick_y, 3) * 40 *deflator);



            // ----------------------------
            // Telemetry
            // ----------------------------

            telemetry.addData("Current Angle in Ticks: ", armAngle);
            telemetry.addData("Current Angle Target in Ticks: ", angleTarget);


            telemetry.addData("Current Extension in Ticks: ", armSubsystem.getExtenderPos());
            telemetry.addData("Current Extension Target in Ticks: ", extendTarget);


            telemetry.addData("Arm Angle: ", armSubsystem.getAnglePosDEG());
            telemetry.addData("Arm extension: ", armSubsystem.getExtenderPosIN());


            telemetry.addLine("Don't Crash!");
            telemetry.addData("Driver Centric?", driveCentric);



            // ---------------
            // Motor Calculations
            // ----------------



            armSubsystem.motorCalculations(angleTarget,extendTarget, angleController, extendController);
            // ----------------------------
            // Updaters
            // ----------------------------

            clawSubsystem.setAnglePosition(clawTarget);
//            follower.setTeleOpMovementVectors(-gamepad1.left_stick_y*deflator2, -gamepad1.left_stick_x*deflator2, -gamepad1.right_stick_x*deflator2, driveCentric);
//            follower.update();

            telemetry.update();
        }




    }
}
