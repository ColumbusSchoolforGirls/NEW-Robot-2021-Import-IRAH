// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
import frc.robot.PIDCalculator;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

public class Tracking extends CommandBase {
  private Limelight m_limelight;
  private DriveTrain m_drivetrain;
  private PIDCalculator trackPID;

  /** Creates a new Tracking. */
  public Tracking(Limelight limelight, DriveTrain drivetrain) {
    m_limelight = limelight;
    m_drivetrain = drivetrain;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain, limelight);
    m_drivetrain.Wheelspeed(0, 0); 
    // track is variable that finds the angle needed to turn from the camera
    trackPID = new PIDCalculator(Global.TRACK_P, Global.TRACK_I, Global.TRACK_D);
   
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limelight.getValidTarget()){
      while(Math.abs(m_limelight.getX())>= 9){
        //output wants error to goal, and so it is set point-error (how far from goal), and our set point 0, so its just 0-tx, so -tx
        double trackOutput = trackPID.getOutput(-m_limelight.getX());

        SmartDashboard.putNumber("Track Output", trackOutput);
        SmartDashboard.putNumber("Track Error", -m_limelight.getX());

        //i don't remember how it really works but left and right should be right now
        m_drivetrain.Wheelspeed(trackOutput, -trackOutput);
      }
      //m_drivetrain.Wheelspeed(-0.2, -0.2);
    }else {
      m_drivetrain.Wheelspeed(-0.2, -0.2);
    }
    m_drivetrain.Wheelspeed(-0.4, -0.4);
    // else{
    //   m_drivetrain.Wheelspeed(0,0);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.Wheelspeed(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !m_limelight.getValidTarget();
  }
}
