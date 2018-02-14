package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchRightToNullZoneRight extends CommandGroup {

	private static final double SEGMENT_1 = -48;
	private static final double SEGMENT_1_TIMEOUT = 5;

	private static final double TURN_1_ANGLE = 90; // right turn
	private static final double TURN_1_TIMEOUT = 5;

	private static final double SEGMENT_2 = 60.63;
	private static final double SEGMENT_2_TIMEOUT = 3;

	private static final double TURN_2_ANGLE = -90; // left turn
	private static final double TURN_2_TIMEOUT = 5;

	private static final double SEGMENT_3 = 204;
	private static final double SEGMENT_3_TIMEOUT = 5;

	public SwitchRightToNullZoneRight() { // D to B

		addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_1_ANGLE, TURN_1_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_2_ANGLE, TURN_2_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_3, SEGMENT_3_TIMEOUT));
	}
}
