package frc.robot.subsystems.arm;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristConstants;

public class Wrist extends SubsystemBase {
    // Set up tilt and twist motors, and encoders
    private final SparkMax tiltMotor = new SparkMax(WristConstants.kTilt, MotorType.kBrushless);
    private final SparkMax twistMotor = new SparkMax(WristConstants.kTwist, MotorType.kBrushless);
    private final SparkAbsoluteEncoder tiltEncoder = tiltMotor.getAbsoluteEncoder();
    private final SparkAbsoluteEncoder twistEncoder = twistMotor.getAbsoluteEncoder();

    public Wrist() {
    }

    public double getTwistEncoder() {
        return twistEncoder.getPosition();
    }

    public double getTiltEncoder() {
        return tiltEncoder.getPosition();
    }

    public void setTiltSpeed(double speed) {
        boolean positiveTilt = speed < 0 && getTiltEncoder() > WristConstants.kMaxNegativeTilt;
        boolean negativeTilt = speed > 0 && getTiltEncoder() < WristConstants.kMaxPositiveTilt;

        if (positiveTilt || negativeTilt) {
            tiltMotor.set(WristConstants.kMaxTiltSpeed * speed);
        } else {
            tiltMotor.set(0);
        }
    }

    public void setTwistSpeed(double speed) {
        boolean positiveTwist = speed < 0 && getTwistEncoder() > WristConstants.kMaxNegativeTwist;
        boolean negativeTwist = speed > 0 && getTwistEncoder() < WristConstants.kMaxPositiveTwist;

        if (positiveTwist || negativeTwist) { 
            twistMotor.set(WristConstants.kMaxTwistSpeed * speed);
        } else {
            twistMotor.set(0);
        }
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Wrist Tilt Encoder", getTiltEncoder());
        SmartDashboard.putNumber("Wrist Rotate Encoder", getTwistEncoder());
        
        SmartDashboard.putNumber("Wrist Extend speed", twistMotor.get());
        SmartDashboard.putNumber("Wrist Tilt speed", tiltMotor.get());
        SmartDashboard.putNumber("Wrist Tilt output current", tiltMotor.getOutputCurrent());
    }
}