package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;

public class ActuatorUp extends Command{

    public Actuator m_actuator;

    public ActuatorUp(Actuator actuator) {
        m_actuator = actuator;
        addRequirements(actuator);
    }

    public void execute() {
        m_actuator.set(0.5f);
        System.out.println("Moving up");
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        m_actuator.set(0.0f);
        System.out.println("Not moving");
    }
}
