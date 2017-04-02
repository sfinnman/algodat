package lab3.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		ResourceLoader.init();
		String scope = "";
		Scanner sc = new Scanner(System.in);
		List<String> keyset = new ArrayList<>();
		keyset.addAll(ResourceLoader.seqs.keySet());
		String input = "";
		while (!input.equals("q")){
			if (scope.equals("")){
				System.out.println("Please choose input file from list below: ");
				for(int i = 0; i<keyset.size(); i++) {
					System.out.println((i+1) + ": " + keyset.get(i));
				}
				input = sc.next();
				if (input.matches("\\d+")) {
					scope = keyset.get(Integer.parseInt(input)-1);
				} else {
					scope = "";
					System.out.println("Invalid input, please input on the format: 1");
				}
			} else {
				Map<String, Sequence> scopemap = ResourceLoader.seqs.get(scope); 
				List<String> stringset = new ArrayList<>();
				stringset.addAll(scopemap.keySet());
				for(int i = 0; i<stringset.size(); i++) {
					System.out.println((i+1) + ": " + stringset.get(i));
				}
				System.out.println("Choose the first choice from the list above");
				input = sc.next();
				Sequence s1 = null;
				if (input.matches("\\d+")) {
					s1 = scopemap.get(stringset.get(Integer.parseInt(input)-1));
				} else {
					scope = "";
					System.out.println("Invalid input, please input on the format: 1");
					continue;
				}
				
				System.out.println("Choose the second choice from the list above");
				input = sc.next();
				Sequence s2 = null;
				if (input.matches("\\d+")) {
					s2 = scopemap.get(stringset.get(Integer.parseInt(input)-1));
				} else {
					scope = "";
					System.out.println("Invalid input, please input on the format: 1");
					continue;
				}
				
				System.out.println(s1.solve(s2));
				
				
			}
		}
	}
}
