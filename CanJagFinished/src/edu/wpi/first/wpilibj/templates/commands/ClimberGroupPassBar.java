/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Administrator
 */
public class ClimberGroupPassBar extends CommandGroup {
    
    public ClimberGroupPassBar() {      
        // side hands taken care of in ClimberGroup2, not needed here                
        addParallel(new ClimberHooksExtend());
        addSequential(new ClimberArmsExtend()); //opposite vectors movement away from each other
        
        addSequential(new ClimberArmsLatch());
        
        addParallel(new ClimberHooksRetract());
        addSequential(new ClimberArmsRetract()); // both move towards each other, attack to hooks?
        
        // attached to bar by hooks, reach for second
    }
}
