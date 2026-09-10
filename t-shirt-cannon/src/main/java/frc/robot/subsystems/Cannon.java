package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Cannon extends SubsystemBase{
    DigitalOutput m_cannon = new DigitalOutput(Constants.RIO_DIGITAL_PORTS.DIGITAL_PORT);

    public void set(boolean state) {
        m_cannon.set(state);
    }
}