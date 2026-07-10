package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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

public class CannonPivot extends SubsystemBase{
    final TalonSRX m_cannonPivotLeader = new TalonSRX(Constants.CAN_ID.CANNON_PIVOT_MOTOR);
    DCMotor m_cannonPivotGearbox = DCMotor.getNEO(Constants.CANNON_PIVOT.NUMBER_OF_MOTORS);

    public void set(double speed) {
        m_cannonPivotLeader.set(TalonSRXControlMode.PercentOutput, speed);
    }

    public void CannonPivotConfig() {
        SparkMaxConfig globalConfig = new SparkMaxConfig();
    globalConfig
      .smartCurrentLimit(Constants.CANNON_PIVOT.STALL_LIMIT_AMPS)
      .idleMode(IdleMode.kBrake);

    // Apply the global config and invert since it is on the opposite side   

    }

    public void simulationPeriodic() {
        //cannonPivotLeaderSim.iterate(cannonPivotLeader.getVelocity(), )
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
