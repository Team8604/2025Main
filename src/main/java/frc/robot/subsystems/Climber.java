import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase {
    // Extention motor
    private final SparkFlex extendMotor = new SparkFlex(ClimberConstants.kExtend, MotorType.kBrushless);
    
    private final RelativeEncoder climbEncoder = new extendMotor.getEncoder();


    public Climber() {
    }

    public double getClimbEncoder() {
        return climbEncoder.get();
    }

    public void setExtendSpeed(double speed) {
        boolean positiveExtend = (speed < 0 && getExtendValue() > ClimberConstants.kMaxExtend);      ;
        boolean negativeExtend = speed > 0 && getExtendValue() < ClimberConstants.kMinExtend;

        if (positiveExtend || negativeExtend) {
            extendMotor.set(ClimberConstants.kMaxExtendSpeed * speed);
        } else {
            extendMotor.set(0);
        }
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Climber speed", climbEncoder.get());
    }
}