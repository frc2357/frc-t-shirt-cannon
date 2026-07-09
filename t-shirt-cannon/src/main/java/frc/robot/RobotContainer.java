// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Actuator;
import frc.robot.subsystems.ActuatorDown;
import frc.robot.subsystems.ActuatorUp;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Valve;
import frc.robot.subsystems.ValveShoot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Joystick m_controller = new Joystick(Constants.CONTROLS.JOYSTICK_CONTROLLER_PORT);

  private Actuator m_actuator = new Actuator();

  private Valve m_valve = new Valve();

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    m_drivetrain.setDefaultCommand(getArcadeDriveCommand());

    JoystickButton joystickAButton = new JoystickButton(m_controller, Constants.CONTROLS.Z_KEY_BUTTON_NUMBER);
    joystickAButton
        .whileTrue(new ActuatorUp(m_actuator));

    JoystickButton joystickBButton = new JoystickButton(m_controller, Constants.CONTROLS.X_KEY_BUTTON_NUMBER);
    joystickBButton
        .whileTrue(new ActuatorDown(m_actuator));

    JoystickButton joystickCButton = new JoystickButton(m_controller, Constants.CONTROLS.C_KEY_BUTTON_NUMBER);
    joystickCButton
        .whileTrue(new ValveShoot(m_valve));

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }

  public Command getArcadeDriveCommand() {
    return new ArcadeDrive(
      m_drivetrain, () -> -m_controller.getRawAxis(Constants.ROBOT.FORWARD_DRIVE_AXIS), () -> -m_controller.getRawAxis(Constants.ROBOT.TURN_DRIVE_AXIS));
      
  }
}
