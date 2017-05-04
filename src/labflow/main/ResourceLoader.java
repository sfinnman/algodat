package labflow.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ResourceLoader {
	public static Map<String, Object> resources = new HashMap<>();
	
	private ResourceLoader(){}
	
	public static void init(String resourcePath, Reader<? extends Object> r){
		File[] resources = new File(resourcePath).listFiles();
		for (File resource : resources) {
			String filename = resource.getName();
			Scanner sc = null;
			try{
				sc = new Scanner(resource);
				Object o = r.readFile(sc);
				ResourceLoader.resources.put(filename, o);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} finally {
				if (sc != null) {
					sc.close();
				}
			}
		}
	}
	
}
