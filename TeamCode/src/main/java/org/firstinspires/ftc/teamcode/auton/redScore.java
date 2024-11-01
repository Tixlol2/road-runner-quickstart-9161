package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;
@Autonomous
public class redScore extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startRedScore);
        traj1 = mecDrive.actionBuilder(autoPoints.startRedScore)
                .splineToConstantHeading(autoPoints.leftRedSpecimen.component1(), autoPoints.leftRedSpecimen.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.redScore.component1(), autoPoints.redScore.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.middleRedSpecimen.component1(), autoPoints.middleRedSpecimen.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.redScore.component1(), autoPoints.redScore.component2())
                .waitSeconds(.5)
                .turnTo(autoPoints.rightRedSpecimen.component2())
                .waitSeconds(.5)
                .build();
        while(!isStarted() && !opModeIsActive()){
            //Init Loop

        }


        while(!isStopRequested() && opModeIsActive()){
            //Main loop
            Actions.runBlocking(traj1);






        }











    }
}
