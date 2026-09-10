package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.CannonPivotDown;
import frc.robot.commands.CannonPivotUp;
import frc.robot.commands.CannonShoot;
import frc.robot.subsystems.CannonPivot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Cannon;

public class Robot extends TimedRobot {
  public final static Drivetrain drivetrain = new Drivetrain();

  public final static Cannon cannon = new Cannon();
  public final static CannonPivot cannonPivot = new CannonPivot();

  private final static CommandXboxController driverController = new CommandXboxController(Constants.CONTROLLER.JOYSTICK_CONTROLLER_PORT);

  public Robot() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(getArcadeDriveCommand());
    driverController.povUp().whileTrue(new CannonPivotUp());
    driverController.povDown().whileTrue(new CannonPivotDown());
    driverController.rightTrigger(Constants.CONTROLLER.CANNON_FIRE_THRESHOLD).whileTrue(new CannonShoot());
  }

  public Command getArcadeDriveCommand() {
    return new ArcadeDrive(
      () -> getControllerSpeed(),
      () -> getControllerRotate());
  }

  public double getControllerSpeed() {
    double speed = driverController.getLeftY();
    speed = Math.copySign(Math.pow(speed, Constants.CONTROLLER.CONTROLLER_SPEED_EXPONENT), speed);
    speed *= Constants.DRIVE.MAX_SPEED;
    return -speed;
  }

  public double getControllerRotate() {
    double rotate = driverController.getRightX();
    rotate = Math.copySign(Math.pow(rotate, Constants.CONTROLLER.CONTROLLER_ROTATE_EXPONENT), rotate);
    rotate *= Constants.DRIVE.MAX_TURN;
    return -rotate;
  }


  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }
}