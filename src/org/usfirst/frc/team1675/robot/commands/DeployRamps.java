package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeployRamps extends Command {

    public DeployRamps() {
        requires(Robot.ramp);
        this.setTimeout(RobotMap.RampConstants.SOLENOID_ACTIVATION_TIME);
    }

    protected void initialize() {
        if(Robot.getTeleopTime() >= RobotMap.RampConstants.RAMP_DROP_TIME) {
            Robot.ramp.Deploy();
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
}
