package lab1.gui;

import lab1.main.ResourceLoader;

public class CaseMenu {

	public static void instance() {
		new MenuItem("Back", 10, 5, 100, 50, 40){
			@Override
			protected void doclick() {
				ViewHandler.popView();
			}
		};
		int i = 0;
		for (String currcase : ResourceLoader.testCases.keySet()){
			i++;			
			new MenuItem(currcase + " (Amount: " + ResourceLoader.testCases.get(currcase).amount + ")", 50, 50 + 50*i, Frame.WIDTH - 100, 50, 30){
				@Override
				protected void doclick() {
					ResourceLoader.testCases.get(currcase).solve();
					ViewHandler.pushView();
					CaseView.instance(ResourceLoader.testCases.get(currcase));
				}
			};
		}
	}
}
