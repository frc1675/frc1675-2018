package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.utils.FieldLocation;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Go extends CommandGroup {

    FieldLocation start;
    FieldLocation end;

    public Go(FieldLocation startLocation, FieldLocation endLocation) {
        if(startLocation == endLocation) {
            return;
        }
        end = endLocation;
        start = startLocation;
        switch (start) {
        case START_FAR_LEFT:
            switch(end) {
            case SWITCH_LEFT_SIDE:
                this.addSequential(new StartFarLeftToSwitchSideLeft());
                break;
            default:
                errorPath();
                break;
            }
            break;
        case START_LEFT:
            switch (end) {
            case SWITCH_LEFT_FRONT:
                this.addSequential(new StartLeftToSwitchLeft());
                break;
            default:
                errorPath();
                break;
            }
            break;
        case START_MIDDLE:
            switch (end) {
            case SWITCH_LEFT_FRONT:
                this.addSequential(new StartMiddleToSwitchLeft());
                break;
            case SWITCH_RIGHT_FRONT:
                this.addSequential(new StartMiddleToSwitchRight());
                break;
            default:
                errorPath();
                break;
            }
            break;
        case START_RIGHT:
            switch (end) {
            case SWITCH_RIGHT_FRONT:
                this.addSequential(new StartRightToSwitchRight());
                break;
            default:
                errorPath();
                break;
            }
            break;
        case START_FAR_RIGHT:
            switch(end) {
            case SWITCH_RIGHT_SIDE:
                this.addSequential(new StartFarRightToSwitchSideRight());
                break;
            default:
                errorPath();
                break;
            }
            break;
        case CUBE_PYRAMID_LEFT:
            switch (end) {
            case EXCHANGE:
                this.addSequential(new PyramidLeftToExchange());
                break;
            case SWITCH_LEFT_FRONT:
                this.addSequential(new PyramidLeftToSwitchLeft());
                break;
            default:
                errorPath();
                break;
            }
        case CUBE_PYRAMID_RIGHT:
            switch (end) {
            case EXCHANGE:
                this.addSequential(new PyramidRightToExchange());
                break;
            case SWITCH_RIGHT_FRONT:
                this.addSequential(new PyramidRightToSwitchRight());
                break;
            default:
                errorPath();
                break;
            }
        case SWITCH_LEFT_FRONT:
            switch (end) {
            case NULL_ZONE_LEFT:
                this.addSequential(new SwitchLeftToNullZoneLeft());
                break;
            case CUBE_PYRAMID_LEFT:
                this.addSequential(new SwitchLeftToPyramidLeft());
                break;
            default:
                errorPath();
                break;
            }
            break;
        case SWITCH_RIGHT_FRONT:
            switch (end) {
            case NULL_ZONE_RIGHT:
                this.addSequential(new SwitchRightToNullZoneRight());
                break;
            case CUBE_PYRAMID_RIGHT:
                this.addSequential(new SwitchRightToPyramidRight());
                break;
            default:
                errorPath();
                break;
            }
            break;
        case EXCHANGE:
            switch (end) {
            case NULL_ZONE_LEFT:
                this.addSequential(new ExchangeToNullZoneLeft());
                break;
            case NULL_ZONE_RIGHT:
                this.addSequential(new ExchangeToNullZoneRight());
                break;
            case CUBE_PYRAMID_LEFT:
                this.addSequential(new ExchangeToPyramidLeft());
                break;
            default:
                errorPath();
            }
        default:
            errorPath();
            break;
        }
    }
    private void errorPath() {
        System.err.println("Start: " + start.toString());
        System.err.println("End: " + end.toString());
    }
}
