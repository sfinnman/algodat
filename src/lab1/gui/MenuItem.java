package lab1.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import lab1.utility.DrawHandler;
import lab1.utility.Drawable;
import lab1.utility.EventHandler;
import lab1.utility.Listener;
import lab1.utility.Point;
import lab1.utility.EventHandler.EventData;

public abstract class MenuItem implements Listener, Drawable{
	private final String text;
	public final Point p;
	public final Point size;
	private boolean focus;
	private int fsize;
	
	
	public MenuItem(String text, int x, int y, int sizex, int sizey, int fontSize){
		this.text = text;
		this.p = new Point(x, y);
		this.size = new Point(sizex, sizey);
		this.fsize = fontSize;
		subscribeEvents();
		DrawHandler.instance().register(this);
	}
	
	protected void subscribeEvents(){
		EventHandler.instance().subscribeEvent("mouse_clicked", this);
		EventHandler.instance().subscribeEvent("mouse_moved", this);
	}
	
	abstract protected void doclick();
	
	@Override
	public void onRegister(String key, EventData data) {
		switch(key) {
		case ("mouse_clicked"):
			if (data.p.isInside(p, size)) doclick();
			break;
		case ("mouse_moved"):
			focus = data.p.isInside(p, size);
			break;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		int text_size = (focus)?fsize + 6:fsize;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, text_size));
		FontMetrics fm = g2.getFontMetrics();
		int twidth = fm.stringWidth(text);
		int theight = fm.getHeight();
		g2.drawString(text, p.x + size.x/2 - twidth/2 , p.y + size.y/2 + theight/3);
	}
}
