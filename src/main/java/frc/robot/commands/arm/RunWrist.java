package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class RunWrist extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public double tiltSpeed, twistSpeed;

    public RunWrist() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(RobotContainer.effector);
    }

    @Override
    public void execute() {
        tiltSpeed = 0;//RobotContainer.m_XboxController.getRawAxis(0);
        twistSpeed = 0;//RobotContainer.m_XboxController.getRawAxis(0);

        // Send speeds 
        RobotContainer.wrist.setTiltSpeed(tiltSpeed);
        RobotContainer.wrist.setTwistSpeed(twistSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.wrist.setTiltSpeed(0);
        RobotContainer.wrist.setTwistSpeed(0);
    }
}