package org.firstinspires.ftc.teamcode.limelight;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class limelightSubsystem extends SubsystemBase {


    public Limelight3A ll3a;


    public limelightSubsystem(HardwareMap hMap, final int pipeline){
        ll3a = hMap.get(Limelight3A.class, "LL3A");
        ll3a.pipelineSwitch(pipeline);
    }


}
