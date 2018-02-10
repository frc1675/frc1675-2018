package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ActivateClaw extends Command {

	int cubeManipulationDirection;
	
    public ActivateClaw(int manipulationDirection) {
       requires(Robot.claw);
       cubeManipulationDirection = manipulationDirection;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(cubeManipulationDirection == RobotMap.ClawConstants.DIRECTION_INPUT) {
    		Robot.claw.setLeftIntakePower(RobotMap.ClawConstants.LEFT_INPUT_POWER);
    		Robot.claw.setRightIntakePower(RobotMap.ClawConstants.RIGHT_INPUT_POWER);
    	}
    	if(cubeManipulationDirection == RobotMap.ClawConstants.DIRECTION_OUTPUT) {
    		Robot.claw.setLeftIntakePower(RobotMap.ClawConstants.LEFT_OUTPUT_POWER);
    		Robot.claw.setRightIntakePower(RobotMap.ClawConstants.RIGHT_OUTPUT_POWER);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.setLeftIntakePower(0);
		Robot.claw.setRightIntakePower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
