package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;

public class blueScore extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startBluePark);
        traj1 = mecDrive.actionBuilder(autoPoints.startBlueScore)
                .splineToConstantHeading(autoPoints.leftBlueSpecimen.component1(), autoPoints.leftBlueSpecimen.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.blueScore.component1(), autoPoints.blueScore.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.middleBlueSpecimen.component1(), autoPoints.middleBlueSpecimen.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.blueScore.component1(), autoPoints.blueScore.component2())
                .turnTo(autoPoints.rightBlueSpecimen.component2())
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
