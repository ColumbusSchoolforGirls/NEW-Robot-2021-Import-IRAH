/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
import frc.robot.PIDCalculator;
import frc.robot.subsystems.DriveTrain;

public class barrelCircle extends CommandBase {
  /**
   * Creates a new starting_infront_port.
   */
  private double leftError;
  private double rightError;
  private double angle;
  private double setpoint;
  private PIDCalculator leftDistPID;
  private PIDCalculator rightDistPID;
  private PIDCalculator anglePID;
  private DriveTrain m_drivetrain;
  private boolean scaleAuto;
  //left -- true, right -- false
  private boolean direction;
  private double ratio = 2.87;
  

  public barrelCircle(double insideTicks,  DriveTrain drivetrain, boolean scale, boolean direct) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    direction = direct;
    addRequirements(drivetrain);
    setpoint = insideTicks; //Ticks should be a global constant - MAKE THAT CHANGE
    m_drivetrain.Wheelspeed(0, 0); 
    //why does this need to be static in this instance but it doesn't need to be in tankdrive command
    scaleAuto = scale;

    
    // leftDistPID = new PIDCalculator(Global.DRIVETRAIN_P, Global.DRIVETRAIN_I, Global.DRIVETRAIN_D); 
    // rightDistPID = new PIDCalculator(Global.DRIVETRAIN_P, Global.DRIVETRAIN_I, Global.DRIVETRAIN_D);
    // anglePID = new PIDCalculator(Global.DRIVESTRAIGHT_ANGLE_P, Global.DRIVESTRAIGHT_ANGLE_I, Global.DRIVESTRAIGHT_ANGLE_D);
    
    
    
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoders();
    m_drivetrain.resetGyro();
    angle = m_drivetrain.getFacingAngle();
    SmartDashboard.putString("Hello World", "Hi");
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftEncoder = m_drivetrain.getLeftCanEncoder();
    double rightEncoder = m_drivetrain.getRightCanEncoder();

    //turn left
    if(direction){
     leftError = (-setpoint - leftEncoder);
     rightError = ratio*(setpoint - rightEncoder);
    }
    //turn right
    else {
      leftError = ratio*(setpoint - leftEncoder);
      rightError = -setpoint - rightEncoder;
    }
    //double angleError = angle - m_drivetrain.getFacingAngle();

   // if not work it should be leftPID.getOutput(leftOutput)  
    double leftOutput = 1;
    double rightOutput = 1;
    //double angleOutput = anglePID.getOutput(angleError);

    SmartDashboard.putNumber("Left Error", leftError);
    SmartDashboard.putNumber("Right Error",rightError);
    SmartDashboard.putNumber("rightMotorOutput", rightOutput);
    SmartDashboard.putNumber("leftMotorOutput", leftOutput);


    //m_drivetrain.Wheelspeed(-leftOutput - angleOutput, -rightOutput + angleOutput);
    if (scaleAuto == true) {
      //for left
      if(direction){
        m_drivetrain.Wheelspeed(0.3*(-leftOutput), 0.3*ratio*(rightOutput));
      //right
      }else{
        m_drivetrain.Wheelspeed(0.3*ratio*(-leftOutput), 0.3*(rightOutput));
      }
    } else {
      m_drivetrain.Wheelspeed(-leftOutput, -rightOutput);
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
    m_drivetrain.Wheelspeed(0, 0);
    //ahhhhhhhhhhhhhhhhhhhhhhhh
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(leftError) <= setpoint + 5;
  }
}
