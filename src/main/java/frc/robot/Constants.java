// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be
 * declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class OperatorConstants {
    // Joystick Deadband
    public static final double DEADBAND = 0.1;
    public static final double LEFT_Y_DEADBAND = 0.1;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final double TURN_CONSTANT = 6;
    public static final int kOperatorButtonBoardPort = 0;
  }

  public static class EffectorConstants {
    // TEMPORARY ID
    public static final int kEffector = 50;

    // TEMPORARY speeds
    public static final double kMaxSpeed = 0.5;
    public static final double kIntakeSpeed = 0.3;
    public static final double kOutSpeed = -0.4;
  }

  public static class WristConstants {
    // TEMPORARY CAN IDs for
    public static final int kTwist = 51;
    public static final int kTilt = 52;

    // TEMPORARY speeds TEMPORARY PLACEHOLDERS
    public static final double kMaxTwistSpeed = 0.1;
    public static final double kMaxTiltSpeed = 0.1;

    // Wrist Positions TEMPORARY PLACEHOLDERS figure out from encoders values
    public static final double kMaxPositiveTwist = 90;
    public static final double kMaxNegativeTwist = -90;

    public static final double kMaxPositiveTilt = 90;
    public static final double kMaxNegativeTilt = -90;

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

    // Pickup positions
    public static final double kTiltPickupPos = 0;
    public static final double kRotatePickupPos = 0;


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
    public static final double kExtendP = 0.1;
    public static final double kExtendI = 0;
    public static final double kExtendD = 0;

    public static final double kTiltP = 0.1;
    public static final double kTiltI = 0;
    public static final double kTiltD = 0;

    // Arm Restrainghts 
    public static final double kMaxTiltSpeed = 0.1;
    public static final double kMaxExtendSpeed = 0.1;
    
    // Placeholders (again)
    public static final double kMaxExtend = 0.26; // Value on potentionometer
    public static final double kMinExtend = 0.4;

    public static final double kMaxTilt = 146;
    public static final double kMinTilt = 41;//40;
    
    // Arm positions
    public static final double kTiltStartingPos = 0;
    public static final double kExtendStartingPos = 0;

    // Coral scoring positions 
    public static final double kTiltTrofPos = 45;
    public static final double kExtendTrofPos = 2;
    public static final double kTiltL2Pos = 100;
    public static final double kExtendL2Pos = 3;
    public static final double kTiltL3Pos = 100;
    public static final double kExtendL3Pos = 6;
    public static final double kTiltL4Pos = 100;
    public static final double kExtendL4Pos = 8;

    // Pickup positions
    public static final double kTiltPickupPos = 45;
    public static final double kExtendPickupPos = 2;

    // Constants for calculating max extensions
    public static final double kTiltUpwards = 90; // arm angled straight upwards

    public static final double kMaxDistFromPivotToFront = 20.75; // distance from pivot to extension limit in inches
    public static final double kMaxDistFromPivotToRear = 27.25 + 18; // distance from pivot to extension limit of the other side
  }
}
