// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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
public class SearchBluePathOne extends SequentialCommandGroup {

  private final DriveTrain m_drivetrain;  
  private final ConveyorMotors m_conveyormotors;
  private final Limelight m_limelight;
  //private final ticks m_ticks;

  /** Creates a new SearchBluePathOne. */
  public SearchBluePathOne(DriveTrain drivetrain, ConveyorMotors conveyormotors, Limelight limelight) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;
    m_conveyormotors = conveyormotors;
    m_limelight = limelight;
    //m_ticks = ticks;
    addCommands(
      new ResetGyro(m_drivetrain),
      //idk where this starts but comment in later
      //starts w/ robot in center pointedd at first ball
      new straightforward(50, m_drivetrain, false),
      new Tracking(m_limelight, m_drivetrain),
      new ResetGyro(m_drivetrain),
      new ParallelCommandGroup(
        new straightforward(10, m_drivetrain, false),
        new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(1)
      ),
      new edu.wpi.first.wpilibj2.command.WaitCommand(0.5),
      new Turn(55, m_drivetrain),
      new straightforward(40, m_drivetrain, false),
      new Tracking(m_limelight, m_drivetrain),
      new ParallelCommandGroup(
        new straightforward(10, m_drivetrain, false),
        new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(1)
      ),
      new ResetGyro(m_drivetrain),
      new Turn(-70, m_drivetrain),
      new straightforward(30, m_drivetrain, false),
      new Tracking(m_limelight, m_drivetrain),
      new ParallelCommandGroup(
        new straightforward(10, m_drivetrain, false),
        new ConveyorManual(0.65, m_conveyormotors, true).withTimeout(1.5)
      ),
      new straightforward(100, m_drivetrain, false).withTimeout(0.5)
    
      // new straightforward(ticks.calculateTicks(180), m_drivetrain, false),
      // new ConveyorManual(1, m_conveyor, true),
      // new ResetGyro(m_drivetrain),
      // new Turn(18.434, m_drivetrain),

      // new straightforward(ticks.calculateTicks(95.254), m_drivetrain, false),
      // new ConveyorManual(1, m_conveyor, true),
      // new ResetGyro(m_drivetrain),
      // new Turn(26.565, m_drivetrain),
      
      // new straightforward(ticks.calculateTicks(94.868), m_drivetrain, false),
      // new ConveyorManual(1, m_conveyor, true),
      // new ResetGyro(m_drivetrain),
      // new Turn(153.434, m_drivetrain),

      // new straightforward(ticks.calculateTicks(90), m_drivetrain, false)

    );
  }
}
