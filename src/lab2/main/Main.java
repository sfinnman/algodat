package lab2.main;

public class Main {
	public static void main(String[] args) {
		ResourceLoader.init();
		Graph g = ResourceLoader.graphs.get("USA-highway-miles");
		g.solve(0);
		System.out.println(g.minspan);
		System.out.println(g.len);
		Graph g2 = ResourceLoader.graphs.get("tinyEWG-alpha");
		g2.solve(0);
		System.out.println(g2.minspan);
		System.out.println(g2.len);
		
	}
}
