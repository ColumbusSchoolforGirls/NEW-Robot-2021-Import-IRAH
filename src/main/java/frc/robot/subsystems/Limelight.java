package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class Limelight extends SubsystemBase {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tv = table.getEntry("tv");
    //NetworkTableEntry ta = table.getEntry("ta");
    
    public double getX(){
        return tx.getDouble(0.0);
    }
    public double getY(){
        return ty.getDouble(0.0);
    }
    public boolean getValidTarget(){
       return tv.getDouble(0.0)==1;
    }    
    //double area = ta.getDouble(0.0);
    
    
    public void teleopPeriodic() {
        SmartDashboard.putNumber("LimelightX", getX());
        SmartDashboard.putNumber("LimelightY", getY());
        //SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putBoolean("LimelightV",getValidTarget());    
    }
    
    
}
