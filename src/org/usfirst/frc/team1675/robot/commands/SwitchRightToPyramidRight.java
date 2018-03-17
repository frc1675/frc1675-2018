package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchRightToPyramidRight extends CommandGroup {
    
    private static final double TIMEOUT = 5;

    private static final double SEGMENT_1 = -39;

    private static final double TURN_LEFT_ANGLE = -68;
    
    private static final double SEGMENT_2 = 44;
 
    public SwitchRightToPyramidRight() { // D to I

        // move backwards, turn at angle t move forward
        addSequential(new DriveForDistance(SEGMENT_1, 1));
        addSequential(new TurnWithGyro(TURN_LEFT_ANGLE, TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_2, 2.5));
    }
}
