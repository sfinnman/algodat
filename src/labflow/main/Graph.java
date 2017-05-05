package labflow.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
	
	public List<String> names;
	public List<List<Edge>> edgeList;
	public int maxflow;
	
	public Graph(int length) {
		edgeList = new ArrayList<>(length);
		names = new ArrayList<>(length);
	}
	
	public void addNode(String name){
		names.add(name);
		edgeList.add(new ArrayList<>());
	}
	
	public void addEdge(int o, int d, int w) {
		Flow f = new Flow();
		f.f = 0;
		//Notice that the flow in each edge is one object which is pointed at... This reduces lookup times for the edges dramatically.
		edgeList.get(o).add(new Edge(o, d, (w==-1)?Integer.MAX_VALUE:w, f));
		edgeList.get(d).add(new Edge(d, o, 0, f));
	}
	
	public void solve(){
		Edge[] p = new Edge[names.size()];
		maxflow = 0; 
		while (BFS(p)){
			StringBuilder sb = new StringBuilder();
			int pathflow = Integer.MAX_VALUE;
			int t = names.size()-1;
			sb.append(p[t].d);
			while (t>0) {
				pathflow = Math.min(pathflow, p[t].getCapacity());
				sb.append("<-(");
				sb.append(p[t].getCapacity());
				sb.append(")-");
				sb.append(p[t].o);
				t = p[t].o;
			}
			System.out.println(sb.toString());
			maxflow += pathflow;
			t = names.size()-1;
		    while(t>0) {
		    	p[t].addFlow(pathflow);
		    	t = p[t].o;
		    }			
		}
		
		for (List<Edge> edges : edgeList){
			for (Edge e : edges) {
				if (e.c != 0 && e.c == e.f.f) {
					System.out.println(e);
				}
			}
		}
		System.out.println(maxflow);
	}
	
	private boolean BFS(Edge[] p) {
		boolean[] visited = new boolean[names.size()];
		
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		visited[0] = true;
		while (!q.isEmpty()){
			int u = q.poll();
			List<Edge> edges = edgeList.get(u);
			for (Edge e : edges) {
				if (!visited[e.d] && e.getCapacity()>0) {
					q.add(e.d);
					visited[e.d] = true;
					p[e.d] = e;
				}
			}
		}
		return visited[visited.length-1];
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		String ls = System.lineSeparator();
		sb.append(names.size() + ls);
		for (String name : names) {
			sb.append(name + ls);
		}
		for (List<Edge> nodeList: edgeList) {
			for ( Edge e : nodeList ) {
				if (e.c != 0) {
					sb.append(e.o);
					sb.append(" ");
					sb.append(e.d);
					sb.append(" ");
					sb.append(e.c + ls);
				}
			}
		}
		return sb.toString();
	}
	
	public static class Edge {
		public final int o;
		public final int d;
		public final int c;
		public final Flow f;
		
		public Edge(int o, int d, int w, Flow f) {
			this.o = o;
			this.d = d;
			this.c = w;
			this.f = f;
		}
		
		public int getCapacity() {
			if (c == 0) {
				return f.f;
			} else {
				return c - f.f;
			}
		}
		
		public void addFlow(int f){
			if (c == 0) {
				System.out.println("Got Here!");
				this.f.f -= f;
			} else {
				this.f.f += f;
			}
		}
		
		@Override
		public String toString(){
			return o + " -> " + d; 
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Integer) {
				int dest = (int)o;
				return dest == d;
			} else if (o instanceof Edge) {
				Edge e = (Edge)o;
				return e.d == this.d;
			} else {
				return super.equals(o);
			}
		}
	}
	
	public static class Flow {
		public int f;
	}
}
