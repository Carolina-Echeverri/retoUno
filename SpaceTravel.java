
//Participantes: Carolina Echeverri Correa - Alejandro Gonzalez Taborda

import java.util.Random;
import java.util.Scanner;

public class SpaceTravel {

    static Scanner scanner = new Scanner(System.in);

    // Declarar variables planetas y sus caracteriticas
    static String[] planetas = {"Marte", "Júpiter", "Saturno"};
    static int[] distancias = {139, 623, 1383}; // Distancias en millones de km

    // Declarar vaiables naves y sus caracteristicas
    static String[] naves = {"Nave Marinero", "Nave Juno", "Nave Cassini"};
    static int[] velocidades = {3000, 6000, 9000}; // Velocidades en km/h
    static int[] consumoCombustible = {1, 2, 3}; // Consumo de combustible por millón de km

    // Variables 
    static int naveSeleccionada = -1;
    static int planetaSeleccionado = -1;
    static int combustibleDisponible = 0;
    static int oxigenoDisponible = 0;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1: seleccionarPlaneta(); break;
                case 2: seleccionarNave(); break;
                case 3: calcularRecursos(); break;
                case 4: iniciarSimulacion(); break;
                case 5: System.out.println("Gracias por usar el simulador. ¡Hasta luego!"); break;
                default: System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 5);
    }

    // Imprimir el menú principal
    public static void mostrarMenu() {
        System.out.println("\n---- Menú Principal ----");
        System.out.println("1. Seleccionar un planeta");
        System.out.println("2. Seleccionar una nave espacial");
        System.out.println("3. Calcular y ajustar recursos");
        System.out.println("4. Iniciar la simulación del viaje");
        System.out.println("5. Salir");
        System.out.print("Por favor, elige una opción: ");
    }

    // Seleccionar del planeta
    public static void seleccionarPlaneta() {
        System.out.println("Selecciona un planeta: ");
        for (int i = 0; i < planetas.length; i++) {
            System.out.println((i + 1) + ". " + planetas[i] + " - Distancia: " + distancias[i] + " millones de km");
        }
        int seleccion = scanner.nextInt();
        if (seleccion > 0 && seleccion <= planetas.length) {
            planetaSeleccionado = seleccion - 1;
            System.out.println("Has seleccionado el planeta " + planetas[planetaSeleccionado] + ".");
        } else {
            System.out.println("Selección inválida.");
        }
    }

    // Selección de la nave
    public static void seleccionarNave() {
        if (planetaSeleccionado == -1) {
            System.out.println("Selecciona un planeta primero.");
            return;
        }

        System.out.println("Selecciona una nave: ");
        for (int i = 0; i < naves.length; i++) {
            System.out.println((i + 1) + ". " + naves[i] + " - Velocidad: " + velocidades[i] + " km/h");
        }
        int seleccion = scanner.nextInt();
        if (seleccion > 0 && seleccion <= naves.length) {
            naveSeleccionada = seleccion - 1;
            System.out.println("Has seleccionado la nave " + naves[naveSeleccionada] + ".");
        } else {
            System.out.println("Selección inválida.");
        }
    }

    // Calcular y registrar recursos
    public static void calcularRecursos() {
        if (planetaSeleccionado == -1 || naveSeleccionada == -1) {
            System.out.println("Selecciona un planeta y una nave.");
            return;
        }

        int distancia = distancias[planetaSeleccionado];
        int combustibleNecesario = distancia * consumoCombustible[naveSeleccionada];
        int oxigenoNecesario = distancia * 100; // 100 unidades de oxígeno por millón de km

        System.out.printf("Combustible necesario: %d, Oxígeno necesario: %d%n", combustibleNecesario, oxigenoNecesario);
        System.out.print("Ingresa la cantidad de combustible disponible: ");
        combustibleDisponible = scanner.nextInt();
        System.out.print("Ingresa la cantidad de oxígeno disponible: ");
        oxigenoDisponible = scanner.nextInt();

        System.out.println("Recursos registrados.");
    }

    // Simulación del viaje
    public static void iniciarSimulacion() {
        if (planetaSeleccionado == -1 || naveSeleccionada == -1) {
            System.out.println("Selecciona un planeta y una nave.");
            return;
        }

        int distancia = distancias[planetaSeleccionado];
        int combustibleNecesario = distancia * consumoCombustible[naveSeleccionada];
        int oxigenoNecesario = distancia * 100;
        boolean recursosSuficientes = combustibleDisponible >= combustibleNecesario && oxigenoDisponible >= oxigenoNecesario;

        Random random = new Random();

        // Iniciar el viaje
        for (int i = 10; i <= 100; i += 10) {
            System.out.println(i + "%");

            try {
                Thread.sleep(500); // Pausa para mostrar el progreso lentamente
            } catch (InterruptedException e) {
                System.out.println("Ocurrió un error al mostrar el progreso.");
            }

            // Si no hay recursos suficientes al 50%, el usuario ajusta recursos
            if (i == 50 && !recursosSuficientes) {
                System.out.println("Recursos insuficientes. ¿Qué deseas hacer?");
                System.out.printf("Te faltan %d unidades de combustible y %d unidades de oxígeno.%n", 
                                  combustibleNecesario - combustibleDisponible, oxigenoNecesario - oxigenoDisponible);
                System.out.println("1. Volver al menú principal");
                System.out.println("2. Ajustar recursos");

                int opcion = scanner.nextInt();

                if (opcion == 1) {
                    System.out.println("Volviendo al menú principal...");
                    return;
                } else if (opcion == 2) {
                    // Ajustar recursos
                    System.out.printf("Te faltan %d unidadess de combustible. Ingresa la cantidad adicional: ", combustibleNecesario - combustibleDisponible);
                    combustibleDisponible += scanner.nextInt();
                    System.out.printf("Te faltan %d unidades de oxígeno.. Ingresa la cantidad adicional: ", oxigenoNecesario - oxigenoDisponible);
                    oxigenoDisponible += scanner.nextInt();

                    // Recalcular si ahora los recursos son suficientes
                    recursosSuficientes = combustibleDisponible >= combustibleNecesario && oxigenoDisponible >= oxigenoNecesario;
                    if (!recursosSuficientes) {
                        System.out.println("Aún no tienes suficientes recursos.");
                        return;
                    }
                }
            }

            // Inconvenientes en el 40% y 80%
            if (i == 40) {
                mostrarInconveniente("¡Atención! Hay una lluvia de asteroides en tu camino.");
            } else if (i == 80) {
                mostrarInconveniente("¡Cuidado! Estás pasando cerca de un campo de radiación.");
            }

            combustibleDisponible -= consumoCombustible[naveSeleccionada];
            oxigenoDisponible -= 50; // Consumo constante

            // Si no hay recursos suficientes al final
            if (combustibleDisponible <= 0 || oxigenoDisponible <= 0) {
                System.out.println("Te has quedado sin recursos. El viaje ha fallado.");
                return;
            }
        }

        System.out.println("100% viaje finalizado. ¡Has llegado a " + planetas[planetaSeleccionado] + "!");
    }

    // Mostrar inconvenientes aleatorios
    public static void mostrarInconveniente(String mensaje) {
        System.out.println(mensaje);
    }
}
