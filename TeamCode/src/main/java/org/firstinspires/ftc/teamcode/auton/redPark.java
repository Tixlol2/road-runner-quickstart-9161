package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;

public class redPark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startRedPark);
        traj1 = mecDrive.actionBuilder(autoPoints.startBluePark)
                .strafeToConstantHeading(autoPoints.redPark.component1())
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
