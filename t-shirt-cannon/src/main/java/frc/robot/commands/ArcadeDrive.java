package frc.robot.commands;

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
    Robot.drivetrain.arcadeDrive(m_speedSupplier.get(), m_rotateSupplier.get());
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
