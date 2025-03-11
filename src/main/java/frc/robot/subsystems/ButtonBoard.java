package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
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
        
        buttonBoard.button(0).onTrue(Commands.runOnce(this::toggleFunction)); // Toggle
        buttonBoard.button(0).onChange(Commands.runOnce(this::toggleFunction));
        
        //buttonBoard.button(0).whileTrue(Commands.either(null, null, this::getFunction));
    }
    
    private void toggleFunction() {
        this.function = !function;
    }

    public boolean getFunction() {
        return function;
    }
}
