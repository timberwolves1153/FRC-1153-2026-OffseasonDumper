package frc.robot.subsystems.launcher.drum;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Drum extends SubsystemBase {
  private final DrumIO io;
  private final DrumIOInputsAutoLogged inputs = new DrumIOInputsAutoLogged();

  public Drum(DrumIO drumIO) {
    io = drumIO;
  }

  /* Periodic to update inputs */
  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Drum", inputs);
  }

  /* Methods */
  public void setVoltageDrum(double volts) {
    io.setVoltageDrum(volts);
  }

  public void setVelocityDrum(double velocity) {
    io.setVelocityDrum(velocity);
  }

  public void stopDrum() {
    io.stopDrum();
  }
}
