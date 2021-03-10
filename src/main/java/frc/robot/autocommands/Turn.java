/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
import frc.robot.PIDCalculator;
import frc.robot.subsystems.DriveTrain;

public class Turn extends CommandBase {
  /**
   * Creates a new Turn.
   */
  private double angle;
  private double angleError;
  private PIDCalculator anglePID;
  private DriveTrain m_drivetrain;
  private PigeonIMU gyro;

  public Turn(double angle, DriveTrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    // m_drivetrain HAS TO  go above it
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
    this.angle = angle;
    //sets anglePID equal to the motor output needed to... turn?
    anglePID = new PIDCalculator(Global.TURNANGLE_P, Global.TURNANGLE_I, Global.TURNANGLE_D, Global.TURNANGLE_IZONE);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //setTimeout(m_timeout);
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //angleError set equal to how far it is from angle
    angleError = angle - m_drivetrain.getFacingAngle();

    double angleOutput = anglePID.getOutput(angleError);

    SmartDashboard.putNumber("Angle Output", angleOutput);
    SmartDashboard.putNumber("Angle Error", angleError);

    //the part that actually turns the robot
    //right side goes backward?
    //when angleOutput positive, will turn right
    //REMEMBER THE RIGHT IS THE FIRST ONE!!
    m_drivetrain.Wheelspeed(-angleOutput, angleOutput);
  
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.Wheelspeed(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return Math.abs(angleError) <= Global.DRIVE_ANGLE_TOLERANCE || isTimedOut();
    return Math.abs(angleError) <= Global.DRIVE_ANGLE_TOLERANCE;
  }
}  
