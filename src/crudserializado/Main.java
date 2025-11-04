package crudserializado;
import crudserializado.model.Persona;
import crudserializado.service.PersonaService;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PersonaService servicio = new PersonaService();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== CRUD DE PERSONAS ===");
            System.out.println("1. Crear persona");
            System.out.println("2. Leer persona");
            System.out.println("3. Listar personas");
            System.out.println("4. Actualizar persona");
            System.out.println("5. Eliminar persona");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> crearPersona();
                case 2 -> leerPersona();
                case 3 -> listarPersonas();
                case 4 -> actualizarPersona();
                case 5 -> eliminarPersona();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void crearPersona() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = sc.nextInt();
        servicio.crear(new Persona(id, nombre, edad));
        System.out.println("Persona creada correctamente.");
    }

    private static void leerPersona() {
        System.out.print("Ingrese ID: ");
        int id = sc.nextInt();
        Persona p = servicio.leer(id);
        System.out.println(p != null ? p : "No encontrada.");
    }

    private static void listarPersonas() {
        servicio.listar().forEach(System.out::println);
    }

    private static void actualizarPersona() {
        System.out.print("ID a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nueva edad: ");
        int edad = sc.nextInt();
        servicio.actualizar(new Persona(id, nombre, edad));
        System.out.println("Persona actualizada.");
    }

    private static void eliminarPersona() {
        System.out.print("ID a eliminar: ");
        int id = sc.nextInt();
        servicio.eliminar(id);
        System.out.println("Persona eliminada.");
    }
}
