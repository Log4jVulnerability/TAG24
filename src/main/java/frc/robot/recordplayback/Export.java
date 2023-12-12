package frc.robot.recordplayback;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Export extends CommandBase {

  double thisLine[] = new double[14];

  int currentLine = 0;
  public boolean isPlaying = false;

  long startTime;

  boolean onTime = true;
  double nextDouble;
  String routineName;
  Gson gson;

  boolean isFinished;
  FileReader fileReader;
  FileWriter writer;

  ArrayList<double[]> allLines;

  public Export() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void initialize() {
    gson = new Gson();
    routineName = "defaultEmpty";
    routineName = SmartDashboard.getString("Routine Name", "defaultEmpty");
    System.out.println("exporting " + routineName);
    String file = "/media/sda1/" + routineName + ".json";
    try {
      writer = new FileWriter(file);
    } catch (Exception e){
      System.out.println("could not create FileWriter -- did you put a flash drive into the RoboRIO?");
      System.out.println(e);
    }
    try {
      fileReader = new FileReader("/c/" + routineName + ".json");
    } catch (Exception e) {
      System.out.println("could not create FileReader");
      System.out.println(e);
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

    // Write the json to the writer
    gson.toJson(allLines, writer);

    try {
      writer.flush();
      writer.close();
    } catch (Exception e) {
      System.out.println("failed to write file");
      System.out.println(e);
    }

  }

  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    try {fileReader.close();}
    catch(Exception e) {}
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
