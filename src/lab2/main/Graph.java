package lab2.main;

import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
	public List<List<Edge>> graph = new ArrayList<>();
	public List<Edge> minspan;
	public int len;
	
	public void solve(int start){
		
		boolean [] vertices = new boolean[graph.size()];
		int[] weights = new int[graph.size()];
		for (int i = 0; i<weights.length; i++) {
			weights[i] = Integer.MAX_VALUE;
		}
		vertices[start] = true;
		minspan = new ArrayList<>(graph.size());
		Queue<Edge> edges = new PriorityQueue<>(graph.get(start));
		for (Edge e : graph.get(start)) {
			weights[e.to] = e.weight;
		}
		
		while(minspan.size()<(graph.size()-1)){
			Edge next = edges.poll();
			if (!vertices[next.to]){
				minspan.add(next);
				vertices[next.to] = true;
				for (Edge e : graph.get(next.to)){
					if (!vertices[e.to] && weights[e.to]>e.weight) {
						edges.add(e);
						weights[e.to] = e.weight;
					}
				}
			}
		}
		for (Edge e: minspan){
			len += e.weight;
		}
	}
	
	public static class Edge implements Comparable<Edge> {
		public String sFrom;
		public int from;
		public int weight;
		public int to;
		public String sTo;
		
		public Edge(String sFrom, int from, int weight, int to, String sTo){
			this.sFrom = sFrom;
			this.from = from;
			this.weight = weight;
			this.to = to;
			this.sTo = sTo;
		}
		
		@Override
		public String toString(){
			return sFrom + "-["+weight+"]-" + sTo;
		}
		
		@Override
		public boolean equals(Object o){
			if (o instanceof Edge){
				Edge e = (Edge)o;
				return e.to == to;
			}
			return super.equals(o);			
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
	
}
