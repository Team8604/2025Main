package frc.robot.commands.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.Arm.Position;
import frc.robot.subsystems.arm.Wrist;

public class SetArmToAngle extends Command {
    private final PIDController wristTilt = new PIDController(WristConstants.kTiltP, 0, WristConstants.kTiltD);
    private final PIDController wristTwist = new PIDController(WristConstants.kTwistP, 0, WristConstants.kTwistD);
    private final PIDController armTilt = new PIDController(ArmConstants.kTiltP, 0, ArmConstants.kTiltD);
    private final PIDController armExtend = new PIDController(ArmConstants.kExtendP, 0, ArmConstants.kExtendD);

    // Arm base 
    Wrist wrist;
    Arm arm;

    boolean start;
    boolean source;

    /** Sets arm to angle with pids
     * positions - 0-trough, 1-L2, 2-L3, 3-L4
     * 4-Ground, 5-Processor, 6-Source, 7-Barge
     * 8-lower Alague, 9-Higher Alague
     */
    public SetArmToAngle(Arm m_arm, Wrist m_wrist, Position position) { 
        addRequirements(m_arm, m_wrist);
        this.wrist = m_wrist;
        this.arm = m_arm;

        start = position == Position.kStart;
        source = position == Position.kSource;

        armExtend.setTolerance(3,1);
        armTilt.setTolerance(3,1);
        wristTilt.setTolerance(3,1);
        wristTwist.setTolerance(3,1);

        switch (position) {
            case kTrough:
                // Score in trough
                armTilt.setSetpoint(ArmConstants.kTiltTrofPos);
                armExtend.setSetpoint(ArmConstants.kExtendTrofPos);
                wristTilt.setSetpoint(WristConstants.kTiltTrofPos);
                wristTwist.setSetpoint(WristConstants.kRotateTrofPos);
                break; 
                
            case kL2:
                // Score in level 2
                armTilt.setSetpoint(ArmConstants.kTiltL2Pos);
                armExtend.setSetpoint(ArmConstants.kExtendL2Pos);
                wristTilt.setSetpoint(WristConstants.kTiltL2Pos);
                wristTwist.setSetpoint(WristConstants.kRotateL2Pos);
                break; 
            case kL3:
                // Score in level 3
                armTilt.setSetpoint(ArmConstants.kTiltL3Pos);
                armExtend.setSetpoint(ArmConstants.kExtendL3Pos);
                wristTilt.setSetpoint(WristConstants.kTiltL3Pos);
                wristTwist.setSetpoint(WristConstants.kRotateL3Pos);
                break;
            
            case kL4:
                // Score in Level 4
                armTilt.setSetpoint(ArmConstants.kTiltL4Pos);
                armExtend.setSetpoint(ArmConstants.kExtendL4Pos);
                wristTilt.setSetpoint(WristConstants.kTiltL4Pos);
                wristTwist.setSetpoint(WristConstants.kRotateL4Pos);
                break;

            case kGround:
                // Ground Pickup
                break;

            case kProcessor:
                // Processor drop off
                break; 

            case kSource:
                // Source Pickup
                armExtend.setSetpoint(ArmConstants.kExtendSourcePickupPos);
                wristTwist.setSetpoint(WristConstants.kRotatePickupPos);
                wristTilt.setSetpoint(WristConstants.kTiltPickupPos);
                armTilt.setSetpoint(ArmConstants.kTiltSourcePickupPos);
                break;

            case kStart:
                // Starting Pos
                armExtend.setSetpoint(ArmConstants.kExtendStartingPos);
                wristTwist.setSetpoint(WristConstants.kRotateStartingPos);
                wristTilt.setSetpoint(WristConstants.kTiltStartingPos);
                armTilt.setSetpoint(ArmConstants.kTiltStartingPos);

                break;

            case kLowAlgae:
                // Lower Algae Pickup
                break;

            case kHighAlgae:
                // Higher Algae Pickup
                break;

            case kNothing:
                armExtend.close();
                armTilt.close();
                wristTilt.close();
                wristTwist.close();
                break;
        }
    }


    @Override
    public void execute() {
        // Exceptions in the cases that Robert had in the switch statement, not 
        if (start) {
            arm.setExtendSpeed(armExtend.calculate(arm.getExtendEncoder()));
            wrist.setTiltSpeed(wristTilt.calculate(wrist.getTiltEncoder()));
            wrist.setTwistSpeed(wristTwist.calculate(wrist.getTwistEncoder()));
            if (arm.getExtendEncoder() < 8 && wrist.getTiltEncoder() > .54) {
                arm.setTiltSpeed(armTilt.calculate(arm.getTiltEncoder()));
            }
        } else if (source) {
            arm.setExtendSpeed(armExtend.calculate(arm.getExtendEncoder()));
            wrist.setTiltSpeed(wristTilt.calculate(wrist.getTiltEncoder()));
            wrist.setTwistSpeed(wristTwist.calculate(wrist.getTwistEncoder()));
            if (arm.getExtendEncoder() < 20) {
                arm.setTiltSpeed(armTilt.calculate(arm.getTiltEncoder()));
            }
        } else {
            arm.setTiltSpeed(armTilt.calculate(arm.getTiltEncoder()));
            if (arm.getTiltEncoder() > 70) {
                arm.setExtendSpeed(armExtend.calculate(arm.getExtendEncoder()));
                wrist.setTiltSpeed(wristTilt.calculate(wrist.getTiltEncoder()));
                wrist.setTwistSpeed(wristTwist.calculate(wrist.getTwistEncoder()));
            }
        }
    }

    @Override
    public boolean isFinished() {
        return armExtend.atSetpoint() && armTilt.atSetpoint() && wristTilt.atSetpoint() && wristTwist.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        armExtend.close();
        armTilt.close();
        wristTilt.close();
        wristTwist.close();
        wrist.setTiltSpeed(0);
        wrist.setTwistSpeed(0);
        arm.setTiltSpeed(0);
        arm.setExtendSpeed(0);
    }
}
