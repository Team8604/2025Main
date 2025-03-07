package frc.robot.commands.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Constants.WristConstants;

public class SetWristToAngle extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

    PIDController twistPid;
    PIDController tiltPid;

    double twistTarget, tiltTarget;

    public SetWristToAngle(double twistTarget, double tiltTarget) {
        this.twistTarget = twistTarget;
        this.tiltTarget = tiltTarget;

        twistPid = new PIDController(WristConstants.kTwistP, WristConstants.kTwistI, WristConstants.kTwistD);
        //tiltPid = new PIDController(WristConstants.kTiltP, WristConstants.kTiltI, WristConstants.kTiltD);
    }

    @Override
    public void execute() {
        RobotContainer.wrist.setTwistSpeed(twistPid.calculate(RobotContainer.wrist.getTwistEncoder(), twistTarget));
        RobotContainer.wrist.setTiltSpeed(tiltPid.calculate(RobotContainer.wrist.getTiltEncoder(), tiltTarget));
    }

    @Override
    public boolean isFinished() {
        if (twistPid.atSetpoint() && tiltPid.atSetpoint()){
            return true;
        } else {
            return false;
        }
    }
}
