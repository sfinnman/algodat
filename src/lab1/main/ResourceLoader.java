package lab1.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceLoader {
	public static Map<String, Case> testCases = new HashMap<>();
	public static Map<String, List<String>> solutions = new HashMap<>();
	private ResourceLoader(){}
	
	public static void init(){
		final File location = new File("src/lab1/resources");
		File[] resources = location.listFiles();
		for (File resource : resources) {
			try {
				String extension = resource.getAbsolutePath().replaceAll(".*(?=\\.)", "");
				String name = resource.getName().replace(extension, "");
				if (extension.equals(".txt")){
					Scanner sc = new Scanner(resource);
					String key = name.replaceAll("(\\-out)|(\\-in)", "");
					System.out.println(key);
					if (name.endsWith("in")){
						Case c = readCase(sc);
						c.name = key;
						testCases.put(key, c);
					} else {
						solutions.put(key, readSolution(sc));
					}
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Case readCase(Scanner sc){
		Case c = new Case();
		Pattern p1 = Pattern.compile("(?<=n=)\\d+");
		Pattern p2 = Pattern.compile("(?<=[0-9]\\ )[a-zA-Z0-9']+");
		Pattern p3 = Pattern.compile("(?<=[0-9]:\\ )[0-9\\ ]+");
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if (line.length()>0 && line.charAt(0) == '#') continue;
			Matcher m1 = p1.matcher(line);
			Matcher m2 = p2.matcher(line);
			Matcher m3 = p3.matcher(line);
			if (m1.find()){
				c.amount = (Integer.parseInt(m1.group(0)));
			} else if (m3.find()) {
				String[] prefs = m3.group(0).split("\\ ");
				List<Integer> iprefs = new ArrayList<>();
				for (String token : prefs){
					iprefs.add(Integer.parseInt(token)-1);
				}
				c.preferences.add(iprefs);
			} else if (m2.find()) {
				c.persons.add(m2.group(0));
			}
		}
		return c;
	}
	
	private static List<String> readSolution(Scanner sc){
		List<String> solution = new LinkedList<>();
		while(sc.hasNextLine()){
			solution.add(sc.nextLine());
		}
		solution.sort(new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
		return solution;
	}
}