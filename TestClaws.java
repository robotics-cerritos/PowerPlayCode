package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;




//@TeleOp

public class TestClaws extends LinearOpMode {
    private Servo claw;
    
    @Override
    
    public void runOpMode(){
        
        //Hardware Mapping
        claw = hardwareMap.servo.get("claw");
        double clawPos = 0.6;
        
        waitForStart();
        
        while(opModeIsActive()){
           
           //close CLAW
           if(gamepad1.y){
               claw.setPosition(0.73);
              
           }
           
           //open CLAW
           if(gamepad1.x){
               claw.setPosition(0.4);
               
           }
           
           if(gamepad1.a){
               clawPos-=0.1;
               claw.setPosition(clawPos);
           }
           if(gamepad1.b){
               clawPos+=0.1;
               claw.setPosition(clawPos);
           }
            
          
      telemetry.addData("claw",claw.getPosition());
      
      telemetry.update();
        }
        
    }
}
