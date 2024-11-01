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
public class blueScore extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startBlueScore);
        traj1 = mecDrive.actionBuilder(autoPoints.startBlueScore)
                //Gets in position to score blue Specimen on high bar, this goess to the midpoint but we can offset the x
                .splineToConstantHeading(autoPoints.blueRungMidpoint.component1(), autoPoints.startBlueScore.component2())
                .waitSeconds(.5)
                //Navigates to the blue score zone
                .strafeToConstantHeading(new Vector2d(autoPoints.blueRungMidpoint.component1().x, autoPoints.blueScore.component1().y))
                .strafeToConstantHeading(autoPoints.blueScore.component1())
                .waitSeconds(.5)
                //Grab the right yellow and then score in the high basket
                .turnTo(autoPoints.blueScore.component2())
                .waitSeconds(.5)
                //Grab the middle yellow and then score in the high basket
                .turnTo(autoPoints.middleYellowSpecimenB.component2())
                .waitSeconds(.5)
                .turnTo(autoPoints.blueScore.component2())
                .waitSeconds(.5)
                //Grab the left yellow and then score in the high basket
                .turnTo(autoPoints.rightYellowSpecimenB.component2())
                .waitSeconds(.5)
                .turnTo(autoPoints.blueScore.component2())
                .waitSeconds(.5)
                .splineToLinearHeading(autoPoints.blueSubmersibleMidpoint, autoPoints.blueSubmersibleMidpoint.component2())
                .turnTo(0)
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
