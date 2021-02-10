// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Slalom extends SequentialCommandGroup {
  /** Creates a new Slalom. */
  private DriveTrain m_drivetrain;

  public Slalom(Drivetrain drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;

    m_drivetrain.setAuto();

    addCommands(
      //leaving start
      new straightforward(ticks.calculateTicks(24), m_drivetrain),
      new ResetGyro(m_drivetrain),

      new Turn(55, m_drivetrain),
      new straightforward(ticks.calculateTicks(62), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(-55, m_drivetrain),

      //big forward A 
      new straightforward(ticks.calculateTicks(120), m_drivetrain),

      new ResetGyro(m_drivetrain),
      new Turn(-55, m_drivetrain),
      new straightforward(ticks.calculateTicks(62), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(55, m_drivetrain),

      //--> /\
      new straightforward(ticks.calculateTicks(60), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(90, m_drivetrain),

      //  /\  <
      new straightforward(ticks.calculateTicks(60), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(90, m_drivetrain),

      // <-- 
      new straightforward(ticks.calculateTicks(60), m_drivetrain),
      new ResetGyro(m_drivetrain),

      //prepare for big forward B
      new Turn(55, m_drivetrain),
      new straightforward(ticks.calculateTicks(62), m_drivetrain),
      new Turn(-55, m_drivetrain),

      //big forward B (return)
      new straightforward(ticks.calculateTicks(120), m_drivetrain),

      new Turn(-55, m_drivetrain),
      new straightforward(ticks.calculateTicks(62), m_drivetrain),

      //finish
      new Turn(55, m_drivetrain),
      new straightforward(ticks.calculateTicks(24), m_drivetrain),
    );
  }
}