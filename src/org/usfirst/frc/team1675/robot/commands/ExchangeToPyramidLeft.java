package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExchangeToPyramidLeft extends CommandGroup {

    private static final double SEGMENT_1 = -147;
    private static final double SEGMENT_1_TIMEOUT = 5;

    private static final double TURN_RIGHT_ANGLE = 135;
    private static final double TURN_1_TIMEOUT = 5;

    private static final double SEGMENT_2 = 33.94;
    private static final double SEGMENT_2_TIMEOUT = 5;

    public ExchangeToPyramidLeft() { // H to J

        addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
        addSequential(new TurnWithGyro(TURN_RIGHT_ANGLE, TURN_1_TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));

        // Add Commands here:
        // e.g. addSequential(new Command1());
        // addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        // addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
