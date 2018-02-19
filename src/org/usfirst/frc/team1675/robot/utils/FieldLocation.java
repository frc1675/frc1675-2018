package org.usfirst.frc.team1675.robot.utils;

public enum FieldLocation {
    SWITCH_LEFT("Left Switch"),
    SWITCH_RIGHT("Right Switch"),
    START_LEFT("Left Start"),
    START_MIDDLE("Middle Start"),
    START_RIGHT("Right Start"),
    CUBE_PYRAMID_LEFT("Left Pyramid"),
    CUBE_PYRAMID_RIGHT("Right Pyramid"),
    NULL_ZONE_LEFT("Left Null Zone"),
    NULL_ZONE_RIGHT("Right Null Zone"),
    EXCHANGE("Exchange");
    
private final String NAME;
    
    FieldLocation(final String name){
        this.NAME = name;
    }
    
    public String toString() {
        return NAME;
    }
}
