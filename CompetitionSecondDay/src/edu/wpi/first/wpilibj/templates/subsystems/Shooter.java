
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.ShooterAimWithJoystick;

/**
 *
 */
public class Shooter extends Subsystem {
    
    CANJaguar launchMotor;
    CANJaguar aimingMotor;
    CANJaguar loadMotor;
    DigitalInput limitSwitch;
    boolean on;
    
    public Shooter() {
        try {
            launchMotor = new CANJaguar(RobotMap.launchMotor);  
            launchMotor.setVoltageRampRate(4.0); // stops faults of too much too fast
            aimingMotor = new CANJaguar(RobotMap.aimingMotor);
            loadMotor = new CANJaguar(RobotMap.loadMotor);             
            limitSwitch = new DigitalInput(RobotMap.limitSwitch);
            on = false;
        }
        catch (CANTimeoutException ex) {
        }
    }
    public void initDefaultCommand() {
        setDefaultCommand(new ShooterAimWithJoystick());
    }
    public void FrisbeeMotorControl() {
        try {                
            if (on == false) {
                launchMotor.setX(1.0);
                on = true;               
            }
            else {
                launchMotor.disableControl();
                on = false;
            }
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    public void LoadFrisbee() {        
        try {    
            if (on) {
                loadMotor.setX(1.0);
                while (limitSwitch.get() == false) {
                    loadMotor.setX(1.0); // unpresses
                }
                while (limitSwitch.get() == true) { // presses again
                    loadMotor.setX(1.0);
                }
                loadMotor.disableControl();
            }
        }
        catch (CANTimeoutException ex) {
            ;
        }
    } 
    public void StopFrisbeeMotor() {
        try {
            launchMotor.disableControl();
            on = false;
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }  
    public void SpinUpFrisbeeMotor() {
        try {
            launchMotor.setX(1.0);   
            on = true;
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

