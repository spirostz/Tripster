package spacepi.demonstration;

import java.util.Scanner;

public class Joystick {

	private volatile String theChar;
	
	public Joystick(){
		new Thread(new Runnable() {
			Scanner sc = new Scanner(System.in);
			public void run() {
				while (sc.hasNext() == true) {
					theChar = sc.next();
					System.out.println(theChar);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();;
	}

	public String getTheChar() {
		return theChar;
	}
	
}
