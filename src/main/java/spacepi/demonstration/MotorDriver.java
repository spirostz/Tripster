package spacepi.demonstration;

import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import spacepi.helpers.Tools;

public class MotorDriver {

	final byte firstGearByte = Tools.intToByte(30); // 30
	final byte secondGearByte = Tools.intToByte(110); // 110 - 0x6E
	final byte thirdGearByte = Tools.intToByte(120); // 120 - 0x78
	final byte frontGearByte = Tools.intToByte(134); // 134 front - 0x86
	
	final static int  i2cAddress		= 0x58; 
	final static byte cmdReg 			= 0x10;
	final static byte speedLeftReg		= 0x00;
	final static byte speedRightReg		= 0x01;
	final static byte encoderLeftReg	= 0x02;
	final static byte encoderRightReg	= 0x06;
	final static byte voltReg			= 0x0A;
	final static byte currentLeftReg	= 0x0B;
	final static byte currentRightReg	= 0x0C;
	final static byte softwareVerReg	= 0x0D;
	final static byte accRateReg 		= 0x0E;
	final static byte modeReg			= 0x0F;
	final static byte resetReg			= 0x20;

	
	final byte stopGearByte = Tools.intToByte(128); // 128 - 0x80
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
	
	public int getEncoderLeft() throws IOException
	{
		return readEncoderArray(encoderLeftReg);
	}
	
	public int getEncoderRight() throws IOException
	{
		return readEncoderArray(encoderRightReg);
	}
	
	public void setMotorLeftSpeed(int speed) throws IOException
	{
		setMotorSpeed(speedLeftReg, speed);
	}
	
	public void resetEncoders() throws IOException
	{		
		device.write((int)cmdReg, resetReg);
	}

	private void loadSpeedToBothMotor(byte speed) throws IOException {
		device.write(0x0, speed);
		device.write(0x1, speed);
	}
	
	public void setSpeedToBothMotor(int speed) throws IOException {
		device.write(0x0, Tools.intToByte(speed));
		device.write(0x1, Tools.intToByte(speed));
	}
	
	
	public void turnArround() throws IOException {
		device.write(0x0, Tools.intToByte(136));
		device.write(0x1, Tools.intToByte(120));
	}
	
	public void turnArroundRight() throws IOException {
		device.write(0x0, Tools.intToByte(156));
		device.write(0x1, Tools.intToByte(100));
	}
	
	public void turnArroundLeft() throws IOException {
		device.write(0x0, Tools.intToByte(100));
		device.write(0x1, Tools.intToByte(156));
	}
	
	private void setMotorSpeed(byte motor, int speed) throws IOException {
		device.write((int)motor, Tools.intToByte(speed));
	}	
	
	private int readRegisterByte(byte reg) throws IOException
	{
		return (int)device.read((int)reg);
	}
	
	private int readEncoderArray(int firstRegister) throws IOException {
		int position = 0;

		int firstByte = device.read((int) firstRegister);
		int secondByte = device.read((int) firstRegister + 1);
		int thirdByte = device.read((int) firstRegister + 2);
		int fourthByte = device.read((int) firstRegister + 3);

		position = firstByte << 8 << 8 << 8;
		position |= secondByte << 8 << 8;
		position |= thirdByte << 8;
		position |= fourthByte;
		
		
		System.out.println("firstByte:" + firstByte);
		System.out.println("secondByte:" + secondByte);
		System.out.println("thirdByte:" + thirdByte);
		System.out.println("fourthByte:" + fourthByte);
		System.out.println("position: " + position);
		return position;
	}

}
