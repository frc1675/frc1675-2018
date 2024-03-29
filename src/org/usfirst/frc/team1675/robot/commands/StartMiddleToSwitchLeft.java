package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartMiddleToSwitchLeft extends CommandGroup {

    private static final double Turn_1_ANGLE = -35; //-50

    private static final double Turn_2_ANGLE = 35; //50

    private static final double SEGMENT_1 = 22; //42

    private static final double SEGMENT_2 = 92; //84
    
    private static final double SEGMENT_3 = 18;

    public StartMiddleToSwitchLeft() {

        // F to C from middle: move forward, turn left, move forward, turn right, move
        // forward

        addSequential(new DriveForDistance(SEGMENT_1, 2));
        addSequential(new TurnWithGyro(Turn_1_ANGLE, 3));
        addSequential(new DriveForDistance(SEGMENT_2, 2));
        addSequential(new TurnWithGyro(Turn_2_ANGLE, 3));
       // addSequential(new DriveForDistance(SEGMENT_3,1));
    }
}
