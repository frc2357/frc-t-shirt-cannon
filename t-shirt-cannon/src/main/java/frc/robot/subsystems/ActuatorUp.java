package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ActuatorUp extends Command{

    public Actuator m_actuator;

    public ActuatorUp(Actuator actuator) {
        m_actuator = actuator;
    }

    public void execute() {
        m_actuator.Set(0.5f);
        System.out.println("Moving up");
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        m_actuator.Set(0.0f);
        System.out.println("Not moving");
    }
}
