package frc.robot.subsystems.arm;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristConstants;

public class Wrist extends SubsystemBase {
    // Set up tilt and twist motors, and encoders
    private final SparkMax tiltMotor = new SparkMax(WristConstants.kTilt, MotorType.kBrushless);
    private final SparkMax twistMotor = new SparkMax(WristConstants.kTwist, MotorType.kBrushless);
    private final RelativeEncoder tiltEncoder = tiltMotor.getAlternateEncoder();
    private final RelativeEncoder twistEncoder = twistMotor.getAlternateEncoder();

    private SparkMaxConfig motorConfig = new SparkMaxConfig();;

    public Wrist() {
        tiltMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        twistMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public double getTwistEncoder() {
        return twistEncoder.getPosition();
    }

    public double getTiltEncoder() {
        return tiltEncoder.getPosition();
    }

    public void setTiltSpeed(double speed) {
        boolean positiveTilt = speed > 0 && getTiltEncoder() > WristConstants.kMaxPositiveTilt;
        boolean negativeTilt = speed < 0 && getTiltEncoder() < WristConstants.kMaxNegativeTilt;

        if (positiveTilt && negativeTilt) {
            tiltMotor.set(WristConstants.kMaxTiltSpeed * MathUtil.clamp(speed, -1, 1));
        } else {
            tiltMotor.set(0);
        }
    }

    public void setTwistSpeed(double speed) {
        boolean positiveTwist = speed > 0 && getTwistEncoder() > WristConstants.kMaxPositiveTwist;
        boolean negativeTwist = speed < 0 && getTwistEncoder() < WristConstants.kMaxNegativeTwist;

        if (positiveTwist && negativeTwist) { 
            twistMotor.set(WristConstants.kMaxTwistSpeed * MathUtil.clamp(speed, -.1, .1));
        } else {
            twistMotor.set(0);
        }
    }

    /** Returns the distance the arm's wrist from the pivot, to the tip of the effector 
     * Works with getCurrentDistacne in Arm subsystem
    */
    public double getCurrentDistance() {
        //
        return 0;
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Wrist Tilt Encoder", getTiltEncoder());
        SmartDashboard.putNumber("Wrist Extend Potentiometer", getTwistEncoder());
        
        SmartDashboard.putNumber("Wrist Extend speed", twistMotor.get());
        SmartDashboard.putNumber("Wrist Tilt speed", tiltMotor.get());

    }
}