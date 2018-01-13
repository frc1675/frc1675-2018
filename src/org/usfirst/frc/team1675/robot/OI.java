/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1675.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	Joystick driverController = new Joystick(XBoxControllerMap.DRIVER_CONTROLLER_PORT);
	Joystick operatorController = new Joystick(XBoxControllerMap.OPERATOR_CONTROLLER_PORT);

	JoystickButton driverButtonA = new JoystickButton(driverController, XBoxControllerMap.A_BUTTON);
	JoystickButton driverButtonB = new JoystickButton(driverController, XBoxControllerMap.B_BUTTON);
	JoystickButton driverButtonX = new JoystickButton(driverController, XBoxControllerMap.X_BUTTON);
	JoystickButton driverButtonY = new JoystickButton(driverController, XBoxControllerMap.Y_BUTTON);

	JoystickButton operatorButtonA = new JoystickButton(operatorController, XBoxControllerMap.A_BUTTON);
	JoystickButton operatorButtonB = new JoystickButton(operatorController, XBoxControllerMap.B_BUTTON);
	JoystickButton operatorButtonX = new JoystickButton(operatorController,  XBoxControllerMap.X_BUTTON);
	JoystickButton operatorButtonY = new JoystickButton(operatorController, XBoxControllerMap.Y_BUTTON);

	public double getDriverLeftXAxis() {

		return 0;
	}

	public double getDriverLeftYAxis() {
		return 0;

	}

	public double getDriverRightXAxis() {
		return 0;

	}

	public double getDriverRightYAxis() {
		return 0;

	}

	public double getOperatorLeftXAxis() {
		return 0;

	}

	public double getOperatoriLeftYAxis() {
		return 0;

	}

	public double getOperatorRightXAxis() {
		return 0;

	}

	public double getOperatorRightYAxis() {
		return 0;

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
