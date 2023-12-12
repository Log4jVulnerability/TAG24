package frc.robot.recordplayback;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Playback extends CommandBase {

  double thisLine[] = new double[20];

  int currentLine = 0;
  public boolean isPlaying = false;

  long startTime;

  boolean onTime = true;
  double nextDouble;
  String routineName;
  Gson gson;

  boolean isFinished;
  FileReader fileReader;

  ArrayList<double[]> allLines;

  public Playback() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void initialize() {
    gson = new Gson();
    currentLine = 0;
    isFinished = false;
    routineName = "defaultEmpty";

    // isPlaying = true;
    // SmartDashboard.putBoolean("isPlaying", isPlaying);

    routineName = SmartDashboard.getString("Routine Name", "defaultEmpty");
    System.out.println("playing " + routineName);    

    // Check if the routineName exists locally. If not then look for the file in the Deploy directory
    File localFile = new File("/c/" + routineName + ".json");
    File deployedFile = new File(Filesystem.getDeployDirectory() + "/" + routineName + ".json");
    if (localFile.exists()){
      try {
        fileReader = new FileReader(localFile);
      } catch (Exception e) {
        isFinished = true;
        isPlaying = false;
        System.out.println("could not create Local FileReader");
        System.out.println(e);
      }
    } else if (deployedFile.exists()){
      try {
        fileReader = new FileReader(deployedFile);
      } catch (Exception e) {
        isFinished = true;
        isPlaying = false;
        System.out.println("could not create Deployed FileReader");
        System.out.println(e);
      }
    } else {
      isFinished = true;
      System.out.println("Tried these paths and could not find a file to Play:");
      System.out.println(localFile.getAbsolutePath());
      System.out.println(deployedFile.getAbsolutePath());
    }
  
    if (allLines != null) {
      allLines.clear();
    }
    
    try {
      allLines = gson.fromJson(fileReader, new TypeToken<List<double[]>>(){}.getType());
      System.out.println("opened read file");
    } catch (Exception e) {
      System.out.println("failed to open read file");
      System.out.println(e);
    }
		
    // isPlaying = true;
    // SmartDashboard.putBoolean("isPlaying", isPlaying);

  }

    // Called every time the scheduler runs while the command is scheduled.
    /* current layout:
    XBOX0 (0-19), XBOX1(20-39) 
    joysticks 0-3
    0 = left x axis
    1 = left y axis
    2 = right x axis
    3 = right y axis
    4 = left trigger
    5 = right trigger
    6 = left bumper
    7 = right bumper
    8 = x
    9 = y
    10 = a
    11 = b
    12 = start
    13 = back
    14 = DPAD Up
    15 = DPad Right
    16 = DPad Down
    17 = DPad Left
    18 = Left Joy Click
    19 = Right Joy Click
    */
  @Override
  public void execute() {

    double[] thisLine = allLines.get(currentLine);
    
    // RUN LINE

    // Examples based on Tutorial 201 code
    // Left-joystick Y axis control
    // RobotContainer.m_exampleSubsystem.go(thisLine[1]);

     // BUMPER CONTROL
     // if (thisLine[8] == 1.0 || thisLine[8] == 2.0) {RobotContainer.m_exampleSubsystem.go(.3);}
     // if (thisLine[8] == 3.0) {RobotContainer.m_exampleSubsystem.stopMotor();}

    // END RUN LINE

    currentLine ++;
    if (currentLine >= allLines.size() - 1) {
      isFinished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    isPlaying = false;
    
    SmartDashboard.putBoolean("isPlaying", isPlaying);
    try {fileReader.close();}
    catch(Exception e) {}
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (isFinished){
      return true;
    } else { 
      return false;
    }
  }
}
