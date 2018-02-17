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
        public static final int RAMP_RAISE_LEFT = 2;
        public static final int RAMP_RAISE_RIGHT = 3;
        public static final int RAMP_LOWER_LEFT = 4;
        public static final int RAMP_LOWER_RIGHT = 5;
        public static final int RAMP_RELEASE = 6;
	}

	public static class DriveBaseConstants {
		public static final double SHIFT_TIME = .25;
        public static final double P = .0003;
        public static final double I = 0.0;
        public static final double D = 0.0003; // all placeholders
        public static final double TOLERANCE = 69; // placeholder
        public static final double TICKS_PER_INCH = 410.86; // placeholder
    }
    
    public static class RampConstants {
        public static final double SOLENOID_ACTIVATION_TIME = .25;
    }
	
	public static class ClawConstants{
		public static final int DIRECTION_INPUT = 1;
		public static final int DIRECTION_OUTPUT = 2;
		public static final double LEFT_OUTPUT_POWER = -.5;
		public static final double RIGHT_OUTPUT_POWER = .5;
		public static final double LEFT_INPUT_POWER = .5;
		public static final double RIGHT_INPUT_POWER = -.5;
	}
	
	public static final double CONTROLLER_DEADZONE = 0.1675;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
