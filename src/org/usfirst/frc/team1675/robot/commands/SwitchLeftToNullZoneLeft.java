package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchLeftToNullZoneLeft extends CommandGroup {

	private static final double SEGMENT_1 = -48; // backwards
	private static final double SEGMENT_1_TIMEOUT = 5;

	private static final double TURN_1_ANGLE = -90;// left
	private static final double TURN_1_TIMEOUT = 5;

	private static final double SEGMENT_2 = 62.65; // forward
	private static final double SEGMENT_2_TIMEOUT = 5;

	private static final double TURN_2_ANGLE = 90;// right
	private static final double TURN_2_TIMEOUT = 5;

	private static final double SEGMENT_3 = 204; // forward
	private static final double SEGMENT_3_TIMEOUT = 5;

	public SwitchLeftToNullZoneLeft() { // C to A

		addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_1_ANGLE, TURN_1_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_2_ANGLE, TURN_2_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_3, SEGMENT_3_TIMEOUT));

	}
}