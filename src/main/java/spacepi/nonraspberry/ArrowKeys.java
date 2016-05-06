package spacepi.nonraspberry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import spacepi.model.CommandRQ;
import spacepi.model.CommandType;
import spacepi.model.InfoRS;

public class ArrowKeys extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CartesianPanel panel;
	JLabel label;
	public volatile int lastPressedKey = 0;
	String ip;
	public static JTextField leftMotor, rightMotor;
	
	public ArrowKeys(String ip) {
		super("Keys");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));		
		add(mainPanel);
		
		this.ip = ip;
		JPanel p = new JPanel();
		
		label = new JLabel("Key Listener!");
		p.add(label);
		mainPanel.add(p);
		leftMotor = new JTextField("130", 3);
		rightMotor = new JTextField("128", 3);
		leftMotor.setFocusTraversalKeysEnabled(false);
		rightMotor.setFocusTraversalKeysEnabled(false);
		p.add(leftMotor);
		p.add(rightMotor);
		addKeyListener(this);
		leftMotor.addKeyListener(this);
		rightMotor.addKeyListener(this);
		
		panel = new CartesianPanel();
		mainPanel.add(panel);
		
		setSize(1400, 700);
		setVisible(true);

		new Thread(new Runnable() {

			//@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					lastPressedKey = 0;
				}
			}
		}).start();
		;

	}

	public void keyTyped(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("Right key typed");
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("Left key typed");
		}

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT && lastPressedKey != KeyEvent.VK_RIGHT) {
			lastPressedKey = KeyEvent.VK_RIGHT;
			InfoRS rs = send(new CommandRQ(CommandType.Right), ip);
			//System.out.println(rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && lastPressedKey != KeyEvent.VK_LEFT) {
			lastPressedKey = KeyEvent.VK_LEFT;
			InfoRS rs = send(new CommandRQ(CommandType.Left), ip);
			//System.out.println(rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && lastPressedKey != KeyEvent.VK_UP) {
			lastPressedKey = KeyEvent.VK_UP;
			InfoRS rs = send(new CommandRQ(CommandType.Front), ip);
			//System.out.println(rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && lastPressedKey != KeyEvent.VK_DOWN) {
			lastPressedKey = KeyEvent.VK_DOWN;
			InfoRS rs = send(new CommandRQ(CommandType.Back), ip);
			//System.out.println(rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && lastPressedKey != KeyEvent.VK_SPACE) {
			lastPressedKey = KeyEvent.VK_SPACE;
			InfoRS rs = send(new CommandRQ(CommandType.Reset), ip);
			System.out.println("Reset: " + rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER && lastPressedKey != KeyEvent.VK_ENTER) {
			lastPressedKey = KeyEvent.VK_ENTER;
			CommandRQ commandRQ = new CommandRQ(CommandType.Round);
			commandRQ.setLeftMotorSpeed(readLeftMotorField());
			commandRQ.setRightMotorSpeed(readRightMotorField());
			InfoRS rs = send(commandRQ, ip);
			System.out.println("Round: " + rs);
		}

	}

	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_R) {
			lastPressedKey = KeyEvent.VK_R;
			InfoRS rs = send(new CommandRQ(CommandType.Read), ip);
			System.out.println("Read: " + rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			lastPressedKey = 0;
			send(new CommandRQ(CommandType.Stop), ip);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			lastPressedKey = 0;
			send(new CommandRQ(CommandType.Stop), ip);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			lastPressedKey = 0;
			send(new CommandRQ(CommandType.Stop), ip);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			lastPressedKey = 0;
			send(new CommandRQ(CommandType.Stop), ip);
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			lastPressedKey = 0;
			send(new CommandRQ(CommandType.Stop), ip);
		} 
	}

	public static InfoRS send(CommandRQ commandRq, String ip) {
		Gson gson = new Gson();
		String jsonRq = gson.toJson(commandRq);

		// Object to Json
		HttpResponse<String> postResponse = null;
		try {
			postResponse = Unirest.post("http://" + ip + ":4567/command").header("accept", "application/json")
					.header("Content-Type", "application/json").body(jsonRq).asString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gson.fromJson(postResponse.getBody(), InfoRS.class);
	}
	
	
	private static int readLeftMotorField(){
		return Integer.valueOf(leftMotor.getText());
	}
	
	private static int readRightMotorField(){
		return Integer.valueOf(rightMotor.getText());
	}

	
}

class CartesianPanel extends JPanel {
	 // x-axis coord constants
	 public static final int X_AXIS_FIRST_X_COORD = 50;
	 public static final int X_AXIS_SECOND_X_COORD = 600;
	 public static final int X_AXIS_Y_COORD = 600;
	 
	 // y-axis coord constants
	 public static final int Y_AXIS_FIRST_Y_COORD = 50;
	 public static final int Y_AXIS_SECOND_Y_COORD = 600;
	 public static final int Y_AXIS_X_COORD = 50;
	 
	 //arrows of axis are represented with "hipotenuse" of 
	 //triangle
	 // now we are define length of cathetas of that triangle
	 public static final int FIRST_LENGHT = 10;
	 public static final int SECOND_LENGHT = 5;
	 
	 // size of start coordinate lenght
	 public static final int ORIGIN_COORDINATE_LENGHT = 6;
	 
	 // distance of coordinate strings from axis
	 public static final int AXIS_STRING_DISTANCE = 20;
	 
	 
	 public void paintComponent(Graphics g) {
		 
		 super.paintComponent(g);
		  
		  Graphics2D g2 = (Graphics2D) g;
		  
		  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		    RenderingHints.VALUE_ANTIALIAS_ON);
		  
		  // x-axis
		  g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD,
		     X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		  // y-axis
		  g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD,
		     Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);
		  
		  // x-axis arrow
		  g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
		     X_AXIS_Y_COORD - SECOND_LENGHT,
		     X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		  g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
		    X_AXIS_Y_COORD + SECOND_LENGHT,
		    X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		  
		  // y-axis arrow
		  g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,
		     Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
		     Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
		  g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT, 
		     Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
		     Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
		  
		  // draw origin Point
		  g2.fillOval(
		    X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2), 
		    Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
		    ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);
		  
		// draw text "X" and draw text "Y"
		  g2.drawString("X", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2,
		     X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		  g2.drawString("Y", Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
		     Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);
		  g2.drawString("(0, 0)", X_AXIS_FIRST_X_COORD - AXIS_STRING_DISTANCE,
		     Y_AXIS_SECOND_Y_COORD + AXIS_STRING_DISTANCE);
		  
		  // numerate axis
		  int xCoordNumbers = 10;
		  int yCoordNumbers = 10;
		  int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)
		      / xCoordNumbers;
		  int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)
		      / yCoordNumbers;
		  
		  // draw x-axis numbers
		  for(int i = 1; i < xCoordNumbers; i++) {
		   g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
		     X_AXIS_Y_COORD - SECOND_LENGHT,
		     X_AXIS_FIRST_X_COORD + (i * xLength),
		     X_AXIS_Y_COORD + SECOND_LENGHT);
		   g2.drawString(Integer.toString(i), 
		     X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
		     X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		  }
		  
		//draw y-axis numbers
		  for(int i = 1; i < yCoordNumbers; i++) {
		   g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,
		     Y_AXIS_SECOND_Y_COORD - (i * yLength), 
		     Y_AXIS_X_COORD + SECOND_LENGHT,
		     Y_AXIS_SECOND_Y_COORD - (i * yLength));
		   g2.drawString(Integer.toString(i), 
		     Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, 
		     Y_AXIS_SECOND_Y_COORD - (i * yLength));
		  }
	}
}

 

