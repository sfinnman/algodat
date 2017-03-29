package lab1.main;
import javax.swing.JFrame;

import lab1.gui.Frame;
import lab1.gui.MainMenu;
import lab1.gui.ViewHandler;

public class Main extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main(){
		this.add(new Frame());
		this.setTitle("MatchMaker X2000");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int h = this.getInsets().top + this.getInsets().bottom;
        int w = this.getInsets().left + this.getInsets().right;
        System.out.println(w + ", " + h);
        this.setSize(Frame.WIDTH + w, Frame.HEIGHT + h);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
	}
	
	public static void main(String[] args) {
		ViewHandler.init();
		MainMenu.instance();
		new Main();
	}
}
