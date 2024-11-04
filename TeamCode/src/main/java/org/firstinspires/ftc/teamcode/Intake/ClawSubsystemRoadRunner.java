package org.firstinspires.ftc.teamcode.Intake;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystemRoadRunner {

    private Servo clawDriver;
    private Servo clawAngle;

    public double angleTarget;

    public ClawSubsystemRoadRunner(HardwareMap hardwareMap){

        this.clawDriver = hardwareMap.get(Servo.class, "clawDriver");
        this.clawAngle = hardwareMap.get(Servo.class, "clawAngle");
    }

    public class CloseClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            clawDriver.setPosition(1);
            return false;
        }
    }
    public Action closeClaw() {
        return new CloseClaw();
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            clawDriver.setPosition(.4);
            return false;
        }
    }
    public Action openClaw() {
        return new OpenClaw();
    }


    public class SetAngle implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            clawAngle.setPosition(angleTarget);
            return false;
        }
    }
    public Action setAngle(double pos) {
        angleTarget = pos;
        return new OpenClaw();
    }







}
