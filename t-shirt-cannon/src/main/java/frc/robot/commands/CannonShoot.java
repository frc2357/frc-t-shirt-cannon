package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class CannonShoot extends Command{
    Timer m_timer = new Timer();

    public CannonShoot() {
        addRequirements(Robot.cannon);
    }

    public void initialize() {
        m_timer.start();
    }

    public void execute() {
        Robot.cannon.set(true);
    }

    public boolean isFinished() {
        return m_timer.hasElapsed(Constants.CANNON.CANNONSHOOT_SECONDS_TIME);
    }

    public void end(boolean interrupted) {
        Robot.cannon.set(false);
        m_timer.stop();
        m_timer.reset();
    }
}
