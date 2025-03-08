package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class RunArm extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public double tiltSpeed, extendSpeed;

    public RunArm(){//double tiltSpeed, double extendSpeed) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(RobotContainer.arm);

        //this.tiltSpeed = tiltSpeed;
        //this.extendSpeed = extendSpeed;
        
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        // Send speeds
        tiltSpeed = RobotContainer.m_XboxController.getRawAxis(1);
        extendSpeed = RobotContainer.m_XboxController.getRawAxis(5);

        RobotContainer.arm.setTiltSpeed(tiltSpeed);
        RobotContainer.arm.setExtendSpeed(extendSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.arm.setTiltSpeed(0);
        RobotContainer.arm.setExtendSpeed(0);
    }
}