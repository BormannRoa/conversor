package conversores;

import java.util.Scanner;

public class ConversorMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a Conversor Alura Colombia");
        System.out.println("1. Convertir de cualquier moneda a Dólares (USD)");
        System.out.println("2. Convertir de Dólares (USD) a cualquier moneda");
        System.out.print("Seleccione una opción (1 o 2): ");
        int opcion = scanner.nextInt();

        ConversorMoneda conversor = new ConversorMoneda();

        switch (opcion) {
            case 1 -> {
                System.out.print("Ingrese el código de la moneda (Ejemplo: COP, EUR, MXN): ");
                String moneda = scanner.next().toUpperCase();
                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad = scanner.nextDouble();
                double resultado = conversor.convertirMonedaAUSD(moneda, cantidad);
                System.out.printf("%.2f %s equivalen a %.2f USD.%n", cantidad, moneda, resultado);
            }
            case 2 -> {
                System.out.print("Ingrese el código de la moneda (Ejemplo: COP, EUR, MXN): ");
                String moneda = scanner.next().toUpperCase();
                System.out.print("Ingrese la cantidad en USD a convertir: ");
                double cantidad = scanner.nextDouble();
                double resultado = conversor.convertirUSDAOtraMoneda(moneda, cantidad);
                System.out.printf("%.2f USD equivalen a %.2f %s.%n", cantidad, resultado, moneda);
            }
            default -> System.out.println("Opción no válida.");
        }

        scanner.close();
    }
}
