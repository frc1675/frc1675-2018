package org.usfirst.frc.team1675.robot.utils;

import org.usfirst.frc.team1675.robot.commands.Go;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoAssembler {

    public AutoAssembler() {
        // TODO Auto-generated constructor stub
    }

    public CommandGroup[] generateAutos(FieldLocation startLocation, AutoScoreDirective[] scoreDirectives,
            AutoPostscoreDirective[][] postscoreDirectives) {
        CommandGroup[] autos = new CommandGroup[4];
        autos[0] = generateAuto(startLocation, scoreDirectives[0], FieldColorAssignment.LEFT, FieldColorAssignment.LEFT,
                postscoreDirectives[0]);
        autos[1] = generateAuto(startLocation, scoreDirectives[1], FieldColorAssignment.LEFT,
                FieldColorAssignment.RIGHT, postscoreDirectives[1]);
        autos[2] = generateAuto(startLocation, scoreDirectives[2], FieldColorAssignment.RIGHT,
                FieldColorAssignment.LEFT, postscoreDirectives[2]);
        autos[3] = generateAuto(startLocation, scoreDirectives[3], FieldColorAssignment.RIGHT,
                FieldColorAssignment.RIGHT, postscoreDirectives[3]);
        return autos;
    }

    private CommandGroup generateAuto(FieldLocation startLocation, AutoScoreDirective scoreDirective,
            FieldColorAssignment switchAssignment, FieldColorAssignment scaleAssignment,
            AutoPostscoreDirective[] postscoreDirectives) {
        CommandGroup auto = new CommandGroup();
        FieldLocation currentLocation = startLocation;
        boolean hasBox = true;

        switch (scoreDirective) {
        case SCORE:
            switch (switchAssignment) {
            case RIGHT:
                auto.addSequential(new Go(startLocation, FieldLocation.SWITCH_RIGHT));
                currentLocation = FieldLocation.SWITCH_RIGHT;
                break;
            case LEFT:
                auto.addSequential(new Go(startLocation, FieldLocation.SWITCH_LEFT));
                currentLocation = FieldLocation.SWITCH_LEFT;
                break;
            }
            // TODO: Add score command
            hasBox = false;
        case SKIP:
            break;
        }

        for (int i = 0; i < postscoreDirectives.length; i++) {
            switch (postscoreDirectives[i]) {
            case EXCHANGE:
                if (!hasBox) {
                    switch (currentLocation) {
                    case EXCHANGE:
                    case SWITCH_LEFT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_LEFT));
                        currentLocation = FieldLocation.CUBE_PYRAMID_LEFT;
                        // TODO: auto.addSequential(new PICK UP THE DANG CUBE);
                        hasBox = true;
                        break;
                    case SWITCH_RIGHT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_RIGHT));
                        currentLocation = FieldLocation.CUBE_PYRAMID_RIGHT;
                        // TODO: auto.addSequential(new PICK UP THE DANG CUBE);
                        hasBox = true;
                        break;
                    default:
                        // Robot.break();
                        // TODO: PUT SOMETHING HERE THAT TELLS THEM AUTO IS BROKEN
                        break;
                    }
                }
                auto.addSequential(new Go(currentLocation, FieldLocation.EXCHANGE));
                // TODO: place the dang box
                hasBox = false;
                break;
            case NULL_ZONE:
                switch (scaleAssignment) {
                case LEFT:
                    auto.addSequential(new Go(currentLocation, FieldLocation.NULL_ZONE_LEFT));
                    currentLocation = FieldLocation.NULL_ZONE_LEFT;
                    break;
                case RIGHT:
                    auto.addSequential(new Go(currentLocation, FieldLocation.NULL_ZONE_RIGHT));
                    currentLocation = FieldLocation.NULL_ZONE_RIGHT;
                    break;
                }
                break;
            case SCORE_SWITCH:
                if (!hasBox) {
                    switch (currentLocation) {
                    case EXCHANGE:
                    case SWITCH_LEFT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_LEFT));
                        currentLocation = FieldLocation.CUBE_PYRAMID_LEFT;
                        // TODO: pick up the box
                        hasBox = true;
                        break;
                    case SWITCH_RIGHT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_RIGHT));
                        currentLocation = FieldLocation.CUBE_PYRAMID_RIGHT;
                        // TODO: pick up the box
                        hasBox = true;
                        break;
                    default:
                        // Robot.break();
                        // PUT SOMETHING HERE THAT TELLS THEM AUTO IS BROKEN
                        break;
                    }
                }
                switch (switchAssignment) {
                case LEFT:
                    auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_LEFT));
                    currentLocation = FieldLocation.SWITCH_LEFT;
                    break;
                case RIGHT:
                    auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_RIGHT));
                    currentLocation = FieldLocation.SWITCH_RIGHT;
                    break;
                }
                // TODO: score
                hasBox = false;
                break;
            case CROSS_LINE:
                switch (currentLocation) {
                case EXCHANGE:
                    auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_LEFT));
                    currentLocation = FieldLocation.CUBE_PYRAMID_LEFT;
                    break;
                case START_LEFT:
                    auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_LEFT));
                    currentLocation = FieldLocation.SWITCH_LEFT;
                    break;
                case START_RIGHT:
                    auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_RIGHT));
                    currentLocation = FieldLocation.SWITCH_RIGHT;
                    break;
                default:
                    // Robot.break
                    // TODO: YOU BROKE THE AUTO. ALERT THEM
                }
            }
        }
        return auto;
    }
}
