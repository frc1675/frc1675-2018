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
        addSequential( new StartMiddleToSwitchRight());
        addParallel(new TimedDrive(.5));
        addSequential( new TimedActivateClaw(false,RobotMap.ClawConstants.MID_OUTPUT_POWER,1.0));
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
