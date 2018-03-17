package org.usfirst.frc.team1675.robot.utils;

public enum FieldLocation {
    SWITCH_LEFT("Left Switch", "L"),
    SWITCH_RIGHT("Right Switch", "R"),
    START_LEFT("Left Start", "L"),
    START_MIDDLE("Middle Start", "R"),
    START_RIGHT("Right Start", "R"),
    CUBE_PYRAMID_LEFT("Left Pyramid", "L"),
    CUBE_PYRAMID_RIGHT("Right Pyramid", "R"),
    NULL_ZONE_LEFT("Left Null Zone", "L"),
    NULL_ZONE_RIGHT("Right Null Zone", "R"),
    EXCHANGE("Exchange", "L");
    
private final String NAME;
private final String SIDE;
    
    FieldLocation(final String name, String side){
        this.NAME = name;
        this.SIDE = side;
    }
    
    public String toString() {
        return NAME;
    }
    
    public String getSide() {
        return SIDE;
    }
}
