/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Matthew
 */
public class ShooterSpinUpFrisbeeMotor extends CommandBase {
          
    public ShooterSpinUpFrisbeeMotor() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.shooter);        
    }

    // Called just before this Command runs the first time
    protected void initialize() {    
        setTimeout(1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.shooter.SpinUpFrisbeeMotor();         
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
