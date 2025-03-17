package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.Arm.Position;
import frc.robot.subsystems.arm.Wrist;

public class SetArmToAngle extends Command {
    private Position position;

    // Arm base 
    Wrist wrist;
    Arm arm;

    /** Sets arm to angle with pids
     * positions - 0-trough, 1-L2, 2-L3, 3-L4
     * 4-Ground, 5-Processor, 6-Source, 7-Barge
     * 8-lower Alague, 9-Higher Alague
     */
    public SetArmToAngle(Arm m_arm, Wrist m_wrist, Position position) { 
        addRequirements(m_arm, m_wrist);
        this.position = position;

        this.wrist = m_wrist;
        this.arm = m_arm;
    }

    private void armTilt (double armTiltTarget){
        double armTiltPos = arm.getTiltEncoder();

        if (armTiltPos < armTiltTarget && !(armTiltPos > armTiltTarget-4)) {
            arm.setTiltSpeed(2.5);
        } else if (arm.getTiltEncoder() > armTiltTarget && !(armTiltPos < armTiltTarget+4)) {
            arm.setTiltSpeed(-2.5);
        } else {
            arm.setTiltSpeed(0);
        }
    }

    private void armExtend (double armExtendTarget){
        double armExtendPos = arm.getExtendEncoder();

        // Speed is negative when going outwards
        // Encoder = 0 when in all the way
        if (armExtendPos > armExtendTarget && !(armExtendPos < armExtendTarget+3)) {
            arm.setExtendSpeed(3.5); 
        } else if (armExtendPos < armExtendTarget && !(armExtendPos > armExtendTarget+3)) {
            arm.setExtendSpeed(-3.5);
        } else {
            arm.setExtendSpeed(0);
        }
    }

    private void wristTilt (double wristTiltTarget){
        double wristTiltPos = wrist.getTiltEncoder();

        if (wristTiltPos < wristTiltTarget && !(wristTiltPos > wristTiltTarget-.05)) {
            wrist.setTiltSpeed(4);
        } else if (wristTiltPos > wristTiltTarget && !(wristTiltPos < wristTiltTarget+.05)) {
            wrist.setTiltSpeed(-4);
        } else {
            wrist.setTiltSpeed(0);
        }
    }

    private void wristTwist (double wristTwistTarget){
        double wristTwistPos = wrist.getTwistEncoder();

        if (wristTwistPos < wristTwistTarget && !(wristTwistPos > wristTwistTarget+.2)) {
            wrist.setTwistSpeed(0.25);
        } else if (wristTwistPos > wristTwistTarget && !(wristTwistPos < wristTwistTarget-.1)) {
            wrist.setTwistSpeed(-0.25);
        } else {
            wrist.setTiltSpeed(0);
        }
    }

    @Override
    public void execute() {
        switch (position) {
            case kTrough:
                // Score in trough
                armTilt(ArmConstants.kTiltTrofPos);
                if (arm.getTiltEncoder() > 70) {
                    armExtend(ArmConstants.kExtendTrofPos);
                    wristTilt(WristConstants.kWristTiltTrofPos);
                    wristTwist(WristConstants.kRotateTrofPos);
                }
                break; 
                
            case kL2:
                // Score in level 2
                armTilt(ArmConstants.kTiltL2Pos);
                if (arm.getTiltEncoder() > 70) {
                    armExtend(ArmConstants.kExtendL2Pos);
                    wristTilt(WristConstants.kWristTiltL2Pos);
                    wristTwist(WristConstants.kRotateL2Pos);
                }
                break; 
            case kL3:
                // Score in level 3
                armTilt(ArmConstants.kTiltL3Pos);
                if (arm.getTiltEncoder() > 70) {
                    armExtend(ArmConstants.kExtendL3Pos);
                    wristTilt(WristConstants.kWristTiltL3Pos);
                    wristTwist(WristConstants.kRotateL3Pos);
                }
                break;
            
            case kL4:
                // Score in Level 4
                armTilt(ArmConstants.kTiltL4Pos);
                if (arm.getTiltEncoder() > 70) {
                    armExtend(ArmConstants.kExtendL4Pos);
                    wristTilt(WristConstants.kWristTiltL4Pos);
                    wristTwist(WristConstants.kRotateL4Pos);
                }
                break;
            case kGround:
                // Ground Pickup

                break;
            case kProcessor:
                // Processor drop off

                break;        
            case kSource:
                // Source Pickup
                armExtend(ArmConstants.kExtendSourcePickupPos);
                wristTwist(WristConstants.kRotatePickupPos);
                wristTilt(WristConstants.kTiltPickupPos);
                if (arm.getExtendEncoder() < 20) {
                    armTilt(ArmConstants.kTiltSourcePickupPos);
                }
                break;
            case kStart:
                // Starting Pos
                armExtend(ArmConstants.kExtendStartingPos);
                wristTwist(WristConstants.kRotateStartingPos);
                wristTilt(WristConstants.kTiltStartingPos);
                if (arm.getExtendEncoder() < 8 && wrist.getTiltEncoder() > .54) {
                    armTilt(ArmConstants.kTiltStartingPos);
                }

                break;
            case kLowAlgae:
                // Lower Algae Pickup

                break;
            case kHighAlgae:
                // Higher Algae Pickup
                break;

            case kWristDown:

                
                break;
        } 
    }
}
