package org.usfirst.frc.team1675.robot.utils;

public enum FieldLocation {
    SWITCH_LEFT("Left Switch", "Left"),
    SWITCH_RIGHT("Right Switch", "Right"),
    START_LEFT("Left Start", "Left"),
    START_MIDDLE("Middle Start", "Right"),
    START_RIGHT("Right Start", "Right"),
    CUBE_PYRAMID_LEFT("Left Pyramid", "Left"),
    CUBE_PYRAMID_RIGHT("Right Pyramid", "Right"),
    NULL_ZONE_LEFT("Left Null Zone", "Left"),
    NULL_ZONE_RIGHT("Right Null Zone", "Right"),
    EXCHANGE("Exchange", "Left");
    
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
