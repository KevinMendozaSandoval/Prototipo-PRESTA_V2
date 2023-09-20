import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<String, Material> materiales;

    public Inventario() {
        this.materiales = new HashMap<>();
    }

    public void agregarMaterial(Material material) {
        materiales.put(material.getNombre(), material);
    }

    public boolean verificarDisponibilidad(Material material, int cantidad) {
        Material materialEnInventario = materiales.get(material.getNombre());
        return materialEnInventario != null && materialEnInventario.getDisponibilidad() >= cantidad;
    }

    public void actualizarInventario(Material material, int cantidad) {
        if (verificarDisponibilidad(material, cantidad)) {
            Material materialEnInventario = materiales.get(material.getNombre());
            materialEnInventario.setCantidad(materialEnInventario.getCantidad() - cantidad);
        }
    }
}

