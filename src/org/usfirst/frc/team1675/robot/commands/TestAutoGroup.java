package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutoGroup extends CommandGroup {

    public TestAutoGroup() {
        // Add Commands here:
        addSequential( new DropKickstand());
        addParallel( new MoveArmToEncoderPosition(RobotMap.ArmConstants.HIGH_SWITCH_ENCODER_POSITION));
        //addSequential(new Wait(5.0));
        addSequential( new StartMiddleToSwitchLeft());
        addParallel(new TimedDrive(.5));
        addSequential( new TimedActivateClaw(false,RobotMap.ClawConstants.MID_OUTPUT_POWER,1.0));
        addSequential (new SwitchLeftToPyramidLeft());
        addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.PICK_UP_POSITION));
        addSequential (new PickUpCube());
       
    }
}
