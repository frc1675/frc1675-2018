package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchLeftToPyramidLeft extends CommandGroup {
    // c to j, assuming e to c, and then j to c
    private static final double TIMEOUT = 10;

    private static final int TURN_1_ANGLE = 60;
    private static final int TURN_1_TIMEOUT = 0;

    private static final int SEGMENT_1 = 48;
    private static final int SEGMENT_1_TIMEOUT = 0;

    public SwitchLeftToPyramidLeft() {

        // C to J From left switch: back out, turn right, move forward

        addSequential(new TurnWithGyro(TURN_1_ANGLE, TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_1, TIMEOUT));

    }
}
