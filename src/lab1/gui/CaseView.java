package lab1.gui;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import lab1.gui.ScrollableList.ListItem;
import lab1.main.Case;
import lab1.main.Case.Pair;
import lab1.main.ResourceLoader;

public class CaseView {

	public static void instance(Case c) {
		new MenuItem("Back", 10, 5, 100, 50, 40){
			@Override
			protected void doclick() {
				ViewHandler.popView();
			}
		};
		
		ScrollableList l = new ScrollableList(10, 50, Frame.WIDTH-20, Frame.HEIGHT-100);
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
		int i = 0;
		for (String s : result){
			l.addListItem(new ListItem(s, 300, 50+50*i, 0, 20, 20));
			i++;
		}

		i = 0;
		for (String s : ResourceLoader.solutions.get(c.name)){
			l.addListItem(new ListItem(s, 800, 50+50*i, 0, 20, 20));
			i++;
		}
	}
}
