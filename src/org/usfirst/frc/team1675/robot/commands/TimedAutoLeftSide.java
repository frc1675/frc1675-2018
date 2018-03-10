package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.utils.TimedAutoChooser.ScoreChoice;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedAutoLeftSide extends CommandGroup {

    public TimedAutoLeftSide(String side, ScoreChoice scoring) {
        if (side.charAt(0) == 'L' && scoring == ScoreChoice.DO) {
            addSequential(new TimedDrive(4.5));
            addSequential(new TimedActivateClaw(false, RobotMap.ClawConstants.MID_OUTPUT_POWER, 1.0));
        } else {
            addSequential(new TimedDrive(4.5));
            // don't do the arm thing
        }
    }
}
