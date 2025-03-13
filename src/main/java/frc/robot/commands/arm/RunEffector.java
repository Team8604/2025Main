// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import frc.robot.RobotContainer;
import frc.robot.Constants.EffectorConstants;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.Effector;

import java.util.function.BooleanSupplier;

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
   * @param m_effector
   * @param intake
   * @param fast whether the 
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
    effector.setSpeed((fast ? EffectorConstants.kMaxSpeed : EffectorConstants.kIntakeSpeed) * (intake ? 1 : (Arm.getExtendValue() > 0.7 ? -.7 : -0.3)));
    over = 0;
  }
  
  @Override
  public void execute(){
    over += (effector.getOutputCurrent() > 15) ? 1 : 0;
    if (over > 10) {
      effector.setSpeed(0);
    }
    
  }

  /** Called once the command ends or is interrupted.*/
  @Override
  public void end(boolean interrupted) {
    effector.setSpeed(0);
  }
}