package labshedblaire.main;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

	
	public static void main(String[] args) {
		Reader<Graph> fileReader = new Reader<Graph>(){
			@Override
			public Graph readFile(Scanner sc) {
				int[] lines = Arrays.asList(sc.nextLine().split("\\ +")).stream().mapToInt(Integer::parseInt).toArray();
				Graph g = new Graph(lines[0]);
				String[] se = sc.nextLine().split("\\ +");
				g.start = se[0];
				g.end = se[1];
				while(lines[0]-->0 && sc.hasNext()) {
					String name = sc.nextLine().replaceAll("\\ ", "");
					boolean red = name.matches(".*\\*$");
					g.addNode(name.replaceAll("\\*?$", ""), red);
				}

				while(lines[1]-->0 && sc.hasNext()) {
					String line = sc.nextLine().replaceAll("\\ ", "");
					if (line.matches(".*\\->.*")) {
						String[] tokens = line.split("\\->");
						g.addEdge(tokens[0], tokens[1]);
					} else if (line.matches(".*--.*")) {
						String[] tokens = line.split("\\-{2}");
						g.addEdge(tokens[0], tokens[1]);
						g.addEdge(tokens[1], tokens[0]);
					}
				}				
				return g;
			}
		};
		ResourceLoader.init("src/labshedblaire/resources", fileReader);
		System.out.println(((Graph)ResourceLoader.resources.get("bht.txt")).none());
		System.out.println(((Graph)ResourceLoader.resources.get("bht.txt")).alternate());
		System.out.println(((Graph)ResourceLoader.resources.get("bht.txt")).few());
		BlockingQueue<Runnable> tasks = new ArrayBlockingQueue<>(ResourceLoader.resources.size());
		for (Entry<String, Object> e : ResourceLoader.resources.entrySet()) {
			Graph g = (Graph)e.getValue();
			Runnable r = new Runnable(){
				@Override
				public void run() {
					System.out.println(((Graph)ResourceLoader.resources.get("bht.txt")).none());
					System.out.println(((Graph)ResourceLoader.resources.get("bht.txt")).alternate());
					System.out.println(((Graph)ResourceLoader.resources.get("bht.txt")).few());
				}
				
			};
			tasks.add(r);
		}
		ThreadPoolExecutor tp = new ThreadPoolExecutor(4, 8, 0, null, tasks);
		
	}
}
