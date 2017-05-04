package labshedblaire.main;

import java.util.Scanner;

public interface Reader<T> {
	public T readFile(Scanner sc);
}
