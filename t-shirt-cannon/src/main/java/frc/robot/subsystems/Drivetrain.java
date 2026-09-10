package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  final TalonSRX m_leftMotor = new TalonSRX(Constants.CAN_ID.LEFT_DRIVE_MOTOR);
  final TalonSRX m_rightMotor = new TalonSRX(Constants.CAN_ID.RIGHT_DRIVE_MOTOR);

  public BuiltInAccelerometer accel = new BuiltInAccelerometer();

  public double getXAccel() {
    return accel.getX();
  }

  public double getYAccel() {
    return accel.getY();
  }

  public double getZAccel() {
    return accel.getZ();
  }
  
  private final DifferentialDrive diffDrive =
    new DifferentialDrive(this::setLeft, this::setRight);

  public Drivetrain() {
    var config = new TalonSRXConfiguration();
    config.peakCurrentLimit = Constants.DRIVE.STALL_LIMIT_AMPS;
    config.continuousCurrentLimit = Constants.DRIVE.STALL_LIMIT_AMPS;
    config.peakCurrentDuration = Constants.DRIVE.PEAK_CURRENT_DURATION_MILLISECONDS;
    config.openloopRamp = Constants.DRIVE.OPEN_LOOP_RAMP_SECONDS;

    m_leftMotor.configAllSettings(config);
    m_rightMotor.configAllSettings(config);
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
}