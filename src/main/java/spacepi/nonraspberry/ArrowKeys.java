package spacepi.nonraspberry;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public ArrowKeys(String ip) {
		super("Keys");
		this.ip = ip;
		JPanel p = new JPanel();
		label = new JLabel("Key Listener!");
		p.add(label);
		add(p);
		addKeyListener(this);
		setSize(200, 100);
		setVisible(true);

		new Thread(new Runnable() {

			@Override
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
			InfoRS rs = send(CommandType.Right, ip);
			//System.out.println(rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && lastPressedKey != KeyEvent.VK_LEFT) {
			lastPressedKey = KeyEvent.VK_LEFT;
			InfoRS rs = send(CommandType.Left, ip);
			//System.out.println(rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && lastPressedKey != KeyEvent.VK_UP) {
			lastPressedKey = KeyEvent.VK_UP;
			InfoRS rs = send(CommandType.Front, ip);
			//System.out.println(rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && lastPressedKey != KeyEvent.VK_DOWN) {
			lastPressedKey = KeyEvent.VK_DOWN;
			InfoRS rs = send(CommandType.Back, ip);
			//System.out.println(rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && lastPressedKey != KeyEvent.VK_SPACE) {
			lastPressedKey = KeyEvent.VK_SPACE;
			InfoRS rs = send(CommandType.Reset, ip);
			System.out.println("Reset: " + rs);
		}

	}

	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_R) {
			lastPressedKey = KeyEvent.VK_R;
			InfoRS rs = send(CommandType.Read, ip);
			System.out.println("Read: " + rs);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			lastPressedKey = 0;
			send(CommandType.Stop, ip);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			lastPressedKey = 0;
			send(CommandType.Stop, ip);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			lastPressedKey = 0;
			send(CommandType.Stop, ip);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			lastPressedKey = 0;
			send(CommandType.Stop, ip);
		}
	}

	public static InfoRS send(CommandType command, String ip) {
		Gson gson = new Gson();
		String jsonRq = gson.toJson(new CommandRQ(command));

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

}