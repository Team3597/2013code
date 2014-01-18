
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
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
    Encoder loadEncoder;
    Servo loadRamp;
    double desiredRPM;    
    
    public Shooter() {
        try {
            desiredRPM = 500.0;            
            launchMotor = new CANJaguar(RobotMap.launchMotor);            
            launchMotor.configEncoderCodesPerRev(360);             
            launchMotor.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);                                                         
            loadMotor = new CANJaguar(RobotMap.loadMotor); // jaguar without encoder built in                   
            loadEncoder = new Encoder(RobotMap.loadEncoderA, RobotMap.loadEncoderB);
            loadEncoder.setDistancePerPulse(1.0/360.0); // possibly unnecessary
            aimingMotor = new CANJaguar(RobotMap.aimingMotor);
            aimingMotor.configEncoderCodesPerRev(360);
            aimingMotor.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            loadRamp = new Servo(RobotMap.loadServo);
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new GetEncoder());
        setDefaultCommand(new ShooterAimWithJoystick());
    }
    public void ReleaseServo() {
        loadRamp.set(0.0);
        loadRamp.set(1.0);
    }
    public void SpinUpMotor() {
        try {        
            double voltage = 0.7;
            while (true) {   
                launchMotor.setX(voltage);
                if (launchMotor.getSpeed() <= desiredRPM) {
                    voltage += .1;// takes third of a sec vs 1.5 with +=.01 but extra 50 rpm over
                } // RPMs change += 30 without any 
                if (launchMotor.getSpeed() > desiredRPM) {
                    //PID error [cRIO] >>>>ERROR: status == -1073807298 (0xBFFF003E) in Read() in C:/WindRiver/workspace/BlackJagBridgePlugin/SerialPort.cpp at line 216        
                    ;
                }                                                
            }             
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    
    public void LoadFrisbee() {
        try {
            double stopPoint = .89; // 1 revolution //.11 extra += .02 @ 25% speed  
            loadEncoder.start();
            loadMotor.setX(.25); // low speed for accuracy
            while (true) {
                if (loadEncoder.getDistance() > stopPoint) { 
                    break;
                }
            }
            loadMotor.disableControl(); 
            loadEncoder.stop();
            loadEncoder.reset();
            
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    
    public void StopFrisbeeMotor() {
        try {
            launchMotor.disableControl();            
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

