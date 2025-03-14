package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.arm.Wrist;

public class RunWristTilt extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private double  tiltSpeed;
    CommandXboxController xboxController;
    private Wrist   wrist;

    public RunWristTilt(Wrist m_wrist, double tiltSpeed) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_wrist);
        this.wrist = m_wrist;
        this.tiltSpeed = tiltSpeed;
    }

    @Override
    public void execute() {
        // Send speeds 
        wrist.setTiltSpeed(tiltSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        wrist.setTiltSpeed(0);
    }
}