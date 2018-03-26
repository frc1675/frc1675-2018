package org.usfirst.frc.team1675.robot.utils;

public enum FieldLocation {
    
    SWITCH_LEFT_FRONT("Left Switch Front", "L"),
    SWITCH_LEFT_SIDE("Left Switch Side", "L"),
    SWITCH_RIGHT_FRONT("Right Switch Front", "R"),
    SWITCH_RIGHT_SIDE("Right Switch Side", "R"),
    START_FAR_LEFT("Far Left Start", "L"),
    START_LEFT("Left Start", "L"),
    START_MIDDLE("Middle Start", "M"),
    START_RIGHT("Right Start", "R"),
    START_FAR_RIGHT("Far Right Start", "R"),
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
