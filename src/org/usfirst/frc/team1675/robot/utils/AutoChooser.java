package org.usfirst.frc.team1675.robot.utils;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {

    private SendableChooser startChooser;
    private SendableChooser scoreChooser;
    private SendableChooser postChooser;
    private SendableChooser nullChooser;

    private enum ScoreOption {
        ALWAYS_SCORE, SCORE_AGREE, ALWAYS_SKIP, DRIVE_ONLY_AUTO;
    }

    private enum NullZoneOption {
        NO, YES, ONLY_MATCHING, YES_CUBE, ONLY_MATCHING_CUBE;
    }

    public AutoChooser() {
        startChooser = new SendableChooser();
        scoreChooser = new SendableChooser();
        postChooser = new SendableChooser();
        nullChooser = new SendableChooser();

        startChooser.addObject(FieldLocation.START_LEFT.toString(), FieldLocation.START_LEFT);
        startChooser.addObject(FieldLocation.START_RIGHT.toString(), FieldLocation.START_RIGHT);
        startChooser.addObject(FieldLocation.START_MIDDLE.toString(), FieldLocation.START_MIDDLE);

        scoreChooser.addObject("Score", ScoreOption.ALWAYS_SCORE);
        scoreChooser.addObject("Skip", ScoreOption.ALWAYS_SKIP);
        scoreChooser.addObject("Score Same Side", ScoreOption.SCORE_AGREE);
        scoreChooser.addObject("Just Cross The Line", ScoreOption.DRIVE_ONLY_AUTO);

        postChooser.addObject("Score Switch", AutoPostScoreDirective.SCORE_SWITCH);
        postChooser.addObject("Score Exchange", AutoPostScoreDirective.EXCHANGE);
        postChooser.addObject("Skip", AutoPostScoreDirective.SKIP);

        nullChooser.addObject("No", NullZoneOption.NO);
        nullChooser.addObject("Go", NullZoneOption.YES);
        nullChooser.addObject("Go if Convenient", NullZoneOption.ONLY_MATCHING);
        nullChooser.addObject("Go With Cube", NullZoneOption.YES_CUBE);
        nullChooser.addObject("Go if Convenient With Cube", NullZoneOption.NO);

        SmartDashboard.putData("Start Location", startChooser);
        SmartDashboard.putData("Score Directive", scoreChooser);
        SmartDashboard.putData("PostScore Directive", postChooser);
        SmartDashboard.putData("Null Zone Directive", nullChooser);
    }

    public CommandGroup chooseAuto(FieldColorAssignment switchAssignment, FieldColorAssignment scaleAssignment) {
        FieldLocation start = (FieldLocation) startChooser.getSelected();
        ScoreOption score = (ScoreOption) scoreChooser.getSelected();
        AutoPostScoreDirective postScore = (AutoPostScoreDirective) postChooser.getSelected();
        NullZoneOption nullOption = (NullZoneOption) nullChooser.getSelected();

        AutoAssembler autoAssembler = new AutoAssembler();
        AutoScoreDirective scoreDirective = null;
        AutoPostScoreDirective[] postScoreDirective = new AutoPostScoreDirective[2];

        switch (score) {
        case DRIVE_ONLY_AUTO:
            scoreDirective = AutoScoreDirective.SKIP;
            postScoreDirective[0] = AutoPostScoreDirective.SKIP;
            postScoreDirective[1] = AutoPostScoreDirective.CROSS_LINE;

            return autoAssembler.generateAuto(start, scoreDirective, switchAssignment, scaleAssignment,
                    postScoreDirective);// just
        // goes
        // straight,
        // so
        // actual
        // start
        // position
        // irrelevant
        case ALWAYS_SCORE:
            scoreDirective = AutoScoreDirective.SCORE;
            break;
        case ALWAYS_SKIP:
            scoreDirective = AutoScoreDirective.SKIP;
            break;
        case SCORE_AGREE:
            if (start.getSide().equals(switchAssignment.toString())) {
                scoreDirective = AutoScoreDirective.SCORE;
            } else {
                scoreDirective = AutoScoreDirective.SKIP;
            }
            break;
        }
        
        postScoreDirective[0] = postScore;
        switch (nullOption) {
        case NO:
            postScoreDirective[1] = AutoPostScoreDirective.SKIP;
            break;
        case YES:
            postScoreDirective[1] = AutoPostScoreDirective.NULL_ZONE;
            break;
        case ONLY_MATCHING:
            switch (postScore) {
            case EXCHANGE:
                postScoreDirective[1] = AutoPostScoreDirective.NULL_ZONE;
                break;
            case SCORE_SWITCH:
                if (switchAssignment.toString().equals(scaleAssignment.toString())) {
                    postScoreDirective[1] = AutoPostScoreDirective.NULL_ZONE;
                } else {
                    postScoreDirective[1] = AutoPostScoreDirective.SKIP;
                }
                break;
            case SKIP:
                if (scaleAssignment.toString().equals(start.getSide())) {
                    postScoreDirective[1] = AutoPostScoreDirective.NULL_ZONE;
                } else {
                    postScoreDirective[1] = AutoPostScoreDirective.SKIP;
                }
                break;
            }

            break;
        case YES_CUBE:
            postScoreDirective[1] = AutoPostScoreDirective.NULL_ZONE_CUBE;
            break;
        case ONLY_MATCHING_CUBE:
            switch (postScore) {
            case EXCHANGE:
                postScoreDirective[1] = AutoPostScoreDirective.NULL_ZONE_CUBE;
                break;
            case SCORE_SWITCH:
                if (switchAssignment.toString().equals(scaleAssignment.toString())) {
                    postScoreDirective[1] = AutoPostScoreDirective.NULL_ZONE_CUBE;
                } else {
                    postScoreDirective[1] = AutoPostScoreDirective.SKIP;
                }
                break;
            case SKIP:
                if (scaleAssignment.equals(start.getSide())) {
                    postScoreDirective[1] = AutoPostScoreDirective.NULL_ZONE_CUBE;
                } else {
                    postScoreDirective[1] = AutoPostScoreDirective.SKIP;
                }
                break;
            }
            break;
        }
        return autoAssembler.generateAuto(start, scoreDirective, switchAssignment, scaleAssignment, postScoreDirective);
    }
}
