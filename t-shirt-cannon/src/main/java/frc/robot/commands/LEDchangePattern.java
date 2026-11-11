package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;

public class LEDchangePattern extends Command {
    private int increasing;

    public LEDchangePattern(boolean increasing) {
        if (increasing) {
            this.increasing = 1;
        } else {
            this.increasing = -1;
        }
    }

    public void execute() {
        Robot.LED.changePattern(increasing);
    }

    public boolean isFinished() {
        return true;
    }
}
