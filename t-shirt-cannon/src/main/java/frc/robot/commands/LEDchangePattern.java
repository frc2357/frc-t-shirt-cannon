package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;

public class LEDchangePattern extends Command {
    private boolean increasing;

    public LEDchangePattern(boolean increasing) {
        this.increasing = increasing;
    }

    public void execute() {
        Robot.LED.changePattern(increasing);
    }

    public boolean isFinished() {
        return true;
    }
}
