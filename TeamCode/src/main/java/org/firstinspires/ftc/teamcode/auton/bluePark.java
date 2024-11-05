package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Intake.ClawSubsystemRoadRunner;
import org.firstinspires.ftc.teamcode.Stage1.ArmSubsystemRoadRunner;
import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;


@Autonomous
public class bluePark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;
        ClawSubsystemRoadRunner clawSubsystem = new ClawSubsystemRoadRunner(hardwareMap);
        ArmSubsystemRoadRunner armSubsystem = new ArmSubsystemRoadRunner(hardwareMap);

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startBluePark);

        traj1 = mecDrive.actionBuilder(autoPoints.startBluePark)
                //Go score specimen
                .splineToConstantHeading(autoPoints.blueRungMidpoint.component1(), autoPoints.startBluePark.component2())
                .waitSeconds(.5)
                .strafeToConstantHeading(new Vector2d(autoPoints.blueRungMidpoint.component1().x, autoPoints.blueScore.component1().y))
                .strafeToConstantHeading(autoPoints.blueParkTile.component1())
                .turnTo(0)
                .waitSeconds(.5)
                .strafeTo(autoPoints.blueParkTile.component1().plus(new Vector2d(0, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(0, -48)))
                .strafeTo(autoPoints.bluePark.component1())
                .waitSeconds(.5)
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(0, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-8, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-8, 0)))
                .waitSeconds(.5)
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-8, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-12, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-12, 0)))
                .waitSeconds(.5)
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(0, -24)))
                .waitSeconds(1)
                .strafeTo(autoPoints.bluePark.component1())
                .build();
        while(!isStarted() && !opModeIsActive()){
            //Init Loop
            Actions.runBlocking(new ParallelAction(
                    clawSubsystem.closeClaw(),
                    clawSubsystem.setAngle(.1)
            ));

        }


        while(!isStopRequested() && opModeIsActive()){
            //Main loop
            Actions.runBlocking(new SequentialAction(
                    traj1
            ));






        }











    }
}
