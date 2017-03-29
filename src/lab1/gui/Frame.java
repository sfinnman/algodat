package lab1.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import lab1.utility.DrawHandler;
import lab1.utility.EventHandler;
import lab1.utility.Point;
import lab1.utility.EventHandler.EventData;

public class Frame extends JPanel implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 900;
	public static final int WIDTH = 1200;
	private Timer t;

	public Frame() {
		setFocusable(true);
		setBackground(Color.BLACK);
		addMouseListener(this);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		};
		t = new Timer();
		t.schedule(task, 40, 40);
	}

	@Override
	public void paintComponent(Graphics g) {
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics gImg = img.getGraphics();
		super.paintComponent(gImg);
		DrawHandler.instance().drawAll(gImg);
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		EventHandler.instance().triggerEvent("mouse_clicked", new EventData(this, new Point(e.getX(), e.getY())));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case (KeyEvent.VK_UP):
			EventHandler.instance().triggerEvent("key_arrow", new EventData(this, new Point(8, 0)));
			break;
		case (KeyEvent.VK_RIGHT):
			EventHandler.instance().triggerEvent("key_arrow", new EventData(this, new Point(1, 0)));
			break;
		case (KeyEvent.VK_DOWN):
			EventHandler.instance().triggerEvent("key_arrow", new EventData(this, new Point(2, 0)));
			break;
		case (KeyEvent.VK_LEFT):
			EventHandler.instance().triggerEvent("key_arrow", new EventData(this, new Point(4, 0)));
			break;
		case (KeyEvent.VK_ESCAPE):
			EventHandler.instance().triggerEvent("esc", null);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c != 8) {
			EventHandler.instance().triggerEvent("key_typed", new EventData(this, new Point(c, 0)));
		} else {
			EventHandler.instance().triggerEvent("key_backspace", null);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		EventHandler.instance().triggerEvent("mouse_moved", new EventData(this, new Point(e.getX(), e.getY())));
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		EventHandler.instance().triggerEvent("mouse_scrolled", new EventData(this, new Point(e.getUnitsToScroll(), 0)));
	}

}
