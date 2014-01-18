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
            leftMotor = new CANJaguar(RobotMap.leftMotor); //, CANJaguar.ControlMode.kVoltage);
            rightMotor = new CANJaguar(RobotMap.rightMotor); //, CANJaguar.ControlMode.kVoltage);
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
        Joystick stick = CommandBase.oi.getDriverJoystick();
        double leftValue = stick.getRawAxis(2);  // opposite direction as calling rawAxis in tankDrive, 
        double rightValue = stick.getRawAxis(5); //because tankDrive auto compensates for motors facing different directions
        drive.tankDrive(leftValue, rightValue);
    }    
}