/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class DriveManual extends CommandBase {
  private final DriveTrain m_drivetrain;
  /**
   * Creates a new DriveManual.
   */
  public DriveManual(DriveTrain m_drivetrain) {
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double speed = RobotContainer.m_driverController.getRawAxis(3) - RobotContainer.m_driverController.getRawAxis(2);
    double rotation = RobotContainer.m_driverController.getRawAxis(0);
    boolean quickTurn = speed > -0.2 && speed < 0.2;

    if( speed > -0.1 && speed < 0.1){
      speed = 0;
    }

    if( rotation > -0.1 && rotation < 0.1){
      rotation = 0;
    }
    
    m_drivetrain.curvatureDrive(-speed*.7, rotation*.7, quickTurn);

    //m_drivetrain.tankDrive(RobotContainer.m_driverController.getRawAxis(1), RobotContainer.m_driverController.getRawAxis(5));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
