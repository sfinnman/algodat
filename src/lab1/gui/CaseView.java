package lab1.gui;

import java.awt.Color;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lab1.gui.ScrollableList.ListItem;
import lab1.gui.MenuItem.MenuItemNC;
import lab1.main.Case;
import lab1.main.Case.Pair;
import lab1.main.ResourceLoader;
import lab1.utility.EventHandler;
import lab1.utility.EventHandler.EventData;

public class CaseView {

	public static void instance(Case c) {
		new MenuItem("Back", 10, 5, 100, 50, 40){
			@Override
			protected void doclick() {
				ViewHandler.popView();
			}
		};
		
		new MenuItemNC(c.name, Frame.WIDTH/2, 5, 0, 50, 40);
		
		ScrollableList l = new ScrollableList(Frame.WIDTH/3, 150, Frame.WIDTH*2/3, Frame.HEIGHT-200){
			private Queue<ListItem[]> itemQueue = new LinkedList<>();
			
			@Override
			public void addListItem(ListItem... item){
				itemQueue.add(item);
			}
			
			@Override
			public void subscribeEvents(){
				super.subscribeEvents();
				EventHandler.instance().subscribeEvent("frame_tick", this);
			}
			
			@Override
			public void onRegister(String key, EventData data) {
				super.onRegister(key, data);
				if (key.equals("frame_tick")){
					if (!itemQueue.isEmpty()){
						super.addListItem(itemQueue.poll());
					}
				}
			}
		};
		
		List<String> result = new LinkedList<>();
		for (Pair p : c.pairs){
			result.add(c.persons.get(p.male) + " -- " + c.persons.get(p.female));
		}
		result.sort(new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
		List<String> solution = ResourceLoader.solutions.get(c.name);
		
		new MenuItemNC("Result", l.p.x + l.size.x/3, l.p.y-20, 0, 0, 40);
		new MenuItemNC("Solution", l.p.x + l.size.x*2/3, l.p.y-20, 0, 0, 40);
		System.out.println(l.size);
		int correct = 0;
		int incorrect = 0;
		for (int i = 0; i<solution.size(); i++) {
			Color col;
			if (result.get(i).equals(solution.get(i))) {
				col = Color.GREEN;
				correct++;
			} else {
				col = Color.RED;
				incorrect++;
			}
			ListItem lir = new ListItem(result.get(i), l.size.x/3, 10+50*i, 0, 20, 18);
			lir.setColor(col);
			ListItem lis = new ListItem(solution.get(i), (l.size.x*2)/3, 10+50*i, 0, 20, 18);
			lis.setColor(col);
			l.addListItem(lir, lis);
		}
		
		new MenuItemNC("Result Statistics", Frame.WIDTH/6, l.p.y-20, 0, 0, 40);
		
		new MenuItemNC("Successful: " + correct, Frame.WIDTH/6, 180, 0, 0, 20);
		
		new MenuItemNC("Failed: " + incorrect, Frame.WIDTH/6, 200, 0, 0, 20);
		
		new MenuItemNC("Successrate: " + (correct*100)/(incorrect + correct) + "%", Frame.WIDTH/6, 220, 0, 0, 20);
		
		new MenuItemNC("Runtime: " + c.runtime/1000.0 + "ms", Frame.WIDTH/6, 240, 0, 0, 20);
		
		new MenuItemNC("Operations: " + c.ops, Frame.WIDTH/6, 260, 0, 0, 20);
		
		
	}
}
