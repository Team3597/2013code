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
public class ClimberGroup2 extends CommandGroup {
    
    public ClimberGroup2() {
        // Attached to first bar by body hooks
        addParallel(new ClimberEmergencyCheckAndStop()); //never ends
        addParallel(new HandsGroupChangeHands()); //never ends
        addSequential(new Delay(.001)); // to keep above from stopping others        
        
        addSequential(new ClimberGroupPassBar());
        addSequential(new ClimberGroupPassBar());        
        // hanging from third bar and shoots frisbees        
        addSequential(new ShooterFireAll());              
    }
}
