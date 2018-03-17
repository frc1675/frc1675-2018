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

        int id = 2 * scaleAssignment.getID() + 1 * switchAssignment.getID();

        AutoAssembler autoAssembler = new AutoAssembler();

        AutoScoreDirective[] scoreDirectives = new AutoScoreDirective[4];
        AutoPostScoreDirective[][] postScoreDirectives = new AutoPostScoreDirective[4][2];

        switch (score) {
        case DRIVE_ONLY_AUTO:
            for (int i = 0; i < scoreDirectives.length; i++) {
                scoreDirectives[i] = AutoScoreDirective.SKIP;
                postScoreDirectives[i][0] = AutoPostScoreDirective.SKIP;
                postScoreDirectives[i][1] = AutoPostScoreDirective.CROSS_LINE;
            }
            return autoAssembler.generateAuto(start, scoreDirectives[id], switchAssignment, scaleAssignment,
                    postScoreDirectives[id]);// just
        // goes
        // straight,
        // so
        // actual
        // start
        // position
        // irrelevant
        case ALWAYS_SCORE:
            for (int i = 0; i < scoreDirectives.length; i++) {
                scoreDirectives[i] = AutoScoreDirective.SCORE;
            }
            break;
        case ALWAYS_SKIP:
            for (int i = 0; i < scoreDirectives.length; i++) {
                scoreDirectives[i] = AutoScoreDirective.SKIP;
            }
            break;
        case SCORE_AGREE:
            String side;
            for (int i = 0; i < scoreDirectives.length; i++) {
                if (i < 2) {
                    side = "Left";
                } else {
                    side = "Right";
                }

                if (start.getSide() == side) {
                    scoreDirectives[i] = AutoScoreDirective.SCORE;
                } else {
                    scoreDirectives[i] = AutoScoreDirective.SKIP;
                }
            }
            break;
        }

        String switchSide;
        String nullSide;
        switch (nullOption) {
        case NO:
            for (int i = 0; i < postScoreDirectives.length; i++) {
                postScoreDirectives[i][0] = postScore;
                postScoreDirectives[i][1] = AutoPostScoreDirective.SKIP;
            }
            break;
        case YES:
            for (int i = 0; i < postScoreDirectives.length; i++) {
                postScoreDirectives[i][0] = postScore;
                postScoreDirectives[i][1] = AutoPostScoreDirective.NULL_ZONE;
            }
            break;
        case ONLY_MATCHING:
            for (int i = 0; i < postScoreDirectives.length; i++) {
                if (i % 2 == 0) {
                    nullSide = "Left";
                } else {
                    nullSide = "Right";
                }

                if (i < 2) {
                    switchSide = "Left";
                } else {
                    switchSide = "Right";
                }
                postScoreDirectives[i][0] = postScore;
                switch (postScore) {
                case EXCHANGE:
                    postScoreDirectives[i][1] = AutoPostScoreDirective.NULL_ZONE;
                    break;
                case SCORE_SWITCH:
                    if (nullSide == switchSide) {
                        postScoreDirectives[i][1] = AutoPostScoreDirective.NULL_ZONE;
                    } else {
                        postScoreDirectives[i][1] = AutoPostScoreDirective.SKIP;
                    }
                    break;
                case SKIP:
                    if (nullSide == start.getSide()) {
                        postScoreDirectives[i][1] = AutoPostScoreDirective.NULL_ZONE;
                    } else {
                        postScoreDirectives[i][1] = AutoPostScoreDirective.SKIP;
                    }
                    break;
                }

            }
            break;
        case YES_CUBE:
            for (int i = 0; i < postScoreDirectives.length; i++) {
                postScoreDirectives[i][0] = postScore;
                postScoreDirectives[i][1] = AutoPostScoreDirective.NULL_ZONE_CUBE;
            }
            break;
        case ONLY_MATCHING_CUBE:
            for (int i = 0; i < postScoreDirectives.length; i++) {
                if (i % 2 == 0) {
                    nullSide = "Left";
                } else {
                    nullSide = "Right";
                }

                if (i < 2) {
                    switchSide = "Left";
                } else {
                    switchSide = "Right";
                }
                postScoreDirectives[i][0] = postScore;
                switch (postScore) {
                case EXCHANGE:
                    postScoreDirectives[i][1] = AutoPostScoreDirective.NULL_ZONE_CUBE;
                    break;
                case SCORE_SWITCH:
                    if (nullSide == switchSide) {
                        postScoreDirectives[i][1] = AutoPostScoreDirective.NULL_ZONE_CUBE;
                    } else {
                        postScoreDirectives[i][1] = AutoPostScoreDirective.SKIP;
                    }
                    break;
                case SKIP:
                    if (nullSide == start.getSide()) {
                        postScoreDirectives[i][1] = AutoPostScoreDirective.NULL_ZONE_CUBE;
                    } else {
                        postScoreDirectives[i][1] = AutoPostScoreDirective.SKIP;
                    }
                    break;
                }
            }
            break;
        }
        return autoAssembler.generateAuto(start,
                scoreDirectives[id], switchAssignment,
                scaleAssignment, postScoreDirectives[id]);
    }

}
