package lab3.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException {
		ResourceLoader.init();
		Scanner sc = new Scanner(System.in);
		List<String> keyset = new ArrayList<>();
		keyset.addAll(ResourceLoader.seqs.keySet());
		Map<String, Sequence> scopemap = null;
		Tuple<Sequence> selection = new Tuple<>();
		String input = "";
		while (!input.equals("q")){
			if (scopemap == null){
				System.out.println("Please choose input file from list below: ");
				keyset.clear();
				keyset.addAll(ResourceLoader.seqs.keySet());
				for(int i = 0; i<keyset.size(); i++) {
					System.out.println((i+1) + ": " + keyset.get(i));
				}
				input = sc.next();
				if (input.matches("\\d+")) {
					scopemap = ResourceLoader.seqs.get(keyset.get(Integer.parseInt(input)-1));
				} else {
					System.out.println("Invalid input, please input on the format: 1");
				}
			} else {
				keyset.clear();
				keyset.addAll(scopemap.keySet());
				for(int i = 0; i<keyset.size(); i++) {
					System.out.println((i+1) + ": " + keyset.get(i));
				}
				System.out.println("Choose from the list above" + ((selection.e1 != null)?", matched against previous choice" : ", no previous selection"));
				input = sc.next();
				if (input.matches("\\d+")) {
					selection.add(scopemap.get(keyset.get(Integer.parseInt(input)-1)));
				} else {
					System.out.println("Returning to main menu");
					scopemap = null;
					selection.clear();
					continue;
				}
				if (selection.complete()) {
					System.out.println(selection.e1.solve(selection.e2));
					System.in.read();
				}
				
			}
		}
	}
	
	public static class Tuple<T> {
		public T e1;
		public T e2;
		
		public Tuple(){
			
		}
		
		public void clear(){
			e1 = null;
			e2 = null;
		}
		
		public boolean complete(){
			return e1 != null && e2 != null;
		}
		
		public void add(T e) {
			if (e1 != null) {
				e2 = e;
			} else {
				e1 = e;
			}
		}
		
	}
}
