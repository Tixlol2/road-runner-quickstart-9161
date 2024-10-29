package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Intake.Commands.clawCloseCommand;
import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;

public class bluePark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;
        clawCloseCommand clawCloseCommand;

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startBluePark);

        traj1 = mecDrive.actionBuilder(autoPoints.startBluePark)
                .strafeToConstantHeading(autoPoints.bluePark.component1())
                .build();
        while(!isStarted() && !opModeIsActive()){
            //Init Loop

        }


        while(!isStopRequested() && opModeIsActive()){
            //Main loop
            Actions.runBlocking(new SequentialAction(
                    traj1
            ));






        }











    }
}
