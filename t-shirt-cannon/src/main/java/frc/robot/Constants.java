// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class CONTROLS {
    public static final int JOYSTICK_CONTROLLER_PORT = 8;

    public static final int Z_KEY_BUTTON_NUMBER = 1;
    public static final int X_KEY_BUTTON_NUMBER = 2;
    public static final int C_KEY_BUTTON_NUMBER = 3;
    public static final int V_KEY_BUTTON_NUMBER = 4;

  }

  public static class ROBOT {
    public static final int FORWARD_DRIVE_AXIS = 1;
    public static final int TURN_DRIVE_AXIS = 0;
  }

  public static class PORT {
    public static final int LEFT_MOTOR_PORT = 5;
    public static final int RIGHT_MOTOR_PORT = 6;

    public static final int DIGITAL_PORT = 0;

    public static final int ACTUATOR_MOTOR_PORT = 3;
  }

  public static class CAN_ID {
    public static final int LEFT_WHEEL_GEARBOX_MOTOR_NUMBER = 1;
    public static final int RIGHT_WHEEL_GEARBOX_MOTOR_NUMBER = 2;

    public static final double WHEEL_DIAMETER_CM = 1.91475;

    public static final int ACTUATOR_GEARBOX_MOTOR_NUMBER = 1;

    public static final int STALL_LIMIT_SECS = 50;
  }

  public static class SIM {
    public static final int GYRO_SIM_CHANNEL = 1;
  }

  public static class ACTUATOR {
    public static final double ACTUATOR_UP_SPEED = 0.5f;
    public static final double ACTUATOR_DOWN_SPEED = -0.5f;
    public static final double ACTUATOR_OFF_SPEED = 0.0f;
  }

  public static class VALVE {
    public static final double VALVESHOOT_SECONDS_TIME = 1.0f;
  }

  public static class PROGRAM {
    public static final double PROGRAM_UPDATE_TIME_SECS = 0.02;
  }
}
