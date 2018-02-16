package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PyramidLeftToExchange extends CommandGroup {

	private static final double SEGMENT_1 = 33.94;
	private static final double SEGMENT_1_TIMEOUT = 5;

	private static final double TURN_RIGHT_ANGLE = -135;
	private static final double TURN_1_TIMEOUT = 5;

	private static final double SEGMENT_2 = 147;
	private static final double SEGMENT_2_TIMEOUT = 5;

	public PyramidLeftToExchange() {// J to H

		addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_RIGHT_ANGLE, TURN_1_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));

	}
}
