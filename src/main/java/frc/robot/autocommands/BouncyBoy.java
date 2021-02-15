// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ticks;
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BouncyBoy extends SequentialCommandGroup {
  /** Creates a new BouncyBoy. */

  private DriveTrain m_drivetrain;
  private ticks m_ticks;

  public BouncyBoy(DriveTrain drivetrain, ticks tick) {
    m_drivetrain = drivetrain;
    m_ticks = tick;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new straightforward(tick.calculateTicks(95), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(45, m_drivetrain),

      new straightforward(tick.calculateTicks(-190), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(-45, m_drivetrain),

      new straightforward(tick.calculateTicks(190), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(20, m_drivetrain),

      new straightforward(tick.calculateTicks(-180), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(-70, m_drivetrain),

      new straightforward(tick.calculateTicks(30), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(70, m_drivetrain),
    
      new straightforward(tick.calculateTicks(180), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(45, m_drivetrain),

      new straightforward(tick.calculateTicks(95), m_drivetrain)
     
    );
  }
}
