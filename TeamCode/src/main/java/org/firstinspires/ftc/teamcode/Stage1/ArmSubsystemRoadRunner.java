package org.firstinspires.ftc.teamcode.Stage1;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmSubsystemRoadRunner {

    private DcMotorEx armAng;
    private DcMotorEx armExt;

    public double angleTarget;

    private static final double ticks_in_degree = (751.8 * 3) / 360;

    private static final double ticks_in_inch = 537.7 / (112 / 25.4);

    public ArmSubsystemRoadRunner(HardwareMap hardwareMap){
        this.armAng = hardwareMap.get(DcMotorEx.class, "armAng");
        this.armExt = hardwareMap.get(DcMotorEx.class, "armExt");

    }

    public class ExtendInches implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            return true;
        }
    }
    public Action extendInches(double inches) {
        return new CloseClaw();
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            return true;
        }
    }
    public Action openClaw() {
        return new OpenClaw();
    }










}
