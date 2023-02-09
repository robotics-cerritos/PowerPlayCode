package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

//@TeleOp


public class EncoderTest extends LinearOpMode {
  
  // Motor Variables
  private DcMotor leftFront;
  private DcMotor rightFront;
  private DcMotor leftRear;
  private DcMotor rightRear;
  private DcMotor turntableMotor;
  
  //Servo Variables
  private Servo claw;
  
  //MORE MOTORS
  private DcMotor rightSlide; //Right Side
  private DcMotor leftSlide; //Left Side

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
    
    leftSlide.setDirection(DcMotor.Direction.REVERSE);
    
    rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    turntableMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    
    leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    
    int slidesEncoderVal=0;
    
    int highJunctionVal=1700;
    int medJunctionVal;
    int lowJunctionVal;

    int turntableEncoderVal=0;
    
      
    
    waitForStart();
    
    
    
    

    while (opModeIsActive()) {
    
      /*if(gamepad1.y){
        slidesEncoderVal+=10;
        leftSlide.setTargetPosition(slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
        rightSlide.setTargetPosition(slidesEncoderVal);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
      }
      
      
      if(gamepad1.x){
         slidesEncoderVal-=5;
         leftSlide.setTargetPosition(slidesEncoderVal);
         leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
         rightSlide.setTargetPosition(slidesEncoderVal);
         rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         leftSlide.setPower(0.3);
         rightSlide.setPower(0.3);
        
      }

      
       if(gamepad1.b){
         slidesEncoderVal+=1;
         leftSlide.setTargetPosition(slidesEncoderVal);
         leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
         rightSlide.setTargetPosition(slidesEncoderVal);
         rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         leftSlide.setPower(0.3);
         rightSlide.setPower(0.3);
      }*/


      if(gamepad1.x){
        turntableEncoderVal+=10;
        turntableMotor.setTargetPosition(turntableEncoderVal);
        turntableMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turntableMotor.setPower(0.3);
      }
      if(gamepad1.b){
        turntableEncoderVal-=10;
        turntableMotor.setTargetPosition(turntableEncoderVal);
        turntableMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turntableMotor.setPower(0.3);
      }
      if(gamepad1.y){
        turntableEncoderVal+=1;
        turntableMotor.setTargetPosition(turntableEncoderVal);
        turntableMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turntableMotor.setPower(0.3);
      }
      if(gamepad1.a){
        turntableEncoderVal-=1;
        turntableMotor.setTargetPosition(turntableEncoderVal);
        turntableMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turntableMotor.setPower(0.3);
      }
  
      
      //turntableMotor.setPower(-gamepad1.right_stick_x/2);
      
      /*if(gamepad1.x){
        slidesEncoderVal+=10;
        leftSlide.setTargetPosition(slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setTargetPosition(slidesEncoderVal);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setPower(0.3);
        
      }
      
      if(gamepad1.y){
        slidesEncoderVal-=10;
        leftSlide.setTargetPosition(slidesEncoderVal);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.3);
        rightSlide.setTargetPosition(slidesEncoderVal);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setPower(0.3);
        
      }*/
      
      
    
     
      //Telemetry Data
      telemetry.addData("Slide Encoder Value:", leftSlide.getCurrentPosition());
      telemetry.addData("Slide variable Val: ", slidesEncoderVal);
      telemetry.addData("gamepad1.right_stick_x",gamepad1.right_stick_x);
      telemetry.addData("Turntable Motor Value: ", turntableEncoderVal);
      telemetry.addData("button", gamepad1.x);
      telemetry.addData("TurntableIsBusy:", turntableMotor.isBusy());
      telemetry.addData("EncoderVariableVal:", slidesEncoderVal);
      telemetry.addData("encoderAddButton:",gamepad1.y);
      telemetry.update();
      
      idle();
      
    }

  }
  

}
