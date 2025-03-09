// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.arm.*;
import frc.robot.commands.arm.ArmSetToPoint.SetArmToAngle;
import frc.robot.subsystems.Effector;
import frc.robot.subsystems.arm.*;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Effector effector = new Effector();
  public static Wrist wrist = new Wrist();
  public static Arm arm = new Arm();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public static CommandXboxController m_operatorButtonBoard = new CommandXboxController(
      OperatorConstants.kOperatorButtonBoardPort);

  public static CommandXboxController m_XboxController = new CommandXboxController(1);

  // Operator buttons
  public static Trigger buttonBoardOne = m_operatorButtonBoard.button(1);
  public static Trigger buttonBoardTwo = m_operatorButtonBoard.button(2);
  public static Trigger buttonBoardThree = m_operatorButtonBoard.button(3);
  public static Trigger buttonBoardFour = m_operatorButtonBoard.button(4);
  public static Trigger buttonBoardFive = m_operatorButtonBoard.button(5);
  public static Trigger buttonBoardSix = m_operatorButtonBoard.button(6);
  public static Trigger buttonBoardSeven = m_operatorButtonBoard.button(7);
  public static Trigger buttonBoardEight = m_operatorButtonBoard.button(8);
  public static Trigger buttonBoardNine = m_operatorButtonBoard.button(9);
  public static Trigger buttonBoardTen = m_operatorButtonBoard.button(10);
  public static Trigger buttonBoardEleven = m_operatorButtonBoard.button(11);
  public static Trigger buttonBoardTwelve = m_operatorButtonBoard.button(12);


  public static Trigger a = m_XboxController.a();
  public static Trigger x = m_XboxController.x();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // Set default commands
    CommandScheduler.getInstance().setDefaultCommand(RobotContainer.arm, new RunArm());//m_XboxController.getRawAxis(1), m_XboxController.getRawAxis(5)));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    //new Trigger(m_exampleSubsystem::exampleCondition).onTrue(new ExampleCommand(m_exampleSubsystem));

    //buttonBoardOne.whileTrue(new RunEffector(EffectorConstants.kIntakeSpeed));
    //a.whileTrue(new RunArm(0.1, 0));
    //x.whileTrue(new SetArmToAngle(6));

  }
}
