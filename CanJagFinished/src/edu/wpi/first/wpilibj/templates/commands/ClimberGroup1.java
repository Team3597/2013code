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
public class ClimberGroup1 extends CommandGroup {
    
    public ClimberGroup1() {        
        addParallel(new ClimberEmergencyCheckAndStop()); //never stops
        addSequential(new Delay(.001));               
        addSequential(new ClimberExtendTAG());
        addParallel(new ChassisPyramidBoost()); // finishes sequential and then hits parallel and sequential together         
        addParallel(new HandsExtend(RobotMap.topHandMotor));
        addParallel(new HandsExtend(RobotMap.bottomHandMotor)); 
        addParallel(new ClimberSkidExtend());
        addParallel(new ClimberBoomExtend());
        addParallel(new HandsGroupChangeHands());         
        addSequential(new ClimberArmsRetract()); 
                
        addSequential(new ChassisStopPyramid()); 
        
        // attached to first bar by arms and now ascent begins        
    }
}
