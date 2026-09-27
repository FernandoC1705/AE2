import java.util.ArrayList;

public class Alumno {

    private int legajo;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;

    private Carrera carrera;
    private ArrayList<Inscripcion> inscripciones;

    public Alumno(int legajo, String nombre, String apellido, String dni, String email) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.inscripciones = new ArrayList<>();
    }

    public int getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void matricularEnCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
    }

    public boolean estaInscriptoEn(Materia materia) {

        for (Inscripcion inscripcion : inscripciones) {

            if (inscripcion.getMateria() == materia) {
                return true;
            }
        }

        return false;
    }

    public Inscripcion buscarInscripcion(Materia materia) {

        for (Inscripcion inscripcion : inscripciones) {

            if (inscripcion.getMateria() == materia) {
                return inscripcion;
            }
        }

        return null;
    }

    @Override
    public String toString() {

        return "Legajo: " + legajo +
                " | Nombre: " + nombre + " " + apellido +
                " | DNI: " + dni +
                " | Email: " + email;
    }
}
