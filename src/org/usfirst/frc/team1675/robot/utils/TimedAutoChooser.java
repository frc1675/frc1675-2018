package org.usfirst.frc.team1675.robot.utils;

import org.usfirst.frc.team1675.robot.commands.TimedAutoLeftSide;
import org.usfirst.frc.team1675.robot.commands.TimedAutoRightSide;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimedAutoChooser {
    private SendableChooser startChoice;
    private SendableChooser scoreChoice;
    public enum StartPosition {
        LEFT, RIGHT
    }
    public enum ScoreChoice {
        DO, DONT
    }
    public TimedAutoChooser() {
       
        scoreChoice = new SendableChooser();        
        scoreChoice.addObject("Score", ScoreChoice.DO);
        scoreChoice.addObject("Not Score", ScoreChoice.DONT);
        SmartDashboard.putData("Scoring", scoreChoice);
        
        startChoice = new SendableChooser();
        startChoice.addObject("Left Side Start", StartPosition.LEFT);
        startChoice.addObject("Right Side Start", StartPosition.RIGHT);
        SmartDashboard.putData("Starting Position", startChoice);
    }
    public CommandGroup generateAuto(String side) {
        CommandGroup auto = new CommandGroup();
        StartPosition selectedStart = (StartPosition) startChoice.getSelected();
        ScoreChoice selectedScore = (ScoreChoice) scoreChoice.getSelected();
        switch(selectedStart) {
        case LEFT:
            auto.addSequential(new TimedAutoLeftSide(side, selectedScore));
            break;
        case RIGHT:
            auto.addSequential(new TimedAutoRightSide(side, selectedScore));
            break;
        }
        return auto;
    }

}
