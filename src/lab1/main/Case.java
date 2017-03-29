package lab1.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Case {
	public String name;
	public int amount;
	public List<String> persons = new ArrayList<>();
	public List<Integer[]> preferences = new ArrayList<>();
	public Pair[] pairs;
	public long runtime;
	public long ops;
	
	
	public void solve(){
		runtime = System.nanoTime();
		Pair[] pairs = new Pair[persons.size()/2];
		int[] prefcnt = new int[persons.size()/2];
		Queue<Integer> men = new LinkedList<>();
		for (int i = 0; i< persons.size(); i += 2){
			men.add(i);
		}
		ops = persons.size()/2;
		while(!men.isEmpty()){
			int man = men.peek();
			int woman = preferences.get(man)[prefcnt[man/2]];
			int wpref = preferences.get(woman)[man];
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
			ops++;
		}
		this.pairs = pairs;
		runtime = (System.nanoTime() - runtime)/1000;
	}
	
	public static class Pair{
		public int female;
		public int femalepref;
		public int male;
	}
	
}
