package org.usfirst.frc.team1675.robot.utils;

public enum AutoPostscoreDirective {
    EXCHANGE("Exchange"),
    NULL_ZONE("Null Zone"),
    SCORE_SWITCH("Score Switch"),
    CROSS_LINE("Cross Line");
    
    private final String NAME;
    
    AutoPostscoreDirective(final String name){
        this.NAME = name;
    }
    
    public String toString() {
        return NAME;
    }
    

}
