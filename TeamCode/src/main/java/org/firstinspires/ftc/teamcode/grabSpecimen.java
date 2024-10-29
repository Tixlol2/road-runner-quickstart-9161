//package org.firstinspires.ftc.teamcode;
//
//import com.arcrobotics.ftclib.command.SequentialCommandGroup;
//
//import org.firstinspires.ftc.teamcode.Intake.Commands.clawCloseCommand;
//import org.firstinspires.ftc.teamcode.Intake.Commands.wristDownCommand;
//import org.firstinspires.ftc.teamcode.Intake.Commands.wristUpCommand;
//import org.firstinspires.ftc.teamcode.Intake.clawSubsystem;
//import org.firstinspires.ftc.teamcode.Stage1.Commands.armPIDFCommand;
//import org.firstinspires.ftc.teamcode.Stage1.armSubsystem;
//
//public class grabSpecimen extends SequentialCommandGroup {
//
//    public grabSpecimen(armSubsystem aSubsystem, clawSubsystem cSubsystem){
//
//        addCommands(
//                new armPIDFCommand(aSubsystem, -100, -150),
//                new wristDownCommand(cSubsystem),
//                new clawCloseCommand(cSubsystem),
//                new wristUpCommand(cSubsystem),
//                new armPIDFCommand(aSubsystem, -100, 0)
//        );
//
//
//    }
//
//
//
//
//}
