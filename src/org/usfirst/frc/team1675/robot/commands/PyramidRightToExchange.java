package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PyramidRightToExchange extends CommandGroup {

    private static final double SEGMENT_1 = -30;
    private static final double TURN_1_ANGLE = -50;
    private static final double SEGMENT_2 = 100;
    private static final double TURN_3_ANGLE = -62;
    private static final double SEGMENT_3 = 50;

    public PyramidRightToExchange() {// I to H
        addSequential(new DriveForDistance(SEGMENT_1, 1.5));
        addSequential(new TurnWithGyro(TURN_1_ANGLE, 3));
        addSequential(new DriveForDistance(SEGMENT_2, 4));
        addSequential(new TurnWithGyro(TURN_3_ANGLE, 3));
        addSequential(new DriveForDistance(SEGMENT_3, 3));
    }
}
