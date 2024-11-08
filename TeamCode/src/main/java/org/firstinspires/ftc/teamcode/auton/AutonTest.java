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
public class AutonTest extends LinearOpMode {
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
                //Extend to score, open claw
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(21, 17)),
                        clawSubsystem.setAngle(0.5)
                ))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .waitSeconds(2)
                //Navigates to the red score zone
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(32, 3)),
                        clawSubsystem.setAngle(0)
                ))
                .stopAndAdd(clawSubsystem.closeClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 46)))
                .stopAndAdd(clawSubsystem.setAngle(1))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(clawSubsystem.setAngle(0.5))
                .waitSeconds(1)
                //Grab the right yellow and then score in the high basket
                //Grab the middle yellow and then score in the high basket
                .stopAndAdd(armSubsystem.setPosrr(18,18))
                .waitSeconds(1)
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(34.5, 4.5)),
                        clawSubsystem.setAngle(0)
                ))
                .stopAndAdd(clawSubsystem.closeClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 46)))
                .stopAndAdd(clawSubsystem.setAngle(1))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(clawSubsystem.setAngle(0.5))
                .waitSeconds(2)
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                //Grab the left yellow and then score in the high basket
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(38, 6)),
                        clawSubsystem.setAngle(0)
                ))
                .stopAndAdd(clawSubsystem.closeClaw())
                .stopAndAdd(clawSubsystem.closeClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 46)))
                .stopAndAdd(clawSubsystem.setAngle(1))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(clawSubsystem.setAngle(0.5))
                .waitSeconds(2)
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18, 18)))
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(3, 20)),
                        clawSubsystem.setAngle(1)
                ))
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
