package labshedblaire.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
public class Graph {
	public Map<String, Vertice> v;
	public String start;
	public String end;
	
	public Graph(int length) {
		v = new HashMap<>(length);
	}
	
	public void addNode(String name, boolean red){
		v.put(name, new Vertice(name, red));
	}
	
	public void addEdge(String o, String d) {
		v.get(o).add(v.get(d));
	}
	
	public List<String> none(){
		System.out.println("none() called...");
		Queue<Vertice> q = new LinkedList<>();
		Map<String, Vertice> backtrace = new HashMap<>(v.size());
		backtrace.put(start, null);
		q.add(v.get(start));
		System.out.println("Entering BFS");
		while(!q.isEmpty() && !backtrace.containsKey(end)) {
			Vertice u = q.poll();
			System.out.println("checking " + u.name);
			for(Vertice v : u) {
				if (!(backtrace.containsKey(v.name) || v.red)) {
					backtrace.put(v.name, u);
					q.add(v);
				} else if (v.name.equals(end)){
					backtrace.put(end, u);
				}
			}
		}

		System.out.println("BFS completed, Entering pathfinder");
		LinkedList<String> p = new LinkedList<>();
		p.add(end);
		while (backtrace.containsKey(p.peekFirst()) && !p.peekFirst().equals(start)){
			System.out.println(p);
			p.addFirst(backtrace.get(p.peekFirst()).name);
		}

		System.out.println("none() complete.");
		return p;
	}
	
	public boolean many(){
		System.out.println("many() called... implementation is time complex AF... ");
		for (Vertice u : v.values()) {
			u.weight = Integer.MAX_VALUE;
		}
		v.get(start).weight = 0;
		
		return false;
	}
	
	public boolean some(){
		System.out.println("Entering some...");
		return true;
	}
	
	public int few(){
		System.out.println("few() called...");
		Map<String, Vertice> backtrace = new HashMap<>(v.size());
		PriorityQueue<Vertice> q = new PriorityQueue<>(v.size());
		for (Vertice u : v.values()) {
			u.weight = Integer.MAX_VALUE;
			u.visited = false;
		}
		v.get(start).weight = 0;
		backtrace.put(start, null);
		q.addAll(v.values());
		System.out.println("Entering Djikstras");
		while(!q.isEmpty() && !backtrace.containsKey(end)) {
			Vertice u = q.poll();
			u.visited = true;
			for (Vertice v : u) {
				if (!v.visited) {
					int dist = u.weight + (v.red?1:0);
					if (dist<v.weight) {
						q.remove(v);
						v.weight = dist;
						backtrace.put(v.name, u);
						q.add(v);
					}
				}
			}
		}
		Vertice l = v.get(end);
		return l.weight - (l.red?1:0);
	}
	
	public boolean alternate() {
		System.out.println("alternate() called...");
		Queue<Vertice> q = new LinkedList<>();
		Map<String, Vertice> backtrace = new HashMap<>(v.size());
		backtrace.put(start, null);
		q.add(v.get(start));
		System.out.println("Entering BFS");
		while(!q.isEmpty() && !backtrace.containsKey(end)) {
			Vertice u = q.poll();
			System.out.println("checking " + u.name);
			for(Vertice v : u) {
				if (!(backtrace.containsKey(v.name) || v.red == u.red)) {
					backtrace.put(v.name, u);
					q.add(v);
				}
			}
		}

		System.out.println("BFS completed, Entering pathfinder");
		LinkedList<String> p = new LinkedList<>();
		p.add(end);
		while (backtrace.containsKey(p.peekFirst()) && !p.peekFirst().equals(start)){
			System.out.println(p);
			p.addFirst(backtrace.get(p.peekFirst()).name);
		}

		System.out.println("alternate() complete.");
		return backtrace.containsKey(end);
	}
	
	
	public static class Vertice extends ArrayList<Vertice> implements Comparable<Vertice>{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public final boolean red;
		public final String name;
		public int weight;
		public boolean visited = false;
		
		public Vertice(String name, boolean red){
			super();
			this.red = red;
			this.name = name;
		}
		
		public String toString(){
			return name;
		}

		public boolean equals(Object obj){
			if (obj instanceof Vertice) {
				return ((Vertice)obj).name.equals(this.name);
			} else if (obj instanceof String){
				return ((String)obj).equals(this.name);
			} else {
				return super.equals(obj);
			}
		}
		
		@Override
		public int compareTo(Vertice v) {
			return this.weight - v.weight;
		}
		
	}
}
