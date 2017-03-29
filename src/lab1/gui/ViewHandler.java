package lab1.gui;

import java.util.Timer;
import java.util.TimerTask;

import lab1.utility.DrawHandler;
import lab1.utility.EventHandler;
import lab1.main.ResourceLoader;

public class ViewHandler {

	public static final int UPDATE = 5;

	private ViewHandler() {
	}

	public static void pushView() {
		EventHandler.instance().pushLayer();
		DrawHandler.instance().pushFrame();
	}

	public static void popView() {
		EventHandler.instance().popLayer();
		DrawHandler.instance().popFrame();
	}

	public static void init() {
		ResourceLoader.init();
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EventHandler.instance().triggerEvent("frame_tick", null);
			}
		}, UPDATE, UPDATE);
	}

	public static void cleanView() {
		DrawHandler.instance().clear();
		EventHandler.instance().clear();
	}
}
