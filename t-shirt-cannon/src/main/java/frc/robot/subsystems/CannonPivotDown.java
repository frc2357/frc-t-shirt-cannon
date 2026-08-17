package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class CannonPivotDown extends Command{

    public CannonPivotDown() {
        addRequirements(Robot.cannonPivot);
    }

    public void execute() {
        Robot.cannonPivot.set(Constants.CANNON_PIVOT.CANNON_PIVOT_DOWN_SPEED);
        System.out.println("Moving down");
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        Robot.cannonPivot.set(Constants.CANNON_PIVOT.CANNON_PIVOT_OFF_SPEED);
        System.out.println("Not moving");
    }
}