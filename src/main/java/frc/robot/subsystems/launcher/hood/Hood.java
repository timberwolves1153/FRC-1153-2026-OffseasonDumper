package frc.robot.subsystems.launcher.hood;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Hood extends SubsystemBase {
  private final HoodIO io;
  private final HoodIOInputsAutoLogged inputs = new HoodIOInputsAutoLogged();

  public Hood(HoodIO hoodIO) {
    io = hoodIO;
  }

  /* Periodic to update inputs */
  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Hood", inputs);
  }

  /* Methods */
  public void setVoltageHood(double volts) {
    io.setVoltageHood(volts);
  }

  public void setPositionHood(double position) {
    io.setPositionHood(position);
  }

  public void stopHood() {
    io.stopHood();
  }
}
