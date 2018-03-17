/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1675.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static class CANDeviceIDs {
        public static final int DRIVE_LEFT_FRONT = 1;
        public static final int DRIVE_LEFT_MID = 2;
        public static final int DRIVE_LEFT_BACK = 3;
        public static final int DRIVE_RIGHT_FRONT = 4;
        public static final int DRIVE_RIGHT_MID = 5;
        public static final int DRIVE_RIGHT_BACK = 6;
        public static final int ARM = 7;
        public static final int INTAKE_LEFT = 8;
        public static final int INTAKE_RIGHT = 9;
    }

    public static class PWMChannels {
    }

    public static class SolenoidChannels {
        public static final int SHIFT = 7;
        public static final int RAMP_RAISE_LEFT = 5;
        public static final int RAMP_RAISE_RIGHT = 3;
        public static final int RAMP_LOWER_LEFT = 4;
        public static final int RAMP_LOWER_RIGHT = 2;
        public static final int RAMP_RELEASE = 6;
    }

    public static class DriveBaseConstants {
        public static final double SHIFT_TIME = .25;
        public static final double DRIVE_P = 0.000092;
        public static final double DRIVE_I = 0.0;
        public static final double DRIVE_D = 0.0006;
        public static final double DRIVE_TOLERANCE = 611.16; //464
        public static final double GYRO_P = 0.0375;
        public static final double GYRO_I = 0.0;
        public static final double GYRO_D = 0.145;
        public static final double GYRO_TOLERANCE = 5;
        public static final double TICKS_PER_INCH = 407.44;
        public static final double TIMED_AUTO_POWER_CONSTANT = .4;
        public static final double DRIVE_EXPONENT = 1;
        public static final double TURN_EXPONENT = 1;
    }

    public static class RampConstants {
        public static final double SOLENOID_ACTIVATION_TIME = .25;
        public static final int RAMP_DROP_TIME = 105;// Should always be 105 when on the field
    }

    public static class ClawConstants {
        public static final double MAX_OUTPUT_POWER = 1;
        public static final double MID_OUTPUT_POWER = .5;
        public static final double MIN_OUTPUT_POWER = .2;
        public static final double INPUT_POWER = .75;
    }

    public static class ArmConstants {
        public static final int SCALE_ENCODER_POSITION = 4400;
        public static final int HIGH_SWITCH_ENCODER_POSITION = 3248;
        public static final int NORMAL_SWITCH_ENCODER_POSITION = 2240;
        public static final int PICK_UP_POSITION = 0;

        public static final int FORWARD_LIMIT_POSITION = 4400;
        public static final int REVERSE_LIMIT_POSITION = 500;
        public static final int ARM_ENCODER_BUFFER = 100;
        public static final int ARM_BUTTON = 9;

        public static final double ARM_AUTONOMOUS_MOVEMENT_POWER = .4;
        public static final double ARM_MANUAL_MAX_POWER = .6;

        public static final double DROP_KICKSTAND_TIME = .5; //.25
    }

    public static final double CONTROLLER_DEADZONE = 0.1675;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
