// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import frc.robot.Constants.EffectorConstants;
import frc.robot.subsystems.arm.Effector;

import edu.wpi.first.wpilibj2.command.Command;

/** Command to run Effector to intake and dropOff */
public class RunEffector extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  boolean intake, fast;
  static boolean isCoral = false;
  Effector effector;
  int over = 0;

  /**
   * Sets effector speed
   * 
   * @param m_effector subsystem
   * @param intake direction (in or out)
   * @param fast whether the speed is fast or slow
   */
  public RunEffector(Effector m_effector, boolean intake, boolean fast) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_effector);
    this.effector = m_effector;
    this.intake = intake;
    this.fast = fast;
  }

  /** Called when the command is initially scheduled. */
  @Override
  public void initialize() {
    effector.setSpeed((fast ? EffectorConstants.kMaxSpeed : EffectorConstants.kIntakeSpeed) * (intake ? 1 : -.3));
    over = 0;
  }
  
  @Override
  public void execute(){
    over += (effector.getOutputCurrent() > 15) ? 1 : 0;
  }

  @Override
  public boolean isFinished() {
    return (over > 10);
  }

  /** Called once the command ends or is interrupted.*/
  @Override
  public void end(boolean interrupted) {
    effector.setSpeed(0);
  }
}