package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class ValveShoot extends Command{
    Valve m_valve;
    Timer m_timer = new Timer();

    public ValveShoot(Valve valve) {
        m_valve = valve;
        addRequirements(valve);
    }

    public boolean isFinished() {
        System.out.println("Valve: " + m_timer.get());
        return m_timer.hasElapsed(Constants.VALVESHOOT_SECONDS_TIME);
    }

    public void initialize() {
        m_timer.start();
    }

    public void execute() {
        m_valve.set(true);
        System.out.println("Valve: running");
    }

    public void end(boolean interrupted) {
        System.out.println("Valve: stopped");
        m_valve.set(false);
        m_timer.stop();
        m_timer.reset();
    }
}
