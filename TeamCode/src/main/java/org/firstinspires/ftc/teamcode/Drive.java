package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


//import org.firstinspires.ftc.teamcode.Stage1.Commands.armPIDFCommand;
import org.firstinspires.ftc.teamcode.Stage1.armSubsystem;
import org.firstinspires.ftc.teamcode.Intake.clawSubsystem;
import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;


@TeleOp(name="Drive", group = "Drive")
public class Drive extends LinearOpMode {

    //Class def


    double gp2Deflator;
    double gp1Deflator;

    int angleTarget = 10;
    int extendTarget = 10;
    double clawTarget = 0;
    double clawWrist = 0;



    //Limelight3A ll3a;
    //Follower follower;
    boolean driveCentric;
    //CommandScheduler commandScheduler;
    MecanumDrive mecDrive;

    private PIDController angleController;
    private PIDController extendController;




    public  double pAngle = .0028, iAngle = 0, dAngle = 0.000, fAngle = -0.01;
    public static double pExtend = 0.008, iExtend = 0, dExtend = 0;
    private final double ticks_in_degree = (751.8 * 4) / 360;
    private final double ticks_in_inch = (537.7 / 112) / 25.4;



    @Override
    public void runOpMode() throws InterruptedException {


        //During Initialization:
        angleController = new PIDController(pAngle, iAngle, dAngle);
        extendController = new PIDController(pExtend, iExtend, dExtend);

        mecDrive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));


        //ll3a = hardwareMap.get(Limelight3A.class, "LL3a");



        //commandScheduler = CommandScheduler.getInstance();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        //hMap, name of servo used for claw
        clawSubsystem clawSubsystem = new clawSubsystem(hardwareMap, "clawAngle", "clawDriver", "clawWrist");
        //hMap, name of motor used to change the EXTENSION HEIGHT of the arm/slides
        armSubsystem armSubsystem = new armSubsystem(hardwareMap, "armExt", "armAng");
//        armPIDFCommand armPIDFCommand = new armPIDFCommand(armSubsystem, 0,0 );



        waitForStart();



//        follower = new Follower(hardwareMap);
//
//
//
//        follower.startTeleopDrive();



        while (opModeIsActive()) {
            //-----------------------------
            //Input
            // ----------------------------

            gp2Deflator = gamepad2.left_bumper && gamepad2.right_bumper ? 0.5 : gamepad2.left_bumper ? 0.7 : 1;
            gp1Deflator = gamepad1.left_bumper && gamepad1.right_bumper ? 0.5 : gamepad1.left_bumper ? 0.7 : 1;

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
            if (gamepad2.dpad_down){clawSubsystem.setWristPosition(1);}
            else if (gamepad2.dpad_up){clawSubsystem.setWristPosition(1);}

            //Testing armSubsystem
            clawTarget += (Math.pow(gamepad2.left_trigger + -gamepad2.right_trigger,3) * 0.05 * gp2Deflator);
            clawWrist = gamepad2.dpad_left ? 0 : gamepad2.dpad_right ? 1 : gamepad2.dpad_up ? 0.5 : clawWrist;
            angleTarget += (int) (Math.pow(gamepad2.left_stick_y, 3) * -12 * gp2Deflator);
            extendTarget += (int) (Math.pow(gamepad2.right_stick_y, 3) * -40 * gp2Deflator);



            // ----------------------------
            // Telemetry
            // ----------------------------
//
//            telemetry.addData("Current Angle in Ticks: ", armSubsystem.getAnglePos());
//            telemetry.addData("Current Angle Target in Ticks: ", angleTarget);
//
//
//            telemetry.addData("Current Extension in Ticks: ", armSubsystem.getExtenderPos());
//            telemetry.addData("Current Extension Target in Ticks: ", extendTarget);
//
//
//            telemetry.addData("Arm Angle: ", armSubsystem.getAnglePosDEG());
//            telemetry.addData("Arm extension: ", armSubsystem.getExtenderPosIN());
//
//            telemetry.addData("Arm subsystem Angle Target:", armSubsystem.getAngleTarget());
//            telemetry.addData("Arm subsystem Extension Target:", armSubsystem.getExtTarget());
//
//
//            telemetry.addLine("Don't Crash!");
//            telemetry.addData("Driver Centric?", driveCentric);
//


            // ---------------
            // Motor Calculations
            // ----------------



            armSubsystem.update(angleTarget,extendTarget);
            // ----------------------------
            // Updaters
            // ----------------------------
            clawTarget = Math.max(armSubsystem.getExtenderPos() < 30 ? 0.3: 0, Math.min(1, clawTarget));
            clawWrist = Math.max(0, Math.min(1, clawWrist));
            clawSubsystem.setAnglePosition(clawTarget);
            clawSubsystem.setWristPosition(clawWrist);
            mecDrive.updatePoseEstimate();
            mecDrive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            gp1Deflator *  (driveCentric ? (-gamepad1.left_stick_y* Math.cos(-mecDrive.pose.heading.toDouble()) - -gamepad1.left_stick_x * Math.sin(-mecDrive.pose.heading.toDouble())) : -gamepad1.left_stick_y),
                            gp1Deflator * (driveCentric ? (-gamepad1.left_stick_y* Math.sin(-mecDrive.pose.heading.toDouble()) + -gamepad1.left_stick_x * Math.cos(-mecDrive.pose.heading.toDouble())) : -gamepad1.left_stick_x)) ,
                    -gamepad1.right_stick_x* gp1Deflator
            ));


            angleTarget = armSubsystem.getAngleTarget();
            extendTarget = armSubsystem.getExtTarget();
            telemetry.addLine("Lock In \uD83D\uDD25 \uD83D\uDD25 \uD83D\uDD25");
            telemetry.update();
        }




    }
}
