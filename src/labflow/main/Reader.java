package labflow.main;

import java.util.Scanner;

abstract class Reader<T> {
	public Reader(){
		
	}
	
	abstract public T readFile(Scanner sc);
}
