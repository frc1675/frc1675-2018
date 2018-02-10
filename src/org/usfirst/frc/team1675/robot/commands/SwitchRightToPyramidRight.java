package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchRightToPyramidRight extends CommandGroup {

	double timeout;
	double setpoint;

	private static final double SEGMENT_1 = 54; //backwards
	private static final double SEGMENT_1_TIMEOUT = 5;

	private static final double TURN_LEFT_ANGLE = 45;
	private static final double TURN_1_TIMEOUT = 2;

	private static final double SEGMENT_2 = 76.37;
	private static final double SEGMENT_2_TIMEOUT = 3;

	public SwitchRightToPyramidRight() { // D to I
		
		//move backwards, turn at angle t move forward
		addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_LEFT_ANGLE, TURN_1_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));
	}
}
