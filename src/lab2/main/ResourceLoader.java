package lab2.main;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import lab2.main.Graph.Edge;

public class ResourceLoader {
	public static Map<String, Graph> graphs = new HashMap<>();
	private ResourceLoader(){}
	
	public static void init(){
		final File location = new File("src/lab2/resources");
		File[] resources = location.listFiles();
		for (File resource : resources) {
			try {
				String extension = resource.getAbsolutePath().replaceAll(".*(?=\\.)", "");
				String name = resource.getName().replace(extension, "");
				if (extension.equals(".txt")){
					Scanner sc = new Scanner(resource);
					String key = name;
					System.out.println(key);
					graphs.put(key, readGraph(sc));
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Graph readGraph(Scanner sc){
		Graph g = new Graph();
		Map<String, Integer> namemapper = new HashMap<>();
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if (line.matches("[\"\\ a-zA-Z,\\-]+")){
				String nasty = line.replaceAll("\\ +$", "");
				namemapper.put(nasty, g.graph.size());
				g.graph.add(new LinkedList<>());
			} else {
				String[] tokens = line.split("(\\-\\-)|(\\ \\[)|(\\])");
				System.out.println(tokens[0] + "," + tokens[1] + ", " + tokens[2]);
				int vertex = namemapper.get(tokens[0]);
				int weight = Integer.parseInt(tokens[2]);
				int to = namemapper.get(tokens[1]);
				g.graph.get(vertex).add(new Edge(tokens[0], vertex, weight, to, tokens[1]));
				g.graph.get(to).add(new Edge(tokens[1], to, weight, vertex, tokens[0]));
			}
		}
		return g;
	}
}
