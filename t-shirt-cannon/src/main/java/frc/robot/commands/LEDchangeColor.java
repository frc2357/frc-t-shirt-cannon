package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;

public class LEDchangeColor extends Command {
    public void execute() {
        Robot.LED.changeColor();
    }

    public boolean isFinished() {
        return true;
    }
}
