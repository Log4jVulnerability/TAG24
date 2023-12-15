package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Winch extends SubsystemBase {
    final CANSparkMax winchMotor;

    public Winch() {winchMotor = new CANSparkMax(Constants.WINCH_ID, MotorType.kBrushed);}
    public void run(boolean reverse) {winchMotor.set((reverse ? -1 : 1) * Constants.WINCH_SPEED);}
    public void stop() {winchMotor.set(0);}
    
    @Override
    public void periodic() {}
}
