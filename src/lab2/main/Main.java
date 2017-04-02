package lab2.main;

public class Main {
	public static void main(String[] args) {
		ResourceLoader.init();
		Graph g = ResourceLoader.graphs.get("USA-highway-miles");
		g.solve(0);
		System.out.println(g.minspan);
		System.out.println(g.len);
	}
}
