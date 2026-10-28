package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants.constLED;

public class LED {
    private final Color off = new Color(0, 0, 0);
    private final Color orange = new Color(255, 165, 0);
    private final Color blue = new Color(0, 0, 255);
    private final Color red = new Color(255, 0, 0);
    private final Color colors[] = {orange, blue, red};
    private final int colorsLength = colors.length;
    private final int amountOfPatterns = 2;

    private final AddressableLED LEDinst = new AddressableLED(0);
    private final AddressableLEDBuffer buffer = new AddressableLEDBuffer(constLED.STRIP_LENGTH);
    
    private volatile Runnable patternMethod;
    private boolean isON = false;
    private int colorInt = 0;
    private int pattern = 0;
    private int patternStage = 0;

    public LED() {
        this.patternMethod = () -> fullFill(off);
        LEDinst.setLength(buffer.getLength());
        LEDinst.start();
    }

    public void update() {
        patternStage = (patternStage + 1) % buffer.getLength();
        patternMethod.run();
    }

    public void toggle() {
        isON = !isON;
        updatePattern();
    }

    public void changeColor() {
        colorInt = (colorInt + 1) % colorsLength;
        updatePattern();
    }

    public void changePattern(boolean increaseValue) {
        if (increaseValue) {
            pattern = (pattern + 1) % amountOfPatterns;
        } else if (pattern - 1 < 0) {
            pattern = amountOfPatterns - 1;
        } else {
            pattern = pattern - 1;
        }
        System.out.println(pattern);
        updatePattern();
    }

    public void updatePattern() {
        Color currentColor = colors[colorInt];
        if (isON) {
            switch(pattern) {
                case 0 : {
                    this.patternMethod = () -> fullFill(currentColor);
                    break;
                }
                case 1 : {
                    this.patternMethod = () -> snakeFill(currentColor);
                    break;
                }
            }
        } else {
            this.patternMethod = () -> fullFill(off);
        }
    }

    private void fullFill(Color color) {
        for (int i = 1; i < buffer.getLength(); i++) {
            buffer.setLED(i, color);
        }
        LEDinst.setData(buffer);
    }

    public void snakeFill(Color color) {
        for (int i = 1; i < patternStage; i++) {
            buffer.setLED(i, color);
        }
        for (int i = patternStage; i < buffer.getLength(); i++) {
            buffer.setLED(i, off);
        }
        LEDinst.setData(buffer);
    }
}
