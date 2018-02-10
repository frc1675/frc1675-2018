package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PyramidLeftToExchange extends CommandGroup {

	private static final double TURN_RIGHT_ANGLE = 0;
	private static final double TURN_1_TIMEOUT = 0;

	private static final double SEGMENT_1 = 0;
	private static final double SEGMENT_1_TIMEOUT = 0;

	public PyramidLeftToExchange() {// J to H

		addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_RIGHT_ANGLE, TURN_1_TIMEOUT));
	}
}
