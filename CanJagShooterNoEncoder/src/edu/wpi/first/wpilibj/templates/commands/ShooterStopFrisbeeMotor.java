/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Matthew
 */
public class ShooterStopFrisbeeMotor extends CommandBase {
    
    int end;
    public ShooterStopFrisbeeMotor() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        end = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.shooter.StopMotor(RobotMap.launchMotor);
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
