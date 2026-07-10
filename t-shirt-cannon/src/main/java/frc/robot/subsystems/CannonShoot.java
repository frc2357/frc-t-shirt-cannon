package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class CannonShoot extends Command{
    Timer timer = new Timer();

    public CannonShoot(Cannon cannon) {
        addRequirements(Robot.cannon);
    }

    public boolean isFinished() {
        System.out.println("Cannon: " + timer.get());
        return timer.hasElapsed(Constants.CANNON.CANNONSHOOT_SECONDS_TIME);
    }

    public void initialize() {
        timer.start();
    }

    public void execute() {
        Robot.cannon.set(true);
        System.out.println("Cannon: running");
    }

    public void end(boolean interrupted) {
        System.out.println("Cannon: stopped");
        Robot.cannon.set(false);
        timer.stop();
        timer.reset();
    }
}
