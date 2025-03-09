package frc.robot.commands.arm.ArmSetToPoint;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.RobotContainer;

public class SetArmToAngle extends Command {
    private int position;

    // Arm base 
    PIDController extendPid;
    PIDController tiltPid;

    /** Sets arm to angle with pids
     * positions - 0-trough, 1-L2, 2-L3, 3-L4
     * 4-Ground, 5-Processor, 6-Source, 7-Barge
     * 8-lower Alague, 9-Higher Alague
     */
    public SetArmToAngle(int position) { 
        this.position = position;

        extendPid = new PIDController(ArmConstants.kExtendP, ArmConstants.kExtendI, ArmConstants.kExtendD);
        tiltPid = new PIDController(ArmConstants.kTiltP, ArmConstants.kTiltI, ArmConstants.kTiltD);
    
    }

    @Override
    public void execute() {
        switch (position) {
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
            case 4:
                // Ground Pickup

                break;
            case 5:
                // Processor drop off

                break;        
            case 6:
                // Source Pickup
                RobotContainer.arm.setTiltSpeed(tiltPid.calculate(RobotContainer.arm.getTiltEncoder(), ArmConstants.kTiltSourcePickupPos));
                RobotContainer.arm.setExtendSpeed(extendPid.calculate(RobotContainer.arm.getExtendValue(), ArmConstants.kExtendSourcePickupPos));             
                break;
            case 7:
                // Barge Dropoff

                break;
            case 8:
                // Lower Alague Pickup

                break;
            case 9:
                // Higher Alague Pickup

                break;
        } 
    }
}
