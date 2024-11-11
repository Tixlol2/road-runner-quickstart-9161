package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Intake.ClawSubsystemRoadRunner;
import org.firstinspires.ftc.teamcode.Stage1.ArmSubsystemRoadRunner;
import org.firstinspires.ftc.teamcode.rrFiles.MecanumDrive;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class redScore extends LinearOpMode {

    private List<Action> runningActions = new ArrayList<>();
    private FtcDashboard dash = FtcDashboard.getInstance();
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive mecDrive;
        autonPoints autoPoints = new autonPoints();
        Action traj1;
        Action score;
        ClawSubsystemRoadRunner clawSubsystem = new ClawSubsystemRoadRunner(hardwareMap);
        ArmSubsystemRoadRunner armSubsystem = new ArmSubsystemRoadRunner(hardwareMap);




        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startRedScore);



        traj1 = mecDrive.actionBuilder(autoPoints.startRedScore)
//Gets in position to score red Specimen on high bar, this goess to the midpoint but we can offset the x
                .splineToConstantHeading(autoPoints.redRungMidpoint.component1(), autoPoints.startRedScore.component2())
                //Extend to score, open claw
                .stopAndAdd(new SequentialAction(
                        armSubsystem.setPosrr(new Vector2d(18.5, 23)),
                        clawSubsystem.setAngle(0),
                        armSubsystem.setPosrr(new Vector2d(20, 16))
                ))
                .waitSeconds(0.7)
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                //Navigates to the red score zone
                .strafeToConstantHeading(new Vector2d(autoPoints.redRungMidpoint.component1().x, autoPoints.redScore.component1().y))
                .strafeToConstantHeading(autoPoints.redPick.component1())
                .stopAndAdd(new SequentialAction(
                        //Sets arm to hover over the right block
                        clawSubsystem.setAngle(0),
                        armSubsystem.setPosrr(new Vector2d(32.5, 3.2))


                ))
                .waitSeconds(0.5)
                .stopAndAdd(clawSubsystem.closeClaw())
                .waitSeconds(.2)
                .stopAndAdd(new SequentialAction(
                        //Closes the claw and gets it ready to score
                        clawSubsystem.setAngle(.3),
                        armSubsystem.setPosrr(new Vector2d(18,18))
                ))
                //Turn to face the tower
                .strafeToLinearHeading(autoPoints.redScore.component1(),autoPoints.redScore.component2())
                //Scores the block
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 44)))
                .waitSeconds(0.6)
                .stopAndAdd(new SequentialAction(
                        clawSubsystem.setAngle(1)
                ))

                .waitSeconds(0.2)
                .stopAndAdd(new SequentialAction(
                        clawSubsystem.openClaw(),
                        clawSubsystem.setAngle(0.5),
                        armSubsystem.setPosrr(new Vector2d(18,18))
                ))
                //Allows the time to score
                .waitSeconds(0.5)
                //Grab the middle yellow and then score in the high basket
                .strafeToLinearHeading(autoPoints.redPick.component1(), autoPoints.rightYellowSpecimenR.component2())
                .stopAndAdd(new SequentialAction(
                        new ParallelAction(
                                //Sets arm to hover over the middle block
                                armSubsystem.setPosrr(new Vector2d(34.5, 4.1)),
                                clawSubsystem.setAngle(0)
                        )))
                .waitSeconds(0.5)
                .stopAndAdd(clawSubsystem.closeClaw())
                .waitSeconds(.1)
                .stopAndAdd(new SequentialAction(
                        //Closes the claw and gets it ready to score
                        clawSubsystem.setAngle(.3),
                        armSubsystem.setPosrr(new Vector2d(18,18))
                ))
                //Turn to face the tower
                .strafeToLinearHeading(autoPoints.redScore.component1(), autoPoints.redScore.component2())
                //Scores the block
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 44)))
                .waitSeconds(0.6)
                .stopAndAdd(new SequentialAction(
                        clawSubsystem.setAngle(1)
                ))

                .waitSeconds(0.2)
                .stopAndAdd(new SequentialAction(
                        clawSubsystem.openClaw(),
                        clawSubsystem.setAngle(0.5),
                        armSubsystem.setPosrr(new Vector2d(18,18))
                ))

                //Grab the left yellow and then score in the high basket
                .strafeToLinearHeading(autoPoints.redPick.component1(), autoPoints.leftYellowSpecimenR.component2())
                .stopAndAdd(new SequentialAction(
                        new ParallelAction(
                                //Sets arm to hover over the right block
                                armSubsystem.setPosrr(new Vector2d(32.5, 3.8)),
                                clawSubsystem.setAngle(0)

                        )))

                .waitSeconds(0.5)
                .stopAndAdd(clawSubsystem.closeClaw())
                .waitSeconds(.1)
                .stopAndAdd(new SequentialAction(
                        //Closes the claw and gets it ready to score
                        clawSubsystem.setAngle(.3),
                        armSubsystem.setPosrr(new Vector2d(18,18))
                ))
                //Turn to face the tower
                .strafeToLinearHeading(autoPoints.redScore.component1(), autoPoints.redScore.component2())
                //Scores the block
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 44)))
                .waitSeconds(0.6)
                .stopAndAdd(new SequentialAction(
                        clawSubsystem.setAngle(1)
                ))

                .waitSeconds(0.2)
                .stopAndAdd(new SequentialAction(
                        clawSubsystem.openClaw(),
                        clawSubsystem.setAngle(0.5),
                        armSubsystem.setPosrr(new Vector2d(18,18))
                ))

                .splineToLinearHeading(autoPoints.redSubmersibleMidpoint, Math.toRadians(0))
                .stopAndAdd(new SequentialAction(
                        armSubsystem.setPosrr(new Vector2d(16.5,21)),
                        clawSubsystem.setAngle(0)
                ))
                .build();
        runningActions.add(traj1);
        while(!isStarted() && !opModeIsActive()){
            //Init Loop
            Actions.runBlocking(new ParallelAction(
                    clawSubsystem.setAngle(1),
                    clawSubsystem.setWrist(.5),
                    clawSubsystem.closeClaw()
            ));

        }


        while(!isStopRequested() && opModeIsActive()){

            TelemetryPacket packet = new TelemetryPacket();
            //Main loop
            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;

            dash.sendTelemetryPacket(packet);
            armSubsystem.update();






        }











    }
}
