package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import frc.robot.subsystems.arm.*;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;
import frc.robot.commands.RunClimb;

public class ButtonBoard {
    CommandGenericHID buttonBoard;
    boolean function;

    /**
     * Command to configure all of the operator controls on the button board
     * 
     * @param m_buttonBoard The board the bindings are being configured for
     */
    public ButtonBoard(CommandGenericHID m_buttonBoard, Arm arm, Wrist wrist, Effector effector, Climber climber) {
        this.buttonBoard = m_buttonBoard;
        
        //buttonBoard.button(0).onTrue(Commands.runOnce(this::toggleFunction)); // Toggle
        //buttonBoard.button(0).onChange(Commands.runOnce(this::toggleFunction));
        
        // Button 5 for climber out, button 6 for climb in
        buttonBoard.button(5).whileTrue(RunClimb(climber, false));
        buttonBoard.button(6).whileTrue(RunClimb(climber, true));
    }
   
    private void toggleFunction() {
        this.function = !function;
    }

    public boolean getFunction() {
        return function;
    }
}
