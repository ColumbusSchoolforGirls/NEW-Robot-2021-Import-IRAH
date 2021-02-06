// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import com.revrobotics.ColorSensorV3;

// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.util.Color;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.RobotMap;

// public class ControlSpinner extends SubsystemBase {
//   /**
//    * Creates a new ControlSpinner.%
//    */
//   private static final TalonSRX spin_talon = new TalonSRX(RobotMap.spin_talon);
//   Encoder encoder; 
//   private final I2C.Port i2cPort = I2C.Port.kOnboard;

//   /**
//    * A Rev Color Sensor V3 object is constructed with an I2C port as a 
//    * parameter. The device will be automatically initialized with default 
//    * parameters.
//    */
//   private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  
//   public ControlSpinner() {
//     encoder = new Encoder(RobotMap.spin_encoder_a, RobotMap.spin_encoder_b);

    
//   }

//   /*public void resetEncoder(){
//     encoder.reset();
//   }*/

//   public int getSpinEncoder(){
//     return encoder.get(); 
//   }

//   public void SpinSpeed(final double speed){
//     spin_talon.set(ControlMode.PercentOutput, speed);
//   }

//   public static TalonSRX getTalon(){
//     return spin_talon;
//   }

//   public void update(){
//     //SmartDashboard.putNumber("Spin Encoder", getSpinEncoder());
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//     SmartDashboard.putNumber("Spin Encoder", getSpinEncoder());
//     Color detectedColor = m_colorSensor.getColor();

//     double IR = m_colorSensor.getIR();

//     SmartDashboard.putNumber("Red", detectedColor.red);
//     SmartDashboard.putNumber("Green", detectedColor.green);
//     SmartDashboard.putNumber("Blue", detectedColor.blue);
//     SmartDashboard.putNumber("IR", IR);

//     int proximity = m_colorSensor.getProximity();

//     SmartDashboard.putNumber("Proximity", proximity);

    
//   }
// }
