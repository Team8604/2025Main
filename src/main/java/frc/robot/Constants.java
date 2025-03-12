// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
  public static final double MAX_SPEED  = Units.feetToMeters(14.5);
  // Maximum speed of the robot in meters per second, used to limit acceleration.

//  public static final class AutonConstants
//  {
//
//    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
//    public static final PIDConstants ANGLE_PID       = new PIDConstants(0.4, 0, 0.01);
//  }

  public static final class DrivebaseConstants
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double DEADBAND        = 0.1;
    public static final double LEFT_Y_DEADBAND = 0.1;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final double TURN_CONSTANT    = 6;
  }

  
  public static class EffectorConstants {
    // TEMPORARY ID
    public static final int kEffector = 50;

    // TEMPORARY speeds
    public static final double kMaxSpeed = 0.6;
    public static final double kIntakeSpeed = 0.4;
  }

  public static class WristConstants {
    // TEMPORARY CAN IDs for
    public static final int kTwist = 51;
    public static final int kTilt = 52;

    // TEMPORARY speeds TEMPORARY PLACEHOLDERS
    public static final double kMaxTwistSpeed = 0.1;
    public static final double kMaxTiltSpeed = 0.1;

    // Wrist Positions TEMPORARY PLACEHOLDERS figure out from encoders values
    public static final double kMaxPositiveTwist = 0.745;
    public static final double kMaxNegativeTwist = 0.5;

    public static final double kMaxPositiveTilt = 0.81;
    public static final double kMaxNegativeTilt = 0.24;

    // Wrist positions
    public static final double kTiltStartingPos = 0;
    public static final double kRotateStartingPos = 0;

    // Coral scoring positions 
    public static final double kWristTiltTrofPos = 0;
    public static final double kRotateTrofPos = 0;
    public static final double kWristTiltL1Pos = 0;
    public static final double kRotateL1Pos = 0;
    public static final double kWristTiltL2Pos = 0;
    public static final double kRotateL2Pos = 0;
    public static final double kWristTiltL3Pos = 0;
    public static final double kRotateL3Pos = 0;
    public static final double kWristTiltL4Pos = 0;
    public static final double kRotateL4Pos = 0;

    // Pickup positions
    public static final double kTiltPickupPos = 0.45;
    public static final double kRotatePickupPos = 0.5;


    // Wrist Pid Values
    public static final double kTwistP = 0.1;
    public static final double kTwistI = 0;
    public static final double kTwistD = 0;

    public static final double kWristTiltP = 0.1;
    public static final double kWristTiltI = 0;
    public static final double kWristTiltD = 0;
  }

  public static class ArmConstants {
    // TEMPORARY CAN IDs for motors
    public static final int kTiltMaster = 53;
    public static final int kTiltSlave = 54;
    public static final int kExtend = 55;

    // Ports on RobioRio
    public static final int kTiltEncoderPort = 0; 
    public static final int kPotentiometerPort = 0;

    // Arm Pid Values
    public static final double kExtendP = 1;
    public static final double kExtendI = 0;
    public static final double kExtendD = 0;

    public static final double kTiltP = 1;
    public static final double kTiltI = 0;
    public static final double kTiltD = 5;

    // Arm Restrainghts 
    public static final double kMaxTiltSpeed = 0.1;
    public static final double kMaxExtendSpeed = 0.1;

    public static final double kTiltPIDMaxSpeed = 1;
    public static final double kTiltPIDMaxAcceleration = 0.05;  
    public static final double kExtendPIDMaxSpeed = 0.1;
    public static final double kExtendPIDMaxAcceleration = 0.1;  

    // Placeholders (again)
    public static final double kMaxExtend = 0.23; // .26 Value on potentionometer
    public static final double kMinExtend = 0.725;//.4

    public static final double kMaxTilt = 145;//146
    public static final double kMinTilt = 41;//41;
    
    // Arm positions
    public static final double kTiltStartingPos = 0;
    public static final double kExtendStartingPos = 0;

    // Coral scoring positions 
    public static final double kTiltTrofPos = 145.8;
    public static final double kExtendTrofPos = .72;
    public static final double kTiltL2Pos = 100;
    public static final double kExtendL2Pos = 3;
    public static final double kTiltL3Pos = 139;
    public static final double kExtendL3Pos = 0.53;
    public static final double kTiltL4Pos = 140;
    public static final double kExtendL4Pos = 0.22;

    // Pickup positions
    public static final double kTiltSourcePickupPos = 93.76;
    public static final double kExtendSourcePickupPos = .72;
    public static final double kTiltGroundPickupPos = 91.7;
    public static final double kExtendGroundPickupPos = .72;
    
    // Constants for calculating max extensions
    public static final double kTiltUpwards = 130; // arm angled straight upwards

    public static final double kMaxDistFromPivotToFront = 20.75; // distance from pivot to extension limit in inches
    public static final double kMaxDistFromPivotToRear = 27.25 + 18; // distance from pivot to extension limit of the other side
  }

}
