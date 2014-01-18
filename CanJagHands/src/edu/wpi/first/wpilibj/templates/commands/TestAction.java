/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Matthew
 */
public class TestAction extends CommandGroup {
    
    public TestAction() {
        // Add Commands here:              
        
        addParallel(new HandsCheckGrab());
        addSequential(new Delay(0.0001)); // not sure why, it won't continue unless this delay exists
        addSequential(new RetractHand(RobotMap.topHandMotor)); 
        addSequential(new Delay(0.5));
        addSequential(new ExtendHand(RobotMap.topHandMotor));
        addSequential(new RetractHand(RobotMap.bottomHandMotor));
        addSequential(new Delay(0.5));
        addSequential(new ExtendHand(RobotMap.bottomHandMotor));
        
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    }
}
