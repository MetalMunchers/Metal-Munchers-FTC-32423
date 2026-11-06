package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="MecanumDrive", group="Linear OpMode")
public class MecanumDrive extends LinearOpMode{
    double slowModeMultiplier = 0.5f; // slowmode multiplier (Change this to change slowmode speed)
    double smm; // slowmode multiplier (changes in code) do not set slowmode speed here.
    double turn;
    double forward;
    double strafe;
    double maxDrivePower = 1.0d;
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setDirection(DcMotorEx.Direction.FORWARD);
        frontRight.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.FORWARD);
        backRight.setDirection(DcMotorEx.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        while(opModeIsActive()) {
            turn = gamepad1.right_stick_x;
            forward = -gamepad1.left_stick_y; // y_axis is inverse on left stick
            strafe = gamepad1.left_stick_x;

            // slows down the entire drivetrain for extra precision
            if (gamepad1.right_trigger > 0.25) {
                slowModeMultiplier = smm;
            } else {
                slowModeMultiplier = 1.0f;
            }

            turn = turn * maxDrivePower;
            forward = forward * maxDrivePower;
            strafe = strafe * maxDrivePower;

            double fl = forward + turn + strafe;
            double fr = forward - turn - strafe;
            double bl = forward + turn - strafe;
            double br = forward - turn + strafe;

            double max = Math.max(
                    Math.max(Math.abs(fl), Math.abs(fr)),
                    Math.max(Math.abs(bl), Math.abs(br))
            );

            if (max > 1.0) {
                fl /= max;
                fr /= max;
                bl /= max;
                br /= max;
            }

            frontLeft.setVelocity(fl*2600*slowModeMultiplier);
            frontRight.setVelocity(fr*2600*slowModeMultiplier);
            backLeft.setVelocity(bl*2600*slowModeMultiplier);
            backRight.setVelocity(br*2600*slowModeMultiplier);

            telemetry.addData("frontLeft T/s: ", frontLeft.getVelocity());
            telemetry.addData("frontRight T/s: ", frontRight.getVelocity());
            telemetry.addData("backLeft T/s: ", backLeft.getVelocity());
            telemetry.addData("backRight T/s: ", backRight.getVelocity());
            telemetry.update();
        }
    }
}