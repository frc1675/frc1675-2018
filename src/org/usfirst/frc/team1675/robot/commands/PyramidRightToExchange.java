package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PyramidRightToExchange extends CommandGroup {

    private static final double SEGMENT_1 = 33.94;
    private static final double SEGMENT_1_TIMEOUT = 5; // backwards

    private static final double TURN_1_ANGLE = 45;
    private static final double TURN_1_TIMEOUT = 5; // turn left

    private static final double SEGMENT_2 = 93;
    private static final double SEGMENT_2_TIMEOUT = 5;// forward

    private static final double TURN_3_ANGLE = 90;
    private static final double TURN_3_TIMEOUT = 5;

    private static final double SEGMENT_3 = 147;
    private static final double SEGMENT_3_TIMEOUT = 5;

    public PyramidRightToExchange() {// I to H
        addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
        addSequential(new TurnWithGyro(TURN_1_ANGLE, TURN_1_TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));
        addSequential(new TurnWithGyro(TURN_3_ANGLE, TURN_3_TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_3, SEGMENT_3_TIMEOUT));
    }
}
