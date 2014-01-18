package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.ChassisDriveWithJoystick;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

public class Chassis extends Subsystem {
  
    CANJaguar leftMotor;
    CANJaguar rightMotor;
    RobotDrive drive;
    boolean stopDriving;
    
    public Chassis() {
        try {
            leftMotor = new CANJaguar(RobotMap.leftMotor);
            rightMotor = new CANJaguar(RobotMap.rightMotor);
//            leftMotor.setVoltageRampRate(4.0); // should prevent voltage fault, but ramp rate lacks power.
//            rightMotor.setVoltageRampRate(4.0);
            drive = new RobotDrive(leftMotor, rightMotor);   
            stopDriving = true;               
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    public void initDefaultCommand() {
        setDefaultCommand(new ChassisDriveWithJoystick());
    }
    public void DriveWithJoystick() {
        try {
            Joystick stick = CommandBase.oi.getDriverJoystick();
            double leftValue = stick.getRawAxis(2);  // opposite direction as calling rawAxis in tankDrive, 
            double rightValue = stick.getRawAxis(5); //because tankDrive auto compensates for motors facing different directions
            drive.tankDrive(leftValue, rightValue);
            System.out.println(leftMotor.getFaults());
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }    
}