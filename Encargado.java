import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Encargado {
    private List<Alumno> alumnosConPrestamos;
    private Scanner scanner; 

    public Encargado(Scanner scanner) {
        this.alumnosConPrestamos = new ArrayList<>();
        this.scanner = scanner; 
    }

    public void aceptarPrestamo(Alumno alumno, Inventario inventario) {
        for (Prestamo prestamo : alumno.getPrestamos()) {
            Material material = prestamo.getMaterial();
            int cantidad = prestamo.getCantidad();
            inventario.actualizarInventario(material, cantidad);
            System.out.println("Préstamo aceptado: " + alumno.getNombre() + " ha recibido " + cantidad + " " + material.getNombre());
        }
        alumnosConPrestamos.add(alumno);
        alumno.getPrestamos().clear();
    }

    public void verPrestamosPendientes() {
        if (alumnosConPrestamos.isEmpty()) {
            System.out.println("No hay préstamos pendientes en este momento.");
        } else {
            System.out.println("Préstamos pendientes:");
            for (Alumno alumno : alumnosConPrestamos) {
                System.out.println("Alumno: " + alumno.getNombre() + " (Matrícula: " + alumno.getMatricula() + ")");
                System.out.println("Préstamos:");
                for (Prestamo prestamo : alumno.getPrestamos()) {
                    Material material = prestamo.getMaterial();
                    int cantidad = prestamo.getCantidad();
                    System.out.println("- " + cantidad + " " + material.getNombre());
                }
            }
        }
    }

    public void buscarPrestamosPorMatricula(String matricula) {
        System.out.println("Préstamos pendientes para el alumno con matrícula " + matricula + ":");
        for (Alumno alumno : alumnosConPrestamos) {
            if (alumno.getMatricula().equals(matricula)) {
                for (Prestamo prestamo : alumno.getPrestamos()) {
                    Material material = prestamo.getMaterial();
                    int cantidad = prestamo.getCantidad();
                    System.out.println("- " + cantidad + " " + material.getNombre());
                }
                return;
            }
        }
        System.out.println("No se encontraron préstamos para la matrícula " + matricula);
    }

    public void agregarMaterial(Inventario inventario, Material material) {
        inventario.agregarMaterial(material);
        System.out.println("Se ha agregado " + material.getCantidad() + " " + material.getNombre() + " al inventario.");
    }
//Revisar porque no funciona :(
    public void devolverMaterial(String matricula) {
        boolean alumnoEncontrado = false;

        for (Alumno alumno : alumnosConPrestamos) {
            if (alumno.getMatricula().equals(matricula)) {
                List<Prestamo> prestamos = alumno.getPrestamos();
                if (!prestamos.isEmpty()) {
                    System.out.println("Préstamos pendientes para el alumno con matrícula " + matricula + ":");
                    for (Prestamo prestamo : prestamos) {
                        Material material = prestamo.getMaterial();
                        int cantidad = prestamo.getCantidad();
                        System.out.println("- " + cantidad + " " + material.getNombre());
                    }
                    System.out.print("¿El alumno ha devuelto el material? (S/N): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("S")) {
                        prestamos.clear(); 
                        System.out.println("Préstamos eliminados para el alumno con matrícula " + matricula + ".");
                    } else {
                        System.out.println("Los préstamos no se han eliminado.");
                    }
                } else {
                    System.out.println("El alumno con matrícula " + matricula + " no tiene préstamos pendientes.");
                }
                alumnoEncontrado = true;
                break;
            }
        }

        if (!alumnoEncontrado) {
            System.out.println("No se encontró ningún alumno con esa matrícula.");
        }
    }
}
