package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedAutoMidSide extends CommandGroup {

    public TimedAutoMidSide(String side) {
        if (side.charAt(0) == 'L') {
            addSequential(new TimedDrive(0.5));
            addSequential(new TimedTurn(.5,true));
            addSequential(new TimedDrive(4.0));
            addSequential(new TimedActivateClaw(false,RobotMap.ClawConstants.MID_OUTPUT_POWER, 1.0));
        }else {
            addSequential(new TimedDrive(0.5));
            addSequential(new TimedTurn(.5,false));
            addSequential(new TimedDrive(4.0));
            addSequential(new TimedActivateClaw(false,RobotMap.ClawConstants.MID_OUTPUT_POWER,1.0));
        }
    }
}
