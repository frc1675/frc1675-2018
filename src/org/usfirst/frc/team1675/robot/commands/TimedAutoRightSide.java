package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.utils.TimedAutoChooser.ScoreChoice;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedAutoRightSide extends CommandGroup {

    public TimedAutoRightSide(String side, ScoreChoice scoring) {
        if (side.substring(0, 1) == "R" && scoring == ScoreChoice.DO) {
            addSequential(new TimedDrive(2.5));
            addSequential(new MoveArmToEncoderPosition(RobotMap.ArmConstants.NORMAL_SWITCH_ENCODER_POSITION));
            addSequential(new TimedActivateClaw(false,RobotMap.ClawConstants.MID_OUTPUT_POWER, 1.0));
        }else {
            addSequential(new TimedDrive(2.5));
            addSequential(new MoveArmToEncoderPosition(RobotMap.ArmConstants.NORMAL_SWITCH_ENCODER_POSITION));
            // dont do the arm thing
        }
    }
}