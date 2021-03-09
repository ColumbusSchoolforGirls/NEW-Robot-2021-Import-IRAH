/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
import frc.robot.PIDCalculator;
import frc.robot.subsystems.DriveTrain;

public class barrelTurn extends CommandBase {
  /**
   * Creates a new starting_infront_port.
   */

  //the angle we want to finish at
  private double angleSetpoint;
  private DriveTrain m_drivetrain;
  private boolean scaleAuto;  //scales motor output to percent speed instead of 100?
  private double percentSpeed; //percent speed to scale motor output to (0-1)
  private PigeonIMU gyro;
  private double angle; //what is this for?
  //left -- true, right -- false
  private boolean turnRight; //true if turning right
  private double ratio = 2.87;  //scaling ratio
  private double tolerance = 5; //degree tolerance
  

  public barrelTurn(double finishAngle, double speed,  DriveTrain drivetrain, boolean scale, boolean direct) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    turnRight = direct;
    addRequirements(drivetrain);
    angleSetpoint = finishAngle;
    percentSpeed= -speed; //negative speed bc to go forward wheelspeed wants negative values???
    m_drivetrain.Wheelspeed(0, 0); 
    scaleAuto = scale;
    
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoders();
    m_drivetrain.resetGyro();
    angle = m_drivetrain.getFacingAngle();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //left encoder is negative value
    double leftEncoder = m_drivetrain.getLeftCanEncoder();
    double rightEncoder = m_drivetrain.getRightCanEncoder();

    // SmartDashboard.putNumber("Left Error", leftError);
    // SmartDashboard.putNumber("Right Error",rightError);
    // SmartDashboard.putNumber("rightMotorOutput", rightOutput);
    // SmartDashboard.putNumber("leftMotorOutput", leftOutput);

    //m_drivetrain.Wheelspeed(-leftOutput - angleOutput, -rightOutput + angleOutput);
    //removed negatives before leftOutput bc pid already gives neg

    if (scaleAuto == true) {
      if(turnRight){
        m_drivetrain.Wheelspeed(percentSpeed*ratio, percentSpeed);
      }else{
        m_drivetrain.Wheelspeed(percentSpeed, percentSpeed*ratio);
      }
      
    } else {
      m_drivetrain.Wheelspeed(percentSpeed, percentSpeed);
      //if(direction){
        //m_drivetrain.Wheelspeed(0.1*2.87*(-leftOutput - angleOutput), 0.1*(-rightOutput + angleOutput));
      //left
      //}else{
        //m_drivetrain.Wheelspeed(0.1*(-leftOutput - angleOutput), 0.1*2.87*(-rightOutput + angleOutput));
     // }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.Wheelspeed(0,0);
    //ahhhhhhhhhhhhhhhhhhhhhhhh
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(angleSetpoint-gyro.getFusedHeading()) <= tolerance;

  }
}
