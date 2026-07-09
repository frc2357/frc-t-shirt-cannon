package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.sim.SparkMaxSim;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Actuator extends SubsystemBase{
    final SparkMax actuatorLeader = new SparkMax(Constants.PORT.ACTUATOR_MOTOR_PORT, MotorType.kBrushed);
    DCMotor actuatorGearbox = DCMotor.getNEO(Constants.CAN_ID.ACTUATOR_GEARBOX_MOTOR_NUMBER);

    SparkMaxSim actuatorLeaderSim = new SparkMaxSim(actuatorLeader, actuatorGearbox);

    public void set(double speed) {
        actuatorLeader.set(speed);
    }

    public void ActuatorConfig() {
        SparkMaxConfig globalConfig = new SparkMaxConfig();
    globalConfig
      .smartCurrentLimit(Constants.CAN_ID.STALL_LIMIT_SECS)
      .idleMode(IdleMode.kBrake);

    // Apply the global config and invert since it is on the opposite side

    actuatorLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);    

    }

    public void simulationPeriodic() {
        //actuatorLeaderSim.iterate(actuatorLeader.getVelocity(), )
    }

    public Command MotorControl() {
        throw new UnsupportedOperationException("Unimplemented method 'MotorControl'");
    }

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
                //m_rightMotor.setInverted(true);

    // Use inches as unit for encoder distances
}
