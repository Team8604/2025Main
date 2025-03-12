package frc.robot.subsystems.arm;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
    public enum Position {
        kBrushed(0),
        kBrushless(1);
    
        @SuppressWarnings("MemberName")
        public final int value;
    
        Position(int value) {
          this.value = value;
        }
    
        public static Position fromId(int id) {
          for (Position type : values()) {
            if (type.value == id) {
              return type;
            }
          }
          return null;
        }
      }

    // Set up tilt and twist motors, and encoders
    private final SparkFlex tiltMasterMotor = new SparkFlex(ArmConstants.kTiltMaster, MotorType.kBrushless);
    private final SparkFlex tiltSlaveMotor = new SparkFlex(ArmConstants.kTiltSlave, MotorType.kBrushless);
    private final SparkFlex extendMotor = new SparkFlex(ArmConstants.kExtend, MotorType.kBrushless);

    private final DutyCycleEncoder tiltEncoder = new DutyCycleEncoder(ArmConstants.kTiltEncoderPort);    
    private final AnalogPotentiometer potentiometer = new AnalogPotentiometer(ArmConstants.kPotentiometerPort);

    private SparkFlexConfig motorConfig = new SparkFlexConfig();
    private SparkFlexConfig slaveMotorConfig = new SparkFlexConfig();

    public Arm() {
        // Set yp slave config
        slaveMotorConfig.follow(ArmConstants.kTiltMaster);

        // Configure motors
        tiltMasterMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        tiltSlaveMotor.configure(slaveMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        extendMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    /** Returns arm tilt encoder value in degrees*/
    public double getTiltEncoder() {
        return tiltEncoder.get() * 360;
    }

    /** Returns arm extension potentiometer */
    public double getExtendValue() {
        return potentiometer.get();
    }

     /** Returns the maximum distance before extension limit from the arm pivot 
     * to the edge of the effector
     */
    public double getMaxDistance(){
        double tiltEncoderVal = getTiltEncoder();
        
        if (tiltEncoderVal < ArmConstants.kTiltUpwards) {
            // arm would be angled toward back of robot 
            return ArmConstants.kMaxDistFromPivotToRear / Math.cos(tiltEncoderVal);
        }
        return 0; // there is no extension limit based on arm's angle
    }

    /** Returns the distance the arm is from the pivot, to the tip of the wrist joint
     * Works with getCurrentDistacne in Wrist subsystem
     */
    public double getCurrentDistance(){
        //27 inches from base of arm to wrist pitit when all the way in
        //39.25 from base of arm to end of wrist when wrist is all the way out and arm is all the way in
    
        /* Fromulas to get extended value from potentiometer value (use the second one)
        y = -1/73x + 1.1
        x = -73(y - 1.1)

        points if needed (Inches, Potentiometer)
        (27, 0.725), 34, 0.62), (41.5, 0.53)
        */
        return -(73 * (getExtendValue() - 1.1)); // add the wrist part here
    }

    /** Sets arm tilt speed if not going out of limit */
    public void setTiltSpeed(double speed) {
        boolean positiveTilt = (speed > 0 && getTiltEncoder() < ArmConstants.kMaxTilt);
        boolean negativeTilt = speed < 0 && getTiltEncoder() > ArmConstants.kMinTilt;// && (getCurrentDistance() < getMaxDistance());

        if (positiveTilt || negativeTilt) {
            tiltMasterMotor.set(ArmConstants.kMaxTiltSpeed * speed);
        } else {
            tiltMasterMotor.set(0);

        }
    }
     
    /** Sets arm tilt speed if not going out of limit */
    public void setExtendSpeed(double speed) {
        boolean positiveExtend = (speed < 0 && getExtendValue() > ArmConstants.kMaxExtend);// && (getCurrentDistance() < getMaxDistance());        ;
        boolean negativeExtend = speed > 0 && getExtendValue() < ArmConstants.kMinExtend;

        if (positiveExtend || negativeExtend) {
            extendMotor.set(ArmConstants.kMaxExtendSpeed * speed);
        } else {
            extendMotor.set(0);
        }
    }

    public double getTiltSpeed(){
        return tiltMasterMotor.get();
    }    
    
    public double getExtendSpeed(){
        return extendMotor.get();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Arm Tilt Encoder", getTiltEncoder());
        SmartDashboard.putNumber("Arm Extend Potentiometer", getExtendValue());
        
        SmartDashboard.putNumber("Arm Extend speed", extendMotor.get());
        SmartDashboard.putNumber("Arm Tilt speed", tiltMasterMotor.get());

        SmartDashboard.putNumber("Arm Max Distance", getMaxDistance());
        SmartDashboard.putNumber("Arm Current Distance", getCurrentDistance());
    }
}
