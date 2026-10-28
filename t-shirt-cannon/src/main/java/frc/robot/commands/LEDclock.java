package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.Constants.constLED;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class LEDclock extends Command {
    Timer myTimer = new Timer();

    public LEDclock() {
        myTimer.start();
    }

    public void execute() {
        if (myTimer.hasElapsed(constLED.UPDATE_TIME_SECONDS)) {
            Robot.LED.update();
            myTimer.reset();
        }
    }

    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }
}
