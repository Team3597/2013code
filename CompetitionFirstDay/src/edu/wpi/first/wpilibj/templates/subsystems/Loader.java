
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.LoaderMoveLoaderWithJoystick;

/**
 *
 */
public class Loader extends Subsystem {
    
    CANJaguar loadMotor;
    DigitalInput limitSwitch;
    double desiredRPM;    
    
    public Loader() {
        try {          
            loadMotor = new CANJaguar(RobotMap.loadMotor);             
            limitSwitch = new DigitalInput(RobotMap.limitSwitch);
        }
        catch (CANTimeoutException ex) {
        }
    }
    public void initDefaultCommand() {
        setDefaultCommand(new LoaderMoveLoaderWithJoystick());
    } 
    public void LoadFrisbee() {
        try {    
            loadMotor.setX(1.0);
            while (limitSwitch.get() == false) {
                loadMotor.setX(1.0); // unpresses
            }
            while (limitSwitch.get() == true) { // presses again
                loadMotor.setX(1.0);
            }
            loadMotor.disableControl();
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }     
    public void MoveLoaderWithJoystick() {
        try {
            Joystick stick = CommandBase.oi.getShooterJoystick();
            double speed = stick.getRawAxis(5);
            loadMotor.setX(speed);
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }    
}

