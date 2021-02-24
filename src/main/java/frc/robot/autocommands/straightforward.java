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

public class straightforward extends CommandBase {
  /**
   * Creates a new starting_infront_port.
   */
  private double leftError;
  private double rightError;
  private double angle;
  private double setpoint;
  private PIDCalculator distPID;
  private PIDCalculator anglePID;
  private DriveTrain m_drivetrain;
  private boolean scaleAuto;

  

  public straightforward(double ticks, DriveTrain drivetrain, boolean scale) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
    setpoint = ticks; //Ticks should be a global constant - MAKE THAT CHANGE
    m_drivetrain.Wheelspeed(0, 0); 
    //why does this need to be static in this instance but it doesn't need to be in tankdrive command
    scaleAuto = scale;
    
    distPID = new PIDCalculator(Global.DRIVETRAIN_P, Global.DRIVETRAIN_I, Global.DRIVETRAIN_D); 
    anglePID = new PIDCalculator(Global.DRIVESTRAIGHT_ANGLE_P, Global.DRIVESTRAIGHT_ANGLE_I, Global.DRIVESTRAIGHT_ANGLE_D);
    
    
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
    leftError = setpoint - leftEncoder;
    rightError = setpoint - rightEncoder;
    double angleError = angle - m_drivetrain.getFacingAngle();

    double leftOutput = distPID.getOutput(leftError);
    double rightOutput = distPID.getOutput(rightError);
    double angleOutput = anglePID.getOutput(angleError);

    SmartDashboard.putNumber("Left Error", leftError);
    SmartDashboard.putNumber("Right Error",rightError);

    m_drivetrain.Wheelspeed(.3*(-leftOutput - angleOutput), .3*(-rightOutput + angleOutput));
    

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
    return Math.abs(leftError) <= Global.DRIVE_DISTANCE_TOLERANCE;
  }
}
