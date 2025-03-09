// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import frc.robot.subsystems.arm.Effector;
import edu.wpi.first.wpilibj2.command.Command;

/** Command to run Effector to intake and dropOff */
public class RunEffector extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  double effectorSpeed = 0, temp;
  boolean isCoral = false;
  Effector effector;

  /** Sets Effector speed to intake or outtake. */
  public RunEffector(Effector m_effector, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_effector);
    temp = speed;
    this.effector = m_effector;
  }

  /** Called when the command is initially scheduled. */
  @Override
  public void execute() {
    effector.setSpeed(effectorSpeed);
    if (temp < 0) {
      // would mean intake backing out
      isCoral = false;
    } else if (isCoral || effector.getOutputCurrent() > 20) {
      // would mean already tripped or should based on current
      temp = 0;
      isCoral = true;
    }

    // sets speed to intake motor
    effectorSpeed = temp;
    effector.setSpeed(effectorSpeed);
  }

  /** Called once the command ends or is interrupted.*/
  @Override
  public void end(boolean interrupted) {
    effector.setSpeed(0);
  }
}