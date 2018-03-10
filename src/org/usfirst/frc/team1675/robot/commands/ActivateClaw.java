package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ActivateClaw extends Command {

    double power;
    boolean input;

    public ActivateClaw(boolean input, double power) {
        requires(Robot.claw);
        this.input = input;
        this.power = Math.abs(power);
    }

    protected void initialize() {
        if (input) {
            Robot.claw.setLeftIntakePower(power);
            Robot.claw.setRightIntakePower(-power);
        } else {
            Robot.claw.setLeftIntakePower(-power);
            Robot.claw.setRightIntakePower(power);
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.claw.setLeftIntakePower(0);
        Robot.claw.setRightIntakePower(0);
    }

    protected void interrupted() {
        end();
    }
}
