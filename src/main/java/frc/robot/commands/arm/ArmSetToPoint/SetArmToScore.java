package frc.robot.commands.arm.ArmSetToPoint;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.commands.arm.RunArm;
import frc.robot.subsystems.arm.Arm;
import frc.robot.RobotContainer;

public class SetArmToScore extends Command {
    private int level;

    // Arm base 
    PIDController extendPid;
    PIDController tiltPid;

    private void SetBaseArmToAngle() {
        extendPid = new PIDController(ArmConstants.kExtendP, ArmConstants.kExtendI, ArmConstants.kExtendD);
        tiltPid = new PIDController(ArmConstants.kTiltP, ArmConstants.kTiltI, ArmConstants.kTiltD);
    }

    public SetArmToScore(int level) { 
        this.level = level;
    }

    @Override
    public void execute() {
        switch (level) {
            case 0:
                // Score in trough
                RobotContainer.arm.setTiltSpeed(tiltPid.calculate(RobotContainer.arm.getTiltEncoder(), ArmConstants.kTiltTrofPos));
                RobotContainer.arm.setExtendSpeed(extendPid.calculate(RobotContainer.arm.getExtendValue(), ArmConstants.kExtendTrofPos));             
                break; 
                
            case 1:
                // Score in level 2
                RobotContainer.arm.setTiltSpeed(tiltPid.calculate(RobotContainer.arm.getTiltEncoder(), ArmConstants.kTiltL2Pos));
                RobotContainer.arm.setExtendSpeed(extendPid.calculate(RobotContainer.arm.getExtendValue(), ArmConstants.kExtendL2Pos));             
                break; 
            case 2:
                // Score in level 3
                RobotContainer.arm.setTiltSpeed(tiltPid.calculate(RobotContainer.arm.getTiltEncoder(), ArmConstants.kTiltL2Pos));
                RobotContainer.arm.setExtendSpeed(extendPid.calculate(RobotContainer.arm.getExtendValue(), ArmConstants.kExtendL2Pos));             
                break;
            
            case 3:
                // Score in level 4
                RobotContainer.arm.setTiltSpeed(tiltPid.calculate(RobotContainer.arm.getTiltEncoder(), ArmConstants.kTiltL2Pos));
                RobotContainer.arm.setExtendSpeed(extendPid.calculate(RobotContainer.arm.getExtendValue(), ArmConstants.kExtendL2Pos));             
                break;
        } 
    }
}
