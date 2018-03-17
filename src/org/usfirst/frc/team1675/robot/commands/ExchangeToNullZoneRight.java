package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExchangeToNullZoneRight extends CommandGroup {

    private static final double SEGMENT_1 = -147;
    private static final double SEGMENT_1_TIMEOUT = 5;

    private static final double TURN_1_ANGLE = -90;
    private static final double TURN_1_TIMEOUT = 5;

    private static final double SEGMENT_2 = 177.535;
    private static final double SEGMENT_2_TIMEOUT = 5;

    private static final double TURN_2_ANGLE = -90;
    private static final double TURN_2_TIMEOUT = 5;

    private static final double SEGMENT_3 = 177;
    private static final double SEGMENT_3_TIMEOUT = 5;

    public ExchangeToNullZoneRight() { // H to B

        addSequential(new DriveForDistance(SEGMENT_1, SEGMENT_1_TIMEOUT));
        addSequential(new TurnWithGyro(TURN_1_ANGLE, TURN_1_TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_2, SEGMENT_2_TIMEOUT));
        addSequential(new TurnWithGyro(TURN_2_ANGLE, TURN_2_TIMEOUT));
        addSequential(new DriveForDistance(SEGMENT_3, SEGMENT_3_TIMEOUT));

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
