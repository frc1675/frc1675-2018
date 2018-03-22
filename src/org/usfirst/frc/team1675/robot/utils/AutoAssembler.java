package org.usfirst.frc.team1675.robot.utils;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.Go;
import org.usfirst.frc.team1675.robot.commands.MoveArmToEncoderPosition;
import org.usfirst.frc.team1675.robot.commands.TimedActivateClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAssembler {

    public AutoAssembler() {
        // TODO Auto-generated constructor stub
    }

    public CommandGroup generateAuto(FieldLocation startLocation, AutoScoreDirective scoreDirective,
            FieldColorAssignment switchAssignment, FieldColorAssignment scaleAssignment,
            AutoPostScoreDirective[] postscoreDirectives) {
        CommandGroup auto = new CommandGroup();
        FieldLocation currentLocation = startLocation;
        FieldLocation nextLocation = null;
        boolean hasBox = true;
        int commandCount = 0;

        switch (scoreDirective) {
        case SCORE:
            switch (switchAssignment) {
            case RIGHT:
                nextLocation = FieldLocation.SWITCH_RIGHT_FRONT;
                break;
            case LEFT:
                nextLocation = FieldLocation.SWITCH_LEFT_FRONT;
                break;
            }
            auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.HIGH_SWITCH_ENCODER_POSITION));
            commandCount++;
            SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                    "Arm to High Switch");
            
            auto.addSequential(new Go(currentLocation, nextLocation));
            commandCount++;
            SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                    currentLocation + " to " + nextLocation);
            currentLocation = nextLocation;
            auto.addSequential(new TimedActivateClaw(false, RobotMap.ClawConstants.MID_OUTPUT_POWER, 1.0));
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
                        case SWITCH_LEFT_FRONT:
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
                        case SWITCH_RIGHT_FRONT:
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
                            System.err.println("Error 51");
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
                        case SWITCH_LEFT_FRONT:
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
                        case SWITCH_RIGHT_FRONT:
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
                        case SWITCH_LEFT_FRONT:
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
                        case SWITCH_RIGHT_FRONT:
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
                        auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_LEFT_FRONT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.SWITCH_LEFT_FRONT);
                        currentLocation = FieldLocation.SWITCH_LEFT_FRONT;
                        break;
                    case RIGHT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_RIGHT_FRONT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.SWITCH_RIGHT_FRONT);
                        currentLocation = FieldLocation.SWITCH_RIGHT_FRONT;
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
                        auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_LEFT_FRONT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.SWITCH_LEFT_FRONT);
                        currentLocation = FieldLocation.SWITCH_LEFT_FRONT;
                        break;
                    case START_RIGHT:
                        auto.addSequential(new Go(currentLocation, FieldLocation.SWITCH_RIGHT_FRONT));
                        commandCount++;
                        SmartDashboard.putString(switchAssignment + "" + scaleAssignment + " Command #" + commandCount,
                                currentLocation + " to " + FieldLocation.SWITCH_RIGHT_FRONT);
                        currentLocation = FieldLocation.SWITCH_RIGHT_FRONT;
                        break;
                    default:
                        // Robot.break
                        // TODO: YOU BROKE THE AUTO. ALERT THEM
                        break;
                    }
                    break;
                case SKIP:
                    break;
                }
            }
        }
        SmartDashboard.putNumber(switchAssignment + "" + scaleAssignment + " # of Cmds", commandCount);
        return auto;
    }
}
