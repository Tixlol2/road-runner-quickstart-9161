package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
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
public class redPark extends LinearOpMode {

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

        mecDrive = new MecanumDrive(hardwareMap, autoPoints.startRedPark);
        traj1 = mecDrive.actionBuilder(autoPoints.startRedPark)
                //Go score specimen
                .splineToConstantHeading(autoPoints.redRungMidpoint.component1(), autoPoints.startRedScore.component2())
                //Extend to score, open claw
                .stopAndAdd(new SequentialAction(
                        armSubsystem.setPosrr(new Vector2d(18, 21)),
                        clawSubsystem.setAngle(0),
                        armSubsystem.setPosrr(new Vector2d(20, 16.5))
                ))
                .waitSeconds(0.7)
                .stopAndAdd(clawSubsystem.openClaw())
                .stopAndAdd(armSubsystem.setPosrr(18,10))
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
            //Main loop
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
