/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Matthew
 */
public class ChassisPyramidBoost extends CommandBase {
            
    public ChassisPyramidBoost() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.chassis);        
    }

    // Called just before this Command runs the first time
    protected void initialize() {   
        CommandBase.chassis.startPyramidBoost();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.chassis.tankDriveTimedOut(); // both motors set to this
        // make a return of that value which ends this.        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (CommandBase.chassis.checkStopPyramidBoost());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
