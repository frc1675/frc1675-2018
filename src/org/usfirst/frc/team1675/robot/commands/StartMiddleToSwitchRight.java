package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartMiddleToSwitchRight extends CommandGroup {
    private static final double TIMEOUT = 10;

    private static final double Turn_1_ANGLE = 50;
    private static final double TURN_1_TIMEOUT = 0;

    private static final double Turn_2_ANGLE = -50;
    private static final double TURN_2_TIMEOUT = 0;

    private static final double SEGMENT_1 = 42;
    private static final double SEGMENT_1_TIMEOUT = 0;

    private static final double SEGMENT_2 = 60;
    private static final double SEGMENT_2_TIMEOUT = 0;

    public StartMiddleToSwitchRight() {

        // F to D from middle: move forward, turn left, move forward, turn right, move
        // forward

        addSequential(new DriveForDistance(SEGMENT_1, TIMEOUT));
        addSequential(new TurnWithGyro(Turn_1_ANGLE, TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_2, TIMEOUT));
        addSequential(new TurnWithGyro(Turn_2_ANGLE, TIMEOUT));
    }
}
