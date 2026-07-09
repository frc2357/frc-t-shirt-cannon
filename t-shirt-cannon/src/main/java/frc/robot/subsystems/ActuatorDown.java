package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class ActuatorDown extends Command{

    public Actuator m_actuator;

    public ActuatorDown(Actuator actuator) {
        m_actuator = actuator;
        addRequirements(actuator);
    }

    public void execute() {
        m_actuator.set(Constants.CANNON_PIVOT.ACTUATOR_DOWN_SPEED);
        System.out.println("Moving down");
    }

    public void end(boolean interrupted) {
        m_actuator.set(Constants.CANNON_PIVOT.ACTUATOR_OFF_SPEED);
        System.out.println("Not moving");
    }
}
