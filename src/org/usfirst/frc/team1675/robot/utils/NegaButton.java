package org.usfirst.frc.team1675.robot.utils;

import edu.wpi.first.wpilibj.buttons.Button;

public class NegaButton extends Button {

    private final Button button;

    public NegaButton(Button button) {
        this.button = button;
    }

    public boolean get() {
        return !button.get();
    }
}
