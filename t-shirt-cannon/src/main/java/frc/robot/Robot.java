// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.CannonPivot;
import frc.robot.subsystems.CannonPivotDown;
import frc.robot.subsystems.CannonPivotUp;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Cannon;
import frc.robot.subsystems.CannonShoot;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final Drivetrain m_drivetrain = new Drivetrain();

  public static CannonPivot m_cannonPivot = new CannonPivot();

  private Cannon m_cannon = new Cannon();

  private final CommandXboxController m_driverController = new CommandXboxController(Constants.CONTROLLER.JOYSTICK_CONTROLLER_PORT);

  public Robot() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    m_drivetrain.setDefaultCommand(getArcadeDriveCommand());
     m_driverController.povUp().whileTrue(new CannonPivotUp(m_cannonPivot));
     m_driverController.povDown().whileTrue(new CannonPivotDown(m_cannonPivot));
     m_driverController.rightTrigger(Constants.CONTROLLER.CANNON_FIRE_THRESHOLD).whileTrue(new CannonShoot(m_cannon));
  }

  public Command getArcadeDriveCommand() {
    return new ArcadeDrive(
      m_drivetrain, () -> -m_driverController.getLeftY(), () -> -m_driverController.getRightX());
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    if (m_autonomousCommand != null) {
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}