package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.simulation.DigitalPWMSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Cannon extends SubsystemBase{
    DigitalOutput m_cannon = new DigitalOutput(Constants.RIO_DIGITAL_PORTS.DIGITAL_PORT);

    DigitalPWMSim m_cannonSim = new DigitalPWMSim(m_cannon);

    public void set(boolean state) {
        m_cannon.set(state);
        System.out.println("Created: Cannon");
    }

    public void simulationPeriodic() {
        m_cannonSim.setDutyCycle(m_cannon.get() ? 1.0f : 0.0f);
    }
}