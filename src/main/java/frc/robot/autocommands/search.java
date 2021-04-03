// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.command.WaitCommand;
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
      new ParallelRaceGroup(
        new Tracking(limelight, m_drivetrain),
        new ConveyorManual(0.5, m_conveyormotors, true)
      ),
      new searchTurn(m_limelight, m_drivetrain),
      //grab second ball
      //new Tracking(m_limelight, m_drivetrain),
      new ParallelCommandGroup(
        new straightforward(10, m_drivetrain, false),
        new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(1)
      ),
      //look for and grab third
      new searchTurn(m_limelight, m_drivetrain),
      new Tracking(m_limelight, m_drivetrain),
      new ParallelCommandGroup(
        new straightforward(10, m_drivetrain, false),
        new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(1)
      ),
      // new searchTurn(m_limelight, m_drivetrain),
      // new Tracking(m_limelight, m_drivetrain),
      // new ParallelCommandGroup(
      //   new straightforward(10, m_drivetrain, false),
      //   new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(1)
      // ),
      new Turn(m_drivetrain.getFinalAngle(), m_drivetrain, 1)
     
      // new searchTurn(m_limelight, m_drivetrain, 2.3),
      // new Tracking(m_limelight, m_drivetrain),
      // new ParallelCommandGroup(
      //   new straightforward(10, m_drivetrain, false),
      //   new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(1)
      // )
      // new Turn(the angle thing, m_drivetrain, 1)
    );
  }
}
