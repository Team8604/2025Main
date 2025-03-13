package frc.robot.subsystems.arm;  

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.EffectorConstants;

public class Effector extends SubsystemBase{
    //initialize motor
    private final SparkMax effectorMotor = new SparkMax(EffectorConstants.kEffector, MotorType.kBrushless);
    public Effector() {
    }

    public void setSpeed(double speed) {
        effectorMotor.set(MathUtil.clamp(speed, -1, 1));
    }

    public double getOutputCurrent(){
        return effectorMotor.getOutputCurrent();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Effector speed", effectorMotor.get());
    }
}