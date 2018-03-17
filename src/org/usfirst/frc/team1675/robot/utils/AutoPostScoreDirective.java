package org.usfirst.frc.team1675.robot.utils;

public enum AutoPostScoreDirective {
    EXCHANGE("Exchange"),
    NULL_ZONE("Null Zone"),
    NULL_ZONE_CUBE("Null Zone With Cube"),
    SCORE_SWITCH("Score Switch"),
    CROSS_LINE("Cross Line"),
    SKIP("Skip");
    
    private final String NAME;
    
    AutoPostScoreDirective(final String name){
        this.NAME = name;
    }
    
    public String toString() {
        return NAME;
    }
    

}
