package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ActuatorDown extends Command{

    public Actuator m_actuator;

    public ActuatorDown(Actuator actuator) {
        m_actuator = actuator;
    }

    public void execute() {
        m_actuator.Set(-0.5f);
        System.out.println("Moving down");
    }

    public void end(boolean interrupted) {
        m_actuator.Set(0.0f);
        System.out.println("Not moving");
    }
}
