package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ButtonBoard;
import frc.robot.subsystems.arm.Arm;

public class RunArmTilt extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public double tiltSpeed;
    Arm arm;

    public RunArmTilt(Arm m_arm, double tiltSpeed){
        addRequirements(m_arm);  
        this.arm = m_arm;
        this.tiltSpeed = tiltSpeed;
    }

    @Override
    public void execute() {
        arm.setTiltSpeed(tiltSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        arm.setTiltSpeed(0);
    }
}