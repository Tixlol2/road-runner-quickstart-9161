package org.firstinspires.ftc.teamcode.Stage1;

import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Vector;

public class armSubsystem extends SubsystemBase {

    //Motor used to change the angle of the arm
    private final DcMotorEx extenderMotor;
    private final DcMotorEx angleMotor;





    private static final double pAngle = 0.0035, iAngle = 0.05, dAngle = 0, fAngle = 0;

    private static final double ticks_in_degree = (751.8 * 3) / 360;

    private static final double ticks_in_inch = 537.7 / (112 / 25.4);


    private static double pExtend = 0.008, iExtend = 0.05, dExtend = 0, fExtend = 0;


    private static int anglePos;
    private static int angleTarget;
    private int extPos;
    private int extTarget;

    private static final int angleMin = 15;
    private static final int angleMax = 550;
    private static final int extMin = 0;
    private static final int extMax = 3400;

    private static final PIDController angleController = new PIDController(pAngle, iAngle, dAngle);
    private static final PIDController extendController = new PIDController(pExtend, iExtend, dExtend);




    public armSubsystem(final HardwareMap hmap, final String extension, final String angle){
        extenderMotor = hmap.get(DcMotorEx.class, extension);
        angleMotor = hmap.get(DcMotorEx.class, angle);

        extenderMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        angleMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        extenderMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        angleMotor.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    // ----------------
    // Setters
    // ----------------
    public void setArm(Vector2d armPos) {

    }
    // ----------------
    // Getters
    // ----------------
    public int getAngleTarget(){return angleTarget;}
    public double getAngleTargetDG(){return (angleTarget / ticks_in_degree);}

    public int getAnglePos(){return angleMotor.getCurrentPosition();}
    public double getAnglePosDEG(){return (angleMotor.getCurrentPosition() / ticks_in_degree);}

    public int getExtTarget(){return extTarget;}
    public double getExtTargetIN(){return (extTarget / ticks_in_inch);}

    public int getExtenderPos(){return extenderMotor.getCurrentPosition();}
    public double getExtenderPosIN(){return (extenderMotor.getCurrentPosition() / ticks_in_inch);}


    // ----------------
    // Calculations
    // ----------------

    public void motorCalculations(int angleTarget, int extendTarget) {
        this.angleTarget = angleTarget;
        this.extTarget = extendTarget;
        double anglePower;
        double extendPower;
        double anglefeedForward;
        double anglePIDFpower;
        int extendPos;

        double armAngle = angleMotor.getCurrentPosition();
        double armExt = extenderMotor.getCurrentPosition();


        double anglePIDFPower;
        armAngle = angleMotor.getCurrentPosition();

        // CLamping

        angleTarget = Math.min(angleMin, Math.max(angleMax, angleTarget));
        extendTarget = (int) Math.min(extMin, Math.max(extMax - ((extMax - 20*ticks_in_inch) * Math.cos(Math.toRadians(armAngle / ticks_in_degree))), extendTarget));

        //Angle motor

        anglePIDFpower = angleController.calculate(armAngle, angleTarget);
        anglefeedForward = Math.cos(Math.toRadians(armAngle / ticks_in_degree)) * fAngle;
        anglePower = Math.min(-0.8, Math.max(0.8, anglePIDFpower + anglefeedForward));

        angleMotor.setPower(anglePower);

        //Extension motor
        extendPower = Math.min(-0.8, Math.max(0.8, extendController.calculate(extenderMotor.getCurrentPosition(), extendTarget)));

        extenderMotor.setPower(extendPower);
    }
}
