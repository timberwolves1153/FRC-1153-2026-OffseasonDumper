package frc.robot.subsystems.launcher.drum;

import org.littletonrobotics.junction.AutoLog;

public interface DrumIO {
  @AutoLog
  public static class DrumIOInputs {
    /* Inputs */
    /* Top right motor */
    public double TRDrumVoltage = 0.0;
    public double TRDrumCurrent = 0.0;
    public double TRDrumTemp = 0.0;
    public double TRDrumVelocity = 0.0;

    /* Bottom right motor */
    public double BRDrumVoltage = 0.0;
    public double BRDrumCurrent = 0.0;
    public double BRDrumTemp = 0.0;
    public double BRDrumVelocity = 0.0;

    /* Top left motor */
    public double TLDrumVoltage = 0.0;
    public double TLDrumCurrent = 0.0;
    public double TLDrumTemp = 0.0;
    public double TLDrumVelocity = 0.0;

    /* Bottom left motor */
    public double BLDrumVoltage = 0.0;
    public double BLDrumCurrent = 0.0;
    public double BLDrumTemp = 0.0;
    public double BLDrumVelocity = 0.0;
  }

  /* Methods */
  public default void updateInputs(DrumIOInputs drumInputs) {}

  public default void setVoltageDrum(double volts) {}

  public default void setVelocityDrum(double velocity) {}

  public default void stopDrum() {}
}
