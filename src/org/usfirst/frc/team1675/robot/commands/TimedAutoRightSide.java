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
        if (side.substring(0, 1) == "L") {
            // addSequential(new MoveArmWithEncoderPosition(encoderposition));
            addSequential(new TimedDrive(2.5));
            // dont do the arm thing
        }
    }
}
