package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpCube extends CommandGroup {

    public PickUpCube() {
          addParallel(new TimedActivateClaw(true,RobotMap.ClawConstants.INPUT_POWER,1.0));
          addSequential(new DriveForDistance(18,2));
          addSequential(new DriveForDistance(-18,2));   
    }
}