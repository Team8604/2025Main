package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.arm.*;

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
        buttonBoard.button(7).whileTrue(new SetArmToAngle(arm, wrist, 7));

        buttonBoard.button(8).whileTrue(new SetArmToAngle(arm, wrist, 6));
        // Button 9 is for Processor - which we cannot do
        buttonBoard.button(10).whileTrue(new SetArmToAngle(arm, wrist, 4));
        buttonBoard.button(11).whileTrue(new SetArmToAngle(arm, wrist, 3));
        buttonBoard.button(12).whileTrue(new SetArmToAngle(arm, wrist, 2));

        buttonBoard.pov(270).whileTrue(new SetArmToAngle(arm, wrist, 1));
        buttonBoard.pov(90).whileTrue(new SetArmToAngle(arm, wrist, 0));
        buttonBoard.pov(180).whileTrue(new SetArmToAngle(arm, wrist, 9));
        buttonBoard.pov(0).whileTrue(new SetArmToAngle(arm, wrist, 8));
        
        //buttonBoard.button(0).whileTrue(Commands.either(null, null, this::getFunction));
    }
    
    private void toggleFunction() {
        function = !function;
    }

    private boolean getFunction() {
        return function;
    }
}
