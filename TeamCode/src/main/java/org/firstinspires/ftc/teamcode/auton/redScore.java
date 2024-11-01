package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
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
                //Gets in position to score red Specimen on high bar, this goess to the midpoint but we can offset the x
                .splineToConstantHeading(autoPoints.redRungMidpoint.component1(), autoPoints.startRedScore.component2())
                .waitSeconds(.5)
                //Navigates to the red score zone
                .strafeToConstantHeading(new Vector2d(autoPoints.redRungMidpoint.component1().x, autoPoints.redScore.component1().y))
                .strafeToConstantHeading(autoPoints.redScore.component1())
                .waitSeconds(.5)
                //Grab the right yellow and then score in the high basket
                .turnTo(autoPoints.redScore.component2())
                .waitSeconds(.5)
                //Grab the middle yellow and then score in the high basket
                .turnTo(autoPoints.middleYellowSpecimenR.component2())
                .waitSeconds(.5)
                .turnTo(autoPoints.redScore.component2())
                .waitSeconds(.5)
                //Grab the left yellow and then score in the high basket
                .turnTo(autoPoints.rightYellowSpecimenR.component2())
                .waitSeconds(.5)
                .turnTo(autoPoints.redScore.component2())
                .waitSeconds(.5)
                .splineToLinearHeading(autoPoints.redSubmersibleMidpoint, autoPoints.redSubmersibleMidpoint.component2())
                .turnTo(Math.toRadians(180))
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
