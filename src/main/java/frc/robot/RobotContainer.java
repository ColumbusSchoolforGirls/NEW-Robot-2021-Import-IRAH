/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import org.graalvm.compiler.lir.profiling.MoveProfilingPhase_OptionDescriptors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autocommands.BouncyBoy;
import frc.robot.autocommands.GTFO;
import frc.robot.autocommands.NothingTest;
import frc.robot.autocommands.SearchBluePathOne;
import frc.robot.autocommands.SearchBluePathTwo;
import frc.robot.autocommands.SearchRedPathOne;
import frc.robot.autocommands.SearchRedPathTwo;
import frc.robot.autocommands.Slalom;
import frc.robot.autocommands.Testing;
import frc.robot.autocommands.barrels;
import frc.robot.autocommands.firstPlanAutoMiddle;
import frc.robot.autocommands.leftStartAutoMiddle;
import frc.robot.autocommands.middleStartAutoMiddle;
import frc.robot.autocommands.straightforward;
//import frc.robot.commands.ControlSpinnerManual;
import frc.robot.commands.ConveyorManual;
//import frc.robot.commands.HookManual;
//import frc.robot.commands.LiftManual;
import frc.robot.commands.ResetGyro;
//import frc.robot.commands.Shooting;
import frc.robot.commands.TankDrive;
//import frc.robot.commands.UnwindLift;
//import frc.robot.commands.FlapManual;
import frc.robot.commands.FullSpeedAhead;
//import frc.robot.commands.DeployHook;
//import frc.robot.subsystems.ControlSpinner;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.DriveTrain;
//import frc.robot.subsystems.Flap;
//import frc.robot.subsystems.Hook;
//import frc.robot.subsystems.HookPneumatic;
//import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Limelight;
//import frc.robot.subsystems.RelayCompressor;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
 //(connects code to buttons so we can make it do stuff)

public class RobotContainer {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  // joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);  --find port number from driver station?
  // Button button = new JoystickButton(stick, buttonNumber);

  //creates the joysick in the code
  
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  SendableChooser<Command> m_chooser = new SendableChooser<>(); //this is what lets us select the autopaths

  //Changed to public static final to be able to use them across the board and in auto
  //If commands do not work b/c of this, make it private final and figure out how to 
  //pass the subsystem to the auto commands
  public final DriveTrain driveTrain = new DriveTrain();
  public final ConveyorMotors conveyorMotors = new ConveyorMotors();

  //private final Hook hook = new Hook(); 
  //private final Lift lift = new Lift();
  //private final Flap flap = new Flap();
  //public ControlSpinner controlSpinner = new ControlSpinner();
  //private final RelayCompressor relayCompressor = new RelayCompressor();
  //private final HookPneumatic hookPneumatic = new HookPneumatic();
  private final Limelight limeLight = new Limelight();
  //private final ticks ticks = new ticks(0);

  public final TankDrive TankDrive = new TankDrive(driveTrain); //tankdrive controls driving in teleop
  //the commands that run aux things
  public final ConveyorManual ConveyorManual = new ConveyorManual(0, conveyorMotors, false);
  //private final HookManual hookManual = new HookManual(0, hook);
  //private final LiftManual LiftManual = new LiftManual(0, lift);
  //true means closed
  //private final FlapManual FlapManual = new FlapManual(flap, true, false);
  //public final ControlSpinnerManual ControlSpinnerManual = new ControlSpinnerManual(controlSpinner, 0);
  //private final UnwindLift UnwindLift = new UnwindLift(lift, 0);
  private final FullSpeedAhead FullSpeedAhead = new FullSpeedAhead(driveTrain);
  //private final DeployHook DeployHook = new DeployHook(hookPneumatic, false);
  
  //auto paths for testing
  private final NothingTest m_nothing = new NothingTest();
  private final Testing m_testing = new Testing(driveTrain, conveyorMotors, limeLight);


  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:
  
  //:O I think these are the 3 things
  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());
  
  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  //joystick used for the... joysticks... on the controller
  public static Joystick driveCont = new Joystick(Global.JoystickDrive);
  public static Joystick auxCont = new Joystick(Global.JoystickAux);

  //buttons for all the normal pushy things, idk the difference between them and the "joystickAnalogButton"
  public static Button auxButtonA = new JoystickButton(auxCont, Global.BUTTON_A);
  public static Button auxButtonY = new JoystickButton (auxCont, Global.BUTTON_Y);
  public static Button auxButtonB = new JoystickButton(auxCont, Global.BUTTON_B);
  public static Button auxButtonX = new JoystickButton(auxCont, Global.BUTTON_X);
  public static Button auxStart = new JoystickButton(auxCont, Global.START);
  public static Button auxRightBumper = new JoystickButton(auxCont, Global.RIGHT_BUMPER);
  public static Button auxLeftBumper = new JoystickButton(auxCont, Global.LEFT_BUMPER);
  //ig the analogbutton is for the triggers?
  public static JoystickAnalogButton rightTrigger = new JoystickAnalogButton(driveCont, 3, .5);
  public static JoystickAnalogButton auxRightTrigger = new JoystickAnalogButton(auxCont, 3,.5);

  public RobotContainer () {
    this.configureButtonBindings(); //connects the buttons to the commands so they know when to run
   
    //technically I think this should be in configureButtonBindings but I don't think it matters?
    auxButtonA.whileHeld(new ResetGyro(driveTrain));
    //auxButtonY.whenPressed(new HookManual(1));
    //auxButtonB.whenPressed(new LiftManual(1));


    //sets the default commands (what the subsystems should be doing when they start)
    //I actually don't get what this is doing so... that's just a guess
    //they say "initializes" but? wouldn't it initialize whenever you call it why the setDefaultCommand thing
    //maybe I'm just confuzzled

    //initializes TankDrive
    driveTrain.setDefaultCommand(TankDrive);
    
    //initializes Conveyor Motors
    conveyorMotors.setDefaultCommand(ConveyorManual);

    //DO NOT INITIZIALIZE A COMMAND IF IT HAS A SOFT STOP OR SOMETHING
    //initializes Hook
    //hook.setDefaultCommand(hookManual);

    //initializes Lift
    //lift.setDefaultCommand(LiftManual);

    //initializes Flap
    //flap.setDefaultCommand(FlapManual);

    //initializes control manual
    //controlSpinner.setDefaultCommand(ControlSpinnerManual);
    
    //initializes deploy hook
    //hookPneumatic.setDefaultCommand(DeployHook);

    
    //puts the thing (m_chooser ig) that lets us choose which auto path to run
    SmartDashboard.putData("Auto mode", m_chooser);
    m_chooser.setDefaultOption("Nothing", new NothingTest()); //defaults to select nothing path...
    //addOptions adds another choosey-button thing on the dashboard
    //takes in the ("text label thing", new commandYouWantItToRun)

    // m_chooser.addOption("GTFO", new GTFO(driveTrain));
		// m_chooser.addOption("Starting in Front of Port", new firstPlanAutoMiddle(driveTrain, conveyorMotors, flap));		
		// m_chooser.addOption("Starting in Middle, Ending Middle", new middleStartAutoMiddle(driveTrain, conveyorMotors, flap));
    // m_chooser.addOption("Starting On Left, Ending Middle", new leftStartAutoMiddle(driveTrain, conveyorMotors, flap));
    
    m_chooser.addOption("Test", m_testing);
    
    m_chooser.addOption("Barrels",new barrels(driveTrain));
    m_chooser.addOption("Slalom", new Slalom(driveTrain));
    m_chooser.addOption("Search Red One", new SearchRedPathOne(driveTrain, conveyorMotors, limeLight));
    m_chooser.addOption("Search Red Two", new SearchRedPathTwo(driveTrain, conveyorMotors));
    m_chooser.addOption("Search Blue One", new SearchBluePathOne(driveTrain, conveyorMotors, limeLight));
    m_chooser.addOption("Search Blue Two", new SearchBluePathTwo(driveTrain, conveyorMotors));
    m_chooser.addOption("Bounce", new BouncyBoy(driveTrain));
    //tried moving it down cause that was what was in the documentation butttttt idk
    //that's why it's down here and up there? (I feel like we only need it down here maybe?)
    SmartDashboard.putData("Auto Mode", m_chooser);

  }
  
  //sets the buttons to run specific commands when they are held/pressed
  private void configureButtonBindings(){
    // Y on 2nd driver controller moves hook arm up
    //auxButtonY.whileHeld(new HookManual(0.6, hook));
    // A on 2nd driver controller moves hook arm down
    //auxButtonA.whileHeld(new HookManual(-0.55, hook));
    // B on 2nd driver controller moves the wench and lifts the robot
    //auxButtonB.whileHeld(new LiftManual(1, lift));
    // X on 2nd driver controller deploys the flap
    //auxButtonX.whileHeld(new FlapManual(flap, false, false));
    // Right Bumper on 2nd driver controller moves the control spinner wheel
    //auxRightBumper.whileHeld(new ControlSpinnerManual(controlSpinner, 1));
    // Left Bumper on 2nd driver controller unwinds the wench
    //auxLeftBumper.whileHeld(new UnwindLift(lift, 1));
    // Right Trigger on 1st driver controller makes robot go vroom vroom
    rightTrigger.whileHeld(new FullSpeedAhead(driveTrain));
    // Right Trigger on 2nd driver controller deploys the hook pneumatic
    //auxRightTrigger.whileHeld(new DeployHook(hookPneumatic, true));
  }
  
  //connects the chooser for autopaths on the dashboard to the actual code
  //called in robot
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

  }



