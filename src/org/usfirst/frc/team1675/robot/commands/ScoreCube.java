package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScoreCube extends CommandGroup {

    public ScoreCube() {
        addParallel(new TimedDrive(.5));
        addSequential(new TimedActivateClaw(false, RobotMap.ClawConstants.MIN_OUTPUT_POWER, 1.0));
    }
}
