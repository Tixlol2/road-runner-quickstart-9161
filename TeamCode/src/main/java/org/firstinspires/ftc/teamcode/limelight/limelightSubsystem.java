package org.firstinspires.ftc.teamcode.limelight;

import static java.lang.Math.acos;
import static java.lang.Math.asin;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.List;


public class limelightSubsystem extends SubsystemBase {


    public Limelight3A ll3a;


    public limelightSubsystem(HardwareMap hMap, final int pipeline){
        ll3a = hMap.get(Limelight3A.class, "LL3A");
        ll3a.pipelineSwitch(pipeline);
    }

    public static List<Integer> limelightOffset(int idNum, int angle, int distance) {
        double limelightY = (int) (asin(angle)*distance);
        double limelightX = (int) (acos(angle)*distance);


        if (idNum == 11) {
            limelightX -= 72;
            limelightY += 48;
        }
        else if (idNum == 12) {
            limelightY += 72;
        }
        else if (idNum == 13) {
            limelightX += 72;
            limelightY += 48;
        }
        else if (idNum == 14) {
            limelightX += 72;
            limelightY -= 48;
        }
        else if (idNum == 15) {
            limelightY -= 72;
        }
        else if (idNum == 16) {
            limelightX -= 72;
            limelightY -= 48;
        }
        List<Integer> robotPos = new ArrayList<>();
        robotPos.add((int) limelightX);
        boolean add = robotPos.add((int) limelightY);
        return(robotPos);

    }


}
