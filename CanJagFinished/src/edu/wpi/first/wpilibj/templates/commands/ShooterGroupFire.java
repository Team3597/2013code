/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Matthew
 */
public class ShooterGroupFire extends CommandGroup {
    
    public ShooterGroupFire() {
        // Add Commands here:
        addSequential(new ShooterSpinUpFrisbeeMotor()); // times out
        addSequential(new ShooterLoadFrisbee()); 
        addSequential(new Delay(1.0)); //waits for the frisbee to fall into launch motor
        addSequential(new ShooterStopFrisbeeMotor());
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
