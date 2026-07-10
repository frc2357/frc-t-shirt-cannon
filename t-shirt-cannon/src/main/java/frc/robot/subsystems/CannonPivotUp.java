package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class CannonPivotUp extends Command{

    public CannonPivot cannon_pivot;

    public CannonPivotUp(CannonPivot cannonPivot) {
        cannon_pivot = cannonPivot;
        addRequirements(cannonPivot);
    }

    public void execute() {
        cannon_pivot.set(Constants.CANNON_PIVOT.CANNON_PIVOT_UP_SPEED);
        System.out.println("Moving up");
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        cannon_pivot.set(Constants.CANNON_PIVOT.CANNON_PIVOT_OFF_SPEED);
        System.out.println("Not moving");
    }
}
