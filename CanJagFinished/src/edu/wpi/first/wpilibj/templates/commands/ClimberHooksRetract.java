/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Matthew
 */
public class ClimberHooksRetract extends CommandBase {
    
    int end;
    public ClimberHooksRetract() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        end = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.climber.retractHooks();
        end -= 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (end == 0);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
