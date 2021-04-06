// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ticks;
import frc.robot.commands.ConveyorManual;
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class search extends SequentialCommandGroup {
  /** Creates a new search. */
  private final DriveTrain m_drivetrain;  
  private final ConveyorMotors m_conveyormotors;
  private final Limelight m_limelight;
  
  public search(DriveTrain drivetrain, ConveyorMotors conveyormotors, Limelight limelight) {
    m_drivetrain = drivetrain;
    m_conveyormotors = conveyormotors;
    m_limelight = limelight;
  
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(
      //grab first ball
      //new straightforward(10, m_drivetrain, false),
      //new Tracking(m_limelight, m_drivetrain),
      //new edu.wpi.first.wpilibj2.command.WaitCommand(0.25),
      new ParallelRaceGroup(
        new Tracking(m_limelight, m_drivetrain),
        new ConveyorManual(0.5, m_conveyormotors, true)
      ),

      new ParallelRaceGroup(
        new straightforward(30, m_drivetrain, false).withTimeout(2),
        new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(2)
      ),

      new searchTurn(m_limelight, m_drivetrain),
      new WaitCommand(0.5),
      new Tracking(m_limelight, m_drivetrain),
      new ParallelRaceGroup(
        new straightforward(30, m_drivetrain, false),
        new ConveyorManual(0.75, m_conveyormotors, true).withTimeout(3)
      ),

      //new WaitCommand(1),

      //new searchTurn(m_limelight, m_drivetrain),
       //new WaitCommand(0.5),
      // // //grab second ball
      //new Tracking(m_limelight, m_drivetrain),
      // new ParallelRaceGroup(
      //    new straightforward(10, m_drivetrain, false),
      //    new ConveyorManual(0.6, m_conveyormotors, true).withTimeout(3)
      //   ),
      //  
      // //look for and grab third
     
      new Turn(m_drivetrain.getFinalAngle(), m_drivetrain, 0.75),
      new straightforward(200, m_drivetrain, false).withTimeout(1.5)
      
     

    );
  }
}
