// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.sim.SparkMaxSim;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  final SparkMax m_leftLeader = new SparkMax(Constants.BRAIN_PORTS.LEFT_MOTOR_PORT, MotorType.kBrushed);
  final SparkMax m_rightLeader = new SparkMax(Constants.BRAIN_PORTS.RIGHT_MOTOR_PORT, MotorType.kBrushed);

  DCMotor m_leftGearbox = DCMotor.getNEO(Constants.BONUS_MOTOR_DETAILS.LEFT_WHEEL_GEARBOX_MOTOR_NUMBER);
  DCMotor m_rightGearbox = DCMotor.getNEO(Constants.BONUS_MOTOR_DETAILS.RIGHT_WHEEL_GEARBOX_MOTOR_NUMBER);
  
  SparkMaxSim m_leftLeaderSim = new SparkMaxSim(m_leftLeader, m_leftGearbox);
  SparkMaxSim m_rightLeaderSim = new SparkMaxSim(m_rightLeader, m_rightGearbox);

  private AnalogGyro m_gyro = new AnalogGyro(Constants.PROGRAM_VISUAL_SIMULATION.GYRO_SIM_CHANNEL);

  private AnalogGyroSim m_gyroSim = new AnalogGyroSim(m_gyro);

  private Field2d m_field = new Field2d();

  DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(
    m_gyro.getRotation2d(), 
    m_leftLeader.getEncoder().getPosition(), 
    m_rightLeader.getEncoder().getPosition());

DifferentialDrivetrainSim driveSim = DifferentialDrivetrainSim.createKitbotSim(KitbotMotor.kSingleNEOPerSide,
  KitbotGearing.k5p95, 
  KitbotWheelSize.kTenInch,
  null);

  // The XRP has the left and right motors set to
  // channels 0 and 1 respectively
            //private final XRPMotor m_leftMotor = new XRPMotor(0);
            //private final XRPMotor m_rightMotor = new XRPMotor(1);

  // The XRP has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
        
          // Set up the differential drive controller
          private final DifferentialDrive diffDrive =
                new DifferentialDrive(m_leftLeader::set, m_rightLeader::set);

  // Set up the XRPGyro
            //private final XRPGyro m_gyro = new XRPGyro();

  // Set up the BuiltInAccelerometer
  private final BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();

  /** Creates a new Drivetrain. */
  public Drivetrain() {
        SendableRegistry.addChild(diffDrive, m_leftLeader);
        SendableRegistry.addChild(diffDrive, m_rightLeader);

        SparkMaxConfig globalConfig = new SparkMaxConfig();
        SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
        SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
        SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();

        SmartDashboard.putData("Field", m_field);

    globalConfig
      .smartCurrentLimit(50)
      .idleMode(IdleMode.kBrake);

    // Apply the global config and invert since it is on the opposite side
    rightLeaderConfig
        .apply(globalConfig)
        .inverted(true);

    // Apply the global config and set the leader SPARK for follower mode
    leftFollowerConfig
        .apply(globalConfig)
        .follow(m_leftLeader);

    // Apply the global config and set the leader SPARK for follower mode
    rightFollowerConfig
        .apply(globalConfig)
        .follow(m_rightLeader);

    m_leftLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_rightLeader.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);    
    
    XboxController joystick = new XboxController(0);

    double forward = -joystick.getLeftY();
    double rotation = joystick.getRightX();

    m_leftLeader.set(forward + rotation);
    m_rightLeader.set(forward - rotation);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
                //m_rightMotor.setInverted(true);

    // Use inches as unit for encoder distances
  }

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }


  /**
   * The acceleration in the X-axis.
   *
   * @return The acceleration of the XRP along the X-axis in Gs
   */
  public double getAccelX() {
    return accelerometer.getX();
  }

  /**
   * The acceleration in the Y-axis.
   *
   * @return The acceleration of the XRP along the Y-axis in Gs
   */
  public double getAccelY() {
    return accelerometer.getY();
  }

  /**
   * The acceleration in the Z-axis.
   *
   * @return The acceleration of the XRP along the Z-axis in Gs
   */
  public double getAccelZ() {
    return accelerometer.getZ();
  }

  /**
   * Current angle of the XRP around the X-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  //public double getGyroAngleX() {
    //return m_gyro.getAngleX();
  //}

  /**
   * Current angle of the XRP around the Y-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  //public double getGyroAngleY() {
    //return m_gyro.getAngleY();
  //}

  /**
   * Current angle of the XRP around the Z-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  //public double getGyroAngleZ() {
    //return m_gyro.getAngleZ();
  //}

  /** Reset the gyro. */
  public void resetGyro() {
    //m_gyro.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_odometry.update(m_gyro.getRotation2d(),
        m_leftLeader.getEncoder().getPosition() * Constants.BONUS_MOTOR_DETAILS.WHEEL_DIAMETER_CM,
        m_rightLeader.getEncoder().getPosition() * Constants.BONUS_MOTOR_DETAILS.WHEEL_DIAMETER_CM);
    m_field.setRobotPose(m_odometry.getPoseMeters());
  }

  @Override
  public void simulationPeriodic() {
    driveSim.setInputs(m_leftLeader.get() * RobotController.getInputVoltage(),
    m_rightLeader.get() * RobotController.getInputVoltage());

    driveSim.update(0.02);
    m_leftLeaderSim.iterate(driveSim.getLeftVelocityMetersPerSecond(), RoboRioSim.getVInVoltage(), Constants.PROGRAM.PROGRAM_UPDATE_TIME_SECS);
    m_rightLeaderSim.iterate(driveSim.getRightVelocityMetersPerSecond(), RoboRioSim.getVInVoltage(), Constants.PROGRAM.PROGRAM_UPDATE_TIME_SECS);
    m_gyroSim.setAngle(-driveSim.getHeading().getDegrees());

  }
}