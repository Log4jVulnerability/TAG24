# Record Playback System

## Installation

### Add Gson Library
More information at: https://github.com/google/gson

Add dependency to build.gradle
```java
dependencies {
    ...
    implementation 'com.google.code.gson:gson:2.8.9'
    ...
}
```

### Copy the recordplayback folder to the project
The folder contains:
- RecordPlaybackSubystem.java
- Record.java (Command for recording human actions)
- Playback.java (Command for playing back a routine)
- Export.java (Command for exporting routines to a USB drive)

### Register the Subsystem
```java
import frc.robot.recordplayback.RecordPlaybackSubsystem;
...
public class RobotContainer {
  ...
  private final RecordPlaybackSubsystem recordPlaybackSubsystem = new RecordPlaybackSubsystem();
  ...
}
```

### Ensure you have a getXbox0 method so that Record.java can access the controller
```java
public class RobotContainer {
  ...
  public static XboxController getXbox0(){
    return xbox0;
  }
  ...
}
```

### Make sure the subsystems are public and static
RobotContainer.java
```java
public final static ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
```

## Using the RecordPlayback System
The general idea is that Record command will collect double values for each button on the xBox controllers.
It stores those values in an array of double values. The array is stored as a serialized json object (gson).
Then, that array of values can be loaded and replayed, line by line, using the Playback command. 

**IMPORTANT**

The Playback command must be updated each year to map the controller values to desired actions.

Example of Playback.java (designed to be used with Tutorial 201)
```java
public class Playback extends CommandBase {
  ...
  public void execute() {

    double[] thisLine = allLines.get(currentLine);
    
    // Left-joystick Y axis control
    RobotContainer.m_exampleSubsystem.go(thisLine[1]);

     // BUMPER CONTROL
     if (thisLine[8] == 1.0 || thisLine[8] == 2.0) {RobotContainer.m_exampleSubsystem.go(.3);}
     if (thisLine[8] == 3.0) {RobotContainer.m_exampleSubsystem.stopMotor();}
  }
  ...
}
```

Notes:
- Record will need to be revamped if additional controllers are used (increasing the array size and recording those values).
- Buttons only behave properly if set up as whileHeld. Toggle buttons, for example, will need to be retooled.

### Getting Started
After deploying and enabling in Driver's Station you should see:
- a String variable of the name of the routine
- a Boolean variable to Overwrite (allow overwriting of a previous routine)
- a button to Record a new routine
- a button to Playback a recorded routine
- a button to Export a recorded routine

### Exporting
After you have successful routine -- you need to export it so it can be kept
- Place a USB drive into the RoboRIO (it should be available at /media/sda1 but if this fails, use Putty to confirm the path)
- Ensure the correct routine is set in the "Routine Name" field
- Click Export - this will save a file of <routineName>.json
- Remove the Flash drive and insert it into the Driver Station computer
- Copy the .json file from the flash drive into the "src/main/deploy" directory
- The files in the deploy directory are copied to the RoboRIO and this path will be checked by the Playback command
