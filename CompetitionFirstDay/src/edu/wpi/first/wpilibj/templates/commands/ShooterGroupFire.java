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
        addSequential(new ShooterSpinUpFrisbeeMotor());                  
        addSequential(new Delay(3.0));                  
        addSequential(new LoaderLoadFrisbee()); 
        addSequential(new ShooterStopFrisbeeMotor()); 
    }
}
