// Sergio Martínez Bataller

package main.java.com.ieseljust.psp.myshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyShell {

	public static void main(String[] args) {
		Scanner r = new Scanner(System.in);

		boolean finalizar = false;
		while (!finalizar) {

			System.out.print("# MyShell> "); // Mostrem el prompt

			String ordre = r.nextLine();
			if (ordre.equals("quit")) {
				finalizar = true;
			} else {
				executarProcess(ordre);

			}

		}
	}

	private static void executarProcess(String ordre) {

		// Creem el ProceesBuilder
		ProcessBuilder pb = new ProcessBuilder(ordre);
		Process p = null;
		try {
			// Llancem el procés
			p = pb.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("\u001B[32m" + line + "\u001B[0m");

			}
		} catch (IOException e) {
			System.out.println("\u001B[31m" + "Error: Cannot run program " + p + " El fitxer o directori no existeix"
					+ "\u001B[0m");
		}

	}

}
