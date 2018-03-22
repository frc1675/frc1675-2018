package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PyramidLeftToSwitchLeft extends CommandGroup {
    // c to j, assuming e to c, and then j to c
    private static final double TIMEOUT = 5;
    private static final double SEGMENT_2 = 44;
    private static final double TURN_RIGHT_ANGLE = -68;
    private static final double SEGMENT_1 = -39;

    
    
    
 
    public PyramidLeftToSwitchLeft() { // D to I

        // move backwards, turn at angle t move forward
        addSequential(new DriveForDistance(SEGMENT_1, 1));
        addSequential(new TurnWithGyro(TURN_RIGHT_ANGLE, TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_2, 2.5));
    }

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
