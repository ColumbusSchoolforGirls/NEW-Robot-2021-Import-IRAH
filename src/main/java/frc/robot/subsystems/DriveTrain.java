/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU.CalibrationMode;

public class DriveTrain extends SubsystemBase {

  //variables to represent a motor for each wheel
  private final CANSparkMax right_front = new CANSparkMax(RobotMap.right_front_motor, MotorType.kBrushless);
  private final CANSparkMax left_front = new CANSparkMax(RobotMap.left_front_motor, MotorType.kBrushless);
  private final CANSparkMax right_back = new CANSparkMax(RobotMap.right_back_motor, MotorType.kBrushless);
  private final CANSparkMax left_back = new CANSparkMax(RobotMap.left_back_motor, MotorType.kBrushless);

  // private final CANEncoder rightCanEncoder = new CANEncoder(right_front);
  // private final CANEncoder rightBackEncoder = new CANEncoder(right_back);
  // private final CANEncoder leftCanEncoder = new CANEncoder(left_front);
  // private final CANEncoder leftBackEncoder = new CANEncoder(left_back);

  //variables for each encoder
  private CANEncoder m_rightencoder = right_front.getEncoder();
  private CANEncoder m_leftencoder = left_front.getEncoder();
  private CANEncoder m_rightback = right_back.getEncoder();
  private CANEncoder m_leftback = left_back.getEncoder();

  //makes new pidgeon (gyro) connected to the conveyor talon (since that's where ours is)
  public static PigeonIMU gyro = new PigeonIMU(ConveyorMotors.getTalon());

  public static AnalogInput sonar = new AnalogInput(0);
  

  // Talon code for testing on Miles
  /*public static WPI_TalonSRX right_front = new WPI_TalonSRX(RobotMap.right_front_motor);
  public static WPI_TalonSRX left_front = new WPI_TalonSRX(RobotMap.left_front_motor);
  public static WPI_TalonSRX right_back = new WPI_TalonSRX(RobotMap.right_back_motor);
  public static WPI_TalonSRX left_back = new WPI_TalonSRX(RobotMap.left_back_motor);*/

    
    public DriveTrain() {
      //right_front.setIdleMode(IdleMode.kBrake);
      //left_front.setIdleMode(IdleMode.kBrake);
      //right_back.setIdleMode(IdleMode.kBrake);
      //left_back.setIdleMode(IdleMode.kBrake);

      //when a new drivetrain is created the gyro fused heading is set to 0, encoders are reset
      //and... whatever enterCalibrationMode does
      resetEncoders();
      resetGyro();
      gyro.enterCalibrationMode(CalibrationMode.BootTareGyroAccel);
    
    }

  //when called sets into brake mode, resets encoders and fused heading again
  public void setAuto() {
    right_front.setIdleMode(IdleMode.kBrake);
    left_front.setIdleMode(IdleMode.kBrake);
    right_back.setIdleMode(IdleMode.kBrake);
    left_back.setIdleMode(IdleMode.kBrake);

    gyro.setFusedHeading(0);
    resetEncoders();
    //gyro.enterCalibrationMode(CalibrationMode.BootTareGyroAccel);
  }

  //when called sets into coast mode, resets encoders and fused heading again
  public void setTeleop(){
    right_front.setIdleMode(IdleMode.kCoast);
    left_front.setIdleMode(IdleMode.kCoast);
    right_back.setIdleMode(IdleMode.kCoast);
    left_back.setIdleMode(IdleMode.kCoast);

    gyro.setFusedHeading(0);
    resetEncoders();
    //gyro.enterCalibrationMode(CalibrationMode.BootTareGyroAccel);
  }
   
    // sets wheelspeeds of motors 
//
  public void Wheelspeed(double leftspeed, double rightspeed){  
    right_front.set(rightspeed);
    left_front.set(-leftspeed);
    right_back.set(rightspeed);
    left_back.set(-leftspeed);
    
    //Talon code for testing on Miles
    /*right_front.set(ControlMode.PercentOutput, rightspeed);
    left_front.set(ControlMode.PercentOutput, leftspeed);
    right_back.set(ControlMode.PercentOutput, rightspeed);
    left_back.set(ControlMode.PercentOutput, leftspeed);*/
  }
  
  //sets all encoders to 0
  public void resetEncoders(){
    m_rightencoder.setPosition(0);
    m_leftencoder.setPosition(0);
    m_leftback.setPosition(0);
    m_rightback.setPosition(0);

  }
  
  //returns... the angle the gyro/robot is facing?
  public double getFacingAngle(){
    PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
    //why do we put in fusionStatus and not just null? do we do something with it? I don't think we do
    return gyro.getFusedHeading(fusionStatus);
  }

  //returns encoder positions
  public double getLeftCanEncoder(){
     return m_leftencoder.getPosition();
  }
  public double getRightCanEncoder(){
    return -m_rightencoder.getPosition();
  }
  public double getRightBackEncoder(){
    return -m_rightback.getPosition();
  }
  public double getLeftBackEncoder(){
    return m_leftback.getPosition();
  }

  //sets fused heading and yaw to 0
  public void resetGyro(){
    gyro.setFusedHeading(0);
    gyro.setYaw(0);
  }

  //updates smartdashboard values, does this periodically
  public void update(){
    SmartDashboard.putNumber("Left Encoder", m_leftencoder.getPosition());
    SmartDashboard.putNumber("Right Encoder", m_rightencoder.getPosition());
    SmartDashboard.putNumber("Right Back Encoder", m_rightback.getPosition());
    SmartDashboard.putNumber("Left Back Encoder", m_leftback.getPosition());
    SmartDashboard.putNumber("Angle", getFacingAngle());
    SmartDashboard.putNumber("Joystick Y", RobotContainer.driveCont.getRawAxis(5));
    SmartDashboard.putNumber("Joystick X", RobotContainer.driveCont.getRawAxis(1));
    SmartDashboard.putNumber("Trigger", RobotContainer.driveCont.getRawAxis(3));
  }
  
  public void initDefaultCommand(){
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  public static double getSonar(){
    return sonar.getAverageVoltage();
  }
     
}
