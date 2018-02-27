package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReckoningRoutineRightSide extends CommandGroup {

    public ReckoningRoutineRightSide(String side) {
        if (side.substring(0, 1) == "R") {
           //add an arm thingwe that puts the arm at the sutable postioon
        addSequential(new ReckoningAuto(2.0));
        addSequential(new ReckoningTurn(0.7, false));
        addSequential(new ReckoningAuto(1.0));
        addSequential(new ReckoningTurn(0.7, true));
        // add an arm thingee thar putds that there box in the swicth
        }
        if (side.substring(0,1) == "L") {
            //add an arm thingwe that puts the arm at the sutable postioon
            addSequential(new ReckoningAuto(2.0));
            addSequential(new ReckoningTurn(0.7, false));
            addSequential(new ReckoningAuto(0.5));
            // dont do the arm thing
        }
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
