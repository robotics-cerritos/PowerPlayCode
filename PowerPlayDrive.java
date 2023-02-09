package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

@TeleOp


public class PowerPlayDrive extends LinearOpMode {
  
  // Motor Variables
  private DcMotor leftFront;
  private DcMotor rightFront;
  private DcMotor leftRear;
  private DcMotor rightRear;
  private DcMotor turntableMotor;
  
  //Servo Variables
  private Servo claw;
  
  //MORE MOTORS
  private DcMotor rightSlide;
  private DcMotor leftSlide;

  //Default Speed
  private double speed = 0.4;

  //X and Y Power of Robot Chasis Movement Motors
  private double x=0;
  private double y=0;


  @Override
  public void runOpMode() {
    // HardwareMapping goes here
    // Wheels
    leftFront = hardwareMap.dcMotor.get("leftF");
    rightFront = hardwareMap.dcMotor.get("rightF");
    leftRear = hardwareMap.dcMotor.get("leftR");
    rightRear = hardwareMap.dcMotor.get("rightR");
    
    // Claws
    claw = hardwareMap.servo.get("claw");
    
    
    //Test Motor
    turntableMotor = hardwareMap.dcMotor.get("testMotor");
    
    //TWO MOTORS ON THE SURFACE THING
    rightSlide = hardwareMap.dcMotor.get("rightSlide");
    leftSlide = hardwareMap.dcMotor.get("leftSlide");
    
    rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    
    // Reversing direction goes here

    rightFront.setDirection(DcMotor.Direction.REVERSE);
    rightRear.setDirection(DcMotor.Direction.REVERSE);
    
    rightSlide.setDirection(DcMotor.Direction.REVERSE);
    //motor2.setDirection(DcMotor.Direction.REVERSE);
    
    leftSlide.setDirection(DcMotor.Direction.REVERSE);
    rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
  
    int slidesEncoderVal=20;
    
    int highJunctionVal=1700;
    int medJunctionVal=1100;
    int lowJunctionVal=850;
    
    /*int position = 1000;
    
    motor1.setTargetPosition(-position);
    motor2.setTargetPosition(position);
    motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
    motor1.setPower(0.1);
    motor2.setPower(0.1);
    */
    waitForStart();
    

    
    while (opModeIsActive()) {
      
      //GAMEPAD 1 - CHASIS MOVEMENT
      
      if(gamepad1.y){
        speed=0.7;
      }
      if(gamepad1.a){
        speed=0.4;
      }

      if(gamepad1.dpad_down){
        y=-1.0;
      }
      
      if(gamepad1.dpad_up){
        y=1.0;
      }
      
      if(gamepad1.dpad_right){
        x=1.0;
      }
      
      if(gamepad1.dpad_left){
        x=-1.0;
      }
      
      
      if(gamepad1.dpad_up==false && gamepad1.dpad_down==false){
        y=0.0;
      }
      
      if(gamepad1.dpad_left==false && gamepad1.dpad_right==false){
        x=0.0;
      }
      
      // x & y (strafing and forwards/backwards) are from left stick
      //x = -gamepad1.left_stick_x;
      //y = gamepad1.left_stick_y;
      // rotation is from right stick
      double rotation = speed*-gamepad1.right_stick_x;

      // Double array of wheel speeds
      double wheelSpeeds[] = new double[4];
      wheelSpeeds[0] = x + y + rotation;
      wheelSpeeds[1] = -x + y - rotation;
      wheelSpeeds[2] = -x + y + rotation;
      wheelSpeeds[3] = x + y - rotation;
      normalize(wheelSpeeds);

      // setting the speed of motors
      
      // Changing wheel speeds
      leftFront.setPower(speed*wheelSpeeds[0]);
      rightFront.setPower(speed*wheelSpeeds[1]);
      leftRear.setPower(speed*wheelSpeeds[2]);
      rightRear.setPower(speed*wheelSpeeds[3]);
      
      
      
      
      
      
      //GAMEPAD 2 - SCORING MECHANISM MOVEMENT
      turntableMotor.setPower(-gamepad2.right_stick_x/2);
      
      //Linear Slides
      //NEW SYSTEM
      
      if(slidesEncoderVal>1400){
        speed=0.3;
      }
      
      if(gamepad2.y){
        slidesEncoderVal=highJunctionVal;
        leftSlide.setTargetPosition(slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
        rightSlide.setTargetPosition(-slidesEncoderVal);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
      }
      
      if(gamepad2.x){
        slidesEncoderVal=medJunctionVal;
        leftSlide.setTargetPosition(slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
        rightSlide.setTargetPosition(-slidesEncoderVal);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
      }
      
      
      if(gamepad2.b){
        slidesEncoderVal=lowJunctionVal;
        leftSlide.setTargetPosition(slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
        rightSlide.setTargetPosition(-slidesEncoderVal);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
      }
      
      if(gamepad2.a){
        slidesEncoderVal=10;
        leftSlide.setTargetPosition(slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
        rightSlide.setTargetPosition(-slidesEncoderVal);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
      }
      
      if(gamepad2.dpad_up){
        slidesEncoderVal+=10;
        leftSlide.setTargetPosition(slidesEncoderVal);
        rightSlide.setTargetPosition(-slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
        
      }
      if(gamepad2.dpad_down){
        slidesEncoderVal-=10;
        leftSlide.setTargetPosition(slidesEncoderVal);
        rightSlide.setTargetPosition(-slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
      }
      
      

      //OPEN CLAW
      if(gamepad2.left_bumper){
        claw.setPosition(0.60);
      }
           
      //CLOSE CLAW
      if(gamepad2.right_bumper){
        claw.setPosition(0.88);
      }
      
      
      
      
      //Telemetry Data
      telemetry.addData("Slide Pos 1", rightSlide.getCurrentPosition() );
      telemetry.addData("Slide Pos 2", leftSlide.getCurrentPosition() );
      telemetry.addData("Slide Encoder Val: ", slidesEncoderVal);
      telemetry.addData("Turntable Pos", turntableMotor.getCurrentPosition());
      telemetry.addData("Claw Pos", claw.getPosition());
      telemetry.addData("motor 1 power", rightSlide.getPower());
      telemetry.addData("trigger", gamepad1.left_trigger);
      telemetry.addData("dpadU", gamepad1.dpad_up);
      telemetry.addData("dpadD", gamepad1.dpad_down);
      telemetry.update();
      
      
      
    }

  }

  private void normalize(double[] wheelSpeeds) {
    // Find the maximum wheel speed
    double maxMagnitude = Math.abs(wheelSpeeds[0]);
    for (int i = 1; i < wheelSpeeds.length; i++) {
      double magnitude = Math.abs(wheelSpeeds[i]);
      if (magnitude > maxMagnitude) {

        maxMagnitude = magnitude;
      }
    }
    // If the maximum wheel speed is greater than 1
    // then divide all the wheel speeds by the maximum wheel speed value
    if (maxMagnitude > 1.0) {
      for (int i = 0; i < wheelSpeeds.length; i++) {
        wheelSpeeds[i] /= maxMagnitude;
      }
    }
  } // normalize

}
