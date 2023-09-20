class Prestamo {
    private Material material;
    private int cantidad;
    private String matriculaAlumno; 

    public Prestamo(Material material, int cantidad, String matriculaAlumno) {
        this.material = material;
        this.cantidad = cantidad;
        this.matriculaAlumno = matriculaAlumno;
    }

    public Material getMaterial() {
        return material;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
