/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Matthew
 */
public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.     
    
    CANJaguar extendingArmsMotor;    
    CANJaguar bodyHooksMotor;    
    CANJaguar boomerangMotor;    
    CANJaguar skidMotor;   
    Servo TAGservo;
    DigitalInput armSwitch;
    boolean Estop;
    double armStopPoint;
    double hookStopPoint;
    
    public Climber() {
        try {
            extendingArmsMotor = new CANJaguar(RobotMap.extendingArmsMotor);  
            extendingArmsMotor.configEncoderCodesPerRev(360);
            extendingArmsMotor.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            bodyHooksMotor = new CANJaguar(RobotMap.bodyHooksMotor);  
            bodyHooksMotor.configEncoderCodesPerRev(360);
            bodyHooksMotor.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            boomerangMotor = new CANJaguar(RobotMap.boomerangMotor);   // limit switch stopped
            skidMotor = new CANJaguar(RobotMap.skidMotor);   // limit switch stopped
            TAGservo = new Servo(RobotMap.TAGservo);
            armSwitch = new DigitalInput(RobotMap.module2, RobotMap.armSwitch);
            Estop = false;
            armStopPoint = 720;
            hookStopPoint = 720;
        }
        
        catch (CANTimeoutException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new ClimberAimWithJoystick());
        
    }        
    public void extendTAG() { //transition assist guide
        TAGservo.setAngle(170.0); //range of 0-170. set() works on percent of that servo requires jumper on sidecar
        TAGservo.setAngle(0.0); // makes sure it has released
    }
    public void extendSkid() { // limit switch stopped
        try {            
            skidMotor.setX(0.5);
        }
        catch (CANTimeoutException Exception) {
            ;
        }
    }
    public void extendBoom() {
        try {
            boomerangMotor.setX(0.5); 
        }
        catch (CANTimeoutException Exception) {
            ;
        }
    }
    public void extendArms() { //arm is extension, hook is on robot base
        try {                                    
            extendingArmsMotor.changeControlMode(CANJaguar.ControlMode.kPosition);
            extendingArmsMotor.enableControl(0.0); 
            extendingArmsMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            extendingArmsMotor.setX(1.0);  
            while (true) {
                if (extendingArmsMotor.getPosition()*360 > armStopPoint) {
                    break;
                }            
            }
            extendingArmsMotor.disableControl();
        }
        catch (CANTimeoutException Exception) {
            ;
        }
    }    
    public void retractArmsToSwitch() {
        try {                       
            extendingArmsMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            while (true) {                
                extendingArmsMotor.setX(-0.8);
                if (armSwitch.get()) { // if switch is pressed, it returns true
                    break;
                }            
            }
            extendingArmsMotor.disableControl(); // after loop is over
        }
        catch (CANTimeoutException Exception) {
            ;
        }
    }
    public void retractArmsFully() {    
        try {                                                
            extendingArmsMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);   // no reset        
            extendingArmsMotor.setX(-1.0); // encoder hasn't been reset since extension, so 0 is where it started
            while (true) {
                extendingArmsMotor.changeControlMode(CANJaguar.ControlMode.kPosition);               
                if (extendingArmsMotor.getPosition()*360 < 200) { // quick retraction first
                    break;
                }            
            }
            extendingArmsMotor.disableControl(); 
            extendingArmsMotor.setX(-.2);
            while (true) {
                extendingArmsMotor.changeControlMode(CANJaguar.ControlMode.kPosition);               
                if (extendingArmsMotor.getPosition()*360 < 36) { // slower retraction next, give some buffer
                    break;
                }            
            }
            extendingArmsMotor.disableControl(); 
        }
        catch (CANTimeoutException Exception) {
            ;
        }
    }
    public void extendHooks() {
        try {                                                  
            bodyHooksMotor.changeControlMode(CANJaguar.ControlMode.kPosition);
            bodyHooksMotor.enableControl(0.0);
            bodyHooksMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            bodyHooksMotor.setX(1.0);   
            while (true) {                
                if (bodyHooksMotor.getPosition()*360 > hookStopPoint) {
                    break;
                }            
            }
            bodyHooksMotor.disableControl();   
        }
        catch (CANTimeoutException Exception) {
            ;
        }
    }  
    public void retractHooks() {
        try {                              
            bodyHooksMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            bodyHooksMotor.setX(-1.0);                         
            while (true) {
                if (bodyHooksMotor.getPosition()*360 < 200) { //safety
                    break;
                }            
            }
            bodyHooksMotor.disableControl();  
            bodyHooksMotor.setX(-.2);                         
            while (true) {
                if (bodyHooksMotor.getPosition()*360 < 36) {
                    break;
                }            
            }
            bodyHooksMotor.disableControl();
        }
        catch (CANTimeoutException Exception) {
            ;
        }
    }
    public void emergencyStop() {
        Estop = true;
    }
    public void emergencyStopCheck() {
        if (Estop == true) { 
            try {
                extendingArmsMotor.disableControl();
                bodyHooksMotor.disableControl();            
                Estop = false;
            }       
            catch (CANTimeoutException Exception) {
                ;
            }
        }
    }                  
}
