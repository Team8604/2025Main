package frc.robot.subsystems.arm;  

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.EffectorConstants;

public class Effector extends SubsystemBase{
    //initialize motor
    private final SparkMax effectorMotor = new SparkMax(EffectorConstants.kEffector, MotorType.kBrushless);
    private SparkMaxConfig motorConfig = new SparkMaxConfig();;
    public Effector() {
        effectorMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public void setSpeed(double speed) {
        effectorMotor.set(EffectorConstants.kMaxSpeed * MathUtil.clamp(speed, -1, 1));
    }

    public double getOutputCurrent(){
        return effectorMotor.getOutputCurrent();
    }
}