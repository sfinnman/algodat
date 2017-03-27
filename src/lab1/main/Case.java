package lab1.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Case {
	public String name;
	public int amount;
	public List<String> persons = new ArrayList<>();
	public List<List<Integer>> preferences = new ArrayList<>();
	public Pair[] pairs;
	
	
	public void solve(){
		Pair[] pairs = new Pair[persons.size()/2];
		int[] prefcnt = new int[persons.size()/2];
		Queue<Integer> men = new LinkedList<>();
		men.addAll(preferences.get(1));
		
		while(!men.isEmpty()){
			int man = men.peek();
			int woman = preferences.get(man).get(prefcnt[man/2]);
			int wpref = preferences.get(woman).indexOf(man);
			if (pairs[woman/2] != null && pairs[woman/2].femalepref>wpref){
				men.add(pairs[woman/2].male);
				pairs[woman/2] = null;
			}
			if (pairs[woman/2] == null){
				Pair p = new Pair();
				p.female = woman;
				p.male = men.poll();
				p.femalepref = wpref;
				pairs[woman/2] =  p;
			}
			prefcnt[man/2]++;
		}
		this.pairs = pairs;
	}
	
	public static class Pair{
		public int female;
		public int femalepref;
		public int male;
	}
	
}
