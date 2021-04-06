/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Global;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

public class searchTurn extends CommandBase {
  /**
   * Creates a new starting_infront_port.
   */
  //private double angle; 
  private DriveTrain m_drivetrain;
  private double ratio;
  private Limelight m_limelight;
  private double initialAngle;
  
  //left -- true, right -- false

  

  public searchTurn(Limelight limelight, DriveTrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_limelight = limelight;

    //this.angle = angle;

    addRequirements(drivetrain);
    addRequirements(limelight);
 //Ticks should be a global constant - MAKE THAT CHANGE
     
    //why does this need to be static in this instance but it doesn't need to be in tankdrive command
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoders();
    //m_drivetrain.resetGyro();
    initialAngle = m_drivetrain.getFacingAngle();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.Wheelspeed(0.3, -0.3);
    // if angle negative, turn right
    // if angle positive, turn left
    // for < 0, -0.3, -0.1
    // for > 0, -0.1, -0.3
    // if (angle < 0){
    //   m_drivetrain.Wheelspeed(-0.2*ratio, -0.2);
    // } else if (angle > 0){
    //   m_drivetrain.Wheelspeed(-0.2, -0.2*ratio);
    // } else {
    //   m_drivetrain.Wheelspeed(0, 0);
    // }
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
    return (m_limelight.getValidTarget() || m_drivetrain.getFacingAngle() >= initialAngle + 360);
    
    //return Math.abs(m_drivetrain.getFacingAngle()) >= Math.abs(angle) ;
  }
}
