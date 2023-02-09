/**package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
@TeleOp

public class TestColorSensor extends LinearOpMode{
    ColorSensor color;

    public void runOpMode(){
        color = hardwareMap.get(ColorSensor.class, "color");
        
        waitForStart();
        
        
        while(opModeIsActive()){
            telemetry.addData("Red", color.red());
            telemetry.addData("Green", color.green());
            telemetry.addData("Blue", color.blue());
            telemetry.update();
        }
        
        
        
        
    }

}
*/

