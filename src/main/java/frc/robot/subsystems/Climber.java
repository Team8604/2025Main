package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase {
    // Extention motor
    private final SparkFlex extendMotor = new SparkFlex(ClimberConstants.kExtend, MotorType.kBrushless);
    
    public Climber() {
        extendMotor.getEncoder().setPosition(0);
    }

    public double getClimbEncoder() {
        return extendMotor.get();
    }

    public void setExtendSpeed(double speed) {
        boolean positiveExtend = (speed < 0 && getClimbEncoder() > ClimberConstants.kMaxExtend);
        boolean negativeExtend = speed > 0 && getClimbEncoder() < ClimberConstants.kMinExtend;

        if (positiveExtend || negativeExtend) {
            extendMotor.set(ClimberConstants.kMaxExtendSpeed * speed);
        } else {
            extendMotor.set(0);
        }
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Climber speed", extendMotor.get());
    }
}