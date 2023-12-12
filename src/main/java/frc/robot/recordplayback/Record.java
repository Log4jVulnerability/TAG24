// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.recordplayback;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
 

public class Record extends CommandBase {
  
  long startTime;
  boolean OverwriteModeisTrue = false;
  String routineName = "defaultName";
  FileWriter writer;
  public Gson gson;
  private boolean isFinished;
  public int currentLine;
  public boolean isRecording;
  private ArrayList<double[]> arrayOfLines;  // = new ArrayList<double[]>();
  private double lastLine[] = new double[20];

  public Record() {
    // no dependencies so that all of the other normal commands can run
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    arrayOfLines = new ArrayList<double[]>();
    isFinished = false;
    gson = new Gson();

    routineName = SmartDashboard.getString("Routine Name", "defaultEmpty");
    OverwriteModeisTrue = SmartDashboard.getBoolean("Overwrite?", false);

    if (OverwriteModeisTrue || !(Files.isRegularFile(Paths.get("/c/" + routineName + ".json")))) { 
      try {
        writer = new FileWriter("/c/" + routineName + ".json", false);
        System.out.println("----- successfully made writer -----");
        System.out.println(writer.toString());
      } catch (IOException e) {
        System.out.println("----- IOException FileWriter bad -----");
        System.out.println(e);
        isFinished = true;
      }
      
    } else {
      System.out.println("----- file name already existed and overwrite mode is false. Will not record -----");
      isFinished = true;
    }

    currentLine = 0;
    // isRecording = true;
    // SmartDashboard.putBoolean("isRecording", isRecording);

  }

  private double recordWhileHeld(Integer lineId, Boolean currentValue){
    // return a 0.0 when the button is NOT pressed (false)
    // return a 1.0 when the button is pressed (true)
    // return a 2.0 when the button continues to be pressed (true and previous value is also true)
    // return a 3.0 when the button ceases to be pressed (false and previous value is true)
    if (lastLine[lineId] == 0.0) {
      return (currentValue ? 1.0 : 0.0);
    } else if (lastLine[6] == 3.0) {
      return (currentValue ? 1.0 : 0.0);
    } else {
      return (currentValue ? 2.0 : 3.0);
    }
  }

  public double[] checkButtons() {
    double thisLine[] = new double[20];
    
    // joysticks:
    thisLine[0] = RobotContainer.getXbox0().getLeftX();
    thisLine[1] = RobotContainer.getXbox0().getLeftY();
    thisLine[2] = RobotContainer.getXbox0().getRightX();
    thisLine[3] = RobotContainer.getXbox0().getRightY();
    // triggers:
    thisLine[4] = RobotContainer.getXbox0().getLeftTriggerAxis();
    thisLine[5] = RobotContainer.getXbox0().getRightTriggerAxis();
    // bumpers:
    thisLine[6] = this.recordWhileHeld(6, RobotContainer.getXbox0().getLeftBumper());
    thisLine[7] = this.recordWhileHeld(7, RobotContainer.getXbox0().getRightBumper());
    // coloured buttons:
    thisLine[8] = this.recordWhileHeld(8, RobotContainer.getXbox0().getXButton());  // X
    thisLine[9] = this.recordWhileHeld(9, RobotContainer.getXbox0().getYButton());  // Y
    thisLine[10] = this.recordWhileHeld(10, RobotContainer.getXbox0().getAButton());  // A
    thisLine[11] = this.recordWhileHeld(11, RobotContainer.getXbox0().getBButton());  // B
    // middle small buttons:
    thisLine[12] = this.recordWhileHeld(12, RobotContainer.getXbox0().getStartButton());  // Start
    thisLine[13] = this.recordWhileHeld(13, RobotContainer.getXbox0().getBackButton());  // Back
    // D Pad Buttons (4 cardinal directions)
    thisLine[14] = this.recordWhileHeld(14, (RobotContainer.getXbox0().getPOV() == 0));  // Up
    thisLine[15] = this.recordWhileHeld(15, (RobotContainer.getXbox0().getPOV() == 180));  // Down
    thisLine[16] = this.recordWhileHeld(16, (RobotContainer.getXbox0().getPOV() == 270));  // Left
    thisLine[17] = this.recordWhileHeld(17, (RobotContainer.getXbox0().getPOV() == 90));  // Right
    // Joystick button clickers
    thisLine[18] = this.recordWhileHeld(18, RobotContainer.getXbox0().getLeftStickButtonPressed());  // Left Click
    thisLine[19] = this.recordWhileHeld(19, RobotContainer.getXbox0().getRightStickButtonPressed());  // Right Click

    lastLine = thisLine.clone();
    return thisLine;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!isFinished) {    
      double[] theLine = checkButtons();
      arrayOfLines.add(theLine);
      currentLine++;
   }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    try {
      gson.toJson(arrayOfLines, writer);
      arrayOfLines.clear();
      System.out.println("----- saved json recording -----");
    } catch (Exception e) {
      System.out.println("----- Gson Exception: -----"); 
      System.out.println(e);
      System.out.println("----- End Gson Exception -----");
    }
    try {
      writer.flush(); 
      writer.close();
    } catch (Exception e) {
      System.out.println("----- Writer Exception: -----"); 
      System.out.println(e); 
      System.out.println("----- End Writer Exception -----");
    }   
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if (isFinished) {
      // isRecording = false;
      // SmartDashboard.putBoolean("isRecording", isRecording);
      return true;
    } else {
      return false;
    }
  }
}