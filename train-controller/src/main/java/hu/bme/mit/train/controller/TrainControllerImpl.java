package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Timer followSpeedTimer;

	public void TrainControllerImpl()
	{
		followSpeedTimer = new Timer();
		followSpeedTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				followSpeed();
			}
		}, 3*1000, 3*1000);

	}


	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}
	//Gets refernce speed
	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}
	//Set's speed limit
	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}
	//Enforces speed limit
	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}




}
