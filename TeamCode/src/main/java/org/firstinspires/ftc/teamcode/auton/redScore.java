package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Intake.ClawSubsystemRoadRunner;
import org.firstinspires.ftc.teamcode.Stage1.ArmSubsystemRoadRunner;
import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;
@Autonomous
public class redScore extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;
        ClawSubsystemRoadRunner clawSubsystem = new ClawSubsystemRoadRunner(hardwareMap);
        ArmSubsystemRoadRunner armSubsystem = new ArmSubsystemRoadRunner(hardwareMap);

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startRedScore);
        traj1 = mecDrive.actionBuilder(autoPoints.startRedScore)
                //Gets in position to score red Specimen on high bar, this goess to the midpoint but we can offset the x
                .splineToConstantHeading(autoPoints.redRungMidpoint.component1(), autoPoints.startRedScore.component2())
                //Extend to score, open claw
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(12, 12)))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(0,0)))
                //Navigates to the red score zone
                .strafeToConstantHeading(new Vector2d(autoPoints.redRungMidpoint.component1().x, autoPoints.redScore.component1().y))
                .strafeToConstantHeading(autoPoints.redScore.component1())
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(16, 0)),
                        clawSubsystem.setAngle(0.3)
                ))
                .stopAndAdd(clawSubsystem.closeClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(0, 20)))
                .stopAndAdd(clawSubsystem.setAngle(1))
                //Grab the right yellow and then score in the high basket
                .turnTo(autoPoints.redScore.component2())
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
            armSubsystem.update();






        }











    }
}
