package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.simulation.DigitalPWMSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Cannon extends SubsystemBase{
    DigitalOutput cannon = new DigitalOutput(Constants.BRAIN_PORTS.DIGITAL_PORT);

    DigitalPWMSim cannonSim = new DigitalPWMSim(cannon);


    public void set(boolean state) {
        cannon.set(state);
        System.out.println("Created: Cannon");
    }

    public void simulationPeriodic() {
        cannonSim.setDutyCycle(cannon.get() ? 1.0f : 0.0f);
    }

}
