// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class barrels extends SequentialCommandGroup {
  
  private DriveTrain m_drivetrain;

  /** Creates a new barrels. */
  public barrels(DriveTrain drivetrain) {
    m_drivetrain = drivetrain;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
   
    m_drivetrain.setAuto();

    addCommands(

    //first forward is segment a

      new straightforward(ticks.calculateTicks(150), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(-90, m_drivetrain),

      new straightforward(ticks.calculateTicks(37.5), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(-90, m_drivetrain),

      new straightforward(ticks.calculateTicks(37.5), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(-90, m_drivetrain),

      new straightforward(ticks.calculateTicks(37.5), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(-70, m_drivetrain),

    //big forward is segment b
    

      new straightforward(ticks.calculateTicks(/* big forward*/ ), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(70, m_drivetrain),

      new straightforward(ticks.calculateTicks(37.5), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(90, m_drivetrain),

      new straightforward(ticks.calculateTicks(37.5), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(90, m_drivetrain),

      new straightforward(ticks.calculateTicks(37.5), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(45, m_drivetrain),

    //medium forward is segment c
    

      new straightforward(ticks.calculateTicks(/* medium forward*/ ), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(135, m_drivetrain),

      new straightforward(ticks.calculateTicks(37.5), m_drivetrain),
      new ResetGyro(m_drivetrain),
      new Turn(90, m_drivetrain),

    //big big forward is segment d
    
      new straightforward(ticks.calculateTicks(/*big big forward*/, m_drivetrain),
     
    );
  }
}
