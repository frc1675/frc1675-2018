package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartLeftToSwitchLeft extends CommandGroup {
    //e to c
    private static final double TIMEOUT = 10;

	private static final double SEGMENT_1 = 72;
//	private static final double SEGMENT_1_TIMEOUT = 10;
	private static final double TURN_SEGMENT_1 = 30;
	private static final double SEGMENT_2 = 50;
	private static final double TURN_SEGMENT_2 = -30;
	public StartLeftToSwitchLeft() {

		// E to C from left side: move forward

		addSequential(new DriveForDistance(SEGMENT_1,TIMEOUT));
		addSequential(new TurnWithGyro(TURN_SEGMENT_1,TIMEOUT));
		addSequential(new DriveForDistance(SEGMENT_2,TIMEOUT));
		addSequential(new TurnWithGyro(TURN_SEGMENT_2,TIMEOUT));
	
		// 10 feet from wall, subtract length of robot by 10 ft I think

	}
}
