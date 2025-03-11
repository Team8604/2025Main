package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.arm.Arm;

public class RunArm extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public double tiltSpeed, extendSpeed;
    CommandXboxController xboxController;
    Arm arm;

    public RunArm(Arm m_arm, CommandXboxController m_xboxController){
        addRequirements(m_arm);  
        this.xboxController = m_xboxController;  
        this.arm = m_arm;
    }

    @Override
    public void execute() {
        // Send speeds
        tiltSpeed = xboxController.getRawAxis(1);
        extendSpeed = xboxController.getRawAxis(0);

        arm.setTiltSpeed(tiltSpeed);
        arm.setExtendSpeed(extendSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        arm.setTiltSpeed(0);
        arm.setExtendSpeed(0);
    }
}