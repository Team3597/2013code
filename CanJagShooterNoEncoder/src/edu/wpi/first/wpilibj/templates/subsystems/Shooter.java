
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.ShooterAimWithJoystick;

/**
 *
 */
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    
    CANJaguar launchMotor;
    CANJaguar aimingMotor;
    CANJaguar loadMotor;
    Servo loadRamp;

    public Shooter() {
        try {
            launchMotor = new CANJaguar(RobotMap.launchMotor);            
            loadMotor = new CANJaguar(RobotMap.loadMotor); // jaguar without encoder                     
            aimingMotor = new CANJaguar(RobotMap.aimingMotor);
            loadRamp = new Servo(RobotMap.loadServo);
        }
        catch (CANTimeoutException ex) {
            ;
        }        
    }
    public void ReleaseServo() {
        loadRamp.set(0.0);
        loadRamp.set(1.0);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new GetEncoder());
        setDefaultCommand(new ShooterAimWithJoystick());
    }
    
    public void RunMotor(double speed, int Motor) {
        try {        
            if (Motor == RobotMap.launchMotor) {
                launchMotor.setX(speed);
            }
            else if (Motor == RobotMap.loadMotor) {
                loadMotor.setX(speed);
            }
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    public void StopMotor(int Motor) {
        try {
            if (Motor == RobotMap.launchMotor) {
                launchMotor.disableControl();   
            }
            if (Motor == RobotMap.loadMotor) {
                loadMotor.disableControl();   
            }
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    public void aimWithJoystick(){  
        try {                              
            Joystick stick = CommandBase.oi.getShooterJoystick();
            double speed = stick.getRawAxis(2);
            aimingMotor.setX(speed);
        }
        catch (CANTimeoutException Exception) {
            ;
        }
    } 
}

