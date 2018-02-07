package org.usfirst.frc.team1675.robot.utils;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoStuffWorkingTitle {

    public AutoStuffWorkingTitle() {
        // TODO Auto-generated constructor stub
    }
    
    public CommandGroup[] generateAutos(FieldLocation startLocation, AutoScoreDirective[] scoreDirectives) {
        CommandGroup[] autos = new CommandGroup[4];
        autos[0] = generateAuto(startLocation, scoreDirectives[0], FieldColorAssignment.SWITCH_LEFT_SCALE_LEFT);
        autos[1] = generateAuto(startLocation, scoreDirectives[1], FieldColorAssignment.SWITCH_LEFT_SCALE_LEFT);
        autos[2] = generateAuto(startLocation, scoreDirectives[2], FieldColorAssignment.SWITCH_LEFT_SCALE_LEFT);
        autos[3] = generateAuto(startLocation, scoreDirectives[3], FieldColorAssignment.SWITCH_LEFT_SCALE_LEFT);
        return autos;
    }
    
    private CommandGroup generateAuto(FieldLocation startLocation, AutoScoreDirective scoreDirective, FieldColorAssignment colorAssignment) {
        CommandGroup auto = new CommandGroup();
        return auto;
    }
    
    
    
    

    private CommandGroup Go(FieldLocation startLocation, FieldLocation endLocation) {
        CommandGroup go = new CommandGroup();
        switch (startLocation) {
        case START_MIDDLE:
            switch (endLocation) {
            case SWITCH_LEFT:
                break;
            case SWITCH_RIGHT:
                break;
            default:
                break;
            }
            break;
        default:
            break;
        }
        return go;
    }
}
