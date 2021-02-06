/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;*/

/*import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlSpinner;*/


// public class ControlSpinnerManual extends CommandBase {
//   /**
//    * Creates a new ControlSpinnerManual.
//    */
//   private ControlSpinner m_spinner;
//   private double speed;

//   private final I2C.Port i2cPort = I2C.Port.kOnboard;

//   /**
//    * A Rev Color Sensor V3 object is constructed with an I2C port as a 
//    * parameter. The device will be automatically initialized with default 
//    * parameters.
//    */
//   /*private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

//   /**
//    * A Rev Color Match object is used to register and detect known colors. This can 
//    * be calibrated ahead of time or during operation.
//    * 
//    * This object uses a simple euclidian distance to estimate the closest match
//    * with given confidence range.
//    */
//   private final ColorMatch m_colorMatcher = new ColorMatch();

//   /**
//    * Note: Any example colors should be calibrated as the user needs, these
//    * are here as a basic example.
//    */
//   private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
//   private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
//   private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
//   private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

//   public ControlSpinnerManual(ControlSpinner spinner, double speed) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     m_spinner = spinner;
//     addRequirements(spinner);
//     this.speed = speed;
//   } 

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     m_spinner.SpinSpeed(0);
//     m_colorMatcher.addColorMatch(kBlueTarget);
//     m_colorMatcher.addColorMatch(kGreenTarget);
//     m_colorMatcher.addColorMatch(kRedTarget);
//     m_colorMatcher.addColorMatch(kYellowTarget); 
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     // this logic is so that if it is at or just above the set point it will stop the motor
//     // otherwise it goes at full speed
//     if (m_spinner.getSpinEncoder() <= 1000){
//       m_spinner.SpinSpeed(-0.35);
//    } else {
//       m_spinner.SpinSpeed(0);
//    }
//    //m_spinner.SpinSpeed(speed);
//    Color detectedColor = m_colorSensor.getColor();

//     /**
//      * Run the color match algorithm on our detected color
//      */
//     String colorString;
//     ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

//     if (match.color == kBlueTarget) {
//       colorString = "Blue";
//     } else if (match.color == kRedTarget) {
//       colorString = "Red";
//     } else if (match.color == kGreenTarget) {
//       colorString = "Green";
//     } else if (match.color == kYellowTarget) {
//       colorString = "Yellow";
//     } else {
//       colorString = "Unknown";
//     }
//     SmartDashboard.putNumber("Red", detectedColor.red);
//     SmartDashboard.putNumber("Green", detectedColor.green);
//     SmartDashboard.putNumber("Blue", detectedColor.blue);
//     SmartDashboard.putNumber("Confidence", match.confidence);
//     SmartDashboard.putString("Detected Color", colorString);

//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     m_spinner.SpinSpeed(0);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
