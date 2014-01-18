
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoystick;

/**
 *
 */
public class Chassis extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
//    Jaguar leftMotor;
//    Jaguar rightMotor;
    CANJaguar leftMotor;
    CANJaguar rightMotor;
    RobotDrive drive;
    boolean stopDriving;
    
    public Chassis() {
        try {
        leftMotor = new CANJaguar(RobotMap.leftMotor);
        rightMotor = new CANJaguar(RobotMap.rightMotor);
//        leftMotor = new Jaguar(RobotMap.leftMotor);
//        rightMotor = new Jaguar(RobotMap.rightMotor);
        drive = new RobotDrive(leftMotor, rightMotor);   
        stopDriving = true;
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoystick());
    }
    public void DriveWithJoystick() {
        Joystick stick = CommandBase.oi.getStick();
        double leftValue = stick.getRawAxis(2);  // opposite direction as calling rawAxis in tankDrive, 
        double rightValue = stick.getRawAxis(5); //because tankDrive auto compensates for motors facing different directions
        drive.tankDrive(leftValue, rightValue);
    }    
    public void startPyramidBoost() { 
        stopDriving = false;                                  
    }
    public void stopPyramidBoost() {
        stopDriving = true;
    }
    public boolean checkStopPyramidBoost() {
        return stopDriving;
    }
    public void tankDriveTimedOut() {
        drive.tankDrive(1.0, 1.0);
    }
}

