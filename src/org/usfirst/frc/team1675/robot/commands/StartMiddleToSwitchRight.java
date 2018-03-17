package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartMiddleToSwitchRight extends CommandGroup {
    private static final double TIMEOUT = 5;

    private static final double Turn_1_ANGLE = 50; //-50

    private static final double Turn_2_ANGLE = -50; //50

    private static final double SEGMENT_1 = 22; //42

    private static final double SEGMENT_2 = 100; //84
    
    private static final double SEGMENT_3 = 12;
    

    public StartMiddleToSwitchRight() {

        // F to D from middle: move forward, turn left, move forward, turn right, move
        // forward

        addSequential(new DriveForDistance(SEGMENT_1, 1.5));
        addSequential(new TurnWithGyro(Turn_1_ANGLE, TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_2, 2));
        addSequential(new TurnWithGyro(Turn_2_ANGLE, TIMEOUT));
        
    }
}
