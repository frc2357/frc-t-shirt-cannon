package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class ActuatorUp extends Command{

    public Actuator m_actuator;

    public ActuatorUp(Actuator actuator) {
        m_actuator = actuator;
        addRequirements(actuator);
    }

    public void execute() {
        m_actuator.set(Constants.ACTUATOR_UP_SPEED);
        System.out.println("Moving up");
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        m_actuator.set(Constants.ACTUATOR_OFF_SPEED);
        System.out.println("Not moving");
    }
}
