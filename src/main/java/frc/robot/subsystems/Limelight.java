package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class Limelight extends SubsystemBase {
    //code so we can get data from limelight?
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tv = table.getEntry("tv");
    NetworkTableEntry ta = table.getEntry("ta");
    
    //returns x value of target in camera
    public double getX(){
        return tx.getDouble(0.0);
    }

    //returns y value of target in camera
    public double getY(){
        return ty.getDouble(0.0);
    }
    //returns area of target
    public double getA(){
        return ta.getDouble(0.0);
    }

    //returns true if there is a target in camera view
    public boolean getValidTarget(){
       return tv.getDouble(0.0)==1;
    }    
    //double area = ta.getDouble(0.0);
    
    //nothing in here since it doesn't do anything in teleop
    public void teleopPeriodic() {  
    }

    //when called robot it will update the dashboard numbers
    public void update() {
        SmartDashboard.putNumber("LimelightX", getX());
        SmartDashboard.putNumber("LimelightY", getY());
        //SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putBoolean("LimelightV",getValidTarget());  
    }
      
}
