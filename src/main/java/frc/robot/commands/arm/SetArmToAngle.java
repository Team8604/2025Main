package frc.robot.commands.arm;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.Wrist;

public class SetArmToAngle extends Command {
    private int position;

    // Arm base 
    ProfiledPIDController armExtendPid;
    ProfiledPIDController armTiltPid;
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

        //armExtendPid = new ProfiledPIDController(ArmConstants.kExtendP, ArmConstants.kExtendI, ArmConstants.kExtendD, new TrapezoidProfile.Constraints(ArmConstants.kExtendPIDMaxSpeed, ArmConstants.kExtendPIDMaxAcceleration));
        //armTiltPid = new ProfiledPIDController(ArmConstants.kTiltP, ArmConstants.kTiltI, ArmConstants.kTiltD, new TrapezoidProfile.Constraints(ArmConstants.kTiltPIDMaxSpeed, ArmConstants.kTiltPIDMaxAcceleration));
        armTiltPid = new ProfiledPIDController(5, 0, 30, new Constraints(1, 1));

        armTiltPid.setTolerance(5, 0.1);
        this.wrist = m_wrist;
        this.arm = m_arm;
    }

    private void armTilt (double armTiltTarget){
        double armTiltPos = arm.getTiltEncoder();

        if (armTiltPos < armTiltTarget && !(armTiltPos > armTiltTarget-4)) {
            arm.setTiltSpeed(2);
        } else if (arm.getTiltEncoder() > armTiltTarget && !(armTiltPos < armTiltTarget+4)) {
            arm.setTiltSpeed(-2);
        } else {
            arm.setTiltSpeed(0);
        }
    }

    private void armExtend (double armExtendTarget){
        double armExtendPos = arm.getExtendValue();

        if (armExtendPos > armExtendTarget && !(armExtendPos < armExtendTarget-.8)) {
            arm.setExtendSpeed(-3);
        } else if (armExtendPos < armExtendTarget && !(armExtendPos > armExtendTarget+.8)) {
            arm.setExtendSpeed(3);
        } else {
            arm.setExtendSpeed(0);
        }
    }

    private void wristTilt (double wristTiltTarget){
        double wristTiltPos = wrist.getTiltEncoder();

        if (wristTiltPos < wristTiltTarget && !(wristTiltPos > wristTiltTarget-.005)) {
            wrist.setTiltSpeed(1.5);
        } else if (wristTiltPos > wristTiltTarget && !(wristTiltPos < wristTiltTarget+.005)) {
            wrist.setTiltSpeed(-1.5);
        } else {
            wrist.setTiltSpeed(0);
        }
    }

    private void wristTwist (double wristTwistTarget){
        double wristTwistPos = wrist.getTwistEncoder();

        if (wristTwistPos < wristTwistTarget && !(wristTwistPos > wristTwistTarget-.005)) {
            wrist.setTiltSpeed(1.5);
        } else if (wristTwistPos > wristTwistTarget && !(wristTwistPos < wristTwistTarget+.005)) {
            wrist.setTiltSpeed(-1.5);
        } else {
            wrist.setTiltSpeed(0);
        }
    }

    @Override
    public void execute() {
        switch (position) {
            case 0:
                // Score in trough
                //arm.setTiltSpeed(armTiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltTrofPos));
                //arm.setExtendSpeed(extendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendTrofPos));             
                break; 
                
            case 1:
                // Score in level 2
                //arm.setTiltSpeed(armTiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltL2Pos));
                //arm.setExtendSpeed(armExtendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendL2Pos));             
                break; 
            case 2:
                // Score in level 3
                //arm.setTiltSpeed(armTiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltL2Pos));
                //arm.setExtendSpeed(armExtendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendL2Pos));             
                break;
            
            case 3:
                //armTilt(ArmConstants.kTiltL4Pos);
                //armExtend(ArmConstants.kExtendL4Pos);
                //wristTilt(WristConstants.kWristTiltL4Pos);
                wristTwist(0.6);
                break;
            case 4:
                // Ground Pickup

                break;
            case 5:
                // Processor drop off

                break;        
            case 6:
                // Source Pickup
                //arm.setTiltSpeed(armTiltPid.calculate(arm.getTiltEncoder(), ArmConstants.kTiltSourcePickupPos));
                //arm.setExtendSpeed(armExtendPid.calculate(arm.getExtendValue(), ArmConstants.kExtendSourcePickupPos));             
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
