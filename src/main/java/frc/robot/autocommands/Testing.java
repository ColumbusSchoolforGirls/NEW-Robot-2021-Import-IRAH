/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ticks;
import frc.robot.commands.ConveyorManual;
import frc.robot.commands.FlapManual;
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Flap;
import frc.robot.subsystems.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Testing extends SequentialCommandGroup {
  /**
   * Creates a new GTFO.
   */
  private DriveTrain m_drivetrain;
  private Flap m_flap;
  private ConveyorMotors m_conveyormotors;
  private ticks m_ticks;
  private Limelight m_limelight;
  // if we're with too good teams we just leave
  public Testing(DriveTrain drivetrain, Flap flap, ConveyorMotors conveyormotors, Limelight limelight) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;
    m_flap = flap;
    m_conveyormotors = conveyormotors;
    addRequirements(m_drivetrain);
    addCommands(
      //new straightforward(100, m_drivetrain, false)
      //new FlapManual(m_flap, false, true),
      //new ConveyorManual(1, m_conveyormotors, true).withTimeout(5),
      //new straightforward(, m_drivetrain);
      new ResetGyro(m_drivetrain),
      new barrelCircle(71, drivetrain, true, false)
      //new Turn(87, m_drivetrain)
    //   new ResetGyro(m_drivetrain)
      // new ResetGyro(m_drivetrain),
      // new Tracking(limelight, m_drivetrain)
       );
  }
}
