/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Administrator
 */
public class HandsGroupChangeHands extends CommandGroup {
    
    public HandsGroupChangeHands() {               
        // HandsCheckGrab is a default command, so don't need to worry about it parallel
        addParallel(new HandsCheckGrab());
        addSequential(new Delay(0.0001)); // not sure why, it won't continue unless this delay exists
        addSequential(new HandsRetract(RobotMap.topHandMotor)); 
        addSequential(new Delay(0.5));
        addSequential(new HandsExtend(RobotMap.topHandMotor));
        addSequential(new HandsRetract(RobotMap.bottomHandMotor));
        addSequential(new Delay(0.5));
        addSequential(new HandsExtend(RobotMap.bottomHandMotor));
        
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
