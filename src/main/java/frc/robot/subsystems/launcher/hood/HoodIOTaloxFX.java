package frc.robot.subsystems.launcher.hood;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;

public class HoodIOTaloxFX implements HoodIO {
    /* Motors */
    private final TalonFX hoodMotor = new TalonFX(0); // TODO: set motor id

    /* Configs */
    private final TalonFXConfiguration hoodConfig = new TalonFXConfiguration();

    /* Voltage/position request */
    private VoltageOut voltageRequest = new VoltageOut(0);
    private MotionMagicVoltage positionRequest = new MotionMagicVoltage(0).withSlot(0);

    /* Status signals */
    private final StatusSignal<Voltage> hoodVoltage = hoodMotor.getMotorVoltage();
    private final StatusSignal<Current> hoodCurrent = hoodMotor.getSupplyCurrent();
    private final StatusSignal<Temperature> hoodTemp = hoodMotor.getDeviceTemp();
    private final StatusSignal<Angle> hoodPosition = hoodMotor.getPosition();

    private boolean isHomed = false;

    public HoodIOTaloxFX() {
        configMotors();
    }

    public void configMotors() {
        /* Config values */
        hoodConfig.CurrentLimits.SupplyCurrentLimit = 0;
        hoodConfig.CurrentLimits.SupplyCurrentLimitEnable = true;

        hoodConfig.CurrentLimits.StatorCurrentLimit = 0;
        hoodConfig.CurrentLimits.StatorCurrentLimitEnable = true; // TODO: set current limits

        hoodConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        hoodConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        /* PIDs / Motion Magic */
        var slot0Configs = hoodConfig.Slot0;
        slot0Configs.kS = 0;
        slot0Configs.kV = 0;
        slot0Configs.kA = 0;
        slot0Configs.kP = 0;
        slot0Configs.kI = 0;
        slot0Configs.kD = 0; // TODO: set these

        hoodConfig.MotionMagic.MotionMagicCruiseVelocity = 0;
        hoodConfig.MotionMagic.MotionMagicAcceleration = 0; // TODO: set these too

        /* Apply config */
        hoodMotor.getConfigurator().apply(hoodConfig);

        hoodMotor.optimizeBusUtilization();

        /* Set update frequency */
        BaseStatusSignal.setUpdateFrequencyForAll(50,hoodVoltage, hoodCurrent, hoodPosition, hoodTemp);
    }

    @Override
    public void updateInputs(HoodIOInputs hoodInputs) {
        /* Update inputs, convert to double */
        BaseStatusSignal.refreshAll(hoodVoltage, hoodCurrent, hoodPosition, hoodTemp);

        hoodInputs.hoodVoltage = hoodVoltage.getValueAsDouble();
        hoodInputs.hoodCurrent = hoodCurrent.getValueAsDouble();
        hoodInputs.hoodPosition = hoodCurrent.getValueAsDouble();
        hoodInputs.hoodTemp = hoodTemp.getValueAsDouble();
    }

    /* Methods */
    @Override
    public void setVoltageHood(double volts) {
        hoodMotor.setControl(voltageRequest.withOutput(volts));
    }

    @Override
    public void setPositionHood(double position) {
        hoodMotor.setControl(positionRequest.withPosition(position));
    }

    @Override
    public void stopHood() {
        hoodMotor.setControl(voltageRequest.withOutput(0));
    }

    public void homeHood() {
        if (!isHomed) {
        if (hoodMotor.getSupplyCurrent().getValue().in(Amps) > 0) {
            isHomed = true;
            hoodMotor.setPosition(0);
            setPositionHood(0);
        } else {
            setVoltageHood(0);
        }
    } else {
      setPositionHood(0); // TODO: set positon, current threshold and voltage
    }
  }
}
