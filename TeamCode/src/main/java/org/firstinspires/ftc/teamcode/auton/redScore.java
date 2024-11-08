package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
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
        Action traj2;
        ClawSubsystemRoadRunner clawSubsystem = new ClawSubsystemRoadRunner(hardwareMap);
        ArmSubsystemRoadRunner armSubsystem = new ArmSubsystemRoadRunner(hardwareMap);




        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startRedScore);

        traj2 = mecDrive.actionBuilder(new Pose2d(autoPoints.redRungMidpoint.component1().x, autoPoints.redScore.component1().y, autoPoints.startRedScore.component2().real))
                .strafeToConstantHeading(autoPoints.redScore.component1())
                .build();

        traj1 = mecDrive.actionBuilder(autoPoints.startRedScore)
//Gets in position to score red Specimen on high bar, this goess to the midpoint but we can offset the x
                .splineToConstantHeading(autoPoints.redRungMidpoint.component1(), autoPoints.startRedScore.component2())
                //Extend to score, open claw
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(17, 21)),
                        clawSubsystem.setAngle(0.5)
                ))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                //Navigates to the red score zone
                .strafeToConstantHeading(new Vector2d(autoPoints.redRungMidpoint.component1().x, autoPoints.redScore.component1().y))
                .stopAndAdd(new ParallelAction())
                .strafeToConstantHeading(autoPoints.redScore.component1())
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(32, 3)),
                        clawSubsystem.setAngle(0)
                ))
                .stopAndAdd(clawSubsystem.closeClaw())
                .stopAndAdd(clawSubsystem.setAngle(1))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(clawSubsystem.setAngle(0.5))
                //Grab the right yellow and then score in the high basket
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .turnTo(autoPoints.redScore.component2())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 46)))
                .waitSeconds(1)
                //Grab the middle yellow and then score in the high basket
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .waitSeconds(0.1)
                .turnTo(autoPoints.middleYellowSpecimenR.component2())
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(34.5, 4.5)),
                        clawSubsystem.setAngle(0)
                ))
                .waitSeconds(0.1)
                .stopAndAdd(clawSubsystem.closeClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .turnTo(autoPoints.redScore.component2())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 46)))
                .waitSeconds(1)
                .stopAndAdd(clawSubsystem.setAngle(1))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(clawSubsystem.setAngle(0.5))
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .waitSeconds(0.1)
                //Grab the left yellow and then score in the high basket
                .turnTo(autoPoints.rightYellowSpecimenR.component2())
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(38, 6)),
                        clawSubsystem.setAngle(0)
                ))
                .stopAndAdd(clawSubsystem.closeClaw())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .turnTo(autoPoints.redScore.component2())
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(1.5, 46)))
                .stopAndAdd(clawSubsystem.setAngle(1))
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(clawSubsystem.setAngle(0.5))
                .waitSeconds(1)
                .stopAndAdd(armSubsystem.setPosrr(new Vector2d(18,18)))
                .stopAndAdd(new ParallelAction(
                        armSubsystem.setPosrr(new Vector2d(3, 20)),
                        clawSubsystem.setAngle(1)
                ))
                .stopAndAdd(armSubsystem.setPosrr(18,18))
                .splineToLinearHeading(autoPoints.redSubmersibleMidpoint, Math.toRadians(180))
                .build();
        runningActions.add(traj1);
        while(!isStarted() && !opModeIsActive()){
            //Init Loop


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
