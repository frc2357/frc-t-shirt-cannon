package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.Supplier;

public class ArcadeDrive extends Command {
  private final Supplier<Double> m_speedSupplier;
  private final Supplier<Double> m_rotateSupplier;

  public ArcadeDrive(
    Supplier<Double> speedSupplier,
    Supplier<Double> rotateSupplier) {
      m_speedSupplier = speedSupplier;
      m_rotateSupplier = rotateSupplier;
      addRequirements(Robot.drivetrain);
  }

  @Override
  public void execute() {
    if (m_speedSupplier.get() >= Constants.DRIVE.BRAKE_SPEED_LOWER_BOUND && m_speedSupplier.get() <= Constants.DRIVE.BRAKE_SPEED_UPPER_BOUND) {
      Robot.drivetrain.arcadeDrive(brakeSpeed(), m_rotateSupplier.get());
    }
    else {
      Robot.drivetrain.arcadeDrive(m_speedSupplier.get(), m_rotateSupplier.get());
    }
    
    if (m_rotateSupplier.get() >= Constants.DRIVE.BRAKE_SPEED_LOWER_BOUND && m_rotateSupplier.get() <= Constants.DRIVE.BRAKE_SPEED_UPPER_BOUND) {
      Robot.drivetrain.arcadeDrive(m_speedSupplier.get(), brakeRotate());
    }
    else {
      Robot.drivetrain.arcadeDrive(m_speedSupplier.get(), m_rotateSupplier.get());
    } 
  }

  public double brakeSpeed() {
    return -Robot.drivetrain.getXAccel() * Constants.DRIVE.BRAKE_SPEED_P;
  }

  public double brakeRotate() {
    return -Robot.drivetrain.getYAccel() * Constants.DRIVE.BRAKE_ROTATE_P;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
