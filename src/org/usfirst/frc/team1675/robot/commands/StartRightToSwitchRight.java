package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartRightToSwitchRight extends CommandGroup {
    // g to d
    private static final double TIMEOUT = 10;

    private static final double SEGMENT_1 = 96;
    // private static final double SEGMENT_1_TIMEOUT = 10;
    //private static final double TURN_SEGMENT_1 = -23.25;
    //private static final double SEGMENT_2 = 104.5;
    //private static final double TURN_SEGMENT_2 = 23.25;x
    //private static final double SEGMENT_3 = 36;

    public StartRightToSwitchRight() {

        // G to D move from right side: move forward

        addSequential(new DriveForDistance(SEGMENT_1, TIMEOUT));
//        addSequential(new TurnWithGyro(TURN_SEGMENT_1, TIMEOUT));
//        addSequential(new DriveForDistance(SEGMENT_2, TIMEOUT));
//        addSequential(new TurnWithGyro(TURN_SEGMENT_2, TIMEOUT));
//        addSequential(new DriveForDistance(SEGMENT_3, TIMEOUT));

        // 10 feet from wall, subtract length of robot by 10 ft I think

    }
}
