import java.util.ArrayList;

public class Carrera {

    private String nombre;
    private int duracion;
    private Coordinador coordinador;
    private double precioInscripcion;
    private double precioCuota;

    private ArrayList<Materia> materias;

    public Carrera(String nombre, int duracion, Coordinador coordinador,
                   double precioInscripcion, double precioCuota) {

        this.nombre = nombre;
        this.duracion = duracion;
        this.coordinador = coordinador;
        this.precioInscripcion = precioInscripcion;
        this.precioCuota = precioCuota;

        this.materias = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public double getPrecioCuota() {
        return precioCuota;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public Materia buscarMateria(String nombreMateria) {

        for (Materia materia : materias) {

            if (materia.getNombre().equalsIgnoreCase(nombreMateria)) {
                return materia;
            }
        }

        return null;
    }

    public void mostrarMaterias() {

        if (materias.isEmpty()) {
            System.out.println("La carrera no tiene materias cargadas.");
            return;
        }

        for (Materia materia : materias) {
            System.out.println(materia);
        }
    }

    @Override
    public String toString() {

        return "Carrera: " + nombre +
                " | Duracion: " + duracion + " años" +
                " | Coordinador: " + coordinador.getNombre() +
                " | Precio inscripción: $" + precioInscripcion +
                " | Precio cuota: $" + precioCuota;
    }
}