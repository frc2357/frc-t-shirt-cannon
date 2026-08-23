package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  final TalonSRX m_leftMotor = new TalonSRX(Constants.CAN_ID.LEFT_DRIVE_MOTOR);
  final TalonSRX m_rightMotor = new TalonSRX(Constants.CAN_ID.RIGHT_DRIVE_MOTOR);

  private AnalogGyro m_gyro = new AnalogGyro(Constants.PROGRAM_VISUAL_SIMULATION.GYRO_SIM_CHANNEL);
  
  private final DifferentialDrive diffDrive =
    new DifferentialDrive(this::setLeft, this::setRight);

  public Drivetrain() {
    var config = new TalonSRXConfiguration();
    config.peakCurrentLimit = Constants.DRIVE.STALL_LIMIT_AMPS;
    config.continuousCurrentLimit = Constants.DRIVE.STALL_LIMIT_AMPS;
    config.peakCurrentDuration = 1;

    m_leftMotor.configAllSettings(config);
    m_rightMotor.configAllSettings(config);

    m_rightMotor.setInverted(true);
  }

  private void setLeft(double speed) {
    m_leftMotor.set(TalonSRXControlMode.PercentOutput, speed);
  } 

  private void setRight(double speed) {
    m_rightMotor.set(TalonSRXControlMode.PercentOutput, speed);
  } 

  public void arcadeDrive(double speed, double rotate) {
    diffDrive.arcadeDrive(speed, rotate);
  }

  public void resetGyro() {
    m_gyro.reset();
  }
}