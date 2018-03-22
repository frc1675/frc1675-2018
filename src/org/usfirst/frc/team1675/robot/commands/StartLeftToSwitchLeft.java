package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartLeftToSwitchLeft extends CommandGroup {
    // e to c

    private static final double SEGMENT_1 = 22;
    // private static final double SEGMENT_1_TIMEOUT = 10;
    private static final double TURN_SEGMENT_1 = 15;
    private static final double SEGMENT_2 = 70;
    private static final double TURN_SEGMENT_2 = -15;

    public StartLeftToSwitchLeft() {

        // E to C from left side: move forward

        addSequential(new DriveForDistance(SEGMENT_1, 1.5));
        addSequential(new TurnWithGyro(TURN_SEGMENT_1, 3));
        addSequential(new DriveForDistance(SEGMENT_2, 2));
        addSequential(new TurnWithGyro(TURN_SEGMENT_2, 3));

        // 10 feet from wall, subtract length of robot by 10 ft I think

    }
}
