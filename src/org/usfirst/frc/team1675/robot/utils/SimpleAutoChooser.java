package org.usfirst.frc.team1675.robot.utils;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.DropKickstand;
import org.usfirst.frc.team1675.robot.commands.Go;
import org.usfirst.frc.team1675.robot.commands.MoveArmToEncoderPosition;
import org.usfirst.frc.team1675.robot.commands.PickUpCube;
import org.usfirst.frc.team1675.robot.commands.ScoreCube;
import org.usfirst.frc.team1675.robot.commands.SwitchRightToPyramidRight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimpleAutoChooser {

    private SendableChooser startChooser;
    private SendableChooser scoreChooser;
    private SendableChooser postChooser;

    private enum ScoreOption {
        SCORE_AGREE, SKIP;
    }

    public SimpleAutoChooser() {
        startChooser = new SendableChooser();
        scoreChooser = new SendableChooser();
        postChooser = new SendableChooser();

        startChooser.addObject(FieldLocation.START_FAR_LEFT.toString(), FieldLocation.START_FAR_LEFT);
        startChooser.addObject(FieldLocation.START_LEFT.toString(), FieldLocation.START_LEFT);
        startChooser.addDefault(FieldLocation.START_MIDDLE.toString(), FieldLocation.START_MIDDLE);
        startChooser.addObject(FieldLocation.START_RIGHT.toString(), FieldLocation.START_RIGHT);
        startChooser.addObject(FieldLocation.START_FAR_RIGHT.toString(), FieldLocation.START_FAR_RIGHT);

        scoreChooser.addDefault("Skip", ScoreOption.SKIP);
        scoreChooser.addObject("Score Same Side", ScoreOption.SCORE_AGREE);

        postChooser.addObject("Score Switch", AutoPostScoreDirective.SCORE_SWITCH);
        postChooser.addObject("Score Exchange", AutoPostScoreDirective.EXCHANGE);
        postChooser.addDefault("Skip", AutoPostScoreDirective.SKIP);

        SmartDashboard.putData("Start Location", startChooser);
        SmartDashboard.putData("Score Directive", scoreChooser);
        SmartDashboard.putData("PostScore Directive", postChooser);
        // TODO Auto-generated constructor stub
    }

    public CommandGroup chooseAuto(FieldColorAssignment switchAssignment) {
        FieldLocation start = (FieldLocation) startChooser.getSelected();
        ScoreOption score = (ScoreOption) scoreChooser.getSelected();
        AutoPostScoreDirective postScore = (AutoPostScoreDirective) postChooser.getSelected();
        CommandGroup auto = new CommandGroup();
        FieldLocation currentLocation = start;
        FieldLocation nextLocation;
        boolean hasCube = true;

        if (start == null || score == null || postScore == null) {
            return null;
        }
        auto.addSequential(new DropKickstand());
        auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.HIGH_SWITCH_ENCODER_POSITION));
        nextLocation = getSwitchDestination(start, switchAssignment);
        if(nextLocation == null) {return auto;}
        
        auto.addSequential(new Go(currentLocation, nextLocation));
        currentLocation = nextLocation;
        if(score == ScoreOption.SCORE_AGREE && (currentLocation.getSide().equals(switchAssignment.toString()))) {
            auto.addSequential(new ScoreCube());
            hasCube = false;
        }
        
        if(postScore == AutoPostScoreDirective.SKIP || start == FieldLocation.START_FAR_LEFT || start == FieldLocation.START_FAR_RIGHT) {
            return auto;
        }
        
        if (!hasCube) {
            nextLocation = getCubePickUpLocation(currentLocation);
            if(nextLocation == null) {return auto;}
            auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.PICK_UP_POSITION));
            auto.addSequential(new Go(currentLocation, nextLocation));
            currentLocation = nextLocation;
            auto.addSequential(new PickUpCube());
            hasCube = true;
        }
        
        switch(postScore) {
        case EXCHANGE:
            auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.HOLD_CUBE_ENCODER_POSITION));
            for(FieldLocation l: getExchangePath(currentLocation)) {
                nextLocation = l;
                auto.addSequential(new Go(currentLocation, nextLocation));
                currentLocation = nextLocation;
            }
//            auto.addSequential(new ScoreCube());
            break;
        case SCORE_SWITCH:
            if(!currentLocation.getSide().equals(switchAssignment.toString())) {
                return auto;
            }
            
            nextLocation = getSwitchDestination(currentLocation, switchAssignment);
            auto.addParallel(new MoveArmToEncoderPosition(RobotMap.ArmConstants.HIGH_SWITCH_ENCODER_POSITION));
            auto.addSequential(new Go(currentLocation, nextLocation));
            currentLocation = nextLocation;
            if(switchAssignment.toString().equals(currentLocation.getSide())) {
                auto.addSequential(new ScoreCube());
                hasCube = false;
            }
            break;
        }
        
        return auto;
    }

    private FieldLocation getSwitchDestination(FieldLocation current, FieldColorAssignment switchAssignment) {
        if (current == FieldLocation.START_FAR_LEFT) {
            return FieldLocation.SWITCH_LEFT_SIDE;
        }
        if (current == FieldLocation.START_LEFT || current == FieldLocation.CUBE_PYRAMID_LEFT || current == FieldLocation.SWITCH_LEFT_FRONT) {
            return FieldLocation.SWITCH_LEFT_FRONT;
        }
        if (current == FieldLocation.START_MIDDLE) {
            if (switchAssignment == FieldColorAssignment.LEFT) {
                return FieldLocation.SWITCH_LEFT_FRONT;
            } else {
                return FieldLocation.SWITCH_RIGHT_FRONT;
            }
        }
        if (current == FieldLocation.START_RIGHT || current == FieldLocation.CUBE_PYRAMID_RIGHT || current == FieldLocation.SWITCH_RIGHT_FRONT) {
            return FieldLocation.SWITCH_RIGHT_FRONT;
        }
        if (current == FieldLocation.START_FAR_RIGHT) {
            return FieldLocation.SWITCH_RIGHT_SIDE;
        }
        

        return null;
    }
    
    private FieldLocation getCubePickUpLocation(FieldLocation current) {
        if(current == FieldLocation.SWITCH_LEFT_FRONT) {
            return FieldLocation.CUBE_PYRAMID_LEFT;
        }
        if(current == FieldLocation.SWITCH_RIGHT_FRONT) {
            return FieldLocation.CUBE_PYRAMID_RIGHT;
        }
        return null;
    }

    private FieldLocation[] getExchangePath(FieldLocation current) {
        FieldLocation[] path;
        if(current == FieldLocation.CUBE_PYRAMID_LEFT || current == FieldLocation.CUBE_PYRAMID_RIGHT) {
            path = new FieldLocation[1];
            path[0] = FieldLocation.EXCHANGE;
        }else {
            path = new FieldLocation[2];
            path[0] = getCubePickUpLocation(current);
            path[1] = FieldLocation.EXCHANGE;
        }
        return path;
    }
}
