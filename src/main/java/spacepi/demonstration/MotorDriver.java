package spacepi.demonstration;

import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class MotorDriver {


	final byte firstGearByte = (byte) 0x1E; // 30
	final byte secondGearByte = (byte) 0x6E; // 110
	final byte thirdGearByte = (byte) 0x78; // 120
	final byte frontGearByte = (byte) 0x86; // 134 front

	final byte stopGearByte = (byte) 0x80; // 128
	I2CBus i2cbus;
	I2CDevice device;

	public void init() throws IOException {
		i2cbus = I2CFactory.getInstance(I2CBus.BUS_1);

		device = i2cbus.getDevice(0x58); // run "sudo i2cdetect -y 1"
											// to find that

	}

	public void updateSpeed(GearType gear) throws IOException {
		switch (gear) {
		case first:
			loadSpeedToBothMotor(firstGearByte);
			break;
		case second:
			loadSpeedToBothMotor(secondGearByte);
			break;
		case third:
			loadSpeedToBothMotor(thirdGearByte);
			break;
		case front:
			loadSpeedToBothMotor(frontGearByte);
			break;
		case stopped:
			loadSpeedToBothMotor(stopGearByte);
			break;

		default:
			break;
		}
	}

	private void loadSpeedToBothMotor(byte speed) throws IOException {
		device.write(0x0, speed);
		device.write(0x1, speed);
	}
	
	
	private void turnArround() throws IOException {

		device.write(0x0, (byte) 0x78);
		System.out.println();
		device.write(0x1, (byte) 0x86);
	}

}
