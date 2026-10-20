package juego;

import java.util.Random;
import java.util.Scanner;

public class Ahorcado {

		// TODO Esbozo de método generado automáticamente
		
		private static final String[] PALABRAS = {
		        "programacion", "java", "ordenador", "teclado", "variable",
		        "git", "algoritmo", "servidor", "compilador", "bucle", "conseguir", "acudir", "preparacion"
		    };
		    private static final int MAX_FALLOS = 8;

		    public static void main(String[] args) {
		        Scanner sc = new Scanner(System.in);
		        Random random = new Random();
		        boolean seguirJugando = true;

		        System.out.println("=== JUEGO DEL AHORCADO ===");

		        while (seguirJugando) {
		            String palabra = PALABRAS[random.nextInt(PALABRAS.length)];
		            jugarRonda(palabra, sc);

		            System.out.print("¿Deseas jugar otra vez? (s/n): ");
		            String respuesta = sc.nextLine().trim().toLowerCase();
		            seguirJugando = respuesta.equals("s");
		        }

		        System.out.println("¡Nos vemos pronto!");
		        sc.close();
		    }

		    private static void jugarRonda(String palabra, Scanner sc) {
		        StringBuilder oculta = new StringBuilder("_".repeat(palabra.length()));
		        StringBuilder letrasUsadas = new StringBuilder();
		        int fallos = 0;

		        while (fallos < MAX_FALLOS && oculta.indexOf("_") != -1) {
		            System.out.println("\n" + dibujo(fallos));
		            System.out.println("Palabra: " + oculta.toString().replace("", " ").trim());
		            System.out.println("Letras usadas: " + letrasUsadas);
		            System.out.print("Introduce una letra: ");

		            String entrada = sc.nextLine().trim().toLowerCase();

		            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
		                System.out.println("Introduce una sola letra.");
		                continue;
		            }

		            char letra = entrada.charAt(0);

		            if (letrasUsadas.indexOf(String.valueOf(letra)) != -1) {
		                System.out.println("Introduce otra letra, esa ya la has usado");
		                continue;
		            }

		            letrasUsadas.append(letra).append(" ");

		            if (palabra.indexOf(letra) != -1) {
		                for (int i = 0; i < palabra.length(); i++) {
		                    if (palabra.charAt(i) == letra) {
		                        oculta.setCharAt(i, letra);
		                    }
		                }
		                System.out.println("¡Bien! La letra es correcta");
		            } else {
		                fallos++;
		                System.out.println("La letra no es correcta. Fallos: " + fallos + "/" + MAX_FALLOS);
		            }
		        }

		        System.out.println("\n" + dibujo(fallos));
		        if (oculta.indexOf("_") == -1) {
		            System.out.println("¡Enhorabuena! Has ganado: " + palabra + ", " + fallos + "/" + MAX_FALLOS + ".");
		        } else {
		            System.out.println("Has perdido. La palabra era: " + palabra + ", " + fallos + "/" + MAX_FALLOS + ".");
		        }
		    }

		    private static String dibujo(int fallos) {
		        String[] dibujos = {
		            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
		            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
		            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
		            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
		            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
		            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
		            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
		        };
		        return dibujos[fallos];

	}

}
