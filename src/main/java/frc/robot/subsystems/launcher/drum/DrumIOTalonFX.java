package frc.robot.subsystems.launcher.drum;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;

public class DrumIOTalonFX implements DrumIO {
  /* Motors */
  private final TalonFX TRDrumMotor = new TalonFX(0); // TODO: set ids
  private final TalonFX BRDrumMotor = new TalonFX(0);
  private final TalonFX TLDrumMotor = new TalonFX(0);
  private final TalonFX BLDrumMotor = new TalonFX(0);

  /* Configs */
  private final TalonFXConfiguration drumConfig = new TalonFXConfiguration();

  /* Voltage/Velocity request */
  private VoltageOut voltageRequest = new VoltageOut(0);
  private VelocityVoltage velocityRequest = new VelocityVoltage(0);

  /* Status signals */
  /* Top right motor */
  private final StatusSignal<Voltage> TRDrumVoltage = TRDrumMotor.getMotorVoltage();
  private final StatusSignal<Current> TRDrumCurrent = TRDrumMotor.getSupplyCurrent();
  private final StatusSignal<Temperature> TRDrumTemp = TRDrumMotor.getDeviceTemp();
  private final StatusSignal<AngularVelocity> TRDrumVelocity = TRDrumMotor.getVelocity();

  /* Bottom right motor */
  private final StatusSignal<Voltage> BRDrumVoltage = BRDrumMotor.getMotorVoltage();
  private final StatusSignal<Current> BRDrumCurrent = BRDrumMotor.getSupplyCurrent();
  private final StatusSignal<Temperature> BRDrumTemp = BRDrumMotor.getDeviceTemp();
  private final StatusSignal<AngularVelocity> BRDrumVelocity = BRDrumMotor.getVelocity();

  /* Top left motor */
  private final StatusSignal<Voltage> TLDrumVoltage = TLDrumMotor.getMotorVoltage();
  private final StatusSignal<Current> TLDrumCurrent = TLDrumMotor.getSupplyCurrent();
  private final StatusSignal<Temperature> TLDrumTemp = TLDrumMotor.getDeviceTemp();
  private final StatusSignal<AngularVelocity> TLDrumVelocity = TLDrumMotor.getVelocity();

  /* Bottom left motor */
  private final StatusSignal<Voltage> BLDrumVoltage = BLDrumMotor.getMotorVoltage();
  private final StatusSignal<Current> BLDrumCurrent = BLDrumMotor.getSupplyCurrent();
  private final StatusSignal<Temperature> BLDrumTemp = BLDrumMotor.getDeviceTemp();
  private final StatusSignal<AngularVelocity> BLDrumVelocity = BLDrumMotor.getVelocity();

  public DrumIOTalonFX() {
    configMotors();
  }

  public void configMotors() {
    /* Config values */
    drumConfig.CurrentLimits.SupplyCurrentLimit = 0;
    drumConfig.CurrentLimits.SupplyCurrentLimitEnable = true;

    drumConfig.CurrentLimits.StatorCurrentLimit = 0;
    drumConfig.CurrentLimits.StatorCurrentLimitEnable = true; // TODO: set limits

    drumConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    drumConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    /* PIDs / Motion Magic */
    var slot0Configs = drumConfig.Slot0;
    slot0Configs.kS = 0;
    slot0Configs.kV = 0;
    slot0Configs.kA = 0;
    slot0Configs.kP = 0;
    slot0Configs.kI = 0;
    slot0Configs.kD = 0; // TODO: set these

    drumConfig.MotionMagic.MotionMagicCruiseVelocity = 0;
    drumConfig.MotionMagic.MotionMagicAcceleration = 0; // TODO: set these too

    /* Apply configs */
    TRDrumMotor.getConfigurator().apply(drumConfig);
    BRDrumMotor.getConfigurator().apply(drumConfig);
    TLDrumMotor.getConfigurator().apply(drumConfig);
    BLDrumMotor.getConfigurator().apply(drumConfig);

    /* Set followers */
    BRDrumMotor.setControl(new Follower(0, MotorAlignmentValue.Aligned));
    TLDrumMotor.setControl(new Follower(0, MotorAlignmentValue.Opposed));
    BLDrumMotor.setControl(
        new Follower(0, MotorAlignmentValue.Opposed)); // TODO: set ids to top right motor id

    /* Optimize bus utilization for all motors */
    TRDrumMotor.optimizeBusUtilization();
    BRDrumMotor.optimizeBusUtilization();
    TLDrumMotor.optimizeBusUtilization();
    BLDrumMotor.optimizeBusUtilization();

    /* Set update frequency */
    BaseStatusSignal.setUpdateFrequencyForAll(
        50,
        TRDrumVoltage,
        TRDrumCurrent,
        TRDrumTemp,
        TRDrumVelocity,
        BRDrumVoltage,
        BRDrumCurrent,
        BRDrumTemp,
        BRDrumVelocity,
        TLDrumVoltage,
        TLDrumCurrent,
        TLDrumTemp,
        TLDrumVoltage,
        TLDrumVelocity,
        BRDrumVoltage,
        BLDrumCurrent,
        BLDrumTemp,
        BLDrumVelocity);
  }

  @Override
  public void updateInputs(DrumIOInputs drumInputs) {
    /* Update inputs, convert to double */
    BaseStatusSignal.refreshAll(
        TRDrumVoltage,
        TRDrumCurrent,
        TRDrumTemp,
        TRDrumVelocity,
        BRDrumVoltage,
        BRDrumCurrent,
        BRDrumTemp,
        BRDrumVelocity,
        TLDrumVoltage,
        TLDrumCurrent,
        TLDrumTemp,
        TLDrumVoltage,
        TLDrumVelocity,
        BRDrumVoltage,
        BLDrumCurrent,
        BLDrumTemp,
        BLDrumVelocity);

    drumInputs.TRDrumVoltage = TRDrumVoltage.getValueAsDouble();
    drumInputs.TRDrumCurrent = TRDrumCurrent.getValueAsDouble();
    drumInputs.TRDrumTemp = TRDrumTemp.getValueAsDouble();
    drumInputs.TRDrumVelocity = TRDrumVelocity.getValueAsDouble();

    drumInputs.BRDrumVoltage = BRDrumVoltage.getValueAsDouble();
    drumInputs.BRDrumCurrent = BRDrumCurrent.getValueAsDouble();
    drumInputs.BRDrumTemp = BRDrumTemp.getValueAsDouble();
    drumInputs.BRDrumVelocity = BRDrumVelocity.getValueAsDouble();

    drumInputs.TLDrumVoltage = TLDrumVoltage.getValueAsDouble();
    drumInputs.TLDrumCurrent = TLDrumCurrent.getValueAsDouble();
    drumInputs.TLDrumTemp = TLDrumTemp.getValueAsDouble();
    drumInputs.TLDrumVelocity = TLDrumVelocity.getValueAsDouble();

    drumInputs.BLDrumVoltage = BLDrumVoltage.getValueAsDouble();
    drumInputs.BLDrumCurrent = BLDrumCurrent.getValueAsDouble();
    drumInputs.BLDrumTemp = BLDrumTemp.getValueAsDouble();
    drumInputs.BLDrumVelocity = BLDrumVelocity.getValueAsDouble();
  }

  /* Methods */
  @Override
  public void setVoltageDrum(double volts) {
    TRDrumMotor.setControl(voltageRequest.withOutput(volts));
  }

  @Override
  public void setVelocityDrum(double velocity) {
    TRDrumMotor.setControl(velocityRequest.withVelocity(velocity));
  }

  @Override
  public void stopDrum() {
    TRDrumMotor.setControl(voltageRequest.withOutput(0));
  }
}
