/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1675.robot;

import org.usfirst.frc.team1675.robot.commands.ActivateClaw;
import org.usfirst.frc.team1675.Util.DoubleButton;
import org.usfirst.frc.team1675.robot.commands.DeployRamp;
import org.usfirst.frc.team1675.robot.commands.ShiftHigh;
import org.usfirst.frc.team1675.robot.commands.ShiftLow;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	Joystick driverController = new Joystick(XBoxControllerMap.DRIVER_CONTROLLER_PORT);
	Joystick operatorController = new Joystick(XBoxControllerMap.OPERATOR_CONTROLLER_PORT);

	JoystickButton driverButtonA = new JoystickButton(driverController, XBoxControllerMap.A_BUTTON);
	JoystickButton driverButtonB = new JoystickButton(driverController, XBoxControllerMap.B_BUTTON);
	JoystickButton driverButtonX = new JoystickButton(driverController, XBoxControllerMap.X_BUTTON);
	JoystickButton driverButtonY = new JoystickButton(driverController, XBoxControllerMap.Y_BUTTON);
	
	JoystickButton driverRightBumper = new JoystickButton(driverController, XBoxControllerMap.RIGHT_BUMPER_BUTTON);

	JoystickButton operatorButtonA = new JoystickButton(operatorController, XBoxControllerMap.A_BUTTON);
	JoystickButton operatorButtonB = new JoystickButton(operatorController, XBoxControllerMap.B_BUTTON);
	JoystickButton operatorButtonX = new JoystickButton(operatorController,  XBoxControllerMap.X_BUTTON);
	JoystickButton operatorButtonY = new JoystickButton(operatorController, XBoxControllerMap.Y_BUTTON);

    // DoubleButton operatorDoubleButtonAB = new DoubleButton(operatorController,
    // XBoxControllerMap.A_BUTTON, XBoxControllerMap.B_BUTTON);

	public OI() {
		driverRightBumper.whenPressed(new ShiftHigh());
		driverRightBumper.whenReleased(new ShiftLow());
		operatorButtonX.whileHeld(new ActivateClaw(RobotMap.ClawConstants.DIRECTION_INPUT));
		operatorButtonY.whileHeld(new ActivateClaw(RobotMap.ClawConstants.DIRECTION_OUTPUT));
        // operatorDoubleButtonAB.whenPressed(new ShiftHigh());
	}
	
	public double getDriverLeftXAxis() {
		double leftXAxis = driverController.getRawAxis(XBoxControllerMap.LEFT_X_AXIS);
		double correctedleftXAxis = correctForDeadzone (leftXAxis);
		return correctedleftXAxis;
	}

	public double getDriverLeftYAxis() {
		double leftYAxis = driverController.getRawAxis(XBoxControllerMap.LEFT_Y_AXIS);
		double correctedleftYAxis = correctForDeadzone (leftYAxis);
		return -correctedleftYAxis;
	}

	public double getDriverRightXAxis() {
		double rightXAxis = driverController.getRawAxis(XBoxControllerMap.RIGHT_X_AXIS);
		double correctedrightXAxis = correctForDeadzone (rightXAxis);
		return correctedrightXAxis;
	}

	public double getDriverRightYAxis() {
		double rightYAxis = driverController.getRawAxis(XBoxControllerMap.RIGHT_Y_AXIS);
		double correctedRightYAxis = correctForDeadzone (rightYAxis);
		return -correctedRightYAxis;
	}

	public double getOperatorLeftXAxis() {
		double leftXAxis = operatorController.getRawAxis(XBoxControllerMap.LEFT_X_AXIS);
		double correctedleftXAxis = correctForDeadzone (leftXAxis);
		return correctedleftXAxis;
	}

	public double getOperatoriLeftYAxis() {
		double leftYAxis = operatorController.getRawAxis(XBoxControllerMap.LEFT_Y_AXIS);
		double correctedleftYAxis = correctForDeadzone (leftYAxis);
		return -correctedleftYAxis;
	}

	public double getOperatorRightXAxis() {
		double rightXAxis = operatorController.getRawAxis(XBoxControllerMap.RIGHT_X_AXIS);
		double correctedrightXAxis = correctForDeadzone(rightXAxis);
		return correctedrightXAxis;
	}

	public double getOperatorRightYAxis() {
		double rightYAxis = operatorController.getRawAxis(XBoxControllerMap.RIGHT_Y_AXIS);
		double correctedRightYAxis = correctForDeadzone(rightYAxis);
		return -correctedRightYAxis;
	}
	
	private double correctForDeadzone(double value) {
		double correctedValue = 0;
		if(Math.abs(value)> RobotMap.CONTROLLER_DEADZONE) {
			if( value > 0) {
				correctedValue = (value - RobotMap.CONTROLLER_DEADZONE) / (1 - RobotMap.CONTROLLER_DEADZONE);
			}
			if(value < 0){
				correctedValue = (value + RobotMap.CONTROLLER_DEADZONE) / (1 - RobotMap.CONTROLLER_DEADZONE);
			}
		}
		return correctedValue;
	}

    public void setDriverRumble(double driverRumblePower) {
        driverController.setRumble(RumbleType.kLeftRumble, driverRumblePower);
        driverController.setRumble(RumbleType.kRightRumble, driverRumblePower);

    }

    public void setOperatorRumble(double operatorRumblePower) {
        operatorController.setRumble(RumbleType.kLeftRumble, operatorRumblePower);
        operatorController.setRumble(RumbleType.kRightRumble, operatorRumblePower);

    }

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
