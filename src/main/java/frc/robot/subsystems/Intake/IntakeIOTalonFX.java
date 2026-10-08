


package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;

public class IntakeIOTalonFX implements IntakeIO {

    private TalonFX deployMotor = new TalonFX(0);
    private TalonFX intakeMotor1 = new TalonFX(1);
    private TalonFX intakeMotor2 = new TalonFX(3);

    private VoltageOut voltageRequest;
    private TalonFXConfiguration intakeConfig;
    private TalonFXConfiguration deployConfig;

    private final StatusSignal<Current> deployMotorCurrent = deployMotor.getSupplyCurrent();
    private final StatusSignal<Voltage> deployMotorVoltage = deployMotor.getSupplyVoltage();
    private final StatusSignal<Current> intakeMotor1Current = intakeMotor1.getSupplyCurrent();
    private final StatusSignal<Voltage> intakeMotor1Voltage = intakeMotor1.getSupplyVoltage();
    private final StatusSignal<Current> intakeMotor2Current = intakeMotor2.getSupplyCurrent();
    private final StatusSignal<Voltage> intakeMotor2Voltage = intakeMotor2.getSupplyVoltage();

    public IntakeIOTalonFX() {
        voltageRequest = new VoltageOut(0);
        deployConfig = new TalonFXConfiguration();
        intakeConfig = new TalonFXConfiguration();

        configMotors();
    }

    public void configMotors() {
        deployConfig.CurrentLimits.SupplyCurrentLimit = 15;
        deployConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        intakeConfig.CurrentLimits.SupplyCurrentLimit = 15;
        intakeConfig.CurrentLimits.SupplyCurrentLimitEnable = true;

        deployConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        deployConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        intakeConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        intakeConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        deployMotor.getConfigurator().apply(deployConfig);
        intakeMotor1.getConfigurator().apply(intakeConfig);
        intakeMotor2.getConfigurator().apply(intakeConfig);

        BaseStatusSignal.setUpdateFrequencyForAll(
            50,
            deployMotorCurrent,
            deployMotorVoltage,
            intakeMotor1Current,
            intakeMotor1Voltage,
            intakeMotor2Current,
            intakeMotor2Voltage
        );

        deployMotor.optimizeBusUtilization();
        intakeMotor1.optimizeBusUtilization();
        intakeMotor2.optimizeBusUtilization();
    }

    @Override
    public void setDeployVoltage(double volts){
        deployMotor.setControl(voltageRequest.withOutput(volts));
    }

    @Override
    public void setIntakeVoltage(double volts){
        intakeMotor1.setControl(voltageRequest.withOutput(volts));
        intakeMotor2.setControl(voltageRequest.withOutput(volts));
    }

    @Override
    public void stopDeploy(){
        deployMotor.setControl(voltageRequest.withOutput(0));
    }

    @Override
    public void stopIntake(){
        intakeMotor1.setControl(voltageRequest.withOutput(0));
        intakeMotor2.setControl(voltageRequest.withOutput(0));
    }

}
