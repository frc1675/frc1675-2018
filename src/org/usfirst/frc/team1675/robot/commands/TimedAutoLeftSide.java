package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedAutoLeftSide extends CommandGroup {

    public TimedAutoLeftSide(String side) {
        if (side.substring(0, 1) == "L") {
            addSequential(new TimedDrive(2.5));
            addSequential(new MoveArmToEncoderPosition(RobotMap.ArmConstants.NORMAL_SWITCH_ENCODER_POSITION));
             addSequential(new ActivateClaw(false,RobotMap.ClawConstants.MID_OUTPUT_POWER));
        }
        if (side.substring(0, 1) == "R") {
            addSequential(new MoveArmToEncoderPosition(RobotMap.ArmConstants.NORMAL_SWITCH_ENCODER_POSITION));
            addSequential(new TimedDrive(2.5));
            // dont do the arm thing
        }
    }
}
