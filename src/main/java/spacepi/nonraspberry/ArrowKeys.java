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

	public ArrowKeys(String s) {
		super(s);
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
			send(CommandType.Right);

			System.out.println("Right key pressed");
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && lastPressedKey != KeyEvent.VK_LEFT) {
			lastPressedKey = KeyEvent.VK_LEFT;
			send(CommandType.Left);
			System.out.println("Left key pressed");
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && lastPressedKey != KeyEvent.VK_UP) {
			lastPressedKey = KeyEvent.VK_UP;
			send(CommandType.Front);
			System.out.println("Up key pressed");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && lastPressedKey != KeyEvent.VK_DOWN) {
			lastPressedKey = KeyEvent.VK_DOWN;
			send(CommandType.Back);
			System.out.println("Down key pressed");
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && lastPressedKey != KeyEvent.VK_SPACE) {
			send(CommandType.Stop);
			System.out.println("Space key pressed");
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			send(CommandType.Stop);
			System.out.println("Right key Released");
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			send(CommandType.Stop);
			System.out.println("Left key Released");
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			send(CommandType.Stop);
			System.out.println("up key Released");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			send(CommandType.Stop);
			System.out.println("Down key Released");
		}
	}

	public static void send(CommandType command) {
		Gson gson = new Gson();
		String jsonRq = gson.toJson(new CommandRQ(command));

		// Object to Json
		HttpResponse<String> postResponse = null;
		try {
			postResponse = Unirest.post("http://192.168.2.5:4567/command").header("accept", "application/json")
					.header("Content-Type", "application/json").body(jsonRq).asString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		InfoRS responseMessage = gson.fromJson(postResponse.getBody(), InfoRS.class);
		System.out.println(responseMessage);
	}

}