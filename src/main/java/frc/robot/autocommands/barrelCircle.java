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
  private boolean turnRight;
  private double ratio = 2.87;
  

  public barrelCircle(double insideTicks,  DriveTrain drivetrain, boolean scale, boolean direct) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    turnRight = direct;
    addRequirements(drivetrain);
    setpoint = insideTicks; //Ticks should be a global constant - MAKE THAT CHANGE
    m_drivetrain.Wheelspeed(0, 0); 
    //why does this need to be static in this instance but it doesn't need to be in tankdrive command
    scaleAuto = scale;

    
    leftDistPID = new PIDCalculator(Global.BARRELS_P, Global.BARRELS_I, Global.BARRELS_D);
    rightDistPID = new PIDCalculator(Global.BARRELS_P, Global.BARRELS_I, Global.BARRELS_D);
    anglePID = new PIDCalculator(Global.DRIVESTRAIGHT_ANGLE_P, Global.DRIVESTRAIGHT_ANGLE_I, Global.DRIVESTRAIGHT_ANGLE_D);
    
    
    
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

    //turn right
    if(turnRight){
     leftError = ratio*setpoint - Math.abs(leftEncoder); //negative bc left drives opposite direction? (they go backward)
     rightError = setpoint - Math.abs(rightEncoder);
    }
    //turn left
    else {
      leftError = setpoint - Math.abs(leftEncoder);
      rightError = ratio*setpoint - Math.abs(rightEncoder); //rightEncoder is negative when moving forward
    }
    //double angleError = angle - m_drivetrain.getFacingAngle();

    double leftOutput = leftDistPID.getOutput(leftError);
    double rightOutput = rightDistPID.getOutput(rightError);
    //double angleOutput = anglePID.getOutput(angleError);

    SmartDashboard.putNumber("Left Error", leftError);
    SmartDashboard.putNumber("Right Error",rightError);
    SmartDashboard.putNumber("rightMotorOutput", rightOutput);
    SmartDashboard.putNumber("leftMotorOutput", leftOutput);


    //m_drivetrain.Wheelspeed(-leftOutput - angleOutput, -rightOutput + angleOutput);
    //removed negatives before leftOutput bc pid already gives neg

    // think about ratio and how it is applied at the top and applied here. 
    if (scaleAuto == true) {
      m_drivetrain.Wheelspeed(0.3*(-leftOutput), 0.3*(-rightOutput));
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
    m_drivetrain.Wheelspeed(0,0);
    //ahhhhhhhhhhhhhhhhhhhhhhhh
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (turnRight){
      //turning left
      return Math.abs(rightError) <= Global.DRIVE_DISTANCE_TOLERANCE;
    }
    else {
      return Math.abs(leftError) <= Global.DRIVE_DISTANCE_TOLERANCE;
    }
  }
}
