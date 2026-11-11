package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;

public class LEDtoggle extends Command {

    public void execute() {
        Robot.LED.toggle();
    }

    public boolean isFinished() {
        return true;
    }
}
