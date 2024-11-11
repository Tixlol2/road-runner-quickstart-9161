package org.firstinspires.ftc.teamcode.limelight;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.List;
@TeleOp
@Config
public class ll3aTesting extends LinearOpMode {

    public static int pipeline = 0;
    Limelight3A ll3a;

    @Override
    public void runOpMode() throws InterruptedException {


        ll3a = hardwareMap.get(Limelight3A.class, "LL3A");


        while (opModeInInit()) {
            //Init loop

        }

        while (opModeIsActive()) {
            //Main loop
            ll3a.pipelineSwitch(pipeline);

            LLResult result = ll3a.getLatestResult();
            if (result.getPipelineIndex() == pipeline) {
                // print some data for each detected target
                if (result.isValid()) {
                    double tx = result.getTx(); // How far left or right the target is (degrees)
                    double ty = result.getTy(); // How far up or down the target is (degrees)
                    double ta = result.getTa(); // How big the target looks (0%-100% of the image)

                    telemetry.addData("Target X", tx);
                    telemetry.addData("Target Y", ty);
                    telemetry.addData("Target Area", ta);
                } else {
                    telemetry.addData("Limelight", "No Targets");
                }
            }

        }

    }
}









