package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedAutoRightSide extends CommandGroup {

    public TimedAutoRightSide(String side) {
        if (side.substring(0, 1) == "R") {
            // addSequential(new MoveArmWithEncoderPosition(encoderposition));   
        addSequential(new TimedDrive(2.5));
        // addSequential(new MoveArmWithEncoderPosition(encoderposition));
        // addSequential(new ActivateClaw(2));
        }
        if (side.substring(0,1) == "L") {
            // addSequential(new MoveArmWithEncoderPosition(encoderposition));
            addSequential(new TimedDrive(2.5));
            // dont do the arm thing
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
}
