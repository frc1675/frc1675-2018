package org.usfirst.frc.team1675.robot.utils;

public enum FieldColorAssignment {
    LEFT("L",0), RIGHT("R",1);
    
private final String NAME;
private final int ID;
    
    FieldColorAssignment(final String name, final int ID){
        this.NAME = name;
        this.ID = ID;
    }
    
    public int getID() {
        return ID;
    }
    
    public String toString() {
        return NAME;
    }
    
    public static FieldColorAssignment getSideFromChar(char c) {
        if(c == 'L') {
            return LEFT;
        }
        if(c == 'R') {
            return RIGHT;
        }
        
        return null;
    }
}
