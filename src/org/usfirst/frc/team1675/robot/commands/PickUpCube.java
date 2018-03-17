package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpCube extends CommandGroup {

    public PickUpCube() {
          addSequential(new MoveArmToEncoderPosition(RobotMap.ArmConstants.PICK_UP_POSITION));
          addParallel(new TimedActivateClaw(false,RobotMap.ClawConstants.INPUT_POWER,1.0));
          addSequential(new DriveForDistance(18,2));
          addSequential(new DriveForDistance(-18,2));   
    }
}