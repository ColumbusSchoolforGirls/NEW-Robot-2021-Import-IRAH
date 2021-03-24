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
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.DriveTrain;

public class Turn extends CommandBase {
  /**
   * Creates a new Turn.
   */
  private double angle; //the angle you want to turn, left should be positive
  private double angleError; //how far we are from the turn
  private PIDCalculator anglePID; //PID calculator for angle things
  private DriveTrain m_drivetrain; //just our boy the drivetrain he tries his best
  private PigeonIMU gyro; //I heckin hate you gyro, he supposed to measure the angle but he wack I think

  public Turn(double angle, DriveTrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    // m_drivetrain HAS TO go above it (can't add requirements to something that doesn't exist yet)
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
    this.angle = angle;

    //sets anglePID to a PID calculator with turnangle constants from global?
    anglePID = new PIDCalculator(Global.TURNANGLE_P, Global.TURNANGLE_I, Global.TURNANGLE_D, Global.TURNANGLE_IZONE);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //setTimeout(m_timeout);
    //shouldn't do anything at first
    //jk resets the gyro
    //m_drivetrain.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //angleError set equal to how far current angle is from the goal angle (what you put in)
    angleError = angle - m_drivetrain.getFacingAngle();

    //sets output to motor output needed to get to given angle (get angleError to 0sih?) 
    //i think... idrk how pid works...
    double angleOutput = anglePID.getOutput(angleError);

    //puts values up on dashboard
    SmartDashboard.putNumber("Angle Output", angleOutput);
    SmartDashboard.putNumber("Angle Error", angleError);

    //the part that actually turns the robot
    //should turn left with positive input so why go right??
    //good question but for some reason 
    //having the FIRST parameter as positive and the second negative turns right
    //fun fact having them both positive drives the robot straight backwards so thats why
    m_drivetrain.Wheelspeed(angleOutput, -angleOutput);
  
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.Wheelspeed(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(angleError) <= Global.DRIVE_ANGLE_TOLERANCE;
  }
}  
