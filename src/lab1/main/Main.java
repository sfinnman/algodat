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
		add(new Frame());
		setTitle("MatchMaker X2000");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Frame.WIDTH + 6, Frame.HEIGHT + 26);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
	}
	
	public static void main(String[] args) {
		ViewHandler.init();
		MainMenu.instance();
		new Main();
	}
}
