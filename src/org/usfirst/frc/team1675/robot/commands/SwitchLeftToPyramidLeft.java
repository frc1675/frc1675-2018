package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchLeftToPyramidLeft extends CommandGroup {

	private static final int TURN_1_ANGLE = 0;
	private static final int TURN_1_TIMEOUT = 0;

	private static final int SEGMANT_1 = 0;
	private static final int SEGMENT_1_TIMEOUT = 0;

	private static final int TURN_2_ANGLE = 0;
	private static final int TURN_2_TIMEOUT = 0;

	public SwitchLeftToPyramidLeft() {

		// C to J From left switch: back out, turn right, move forward

		addSequential(new TurnWithGyro(TURN_1_ANGLE, TURN_1_TIMEOUT));
		addSequential(new DriveForDistance(SEGMANT_1, SEGMENT_1_TIMEOUT));
		addSequential(new TurnWithGyro(TURN_2_ANGLE, TURN_2_TIMEOUT));

	}
}
