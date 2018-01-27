/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1675.robot;

import org.usfirst.frc.team1675.Util.DoubleButton;
import org.usfirst.frc.team1675.robot.commands.DeployRamp;
import org.usfirst.frc.team1675.robot.commands.ShiftHigh;
import org.usfirst.frc.team1675.robot.commands.ShiftLow;

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
	
//	DoubleButton operatorDoubleButtonAB = new DoubleButton(operatorController, XBoxControllerMap.A_BUTTON, XBoxControllerMap.B_BUTTON);

	public OI() {
		driverRightBumper.whenPressed(new ShiftHigh());
		driverRightBumper.whenReleased(new ShiftLow());
//		operatorDoubleButtonAB.whenPressed(new ShiftHigh());
	}
	
	public double getDriverLeftXAxis() {
		double leftXAxis;
		leftXAxis = driverController.getRawAxis(XBoxControllerMap.LEFT_X_AXIS);
		return leftXAxis;
	}

	public double getDriverLeftYAxis() {
		double leftYAxis;
		leftYAxis = driverController.getRawAxis(XBoxControllerMap.LEFT_Y_AXIS);
		return -leftYAxis;
	}

	public double getDriverRightXAxis() {
		double rightXAxis;
		rightXAxis = driverController.getRawAxis(XBoxControllerMap.RIGHT_X_AXIS);
		return rightXAxis;
	}

	public double getDriverRightYAxis() {
		double rightYAxis;
		rightYAxis = driverController.getRawAxis(XBoxControllerMap.RIGHT_Y_AXIS);
		return -rightYAxis;
	}

	public double getOperatorLeftXAxis() {
		double leftXAxis;
		leftXAxis = operatorController.getRawAxis(XBoxControllerMap.LEFT_X_AXIS);
		return leftXAxis;
	}

	public double getOperatoriLeftYAxis() {
		double leftYAxis;
		leftYAxis = operatorController.getRawAxis(XBoxControllerMap.LEFT_Y_AXIS);
		return -leftYAxis;
	}

	public double getOperatorRightXAxis() {
		double rightXAxis;
		rightXAxis = operatorController.getRawAxis(XBoxControllerMap.RIGHT_X_AXIS);
		return rightXAxis;
	}

	public double getOperatorRightYAxis() {
		double rightYAxis;
		rightYAxis = operatorController.getRawAxis(XBoxControllerMap.RIGHT_Y_AXIS);
		return -rightYAxis;
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
