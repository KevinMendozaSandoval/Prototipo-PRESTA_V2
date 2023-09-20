import java.util.ArrayList;
import java.util.List;

class Alumno {
    private String matricula;
    private String nombre;
    private List<Prestamo> prestamos;

    public Alumno(String matricula, String nombre) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.prestamos = new ArrayList<>();
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void solicitarPrestamo(Inventario inventario, Material material, int cantidad) {
        if (inventario.verificarDisponibilidad(material, cantidad)) {
            Prestamo prestamo = new Prestamo(material, cantidad, this.matricula);
            prestamos.add(prestamo);
            inventario.actualizarInventario(material, -cantidad);
            System.out.println("Solicitud de préstamo realizada por " + this.nombre);
        } else {
            System.out.println("No hay suficiente material disponible.");
        }
    }

    public void editarPrestamo(Material material, int nuevaCantidad) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getMaterial().getNombre().equals(material.getNombre())) {
                prestamo.setCantidad(nuevaCantidad);
                System.out.println("Préstamo editado para " + material.getNombre() + " (" + nuevaCantidad + " unidades).");
                return;
            }
        }
        System.out.println("No se encontró un préstamo para " + material.getNombre() + ".");
    }

  public void verPrestamosSolicitados() {
        if (!prestamos.isEmpty()) {
            System.out.println("Préstamos solicitados por el alumno " + nombre + " (Matrícula: " + matricula + "):");
            for (Prestamo prestamo : prestamos) {
                Material material = prestamo.getMaterial();
                int cantidad = prestamo.getCantidad();
                System.out.println("- " + cantidad + " " + material.getNombre());
            }
        } else {
            System.out.println("El alumno " + nombre + " (Matrícula: " + matricula + ") no tiene préstamos solicitados.");
        }
    }
}