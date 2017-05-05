package labflow.main;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Reader<Graph> fileReader = new Reader<Graph>(){
			@Override
			public Graph readFile(Scanner sc) {
				int lines = Integer.parseInt(sc.nextLine());
				Graph g = new Graph(lines);
				
				while(lines-->0 && sc.hasNext()) {
					String name = sc.nextLine();
					g.addNode(name);
				}
				lines = Integer.parseInt(sc.nextLine());

				while(lines-->0 && sc.hasNext()) {
					int[] tokens = Arrays.asList(sc.nextLine().split("\\ +")).stream().mapToInt(Integer::parseInt).toArray();
					g.addEdge(tokens[0], tokens[1], tokens[2]);
				}
				
				System.out.println(g);
				
				return g;
			}
		};
		ResourceLoader.init("src/labflow/resources", fileReader);
		Graph g = ((Graph)ResourceLoader.resources.get("rail.txt"));
		g.solve();
	}
}
