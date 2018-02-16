package org.usfirst.frc.team1675.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser {
    
    private SendableChooser startChooser;
    private SendableChooser scoreChooser;
    
    private enum ScoreOption{
        ALWAYS_SCORE,
        SCORE_AGREE,
        ALWAYS_SKIP,
        DRIVE_ONLY_AUTO;
    }
    
    private enum PostScoreOption{
        SCORE,
        EXCHANGE,
        SKIP;
    }
    
    private enum NullZoneOption{
        NO,
        YES,
        ONLY_MATCHING,
        YES_CUBE,
        ONLY_MATCHING_CUBE;
    }

    public AutoChooser() {
        startChooser = new SendableChooser();
        scoreChooser = new SendableChooser();
        
        startChooser.addObject(FieldLocation.START_LEFT.toString(), FieldLocation.START_LEFT);
        startChooser.addObject(FieldLocation.START_RIGHT.toString(), FieldLocation.START_RIGHT);
        startChooser.addObject(FieldLocation.START_MIDDLE.toString(), FieldLocation.START_MIDDLE);
        
        scoreChooser.addObject("Score", ScoreOption.ALWAYS_SCORE);
        scoreChooser.addObject("Skip", ScoreOption.ALWAYS_SKIP);
        scoreChooser.addObject("Score Same Side", ScoreOption.SCORE_AGREE);
        
        
        
        
    }

}
