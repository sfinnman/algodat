package lab1.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import lab1.utility.DrawHandler;
import lab1.utility.Drawable;
import lab1.utility.EventHandler;
import lab1.utility.Point;
import lab1.utility.EventHandler.EventData;
import lab1.utility.Listener;

public class ScrollableList implements Drawable, Listener{
	public final Point p;
	public final Point size;
	private int maxdy = 0;
	private int dy;
	private boolean focus;
	private List<ListItem> listItems = new ArrayList<>();
	
	
	public ScrollableList(int x, int y, int sizex, int sizey){
		this.p = new Point(x, y);
		this.size = new Point(sizex, sizey);
		subscribeEvents();
		DrawHandler.instance().register(this);
	}
	
	public void addListItem(ListItem... items) {
		for (ListItem item : items){
			listItems.add(item);
			int at = item.p.y + item.size.y - this.size.y;
			dy = (at<0)?0:at;
			if (at > maxdy) {
				maxdy = at;
			}
		}
	}
	
	protected void subscribeEvents(){
		EventHandler.instance().subscribeEvent("mouse_moved", this);
		EventHandler.instance().subscribeEvent("mouse_scrolled", this);
	}
	
	@Override
	public void onRegister(String key, EventData data) {
		switch(key) {
		case ("mouse_moved"):
			focus = data.p.isInside(p, size);
			break;
		case ("mouse_scrolled"):
			if (focus){
				dy += data.p.x*13;
			}
			if (dy < 0 || maxdy<this.size.y) {
				dy = 0;
			} else if (dy > maxdy){
				dy = maxdy;
			}
			break;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		BufferedImage img = new BufferedImage(size.x, size.y, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics gImg = img.getGraphics();
		for (int i = 0; i<listItems.size(); i++){
			listItems.get(i).draw(gImg, dy);
		}
		g.drawImage(img, p.x, p.y, null);
	}
	
	public static class ListItem{
		private final String text;
		private final int text_size;
		private final Point p;
		private final Point size;
		private Color c;
		
		public ListItem(String text, int x, int y, int sizex, int sizey, int text_size){
			this.text = text;
			this.text_size = text_size;
			this.p = new Point(x, y);
			this.size = new Point(sizex, sizey);
			c = Color.WHITE;
		}
		
		public void setColor(Color c){
			this.c = c;
		}
		
		public void draw(Graphics g, int dy){
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(c);
			g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, text_size));
			FontMetrics fm = g2.getFontMetrics();
			int twidth = fm.stringWidth(text);
			int theight = fm.getHeight();
			g2.drawString(text, p.x + size.x/2 - twidth/2 , p.y-dy + size.y/2 + theight/3);
		}
	}
}
