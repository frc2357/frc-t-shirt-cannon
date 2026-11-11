package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.constLED;

public class LED extends SubsystemBase{
    private final Color off = new Color(0, 0, 0);
    private final Color orange = new Color(255, 165, 0);
    private final Color blue = new Color(0, 0, 255);
    private final Color red = new Color(255, 0, 0);
    private final Color white = new Color(255, 255, 255);
    private final Color colors[] = {orange, blue, red, white};
    private final int colorsLength = colors.length;

    private final AddressableLED LEDinst = new AddressableLED(0);
    private final AddressableLEDBuffer buffer = new AddressableLEDBuffer(constLED.STRIP_LENGTH);
    
    private volatile Runnable patternMethod;
    private boolean isON = false;
    private int colorInt = 0; //The value of index of colors[] array to be outputed
    private PTRN pattern = PTRN.FILL;
    private int patternStage = 1;

    public enum PTRN {
        OFF,
        FILL,
        SNAKE,
        STROBE
    }

    public LED() {
        patternMethod = () -> fullFill(off);
        LEDinst.setLength(buffer.getLength());
        LEDinst.start();
    }

    public void periodic() {
        patternStage = (patternStage + 1) % (buffer.getLength() + 1);
        if (patternStage == 0) {
            patternStage = 1;
        }
        patternMethod.run();
    }

    public void toggle() {
        isON = !isON;
        changePattern(0);
    }

    public void changeColor() {
        colorInt = (colorInt + 1) % colorsLength;
        changePattern(0);
    }

    public void changePattern(int modifyAmount) {
        if (modifyAmount != 0) {
            PTRN[] possibleValues = PTRN.values();
            pattern = possibleValues[Math.max(1, (pattern.ordinal() + 1) % possibleValues.length)];
        }
        Color currentColor = colors[colorInt];

        if (!isON) {
            patternMethod = () -> fullFill(off);
        } else {
            switch (pattern) {
                case FILL : 
                    patternMethod = () -> fullFill(currentColor);
                    break;

                case SNAKE :
                    patternMethod = () -> {
                        snakeFill(currentColor, (int) Math.floor(buffer.getLength() / 2));
                        };
                    break;
                    
                case STROBE :
                    patternMethod = () -> strobeLight();
                    break;

                default :
                    break;
            }
        }
    }

    private void fullFill(Color color) {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setLED(i, color);
        }
        LEDinst.setData(buffer);
    }

    public void snakeFill(Color color, int snakeLength) {
        for (int i = patternStage; i < patternStage + snakeLength; i++) {
            buffer.setLED(i % buffer.getLength(), color);
        }
        for (int i = patternStage + snakeLength; i < patternStage + snakeLength + (buffer.getLength() - snakeLength); i++) {
            buffer.setLED(i % buffer.getLength(), off);
        }
        LEDinst.setData(buffer);
    }

    public void strobeLight() {
        if (patternStage % 2 == 1) {
            fullFill(white);
        } else {
            fullFill(off);
        }
    }
}
