// Sergio Martínez Bataller

package main.java.com.ieseljust.psp.myshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MyShell2 {

	public static void main(String[] args) throws InterruptedException {
		Scanner r = new Scanner(System.in);

		boolean finalizar = false;
		while (!finalizar) {

			System.out.print("# MyShell2> "); // Mostrem el prompt

			String ordre = r.nextLine();
			if (ordre.equals("quit")) {
				finalizar = true;
			} else {
				executarProcess(ordre);

			}

		}
	}

	// No he pogut implementar bé el mètode startpipeline, no acaba de funcionar
	// correctament.

	private static void executarProcess(String ordre) throws InterruptedException {
		List<String> comandos = Arrays.asList(ordre.split("\\|"));

		try {
			ProcessBuilder pb = new ProcessBuilder(comandos);
			Process process = pb.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errorR = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println("\u001B[32m" + linea + "\u001B[0m");
			}

			// Eixida d'errors
			String errorLinia;
			while ((errorLinia = errorR.readLine()) != null) {
				System.err.println("\u001B[31m" + "Error: " + errorLinia + "\u001B[0m");
			}
		} catch (IOException e) {
			System.err.println("\u001B[31m" + "Error: " + e.getMessage() + "\u001B[0m");
		}
	}

}
