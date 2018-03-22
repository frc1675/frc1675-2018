package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PyramidLeftToExchange extends CommandGroup {

    private static final double SEGMENT_1 = -33.94;
    private static final double TURN_RIGHT_ANGLE = 105;
    private static final double SEGMENT_2 = 87;

    public PyramidLeftToExchange() {// J to H

        addSequential(new DriveForDistance(SEGMENT_1, 2));
        addSequential(new TurnWithGyro(TURN_RIGHT_ANGLE, 4));
        addSequential(new DriveForDistance(SEGMENT_2, 3.5));

    }
}
