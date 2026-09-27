import java.util.ArrayList;

public class UniversidadFacade {

    private Universidad universidad;

    public UniversidadFacade() {
        this.universidad = Universidad.getInstancia();
    }

    public boolean matricularAlumno(int legajo, String nombre, String apellido, String dni, String email, int indiceCarrera) {
        if (universidad.buscarAlumnoPorLegajo(legajo) != null) {
            return false; // El alumno ya existe
        }

        ArrayList<Carrera> carreras = universidad.getCarreras();
        if (indiceCarrera < 0 || indiceCarrera >= carreras.size()) {
            return false; // Carrera inválida
        }

        Carrera carrera = carreras.get(indiceCarrera);
        Alumno alumno = new Alumno(legajo, nombre, apellido, dni, email);
        alumno.matricularEnCarrera(carrera);
        universidad.agregarAlumno(alumno);

        return true;
    }

    public boolean inscribirAlumnoMateria(int legajo, int indiceMateria) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno == null || alumno.getCarrera() == null) {
            return false;
        }

        Carrera carrera = alumno.getCarrera();
        ArrayList<Materia> materias = carrera.getMaterias();

        if (indiceMateria < 0 || indiceMateria >= materias.size()) {
            return false;
        }

        Materia materia = materias.get(indiceMateria);

        if (alumno.estaInscriptoEn(materia)) {
            return false;
        }

        Inscripcion inscripcion = new Inscripcion(alumno, materia);
        alumno.agregarInscripcion(inscripcion);
        return true;
    }


    public boolean registrarAsistencia(int legajo, int indiceInscripcion, boolean estuvoPresente) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno == null || alumno.getInscripciones().isEmpty()) {
            return false;
        }

        ArrayList<Inscripcion> inscripciones = alumno.getInscripciones();
        if (indiceInscripcion < 0 || indiceInscripcion >= inscripciones.size()) {
            return false;
        }

        Inscripcion inscripcion = inscripciones.get(indiceInscripcion);

        if (estuvoPresente) {
            inscripcion.registrarAsistencia();
        } else {
            inscripcion.registrarInasistencia();
        }

        return true;
    }


    public boolean cargarSituacionFinal(int legajo, int indiceInscripcion, String situacion) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno == null || alumno.getInscripciones().isEmpty()) {
            return false;
        }

        ArrayList<Inscripcion> inscripciones = alumno.getInscripciones();
        if (indiceInscripcion < 0 || indiceInscripcion >= inscripciones.size()) {
            return false;
        }

        Inscripcion inscripcion = inscripciones.get(indiceInscripcion);
        Profesor profesor = inscripcion.getMateria().getProfesor();

        profesor.cargarSituacionFinal(inscripcion, situacion);
        return true;
    }

    public ArrayList<Carrera> getCarreras() {
        return universidad.getCarreras();
    }

    public ArrayList<Alumno> getAlumnos() {
        return universidad.getAlumnos();
    }

    public Alumno buscarAlumnoPorLegajo(int legajo) {
        return universidad.buscarAlumnoPorLegajo(legajo);
    }

    public void mostrarCarreras() {
        universidad.mostrarCarreras();
    }

    public void mostrarAlumnos() {
        universidad.mostrarAlumnos();
    }
}