// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import frc.robot.Constants.EffectorConstants;
import frc.robot.subsystems.ButtonBoard;
import frc.robot.subsystems.arm.Effector;
import edu.wpi.first.wpilibj2.command.Command;

/** Command to run Effector to intake and dropOff */
public class RunEffector extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  double effectorSpeed = 0, temp, direction;
  boolean isCoral = false;
  Effector effector;

  /** Sets Effector speed to intake or outtake. */
  public RunEffector(Effector m_effector, double direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_effector);
    this.effector = m_effector;
    this.direction = direction;
  }

  /** Called when the command is initially scheduled. */
  @Override
  public void execute() {
    temp = direction;
    // Set speed
    if (ButtonBoard.getFunction()){
      temp = EffectorConstants.kMaxSpeed;
    } else if (temp > 0 && (isCoral || effector.getOutputCurrent() < 20)) {
      System.out.println("Output" + effector.getOutputCurrent());
      temp = EffectorConstants.kIntakeSpeed;
      isCoral = true;
    } else {
      temp = EffectorConstants.kOutSpeed;
      isCoral = false;
    }

    // sets speed to intake motor
    effector.setSpeed(temp);
  }

  /** Called once the command ends or is interrupted.*/
  @Override
  public void end(boolean interrupted) {
    effector.setSpeed(0);
  }
}