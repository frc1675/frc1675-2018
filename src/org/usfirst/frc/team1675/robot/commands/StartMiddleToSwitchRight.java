package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartMiddleToSwitchRight extends CommandGroup {

	private static final double Turn_1_ANGLE = 0;
	private static final double TURN_1_TIMEOUT = 0;

	private static final double Turn_2_ANGLE = 0;
	private static final double TURN_2_TIMEOUT = 0;

	private static final double SEGMENT_1 = 0;
	private static final double SEGMENT_1_TIMEOUT = 0;

	private static final double SEGMENT_2 = 0;
	private static final double SEGMENT_2_TIMEOUT = 0;

	private static final double SEGMENT_3 = 0;
	private static final double SEGMENT_3_TIMEOUT = 0;

	public StartMiddleToSwitchRight() {
		// F to D from middle: move forward, turn right, move forward, turn left, move
		// forward

		addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
		addSequential(new TurnWithGyro(Turn_1_ANGLE, TURN_1_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));
		addSequential(new TurnWithGyro(Turn_2_ANGLE, TURN_2_TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_3, SEGMENT_3_TIMEOUT));
	}
}
