package spacepi.nonraspberry;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	JLabel label;
	public volatile int lastPressedKey = 0;
	String ip;
	public static JTextField leftMotor, rightMotor;
	public ArrowKeys(String ip) {
		super("Keys");
		this.ip = ip;
		JPanel p = new JPanel();
		label = new JLabel("Key Listener!");
		p.add(label);
		add(p);
		leftMotor = new JTextField("130", 3);
		rightMotor = new JTextField("128", 3);
		p.add(leftMotor);
		p.add(rightMotor);
		addKeyListener(this);
		setSize(300, 300);
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