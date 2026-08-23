package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class CannonPivotUp extends Command{

    public CannonPivotUp() {
        addRequirements(Robot.cannonPivot);
    }

    public void execute() {
        Robot.cannonPivot.set(Constants.CANNON_PIVOT.CANNON_PIVOT_UP_SPEED);
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        Robot.cannonPivot.set(Constants.CANNON_PIVOT.CANNON_PIVOT_OFF_SPEED);
    }
}
