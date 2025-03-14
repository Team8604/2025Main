package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Arm;

public class RunArmExtend extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public double extendSpeed;
    Arm arm;

    public RunArmExtend(Arm m_arm, double extendSpeed){
        addRequirements(m_arm);  
        this.arm = m_arm;
        this.extendSpeed = extendSpeed;
    }

    @Override
    public void execute() {
        arm.setExtendSpeed(extendSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        arm.setExtendSpeed(0);
    }
}