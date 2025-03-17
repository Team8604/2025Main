package frc.robot.subsystems.arm;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
    
    /** Sets arm to angle with pids
     * positions - 0-trough, 1-L2, 2-L3, 3-L4
     * 4-Ground, 5-Processor, 6-Source, 7-Start
     * 8-lower Algae, 9-Higher Algae
     */
    public enum Position {
        kTrough(0),
        kL2(1),
        kL3(2),
        kL4(3),
        kGround(4),
        kProcessor(5),
        kSource(6),
        kStart(7),
        kLowAlgae(8),
        kHighAlgae(9),
        kWristDown(10);

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
    private final static SparkFlex extendMotor = new SparkFlex(ArmConstants.kExtend, MotorType.kBrushless);

    private final DutyCycleEncoder tiltEncoder = new DutyCycleEncoder(ArmConstants.kTiltEncoderPort);

    public Arm() {
        extendMotor.getEncoder().setPosition(0);
    }

    /** Returns arm tilt encoder value in degrees */
    public double getTiltEncoder() {
        return tiltEncoder.get() * 360;
    }

    /** Returns arm extension potentiometer */
    public double getExtendEncoder() {
        return extendMotor.getEncoder().getPosition() * -1;
    }

    /**
     * Returns the maximum distance before extension limit from the arm pivot
     * to the edge of the effector
     */
    public double getMaxDistance() {
        double tiltEncoderVal = getTiltEncoder();

        if (tiltEncoderVal < ArmConstants.kTiltUpwards) {
            // arm would be angled toward back of robot
            return ArmConstants.kMaxDistFromPivotToRear
                    / Math.abs(Math.cos(tiltEncoderVal - ArmConstants.kArmTiltLevel));
        }
        return 999; // there is no extension limit based on arm's angle
    }

    /**
     * Returns the distance the arm is from the pivot, to the tip of the wrist joint
     * Works with getCurrentDistacne in Wrist subsystem
     */
    public double getCurrentDistance() {
        // 27 inches from base of arm to wrist pitit when all the way in
        // 39.25 from base of arm to end of wrist when wrist is all the way out and arm
        // is all the way in

        /*
         * Fromulas to get extended value from encoder value (use the second one)
         * y = 8.5 * (x - 27)
         * x = (y/8.5) + 27
         * 
         * points if needed (Inches, encoder)
         * (27, 0), (41.5, 123.721)
         */
        return (getExtendEncoder() / 8.5) + 27; // 27 is the wrist part
    }

    /** Sets arm tilt speed if not going out of limit */
    public void setTiltSpeed(double speed) {
        boolean positiveTilt = (speed > 0 && getTiltEncoder() < ArmConstants.kMaxTilt);
        boolean negativeTilt = speed < 0 && getTiltEncoder() > ArmConstants.kMinTilt
                && (getCurrentDistance() < getMaxDistance());

        if (positiveTilt || negativeTilt) {
            tiltMasterMotor.set(ArmConstants.kMaxTiltSpeed * speed);
        } else {
            tiltMasterMotor.set(0);
        }
    }

    /** Sets arm tilt speed if not going out of limit */
    public void setExtendSpeed(double speed) {
        boolean positiveExtend = (speed < 0 && getExtendEncoder() < ArmConstants.kMaxExtend)
                && (getCurrentDistance() < getMaxDistance());
        boolean negativeExtend = speed > 0 && getExtendEncoder() > ArmConstants.kMinExtend;

        if (positiveExtend || negativeExtend) {
            extendMotor.set(ArmConstants.kMaxExtendSpeed * speed);
        } else {
            extendMotor.set(0);
        }
    }

    public double getTiltSpeed() {
        return tiltMasterMotor.get();
    }

    public double getExtendSpeed() {
        return extendMotor.get();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm Tilt Encoder", getTiltEncoder());
        SmartDashboard.putNumber("Arm Extend Encoder", getExtendEncoder());

        SmartDashboard.putNumber("Arm Extend speed", extendMotor.get());
        SmartDashboard.putNumber("Arm Tilt speed", tiltMasterMotor.get());

        SmartDashboard.putNumber("Arm Max Distance", getMaxDistance());

        SmartDashboard.putNumber("Arm Current Distance", getCurrentDistance());
    }
}
