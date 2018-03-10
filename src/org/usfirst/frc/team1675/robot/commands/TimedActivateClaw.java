package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;

/**
 *
 */
public class TimedActivateClaw extends ActivateClaw {

    public TimedActivateClaw(boolean input, double power, double timeout) {
        super(input, power);
        requires(Robot.claw);
        this.setTimeout(timeout);
    }

    protected boolean isFinished() {
        if (this.isTimedOut()) {
            return true;
        }
        return false;
    }
}
