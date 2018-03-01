package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.CheesyDrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RampSub extends Subsystem {

    public DoubleSolenoid rampLifterLeft;
    public DoubleSolenoid rampLifterRight;
    public Solenoid rampDeploy;

    public RampSub() {
        rampLifterLeft = new DoubleSolenoid(RobotMap.SolenoidChannels.RAMP_RAISE_LEFT,
                RobotMap.SolenoidChannels.RAMP_LOWER_LEFT);
        rampLifterRight = new DoubleSolenoid(RobotMap.SolenoidChannels.RAMP_RAISE_RIGHT,
                RobotMap.SolenoidChannels.RAMP_LOWER_RIGHT);
        rampDeploy = new Solenoid(RobotMap.SolenoidChannels.RAMP_RELEASE);
    }

    public void rampRaiseRight() {
        rampLifterRight.set(DoubleSolenoid.Value.kForward);
    }
    
    public void rampRaiseLeft() {
         rampLifterLeft.set(DoubleSolenoid.Value.kForward);
    }
    
    public void rampStopRight() {
        rampLifterRight.set(DoubleSolenoid.Value.kOff);
    }
    
    public void rampStopLeft() {
        rampLifterLeft.set(DoubleSolenoid.Value.kOff);
    }

    public void Deploy() {
        rampDeploy.set(true);
    }

    public void initDefaultCommand() {
    }
}
