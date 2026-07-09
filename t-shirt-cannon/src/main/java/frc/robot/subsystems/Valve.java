package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.simulation.DigitalPWMSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Valve extends SubsystemBase{
    DigitalOutput valve = new DigitalOutput(Constants.PORT.DIGITAL_PORT);

    DigitalPWMSim valveSim = new DigitalPWMSim(valve);


    public void set(boolean state) {
        valve.set(state);
        System.out.println("Created: Valve");
    }

    public void simulationPeriodic() {
        valveSim.setDutyCycle(valve.get() ? 1.0f : 0.0f);
    }

}
