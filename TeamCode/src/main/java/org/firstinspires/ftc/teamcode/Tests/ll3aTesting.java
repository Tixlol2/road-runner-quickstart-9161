package org.firstinspires.ftc.teamcode.Tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TeleOp
@Config
public class ll3aTesting extends LinearOpMode {

    public static int pipeline = 0;
    Limelight3A ll3a;
    String color;
    @Override
    public void runOpMode() throws InterruptedException {


        ll3a = hardwareMap.get(Limelight3A.class, "LL3A");


        while (opModeInInit()) {
           if(ll3a.isRunning()){
               telemetry.addLine("Should be good!");
               telemetry.update();
           } else {
               ll3a.pipelineSwitch(pipeline);
               ll3a.setPollRateHz(100);
               ll3a.start();
           }
        }

        while (opModeIsActive()) {

            //Main loop
            LLResult result = ll3a.getLatestResult();
            // Getting numbers from Python
            //TODO: Make this work with multiple blocks in the FOV, shouldn't be too hard in theory?
            //if(result.isValid()){
            double[] pythonOutputs = result.getPythonOutput();
            if (pythonOutputs != null && pythonOutputs.length > 0) {
                if(pythonOutputs[0] == 0){telemetry.addLine("Either we are really good at our jobs \n or the SS isn't detecting for some reason");}
                else {
                    color = determineColor(pythonOutputs);
                    telemetry.addData("Color", color);
                    telemetry.addData("Angle in Degrees", pythonOutputs[0]);}
            } else {
                telemetry.addLine("No data received from Limelight.");
                }
            long staleness = result.getStaleness();
            if (staleness < 100) { // Less than 100 milliseconds old
                telemetry.addData("Data", "Good");
            } else {
                telemetry.addData("Data", "Old (" + staleness + " ms)");
            }
            //} else {telemetry.addLine("Result is not valid");}









            telemetry.update();
        }

    }

    public String determineColor(double[] outputs){
        int colorIndex = (int) outputs[1];

        if(colorIndex == 0){return "Red";}
        else if(colorIndex == 1){return "Blue";}
        else if(colorIndex == 2){return "Yellow";}

        return "Unknown";


    }



}




