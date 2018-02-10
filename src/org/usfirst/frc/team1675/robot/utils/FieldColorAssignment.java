package org.usfirst.frc.team1675.robot.utils;

public enum FieldColorAssignment {
    LEFT("L"), RIGHT("R");
    
private final String NAME;
    
    FieldColorAssignment(final String name){
        this.NAME = name;
    }
    
    public String toString() {
        return NAME;
    }
}
