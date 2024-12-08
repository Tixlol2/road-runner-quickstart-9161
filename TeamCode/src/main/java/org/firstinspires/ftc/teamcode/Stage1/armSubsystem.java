package org.firstinspires.ftc.teamcode.Stage1;

import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class armSubsystem extends SubsystemBase {

    //Motor used to change the angle of the arm
    private final DcMotorEx extenderMotor;
    private final DcMotorEx angleMotor;





    private static final double pAngle = 0.0035, iAngle = 0.05, dAngle = 0;
    private static double fAngle = 0.3;

    private static final double ticks_in_degree = (751.8 * 3) / 360;

    private static final double ticks_in_inch = 537.7 / (112 / 25.4);


    private static double pExtend = 0.008, iExtend = 0.05, dExtend = 0, fExtend = 0;


    private static int anglePos;
    private static int angleTarget;
    private int extPos;
    private int extTarget;

    private static final int angleMin = 0;
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

        angleController.setPID(pAngle, iAngle, dAngle);
        extendController.setPID(pExtend, iExtend, dExtend);


    }

    public armSubsystem(final HardwareMap hmap, final String extension, final String angle, final double pAngle, final double iAngle, final double dAngle, final double fAngle, final double pExtend, final double iExtend, final double dExtend){
        extenderMotor = hmap.get(DcMotorEx.class, extension);
        angleMotor = hmap.get(DcMotorEx.class, angle);

        extenderMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        angleMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        extenderMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        angleMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        angleController.setPID(pAngle, iAngle, dAngle);
        extendController.setPID(pExtend, iExtend, dExtend);

        this.fAngle = fAngle;


    }

    // ----------------
    // Setters
    // ----------------
    public void setPos(Vector2d armPos) {
        angleTarget = (int) (Math.toDegrees(Math.atan(armPos.y/armPos.x)) * ticks_in_degree);
        extTarget = (int) ((Math.sqrt(armPos.x*armPos.x + armPos.y*armPos.y) -18)* ticks_in_inch);
    }
    public void setPos(int ext, int angle) {
        angleTarget = (int) (angle * ticks_in_degree);
        extTarget = (int) ((ext -18)* ticks_in_inch);
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

    public double getX(){return (extenderMotor.getCurrentPosition()/ticks_in_inch +18)* Math.cos(Math.toRadians(angleMotor.getCurrentPosition()/ticks_in_degree));}
    public double getY(){return (extenderMotor.getCurrentPosition()/ticks_in_inch +18) * Math.sin(Math.toRadians(angleMotor.getCurrentPosition()/ticks_in_degree));}
    // ----------------
    // Calculations
    // ----------------

    public void update(int setAngleTarget, int setExtendTarget) {
        angleTarget = setAngleTarget;
        extTarget = setExtendTarget;
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

        angleTarget = Math.max(angleMin, Math.min(angleMax, angleTarget));
        extTarget = (int) Math.max(extMin, Math.min(Math.min(extMax, extMax - ((extMax - 20*ticks_in_inch) * Math.cos(Math.toRadians(armAngle / ticks_in_degree)))), extTarget));

        //Angle motor
        //angleController.setPID(pAngle,iAngle,dAngle);
        anglePIDFpower = angleController.calculate(armAngle, angleTarget);
        anglefeedForward = Math.cos(Math.toRadians(armAngle / ticks_in_degree)) * fAngle;
        anglePower = Math.max(-0.4, Math.min(0.8, anglePIDFpower + anglefeedForward));

        angleMotor.setPower(anglePower);

        //Extension motor
        //extendController.setPID(pExtend,iExtend,dExtend);
        extendPower = Math.max(-0.8, Math.min(0.8, extendController.calculate(extenderMotor.getCurrentPosition(), extTarget)));

        extenderMotor.setPower(extendPower);
    }
    public void update() {
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

        angleTarget = Math.max(angleMin, Math.min(angleMax, angleTarget));
        extTarget = (int) Math.max(extMin, Math.min(Math.min(extMax, extMax - ((extMax - 22*ticks_in_inch) * Math.cos(Math.toRadians(armAngle / ticks_in_degree)))), extTarget));

        //Angle motor
        //angleController.setPID(pAngle,iAngle,dAngle);
        anglePIDFpower = angleController.calculate(armAngle, angleTarget);
        anglefeedForward = Math.cos(Math.toRadians(armAngle / ticks_in_degree)) * fAngle;
        anglePower = Math.max(-0.4, Math.min(0.8, anglePIDFpower + anglefeedForward));

        angleMotor.setPower(anglePower);

        //Extension motor
        //extendController.setPID(pExtend,iExtend,dExtend);
        extendPower = Math.max(-0.8, Math.min(0.8, extendController.calculate(extenderMotor.getCurrentPosition(), extTarget)));

        extenderMotor.setPower(extendPower);
    }
}
