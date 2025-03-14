package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.arm.Wrist;

public class RunWristRotate extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private double  rotateSpeed;
    CommandXboxController xboxController;
    private Wrist   wrist;

    public RunWristRotate(Wrist m_wrist, double rotateSpeed) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_wrist);
        this.wrist = m_wrist;
        this.rotateSpeed = rotateSpeed;
    }

    @Override
    public void execute() {
        // Send speeds 
        wrist.setTwistSpeed(rotateSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        wrist.setTwistSpeed(0);
    }
}