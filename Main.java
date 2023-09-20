import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        List<Alumno> listaDeAlumnos = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        Material material1 = new Material("Laptop", 5);
        Material material2 = new Material("Motores", 7);
        Material material3 = new Material("Multímetro Digital", 6);
        Material material4 = new Material("Puntas B/C ", 15);
        Material material5 = new Material("Fuente DC", 10);

        inventario.agregarMaterial(material1);
        inventario.agregarMaterial(material2);
        inventario.agregarMaterial(material3);
        inventario.agregarMaterial(material4);
        inventario.agregarMaterial(material5);

        Encargado encargado = new Encargado(scanner);

        while (true) {
            System.out.println("----------------------");
            System.out.println("PRESTA");
            System.out.println("1. Simular como Alumno");
            System.out.println("2. Simular como Encargado");
            System.out.println("3. Salir");
            System.out.println("----------------------");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    limpiarPantalla();
                    System.out.print("Ingrese su matrícula: ");
                    String matriculaAlumno = scanner.nextLine();
                    System.out.print("Ingrese su nombre: ");
                    String nombreAlumno = scanner.nextLine();

                    Alumno alumno = new Alumno(matriculaAlumno, nombreAlumno);
                    listaDeAlumnos.add(alumno); // Agregar alumno a la lista
                    limpiarPantalla();
                    while (true) {
                        System.out.println("----------------------");
                        System.out.println("Menú de Alumno:");
                        System.out.println("1. Solicitar Préstamo");
                        System.out.println("2. Editar Préstamo");
                        System.out.println("3. Ver Préstamo");
                        System.out.println("4. Volver al menú principal");
                        System.out.println("----------------------");
                        System.out.print("Elija una opción: ");

                        int opcionAlumno = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea

                        switch (opcionAlumno) {
                            case 1:
                                limpiarPantalla();
                                System.out.print("Ingrese el nombre del material: ");
                                String nombreMaterial = scanner.nextLine();
                                System.out.print("Ingrese la cantidad: ");
                                int cantidad = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea

                                Material materialSolicitado = new Material(nombreMaterial, cantidad);
                                alumno.solicitarPrestamo(inventario, materialSolicitado, cantidad);
                                break;
                            case 2:
                                limpiarPantalla();
                                System.out.print("Ingrese el nombre del material a editar: ");
                                String nombreMaterialEditar = scanner.nextLine();
                                System.out.print("Ingrese la nueva cantidad: ");
                                int nuevaCantidad = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea

                                Material materialEditar = new Material(nombreMaterialEditar, nuevaCantidad);
                                alumno.editarPrestamo(materialEditar, nuevaCantidad);
                                break;
                            case 3:
                                limpiarPantalla();
                                alumno.verPrestamosSolicitados();
                                break;
                            case 4:
                                limpiarPantalla();
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }

                        if (opcionAlumno == 4) {
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("----------------------");
                        System.out.println("Menú Anabella:");
                        System.out.println("1. Aceptar Préstamos");
                        System.out.println("2. Agregar Material al Inventario");
                        System.out.println("3. Ver Préstamos Pendientes");
                        System.out.println("4. Devolver Material de Alumno");
                        System.out.println("5. Volver al menú principal");
                        System.out.println("----------------------");
                        System.out.print("Elija una opción: ");

                        int opcionEncargado = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea
                        limpiarPantalla();
                        switch (opcionEncargado) {
                            case 1:
                                limpiarPantalla();
                                System.out.print("Ingrese la matrícula del alumno: ");
                                String matriculaBusqueda = scanner.nextLine();
                                Alumno alumnoEncontrado = buscarAlumnoPorMatricula(matriculaBusqueda, listaDeAlumnos);

                                if (alumnoEncontrado != null) {
                                    encargado.aceptarPrestamo(alumnoEncontrado, inventario);
                                } else {
                                    System.out.println("No se encontró ningún alumno con esa matrícula.");
                                }
                                break;
                            case 2:
                                limpiarPantalla();
                                System.out.print("Ingrese el nombre del nuevo material: ");
                                String nombreMaterial = scanner.nextLine();
                                System.out.print("Ingrese la cantidad inicial: ");
                                int cantidadMaterial = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea

                                Material nuevoMaterial = new Material(nombreMaterial, cantidadMaterial);
                                encargado.agregarMaterial(inventario, nuevoMaterial);
                                break;
                            case 3:
                                limpiarPantalla();
                                encargado.verPrestamosPendientes();
                                break;
                            case 4:
                                limpiarPantalla();
                                System.out.print("Ingrese la matrícula del alumno: ");
                                String matriculaDevolucion = scanner.nextLine();
                                encargado.devolverMaterial(matriculaDevolucion);
                                break;
                            case 5:
                                limpiarPantalla();
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }

                        if (opcionEncargado == 5) {
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Cerrando");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static Alumno buscarAlumnoPorMatricula(String matricula, List<Alumno> listaAlumnos) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getMatricula().equals(matricula)) {
                return alumno;
            }
        }
        return null; // Si no se encuentra el alumno
    }

    private static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
