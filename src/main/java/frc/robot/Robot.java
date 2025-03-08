// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.MathUtil;

/**
 * The methods in this class are called automatically corresponding to each
 * mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the
 * package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  Joystick logitechController = new Joystick(1);
  SparkMax leftMotor = new SparkMax(10, MotorType.kBrushless);
  SparkMax rightMotor = new SparkMax(20, MotorType.kBrushless);
  SparkMax intakeMotor = new SparkMax(30, MotorType.kBrushless);

  private final SparkFlex tiltMasterMotor = new SparkFlex(53, MotorType.kBrushless);
  private final SparkFlex tiltSlaveMotor = new SparkFlex(54, MotorType.kBrushless);

  private final SparkFlex extendMotor = new SparkFlex(55, MotorType.kBrushless);

  private SparkFlexConfig motorConfig = new SparkFlexConfig();
  private SparkFlexConfig slaveMotorConfig = new SparkFlexConfig();

  // set up analog Potentiometer
  AnalogPotentiometer potentiometer = new AnalogPotentiometer(0);
    private final DutyCycleEncoder tiltEncoder = new DutyCycleEncoder(0);

  // For stopping intake motor when coral is intaked
  double intakeSpeed = 0, temp;
  boolean isCoral = false;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  public Robot() {
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // Set yp slave config
    slaveMotorConfig.follow(53);

    // Configure motors
    tiltMasterMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    tiltSlaveMotor.configure(slaveMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  
    extendMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  @Override
  public void teleopPeriodic() {

    tiltMasterMotor.set(MathUtil.clamp(logitechController.getRawAxis(1),-0.18, 0.18));
    tiltMasterMotor.set(MathUtil.clamp(logitechController.getRawAxis(3),-0.05, 0.05));

    //leftMotor.set(Math.pow(logitechController.getRawAxis(1), 3));
    rightMotor.set(MathUtil.clamp(Math.pow(logitechController.getRawAxis(5), 3), -0.1, 0.1));

    // stopper for when coral inside of intake below
    temp = MathUtil.clamp(Math.pow(logitechController.getRawAxis(3) - logitechController.getRawAxis(2), 3), -0.3, 0.3);

    if (temp < 0) {
      // would mean intake backing out
      isCoral = false;
    } else if (isCoral || intakeMotor.getOutputCurrent() > 20) {
      // would mean already tripped or should based on current
      temp = 0;
      isCoral = true;
    }

    // sets speed to intake motor
    intakeSpeed = temp;
    intakeMotor.set(intakeSpeed);

    // display numbers
    SmartDashboard.putNumber("Bus voltage", rightMotor.getBusVoltage());
    SmartDashboard.putNumber("Intake voltage", intakeMotor.get());
    SmartDashboard.putNumber("Intake speed", intakeSpeed);
    SmartDashboard.putNumber("Intake amps", intakeMotor.getOutputCurrent());
    SmartDashboard.putBoolean("isCoral", isCoral);
    SmartDashboard.putNumber("potentiometer", potentiometer.get());

    SmartDashboard.putNumber("Master Motor Speed", tiltMasterMotor.get());
    SmartDashboard.putNumber("Controller number", logitechController.getRawAxis(1) * 0.18);
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

}
