// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  WPI_VictorSPX leftFront = new WPI_VictorSPX(0);
  WPI_VictorSPX leftRear = new WPI_VictorSPX(15);
  WPI_VictorSPX rightFront = new WPI_VictorSPX(3);
  WPI_VictorSPX rightRear = new WPI_VictorSPX(14);
  WPI_VictorSPX launchWheel = new WPI_VictorSPX(12);
  WPI_VictorSPX feedWheel = new WPI_VictorSPX(13);

  DifferentialDrive myDrive = new DifferentialDrive(leftFront,rightFront);
  private final XboxController driverController = new XboxController(0);
  double driveLimit = 1;

  @Override
  public void robotInit() {
    leftFront.setInverted(true);
    rightFront.setInverted(false);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
    //Drivetrain Code
    if (driverController.getRightBumper()){
      driveLimit = 1;}
    else if (driverController.getLeftBumper()){
      driveLimit = .5f;
    }
    myDrive.arcadeDrive(-driverController.getLeftY()*driveLimit, driverController.getRightX()*driveLimit);

    //Launcher Code
    if(driverController.getXButton()){
      launchWheel.set(-1);
      feedWheel.set(-.2f);
    }
    else{
      launchWheel.set(0);
      feedWheel.set(0);
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
