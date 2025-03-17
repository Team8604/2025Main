package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.arm.*;
import frc.robot.subsystems.arm.Arm.Position;

public class ButtonBoard {
    CommandGenericHID buttonBoard;
    boolean function;

    /**
     * Command to configure all of the operator controls on the button board
     * 
     * @param m_buttonBoard The board the bindings are being configured for
     */
    public ButtonBoard(CommandGenericHID m_buttonBoard, Arm arm, Wrist wrist, Effector effector) {
        this.buttonBoard = m_buttonBoard;
        
        buttonBoard.button(1).onTrue(Commands.runOnce(this::toggleFunction)); // Toggle
        buttonBoard.button(2).onChange(Commands.runOnce(this::toggleFunction));

        buttonBoard.button(3).whileTrue(Commands.either(new RunEffector(effector, false, true), new RunEffector(effector, false, false), this::getFunction));
        buttonBoard.button(4).whileTrue(Commands.either(new RunEffector(effector, true, true), new RunEffector(effector, true, false), this::getFunction));
        // Button 5 & 6 are for climber
        // For starting pos or run wrist left
        buttonBoard.button(7).whileTrue(Commands.either(new SetArmToAngle(arm, wrist, Position.kStart), new RunWristRotate(wrist, 1), this::getFunction));

        // For source pos or run wrist Right
        buttonBoard.button(8).whileTrue(Commands.either(new SetArmToAngle(arm, wrist, Position.kSource), new RunWristRotate(wrist, -1), this::getFunction));

        // For nothing pos or wrist Down
        buttonBoard.button(10).whileTrue(Commands.either(new SetArmToAngle(arm, wrist, Position.kWristDown), new RunWristTilt(wrist, -1), this::getFunction));

        // For L4 pos or arm out
        buttonBoard.button(11).whileTrue(Commands.either(new SetArmToAngle(arm, wrist, Position.kL4), new RunArmExtend(arm, -1), this::getFunction));

        // For L3 pos or arm in
        buttonBoard.button(12).whileTrue(Commands.either(new SetArmToAngle(arm, wrist, Position.kL3), new RunArmExtend(arm, 1), this::getFunction));

        // For L2 pos or arm up
        buttonBoard.pov(270).whileTrue(Commands.either(new SetArmToAngle(arm, wrist, Position.kL2), new RunArmTilt(arm, 1), this::getFunction));

        // For Trough pos or arm down
        buttonBoard.pov(90).whileTrue(Commands.either(new SetArmToAngle(arm, wrist, Position.kTrough), new RunArmTilt(arm, -1), this::getFunction));

        // For Arm out manually
        buttonBoard.pov(180).whileTrue(new RunArmExtend(arm, -1));

        // Fpr Arm in manually
        buttonBoard.pov(0).whileTrue(new RunArmExtend(arm, 1));

        //buttonBoard.button(0).whileTrue(Commands.either(null, null, this::getFunction));
    }
    
    private void toggleFunction() {
        function = !function;
    }

    public boolean getFunction() {
        return function;
    }
}
