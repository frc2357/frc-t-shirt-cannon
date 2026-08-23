package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CannonPivot extends SubsystemBase{
    final TalonSRX m_motor = new TalonSRX(Constants.CAN_ID.CANNON_PIVOT_MOTOR);

    public void set(double speed) {
        m_motor.set(TalonSRXControlMode.PercentOutput, speed);
    }

    public CannonPivot() {
        var config = new TalonSRXConfiguration();
        config.peakCurrentLimit = Constants.CANNON_PIVOT.STALL_LIMIT_AMPS;
        config.continuousCurrentLimit = Constants.CANNON_PIVOT.STALL_LIMIT_AMPS;
        config.peakCurrentDuration = 1;

        m_motor.configAllSettings(config);
    }

    public Command MotorControl() {
        throw new UnsupportedOperationException("Unimplemented method 'MotorControl'");
    }
}
