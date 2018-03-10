package org.usfirst.frc.team1675.robot.utils;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.DropKickstand;
import org.usfirst.frc.team1675.robot.commands.MoveArmToEncoderPosition;
import org.usfirst.frc.team1675.robot.commands.TimedAutoLeftSide;
import org.usfirst.frc.team1675.robot.commands.TimedAutoMidSide;
import org.usfirst.frc.team1675.robot.commands.TimedAutoRightSide;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimedAutoChooser {
    private SendableChooser<StartPosition> startChoice;

    // private SendableChooser<ScoreChoice> scoreChoice;
    public enum StartPosition {
        LEFT, RIGHT, MID, NEVER
    }

    public enum ScoreChoice {
        DO, DONT
    }

    public TimedAutoChooser() {

        startChoice = new SendableChooser<>();
        startChoice.addObject("Left Side Start", StartPosition.LEFT);
        startChoice.addObject("Right Side Start", StartPosition.RIGHT);
        startChoice.addObject("Middle Start", StartPosition.MID);
        startChoice.addObject("Never Score", StartPosition.NEVER);
        SmartDashboard.putData("Starting Position", startChoice);

        // scoreChoice = new SendableChooser<>();
        // scoreChoice.addObject("Scor", ScoreChoice.DO);
        // scoreChoice.addObject("Not Scor", ScoreChoice.DONT);
        // SmartDashboard.putData("Scorin", scoreChoice);
    }

    public CommandGroup generateAuto(String side) {
        CommandGroup auto = new CommandGroup();

        StartPosition selectedStart = (StartPosition) startChoice.getSelected();
        ScoreChoice selectedScore = ScoreChoice.DO;
        // ScoreChoice selectedScore = (ScoreChoice) scoreChoice.getSelected();
        switch (selectedStart) {
        case LEFT:
            auto.addSequential(new DropKickstand());
            auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.HIGH_SWITCH_ENCODER_POSITION));
            auto.addSequential(new TimedAutoLeftSide(side, selectedScore));
            break;
        case RIGHT:
            auto.addSequential(new DropKickstand());
            auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.HIGH_SWITCH_ENCODER_POSITION));
            auto.addSequential(new TimedAutoRightSide(side, selectedScore));
            break;
        case MID:
            auto.addSequential(new DropKickstand());
            auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.HIGH_SWITCH_ENCODER_POSITION));
            auto.addSequential(new TimedAutoMidSide(side));
            break;
        default:
            auto.addSequential(new DropKickstand());
            auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.HIGH_SWITCH_ENCODER_POSITION));
            auto.addSequential(new TimedAutoLeftSide(side, ScoreChoice.DONT));
        }
        return auto;
    }

}
