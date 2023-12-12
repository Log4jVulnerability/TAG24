package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpu.first.wpilibj.Spark;
import frc.robot.Constants;

public class Winch extends SubsystemBase {
    final Spark winchMotor;
    
    public Winch() {winchMotor = new Spark(Constants.WINCH_ID);}
    public void run(double speed) {winchMotor.set(speed);}
    public void stop() {winchMotor.set(0);}
    
    @Override
    public void periodic() {}
}
