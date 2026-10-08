package frc.robot.subsystems.launcher.hood;

import org.littletonrobotics.junction.AutoLog;

public interface HoodIO {
  @AutoLog
  public static class HoodIOInputs {
    /* Inputs for hood motor */
    public double hoodVoltage = 0.0;
    public double hoodCurrent = 0.0;
    public double hoodTemp = 0.0;
    public double hoodPosition = 0.0;

    public boolean isHomed = false;
  }

  /* Methods */
  public default void updateInputs(HoodIOInputs inputs) {}

  public default void setVoltageHood(double volts) {}

  public default void setPositionHood(double position) {}

  public default void stopHood() {}
}
