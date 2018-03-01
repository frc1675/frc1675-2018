package org.usfirst.frc.team1675.Util;

import org.usfirst.frc.team1675.robot.commands.TimedAutoLeftSide;
import org.usfirst.frc.team1675.robot.commands.TimedAutoRightSide;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimedAutoChooser {
    private SendableChooser startChoice;
    public enum startPosition {
        LEFT, RIGHT
    }
    public TimedAutoChooser() {
        startChoice = new SendableChooser();
        startChoice.addObject("Left Side Start", startPosition.LEFT);
        startChoice.addObject("Right Side Start", startPosition.RIGHT);
        SmartDashboard.putData("Starting Position", startChoice);
    }
    public CommandGroup generateAuto(String side) {
        CommandGroup auto = new CommandGroup();
        startPosition selectedStart = (startPosition) startChoice.getSelected();
        switch(selectedStart) {
        case LEFT:
            auto.addSequential(new TimedAutoLeftSide(side));
            break;
        case RIGHT:
            auto.addSequential(new TimedAutoRightSide(side));
            break;
        }
        return auto;
    }

}
