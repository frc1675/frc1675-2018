package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PyramidRightToExchange extends CommandGroup {

	private static final double SEGMENT_1 = 0;
	private static final double SEGMENT_1_TIMEOUT = 0;

	private static final double TURN_1_ANGLE = 0;
	private static final double TURN_1_TIMEOUT = 0;

	private static final double SEGMENT_2 = 0;
	private static final double SEGMENT_2_TIMEOUT = 0;

	private static final double TURN_3_ANGLE = 0;
	private static final double TURN_3_TIMEOUT = 0;

	private static final double SEGMENT_3 = 0;
	private static final double SEGMENT_3_TIMEOUT = 0;

	public PyramidRightToExchange() {// I to H
		addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_1_ANGLE, TURN_1_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_3_ANGLE, TURN_3_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_3, SEGMENT_3_TIMEOUT));
	}
}
