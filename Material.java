public class Material {
    private String nombre;
    private int cantidad;
    private int disponibilidad;

    public Material(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.disponibilidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }
}
