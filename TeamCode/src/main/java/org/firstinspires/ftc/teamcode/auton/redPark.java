package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;
@Autonomous
public class redPark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startRedPark);
        traj1 = mecDrive.actionBuilder(autoPoints.startRedPark)
                //Go score specimen
                .splineToConstantHeading(autoPoints.redRungMidpoint.component1(), autoPoints.startRedPark.component2())
                .waitSeconds(.5)
                .strafeToConstantHeading(new Vector2d(autoPoints.redRungMidpoint.component1().x, autoPoints.redScore.component1().y))
                .strafeToConstantHeading(autoPoints.redParkTile.component1())
                .turnTo(0)
                .waitSeconds(.5)
                .strafeTo(autoPoints.redParkTile.component1().plus(new Vector2d(0, 48)))
                .strafeTo(autoPoints.redPark.component1().plus(new Vector2d(0, 48)))
                .strafeTo(autoPoints.redPark.component1())
                .waitSeconds(.5)
                .strafeTo(autoPoints.redPark.component1().plus(new Vector2d(0, 48)))
                .strafeTo(autoPoints.redPark.component1().plus(new Vector2d(8, 48)))
                .strafeTo(autoPoints.redPark.component1().plus(new Vector2d(8, 0)))
                .waitSeconds(.5)
                .strafeTo(autoPoints.redPark.component1().plus(new Vector2d(8, 48)))
                .strafeTo(autoPoints.redPark.component1().plus(new Vector2d(12, 48)))
                .strafeTo(autoPoints.redPark.component1().plus(new Vector2d(12, 0)))
                .waitSeconds(.5)
                .strafeTo(autoPoints.redPark.component1().plus(new Vector2d(0, 24)))
                .waitSeconds(1)
                .strafeTo(autoPoints.redPark.component1())
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
