package frc.robot.commands.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.Wrist;

public class SetArmToAngle extends Command {
    private int position;

    // Arm base 
    PIDController extendPid;
    PIDController tiltPid;
    Wrist wrist;
    Arm arm;

    /** Sets arm to angle with pids
     * positions - 0-trough, 1-L2, 2-L3, 3-L4
     * 4-Ground, 5-Processor, 6-Source, 7-Barge
     * 8-lower Alague, 9-Higher Alague
     */
    public SetArmToAngle(Arm m_arm, Wrist m_wrist, int position) { 
        addRequirements(m_arm, m_wrist);
        this.position = position;

        extendPid = new PIDController(ArmConstants.kExtendP, ArmConstants.kExtendI, ArmConstants.kExtendD);
        tiltPid = new PIDController(ArmConstants.kTiltP, ArmConstants.kTiltI, ArmConstants.kTiltD);
    
        this.wrist = m_wrist;
        this.arm = m_arm;
    }

    @Override
    public void execute() {
        switch (position) {
            case 0:
                // Score in trough
                arm.setTiltSpeed(tiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltTrofPos));
                arm.setExtendSpeed(extendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendTrofPos));             
                break; 
                
            case 1:
                // Score in level 2
                arm.setTiltSpeed(tiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltL2Pos));
                arm.setExtendSpeed(extendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendL2Pos));             
                break; 
            case 2:
                // Score in level 3
                arm.setTiltSpeed(tiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltL2Pos));
                arm.setExtendSpeed(extendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendL2Pos));             
                break;
            
            case 3:
                // Score in level 4
                arm.setTiltSpeed(tiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltL2Pos));
                arm.setExtendSpeed(extendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendL2Pos));             
                break;
            case 4:
                // Ground Pickup

                break;
            case 5:
                // Processor drop off

                break;        
            case 6:
                // Source Pickup
                arm.setTiltSpeed(tiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltSourcePickupPos));
                arm.setExtendSpeed(extendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendSourcePickupPos));             
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
