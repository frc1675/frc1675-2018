package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartLeftToSwitchLeft extends CommandGroup {

	private static final double SEGMENT_1 = 0;
	private static final double SEGMENT_1_TIMEOUT = 0;

	public StartLeftToSwitchLeft() {

		// E to C from left side: move forward

		addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));

		// 10 feet from wall, subtract length of robot by 10 ft I think

	}
}
