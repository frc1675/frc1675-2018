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

    private enum PostScoreOption {
        SCORE, EXCHANGE, SKIP;
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

        postChooser.addObject("Score Switch", PostScoreOption.SCORE);
        postChooser.addObject("Score Exchange", PostScoreOption.EXCHANGE);
        postChooser.addObject("Skip", PostScoreOption.SKIP);

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

    public CommandGroup[] chooseAuto() {
        FieldLocation start = (FieldLocation) startChooser.getSelected();
        ScoreOption score = (ScoreOption) scoreChooser.getSelected();
        PostScoreOption postScore = (PostScoreOption) postChooser.getSelected();
        NullZoneOption nullOption = (NullZoneOption) nullChooser.getSelected();

        AutoAssembler autoAssembler = new AutoAssembler();

        AutoScoreDirective[] scoreDirectives = new AutoScoreDirective[4];
        AutoPostScoreDirective[][] postScoreDirectives = new AutoPostScoreDirective[4][];

        switch (score) {
        case DRIVE_ONLY_AUTO:
            for (int i = 0; i < scoreDirectives.length; i++) {
                scoreDirectives[i] = AutoScoreDirective.SKIP;
                AutoPostScoreDirective[] specificDirective = { AutoPostScoreDirective.CROSS_LINE };
                postScoreDirectives[i] = specificDirective;
            }
            return autoAssembler.generateAutos(FieldLocation.START_LEFT, scoreDirectives, postScoreDirectives);//just goes straight, so actual start position irrelevant
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
                if(i < 2) {
                    side = "Left";
                }else {
                    side = "Right";
                }
                
                if(start.getSide() == side) {
                    scoreDirectives[i] = AutoScoreDirective.SCORE;
                }else {
                    scoreDirectives[i] = AutoScoreDirective.SKIP;
                }
            }
            break;
        }
        
        for(int i = 0; i < postScoreDirectives.length; i++) {
            AutoPostScoreDirective[] specificDirective = { AutoPostScoreDirective.SKIP};
            postScoreDirectives[i] = specificDirective; 
        }
        return autoAssembler.generateAutos(start, scoreDirectives, postScoreDirectives);
    }

}
