import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Climber;

public class RunClimb extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private double  extendSpeed;
    private Climber climber;

    private int speed = 5; // modify speed here

    public RunClimb(Climber m_climber) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_climber);
        this.climber = m_climber;
    }

    public void out() {
        climber.setExtendSpeed(-speed);
    }

    public void in() {
        climber.setExtendSpeed(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        wrist.setExtendSpeed(0);
    }
}