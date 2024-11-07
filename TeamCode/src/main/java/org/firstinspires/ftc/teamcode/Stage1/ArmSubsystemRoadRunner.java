package org.firstinspires.ftc.teamcode.Stage1;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmSubsystemRoadRunner extends armSubsystem {





    public ArmSubsystemRoadRunner(HardwareMap hardwareMap){
        super(hardwareMap, "armExt", "armAng");


    }

    public class setPosrr implements Action {

        Vector2d pos;
        int ang;
        int ext;

        public setPosrr(Vector2d pos) {
            this.pos = pos;
        }

        public setPosrr(int ext, int ang) {
            this.ext = ext;
            this.ang = ang;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (pos != null)  setPos(pos);
            else ArmSubsystemRoadRunner.this.setPosrr(ext, ang);
            return getAngleTarget() != getAnglePos() && getExtTarget() != getExtenderPos();
        }
    }
    public Action setPosrr(Vector2d pos) {
        return new setPosrr(pos);

    }
    public Action setPosrr(int ext, int ang) {
        return new setPosrr(ext, ang);
    }










}