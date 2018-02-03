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

    public Solenoid rampLifter;
    public DoubleSolenoid rampDeploy;

    public RampSub() {
        rampLifter = new Solenoid(RobotMap.SolenoidChannels.RAMP_ENGAGE);
        rampDeploy = new DoubleSolenoid(RobotMap.SolenoidChannels.RAMP_DEPLOY, RobotMap.SolenoidChannels.RAMP_RETRACT);
    }

    public void rampLift() {
        rampLifter.set(false);
    }

    public void rampReset() {
        rampLifter.set(true);
    }

    public void Deploy() {
        rampDeploy.set(DoubleSolenoid.Value.kForward);
    }

    public void Retract() {
        rampDeploy.set(DoubleSolenoid.Value.kReverse);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new CheesyDrive());
    }
}
