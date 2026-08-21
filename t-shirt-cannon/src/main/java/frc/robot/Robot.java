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
  private Command autonomousCommand;

  public final static Drivetrain drivetrain = new Drivetrain();

  public final static CannonPivot cannonPivot = new CannonPivot();

  public final static Cannon cannon = new Cannon();

  private final static CommandXboxController driverController = new CommandXboxController(Constants.CONTROLLER.JOYSTICK_CONTROLLER_PORT);

  public Robot() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
     drivetrain.setDefaultCommand(getArcadeDriveCommand());
     driverController.povUp().whileTrue(new CannonPivotUp());
     driverController.povDown().whileTrue(new CannonPivotDown());
     driverController.rightTrigger(Constants.CONTROLLER.CANNON_FIRE_THRESHOLD).whileTrue(new CannonShoot(cannon));
  }

  public Command getArcadeDriveCommand() {
    return new ArcadeDrive(
      drivetrain, () -> driverController.getRightX(), () -> driverController.getLeftY());
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
    if (autonomousCommand != null) {
      CommandScheduler.getInstance().schedule(autonomousCommand);
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
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