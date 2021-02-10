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
public class Slalom extends SequentialCommandGroup {
  /** Creates a new Slalom. */
  private DriveTrain m_drivetrain;
  private ticks m_ticks;

  public Slalom(ticks ticks, DriveTrain drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;
    m_ticks = ticks;

    m_drivetrain.setAuto();

    addCommands(
      new straightforward(ticks.calculateTicks(150), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(-90, m_drivetrain)





    );
  }
}
