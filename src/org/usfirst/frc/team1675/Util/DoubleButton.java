package org.usfirst.frc.team1675.Util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class DoubleButton extends Button {

    private final GenericHID joystick;
    private final int button1;
    private final int button2;

    public DoubleButton(GenericHID joystick, int buttonOne, int buttonTwo) {
        this.joystick = joystick;
        button1 = buttonOne;
        button2 = buttonTwo;
    }

    @Override
    public boolean get() {
        return this.joystick.getRawButton(button1) && this.joystick.getRawButton(button2);
    }

}
