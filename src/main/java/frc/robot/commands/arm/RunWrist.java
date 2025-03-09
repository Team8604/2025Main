package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Wrist;

public class RunWrist extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private double  tiltSpeed, twistSpeed;
    private Wrist   wrist;

    public RunWrist(Wrist m_wrist) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_wrist);
        this.wrist = m_wrist;
    }

    @Override
    public void execute() {
        tiltSpeed = 0;//RobotContainer.m_XboxController.getRawAxis(0);
        twistSpeed = 0;//RobotContainer.m_XboxController.getRawAxis(0);

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