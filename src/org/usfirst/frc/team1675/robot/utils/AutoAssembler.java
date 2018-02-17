package org.usfirst.frc.team1675.robot.utils;

import org.usfirst.frc.team1675.robot.commands.Go;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAssembler {

    public AutoAssembler() {
        // TODO Auto-generated constructor stub
    }

    public CommandGroup[] generateAutos(FieldLocation startLocation, AutoScoreDirective[] scoreDirectives,
            AutoPostScoreDirective[][] postscoreDirectives) {
        CommandGroup[] autos = new CommandGroup[4];
        SmartDashboard.putString("Furthest Progress", "start Auto Generation");
        autos[0] = generateAuto(startLocation, scoreDirectives[0], FieldColorAssignment.LEFT, FieldColorAssignment.LEFT,
                postscoreDirectives[0]);
        SmartDashboard.putString("Furthest Progress", "generated LL auto");
        autos[1] = generateAuto(startLocation, scoreDirectives[1], FieldColorAssignment.LEFT,
                FieldColorAssignment.RIGHT, postscoreDirectives[1]);
        SmartDashboard.putString("Furthest Progress", "generated LR auto");
        autos[2] = generateAuto(startLocation, scoreDirectives[2], FieldColorAssignment.RIGHT,
                FieldColorAssignment.LEFT, postscoreDirectives[2]);
        SmartDashboard.putString("Furthest Progress", "generated RL auto");
        autos[3] = generateAuto(startLocation, scoreDirectives[3], FieldColorAssignment.RIGHT,
                FieldColorAssignment.RIGHT, postscoreDirectives[3]);
        SmartDashboard.putString("Furthest Progress", "generated RR auto");
        return autos;
    }

    private CommandGroup generateAuto(FieldLocation startLocation, AutoScoreDirective scoreDirective,
            FieldColorAssignment switchAssignment, FieldColorAssignment scaleAssignment,
            AutoPostScoreDirective[] postscoreDirectives) {
        CommandGroup auto = new CommandGroup();
        FieldLocation currentLocation = startLocation;
        boolean hasBox = true;
        int commandCount = 0;

        switch (scoreDirective) {
        case SCORE:
            switch (switchAssignment) {
            case RIGHT:
                auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_RIGHT));
                commandCount++;
                SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                        currentLocation + " to " + FieldLocation.SWITCH_RIGHT);
                currentLocation = FieldLocation.SWITCH_RIGHT;
                break;
            case LEFT:
                auto.addSequential(new Go(startLocation, FieldLocation.SWITCH_LEFT));
                commandCount++;
                SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                        currentLocation + " to " + FieldLocation.SWITCH_LEFT);
                currentLocation = FieldLocation.SWITCH_LEFT;
                break;
            }
            // TODO: Add score command
            commandCount++;
            SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount, "Score");
            hasBox = false;
        case SKIP:
            break;
        }

        for (int i = 0; i < postscoreDirectives.length; i++) {
            if (postscoreDirectives[i] != null) {
                switch (postscoreDirectives[i]) {
                case EXCHANGE:
                    if (!hasBox) {
                        switch (currentLocation) {
                        case EXCHANGE:
                        case SWITCH_LEFT:
                            auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_LEFT));
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    currentLocation + " to " + FieldLocation.CUBE_PYRAMID_LEFT);
                            currentLocation = FieldLocation.CUBE_PYRAMID_LEFT;
                            // TODO: auto.addSequential(new PICK UP THE DANG CUBE);
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    "Pick up Cube");
                            hasBox = true;
                            break;
                        case SWITCH_RIGHT:
                            auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_RIGHT));
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    currentLocation + " to " + FieldLocation.CUBE_PYRAMID_RIGHT);
                            currentLocation = FieldLocation.CUBE_PYRAMID_RIGHT;
                            // TODO: auto.addSequential(new PICK UP THE DANG CUBE);
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    "Pick up Cube");
                            hasBox = true;
                            break;
                        default:
                            // Robot.break();
                            // TODO: PUT SOMETHING HERE THAT TELLS THEM AUTO IS BROKEN
                            break;
                        }
                    }
                    auto.addSequential(new Go(currentLocation, FieldLocation.EXCHANGE));
                    commandCount++;
                    SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                            currentLocation + " to " + FieldLocation.EXCHANGE);
                    // TODO: place the dang box
                    commandCount++;
                    SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                            "Place Cube");
                    hasBox = false;
                    break;
                case NULL_ZONE_CUBE:
                    if (!hasBox) {
                        switch (currentLocation) {
                        case EXCHANGE:
                        case SWITCH_LEFT:
                            auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_LEFT));
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    currentLocation + " to " + FieldLocation.CUBE_PYRAMID_LEFT);
                            currentLocation = FieldLocation.CUBE_PYRAMID_LEFT;
                            // TODO: auto.addSequential(new PICK UP THE DANG CUBE);
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    "Pick up Cube");
                            hasBox = true;
                            break;
                        case SWITCH_RIGHT:
                            auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_RIGHT));
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    currentLocation + " to " + FieldLocation.CUBE_PYRAMID_RIGHT);
                            currentLocation = FieldLocation.CUBE_PYRAMID_RIGHT;
                            // TODO: auto.addSequential(new PICK UP THE DANG CUBE);
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    "Pick up Cube");
                            hasBox = true;
                            break;
                        default:
                            // Robot.break();
                            // TODO: PUT SOMETHING HERE THAT TELLS THEM AUTO IS BROKEN
                            break;
                        }
                    }
                case NULL_ZONE:
                    switch (scaleAssignment) {
                    case LEFT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.NULL_ZONE_LEFT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.NULL_ZONE_LEFT);
                        currentLocation = FieldLocation.NULL_ZONE_LEFT;
                        break;
                    case RIGHT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.NULL_ZONE_RIGHT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.NULL_ZONE_RIGHT);
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
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    currentLocation + " to " + FieldLocation.CUBE_PYRAMID_LEFT);
                            currentLocation = FieldLocation.CUBE_PYRAMID_LEFT;
                            // TODO: pick up the box
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    "Pick up Cube");
                            hasBox = true;
                            break;
                        case SWITCH_RIGHT:
                            auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_RIGHT));
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    currentLocation + " to " + FieldLocation.CUBE_PYRAMID_RIGHT);
                            currentLocation = FieldLocation.CUBE_PYRAMID_RIGHT;
                            // TODO: pick up the box
                            commandCount++;
                            SmartDashboard.putString(
                                    switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                    "Pick up Cube");
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
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.SWITCH_LEFT);
                        currentLocation = FieldLocation.SWITCH_LEFT;
                        break;
                    case RIGHT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_RIGHT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.SWITCH_RIGHT);
                        currentLocation = FieldLocation.SWITCH_RIGHT;
                        break;
                    }
                    // TODO: score
                    commandCount++;
                    SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                            "Score");
                    hasBox = false;
                    break;
                case CROSS_LINE:
                    switch (currentLocation) {
                    case EXCHANGE:
                        auto.addSequential(new Go(currentLocation, FieldLocation.CUBE_PYRAMID_LEFT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.CUBE_PYRAMID_LEFT);
                        currentLocation = FieldLocation.CUBE_PYRAMID_LEFT;
                        break;
                    case START_LEFT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_LEFT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.SWITCH_LEFT);
                        currentLocation = FieldLocation.SWITCH_LEFT;
                        break;
                    case START_RIGHT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_RIGHT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.SWITCH_RIGHT);
                        currentLocation = FieldLocation.SWITCH_RIGHT;
                        break;
                    default:
                        // Robot.break
                        // TODO: YOU BROKE THE AUTO. ALERT THEM
                    }
                    break;
                case SKIP:
                    break;
                }
            }
        }
        SmartDashboard.putNumber(switchAssignment + "" + scaleAssignment + " Number of Commands", commandCount);
        return auto;
    }
}
