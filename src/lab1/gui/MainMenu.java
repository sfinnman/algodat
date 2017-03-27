package lab1.gui;

public class MainMenu {

	public static void instance() {
		new MenuItem("MatchMaker X2000", 50, 50, Frame.WIDTH - 100, 50, 90){
			@Override
			public void subscribeEvents(){ //this removes clickability!
			}
			@Override
			protected void doclick() {
			}
		};
		
		new MenuItem("TestCases", 50, 150, Frame.WIDTH - 100, 75, 40) {
			@Override
			protected void doclick() {
				ViewHandler.pushView();
				CaseMenu.instance();
			}
		};
		new MenuItem("Exit", 50, 350, Frame.WIDTH - 100, 75, 40) {
			@Override
			protected void doclick() {
				System.exit(0);
			}
		};
	}

}
