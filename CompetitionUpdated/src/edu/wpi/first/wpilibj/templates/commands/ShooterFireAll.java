package edu.wpi.first.wpilibj.templates.commands;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Matthew
 */
public class ShooterFireAll extends CommandGroup {
    
    public ShooterFireAll() {
        // Add Commands here:
        addSequential(new ShooterSpinUpFrisbeeMotor());                           
        addSequential(new Delay(5.0));                  
        addSequential(new ShooterLoadFrisbee());
        addSequential(new Delay(1.0));
        addSequential(new ShooterLoadFrisbee());
        addSequential(new Delay(1.0));
        addSequential(new ShooterLoadFrisbee());
        addSequential(new ShooterStopFrisbeeMotor());
    }
}
