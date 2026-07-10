package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class CannonShoot extends Command{
    Cannon m_cannon;
    Timer m_timer = new Timer();

    public CannonShoot(Cannon cannon) {
        m_cannon = cannon;
        addRequirements(cannon);
    }

    public boolean isFinished() {
        System.out.println("Cannon: " + m_timer.get());
        return m_timer.hasElapsed(Constants.CANNON.CANNONSHOOT_SECONDS_TIME);
    }

    public void initialize() {
        m_timer.start();
    }

    public void execute() {
        m_cannon.set(true);
        System.out.println("Cannon: running");
    }

    public void end(boolean interrupted) {
        System.out.println("Cannon: stopped");
        m_cannon.set(false);
        m_timer.stop();
        m_timer.reset();
    }
}
