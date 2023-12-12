// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.recordplayback;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RecordPlaybackSubsystem extends SubsystemBase {


  public RecordPlaybackSubsystem() {
    Record recordCommand = new Record();
    Playback playerCommand = new Playback();
    Export exportCommand = new Export();
    SmartDashboard.putBoolean("Overwrite?", false);
    SmartDashboard.putString("Routine Name", "defaultEmpty");
    SmartDashboard.putData("Record", recordCommand);
    SmartDashboard.putData("Playback", playerCommand);
    SmartDashboard.putData("Export", exportCommand);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}
