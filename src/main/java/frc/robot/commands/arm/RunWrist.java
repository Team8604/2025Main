package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.arm.Wrist;

public class RunWrist extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private double  tiltSpeed, twistSpeed;
    CommandXboxController xboxController;
    private Wrist   wrist;

    public RunWrist(Wrist m_wrist, CommandXboxController m_xboxController) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_wrist);
        this.xboxController = m_xboxController;  
        this.wrist = m_wrist;
    }

    @Override
    public void execute() {
        tiltSpeed = xboxController.getRawAxis(5);
        twistSpeed = xboxController.getRawAxis(4);

        // Send speeds 
        wrist.setTiltSpeed(tiltSpeed);
        wrist.setTwistSpeed(twistSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        wrist.setTiltSpeed(0);
        wrist.setTwistSpeed(0);
    }
}