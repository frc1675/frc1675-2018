package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartFarLeftToSwitchSideLeft extends CommandGroup {

    private static final double TIMEOUT = 7.5;
        
    private static final double SEGMENT_1 = 124;
    private static final double TURN_SEGMENT_1 = 90;
    private static final double SEGMENT_2 = 36;
        
    public StartFarLeftToSwitchSideLeft() {
        addSequential(new DriveForDistance(SEGMENT_1, 5));
        addSequential(new TurnWithGyro(TURN_SEGMENT_1, TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_2, 2));        
    }
}
