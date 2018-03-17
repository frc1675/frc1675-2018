/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1675.robot;

import org.usfirst.frc.team1675.robot.commands.DropKickstand;
import org.usfirst.frc.team1675.robot.commands.TestAutoGroup;
import org.usfirst.frc.team1675.robot.subsystems.Arm;
import org.usfirst.frc.team1675.robot.subsystems.Claw;
import org.usfirst.frc.team1675.robot.subsystems.PIDDriveBase;
import org.usfirst.frc.team1675.robot.subsystems.RampSub;
import org.usfirst.frc.team1675.robot.utils.AutoChooser;
import org.usfirst.frc.team1675.robot.utils.FieldColorAssignment;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static final Arm arm = new Arm();
    public static final Claw claw = new Claw();

    public static final PIDDriveBase driveBase = new PIDDriveBase();
    public static final RampSub ramp = new RampSub();

    public static OI oi;

    public static AutoChooser autoChooser;
    private static Timer teleopTime = new Timer();

    CommandGroup m_autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        oi = new OI();
        autoChooser = new AutoChooser();
        // chooser.addObject("My Auto", new MyAutoCommand());

        CameraServer.getInstance().startAutomaticCapture();
    }

    /**
     * This function is called once each time the robot enters Disabled mode. You
     * can use it to reset any subsystem information you want to clear when the
     * robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable chooser
     * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
     * remove all of the chooser code and uncomment the getString code to get the
     * auto name from the text box below the Gyro
     *
     * <p>
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons to
     * the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        String sideInfo = null;
        while(sideInfo == null) {
            sideInfo = DriverStation.getInstance().getGameSpecificMessage();
        }
        FieldColorAssignment switchSide = FieldColorAssignment.getSideFromChar(sideInfo.charAt(0));
        FieldColorAssignment scaleSide = FieldColorAssignment.getSideFromChar(sideInfo.charAt(1));
        
        if(switchSide == null || scaleSide == null) {
            System.err.println("Invalid Field Data String");
        }
        m_autonomousCommand = new CommandGroup();
        m_autonomousCommand.addSequential(new DropKickstand());
        m_autonomousCommand.addSequential(autoChooser.chooseAuto(switchSide, scaleSide));

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
         * switch(autoSelected) { case "My Auto": autonomousCommand = new
         * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
         * ExampleCommand(); break; }
         */


        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        teleopTime.reset();
        teleopTime.start();
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {

    }

    public static double getTeleopTime() {
        return teleopTime.get();
    }
}
