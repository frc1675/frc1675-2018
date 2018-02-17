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
        rampLifterLeft = new DoubleSolenoid(RobotMap.SolenoidChannels.RAMP_RAISE_LEFT, RobotMap.SolenoidChannels.RAMP_LOWER_LEFT);
        rampLifterRight = new DoubleSolenoid(RobotMap.SolenoidChannels.RAMP_RAISE_RIGHT, RobotMap.SolenoidChannels.RAMP_LOWER_RIGHT);
        rampDeploy = new Solenoid(RobotMap.SolenoidChannels.RAMP_RELEASE);
    }

    public void rampRaise() {
        rampLifterLeft.set(DoubleSolenoid.Value.kForward);
        rampLifterRight.set(DoubleSolenoid.Value.kForward);
    }

    public void rampLower() {
        rampLifterLeft.set(DoubleSolenoid.Value.kReverse);
        rampLifterRight.set(DoubleSolenoid.Value.kReverse);
    }

    public void Deploy() {
        rampDeploy.set(true);
    }

    public void Retract() {
        rampDeploy.set(false);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new CheesyDrive());
    }
}
