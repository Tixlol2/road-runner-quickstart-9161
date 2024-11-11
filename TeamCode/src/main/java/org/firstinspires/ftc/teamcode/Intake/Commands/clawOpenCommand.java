package org.firstinspires.ftc.teamcode.Intake.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Intake.clawSubsystem;

public class clawOpenCommand extends CommandBase {

    private final clawSubsystem m_clawSubsystem;

    public clawOpenCommand(clawSubsystem subsystem){

        m_clawSubsystem = subsystem;

    }

    @Override
    public void initialize(){
        m_clawSubsystem.open();
    }

    @Override
    public boolean isFinished() {
        if (m_clawSubsystem.driverOfClaw.getPosition() == m_clawSubsystem.open) {
            return true;
        } else {
            return false;
        }

    }

}
