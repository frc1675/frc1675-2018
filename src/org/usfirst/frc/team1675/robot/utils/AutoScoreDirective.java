package org.usfirst.frc.team1675.robot.utils;

public enum AutoScoreDirective {
    SCORE("Score"),
    SKIP("Skip");

    private final String NAME;

    AutoScoreDirective(final String name) {
        this.NAME = name;
    }

    public String toString() {
        return NAME;
    }
}
