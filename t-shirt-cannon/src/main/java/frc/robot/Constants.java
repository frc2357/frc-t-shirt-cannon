package frc.robot;

public final class Constants {

  public static class CONTROLLER {
    public static final int JOYSTICK_CONTROLLER_PORT = 0;

    public static final double CANNON_FIRE_THRESHOLD = 0.8f;
  }

  public static class ROBOT {
    public static final int FORWARD_DRIVE_AXIS = 1;
    public static final int TURN_DRIVE_AXIS = 0;
  }

  public static class RIO_DIGITAL_PORTS {
    public static final int DIGITAL_PORT = 0;
  }

  public static class CAN_ID {
    public static final int LEFT_DRIVE_MOTOR = 11;
    public static final int RIGHT_DRIVE_MOTOR = 12;

    public static final int CANNON_PIVOT_MOTOR = 13;
  }

  public static class DRIVE {
     public static final int NUMBER_OF_MOTORS_PER_SIDE = 1;
     public static final int STALL_LIMIT_AMPS = 40;

     public static final double WHEEL_DIAMETER_CM = 1.91475;
  }

  public static class PROGRAM_VISUAL_SIMULATION {
    public static final int GYRO_SIM_CHANNEL = 1;
  }

  public static class CANNON_PIVOT {
    public static final double CANNON_PIVOT_UP_SPEED = 1.1f;
    public static final double CANNON_PIVOT_DOWN_SPEED = -1.1f;
    public static final double CANNON_PIVOT_OFF_SPEED = 0.0f;

    public static final int NUMBER_OF_MOTORS = 1;

    public static final int STALL_LIMIT_AMPS = 15;
  }

  public static class CANNON {
    public static final double CANNONSHOOT_SECONDS_TIME = 0.1f;
  }

  public static class PROGRAM {
  public static final double PROGRAM_UPDATE_TIME_SECS = 0.02;
  }
}
