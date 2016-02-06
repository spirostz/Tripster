package spacepi;

import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class I2cTest {

	public static void main(String[] args) throws IOException {

		I2CBus i2cbus = I2CFactory.getInstance(I2CBus.BUS_1);
		
		I2CDevice device = i2cbus.getDevice(0x58); //run "sudo i2cdetect -y 1" to find that
		
		
		System.out.println("------BEFORE-");
		device.write(0x0, (byte) 0x10);
		System.out.println();
		device.write(0x1, (byte) 0x10);
		/*System.out.println(device.read(1));
		System.out.println(device.read(0));*/
		
		System.out.println("------AFTER");

	}

}
