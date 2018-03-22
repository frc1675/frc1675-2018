package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PyramidRightToSwitchRight extends CommandGroup {
    private static final double SEGMENT_2 = 44;
    private static final double TURN_RIGHT_ANGLE = 68;
    private static final double SEGMENT_1 = -39;

    public PyramidRightToSwitchRight() {

        // move backwards, turn at angle t move forward
        addSequential(new DriveForDistance(SEGMENT_1, 1));
        addSequential(new TurnWithGyro(TURN_RIGHT_ANGLE, 3));
        addSequential(new DriveForDistance(SEGMENT_2, 2.5));
    }

}
