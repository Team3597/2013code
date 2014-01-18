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
public class Autonomous extends CommandGroup {
    
    public Autonomous() {                
        addSequential(new ShooterFireAll()); //shoots 4, not 3
    }
}
