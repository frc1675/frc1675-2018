package org.usfirst.frc.team1675.robot.utils;

import edu.wpi.first.wpilibj.buttons.Button;

public class DoubleButton extends Button {

    private final Button button1;
    private final Button button2;

    public DoubleButton(Button buttonOne, Button buttonTwo) {
        button1 = buttonOne;
        button2 = buttonTwo;
    }

    @Override
    public boolean get() {
        return button1.get() && button2.get();
    }

}
