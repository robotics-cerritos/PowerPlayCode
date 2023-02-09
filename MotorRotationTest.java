package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//@TeleOp

public class MotorRotationTest extends LinearOpMode{
  private DcMotor testMotor;
  private ElapsedTime runtime= new ElapsedTime();
  
  @Override
  public void runOpMode(){
    testMotor = hardwareMap.dcMotor.get("testMotor");
    
    double PPR = 537.7;
    double gearRatio = 19.2;
    double motorTicks = (1/gearRatio) * PPR;
    
    
    
    testMotor.setTargetPosition((int)motorTicks);
    
    
    testMotor.setPower(0.01);
    waitForStart();
    
    
    
    
    
    
    
    while(opModeIsActive()&&testMotor.isBusy()){
      
      
      
      /*
      while(opModeIsActive()&&runtime.seconds()<=0.2){
        testMotor.setPower(1);
        telemetry.addData("Runtime: ",runtime );
      }
      runtime.reset();
      
      while(opModeIsActive()&&runtime.seconds()<=0.2){
        testMotor.setPower(0);
        telemetry.addData("Runtime: ",runtime );

      }
      runtime.reset();
      */
      
      
      
      /*
      
      double desiredRotations = 1;
      double gearRatio = 19.2;
      double oldEncPos = testMotor.getCurrentPosition();
      double targetEncPos = oldEncPos + (537.7*gearRatio*desiredRotations);
      
      
      
        while(opModeIsActive() && oldEncPos < targetEncPos){
          testMotor.setPower(1);
        }
        testMotor.setPower(0);
      */
        
    }
    testMotor.setPower(0);
    
    
    
  }
}
