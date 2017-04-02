package lab3.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ResourceLoader {
	public static final Map<String, Integer> sim = new HashMap<>();
	public static final Map<String, Map<String, Sequence>> seqs = new HashMap<>();
	public static final String SIM_MATRIX = "BLOSUM62";
	
	public static void init(){
		final File location = new File("src/lab3/resources");
		File[] resources = location.listFiles();
		for (File resource : resources) {
			try {
				String extension = resource.getAbsolutePath().replaceAll(".*(?=\\.)", "");
				String name = resource.getName().replace(extension, "");
				if (extension.equals(".txt")){
					Scanner sc = new Scanner(resource);
					switch(name){
					case SIM_MATRIX:
						readSim(sc);
						break;
					default:
						if (name.endsWith("-in")) {
							String key = name.replaceAll("\\-in$", "");
							System.out.println(key);
							seqs.put(key, read(sc));
						}
						break;
					}
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Map<String, Sequence> read(Scanner sc){
		Map<String, Sequence> filemap = new HashMap<>();
		String name = "NAN";
		Sequence s = new Sequence();
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if (line.charAt(0) == '>'){
				if (!name.equals("NAN")){
					filemap.put(name, s);
				}
				name = line.replaceFirst(">", "").replaceAll("\\ +$", "");
				s = new Sequence();
				s.s = "";
			} else {
				s.s += line;
			}
		}
		filemap.put(name, s);
		return filemap;
	}
	
	private static void readSim(Scanner sc){
		String[] tokens = null;
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if (line.charAt(0) == '#'){
				continue;
			} else if (tokens == null) {
				tokens = line.replaceAll("(^\\ +)|((?<=\\ )\\ +)", "").split("\\ ");
				String s ="";
				for (String t : tokens)
					s+=t+", ";
				System.out.println(s);
			} else {
				line = line.replaceAll("(?<=\\ )\\ +", "");
				String[] lineTokens = line.split("\\ ");
				String token = lineTokens[0];
				for (int i = 1; i<lineTokens.length; i++) {
					int simValue = Integer.parseInt(lineTokens[i]);
					sim.put(token+tokens[i-1], simValue);
				}
			}
		}
	}
}
