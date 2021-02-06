/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ConveyorManual;
import frc.robot.commands.FlapManual;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Flap;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class firstPlanAutoMiddle extends SequentialCommandGroup {
  /**
   * Creates a new firstPlanAutoMiddle.
   */
  private DriveTrain m_drivetrain;
  private ConveyorMotors m_conveyormotors;
  private Flap m_flap;
  public firstPlanAutoMiddle(DriveTrain drivetrain, ConveyorMotors conveyormotors, Flap flap) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;
    m_conveyormotors = conveyormotors;
    m_flap = flap;
    addRequirements(m_drivetrain);
    //auto starting in middle and continuing straight on to middle of port and shooting 
    addCommands(
      new straightforward(65, m_drivetrain).withTimeout(3),
      new FlapManual(m_flap, false, true),
      new ConveyorManual(1, m_conveyormotors, true).withTimeout(5),
      new straightforward(-10, m_drivetrain)
    );
      
    //super();
  }
}
