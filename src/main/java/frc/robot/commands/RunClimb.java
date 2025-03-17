package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class RunClimb extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private double  extendSpeed;
    private Climber climber;

    private boolean direction;
    private int speed = 5; // modify speed here

    public RunClimb(Climber m_climber, Boolean direction) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_climber);
        this.climber = m_climber;
        this.direction = direction; // up = true, down = false

    }

    @Override
    public void execute() {
        if (this.direction == true) {
            climber.setExtendSpeed(speed);
        } else {
            climber.setExtendSpeed(-speed);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        climber.setExtendSpeed(0);
    }
}