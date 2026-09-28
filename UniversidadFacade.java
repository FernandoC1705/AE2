import java.util.ArrayList;

public class UniversidadFacade {

    private Universidad universidad;

    public UniversidadFacade() {
        this.universidad = Universidad.getInstancia();
    }

    // ==========================================
    //   OPERACIONES DE NEGOCIO (MATRICULACIÓN E INSCRIPCIÓN)
    // ==========================================

    public boolean existeAlumno(int legajo) {
        return universidad.buscarAlumnoPorLegajo(legajo) != null;
    }

    public boolean matricularAlumno(int legajo, String nombre, String apellido, String dni, String email, int opcionCarrera) {
        if (existeAlumno(legajo)) {
            return false;
        }

        ArrayList<Carrera> carreras = universidad.getCarreras();
        int indice = opcionCarrera - 1;

        if (indice < 0 || indice >= carreras.size()) {
            return false;
        }

        Carrera carrera = carreras.get(indice);
        Alumno alumno = new Alumno(legajo, nombre, apellido, dni, email);
        alumno.matricularEnCarrera(carrera);
        universidad.agregarAlumno(alumno);

        return true;
    }

    public boolean inscribirAlumnoMateria(int legajo, int opcionMateria) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno == null || alumno.getCarrera() == null) {
            return false;
        }

        ArrayList<Materia> materias = alumno.getCarrera().getMaterias();
        int indice = opcionMateria - 1;

        if (indice < 0 || indice >= materias.size()) {
            return false;
        }

        Materia materia = materias.get(indice);

        if (alumno.estaInscriptoEn(materia)) {
            return false;
        }

        Inscripcion inscripcion = new Inscripcion(alumno, materia);
        alumno.agregarInscripcion(inscripcion);
        return true;
    }

    public boolean registrarAsistencia(int legajo, int opcionInscripcion, boolean estuvoPresente) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno == null || alumno.getInscripciones().isEmpty()) {
            return false;
        }

        ArrayList<Inscripcion> inscripciones = alumno.getInscripciones();
        int indice = opcionInscripcion - 1;

        if (indice < 0 || indice >= inscripciones.size()) {
            return false;
        }

        Inscripcion inscripcion = inscripciones.get(indice);

        if (estuvoPresente) {
            inscripcion.registrarAsistencia();
        } else {
            inscripcion.registrarInasistencia();
        }

        return true;
    }

    public boolean cargarSituacionFinal(int legajo, int opcionInscripcion, String situacion) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno == null || alumno.getInscripciones().isEmpty()) {
            return false;
        }

        ArrayList<Inscripcion> inscripciones = alumno.getInscripciones();
        int indice = opcionInscripcion - 1;

        if (indice < 0 || indice >= inscripciones.size()) {
            return false;
        }

        Inscripcion inscripcion = inscripciones.get(indice);
        Profesor profesor = inscripcion.getMateria().getProfesor();

        profesor.cargarSituacionFinal(inscripcion, situacion);
        return true;
    }

    // ==========================================
    //   MÉTODOS DE PRESENTACIÓN / MOSTRAR DATOS
    // ==========================================

    public void mostrarCarreras() {
        universidad.mostrarCarreras();
    }

    public void mostrarAlumnos() {
        universidad.mostrarAlumnos();
    }

    public void mostrarCarrerasDisponibles() {
        ArrayList<Carrera> carreras = universidad.getCarreras();
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println((i + 1) + ". " + carreras.get(i).getNombre());
        }
    }

    public boolean mostrarMateriasDeCarreraDelAlumno(int legajo) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return false;
        }

        if (alumno.getCarrera() == null) {
            System.out.println("El alumno no esta matriculado en ninguna carrera.");
            return false;
        }

        Carrera carrera = alumno.getCarrera();
        System.out.println("Carrera del alumno: " + carrera.getNombre());
        System.out.println("Materias disponibles:");

        ArrayList<Materia> materias = carrera.getMaterias();
        for (int i = 0; i < materias.size(); i++) {
            Materia m = materias.get(i);
            System.out.println((i + 1) + ". " + m.getNombre() +
                    " | Curso: " + m.getCurso() +
                    " | Cuatrimestre: " + m.getCuatrimestre() +
                    " | Profesor: " + m.getProfesor().getNombre());
        }
        return true;
    }

    public boolean mostrarInscripcionesAlumno(int legajo) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return false;
        }

        if (alumno.getInscripciones().isEmpty()) {
            System.out.println("El alumno no esta inscripto en ninguna materia.");
            return false;
        }

        System.out.println("Materias del alumno:");
        ArrayList<Inscripcion> inscripciones = alumno.getInscripciones();
        for (int i = 0; i < inscripciones.size(); i++) {
            System.out.println((i + 1) + ". " + inscripciones.get(i).getMateria().getNombre());
        }
        return true;
    }

    public void mostrarMateriasDeCarrera(int opcionCarrera) {
        ArrayList<Carrera> carreras = universidad.getCarreras();
        int indice = opcionCarrera - 1;

        if (indice < 0 || indice >= carreras.size()) {
            System.out.println("Carrera no valida.");
            return;
        }

        Carrera carrera = carreras.get(indice);
        System.out.println();
        System.out.println("Materias de " + carrera.getNombre());
        carrera.mostrarMaterias();
    }

    public boolean mostrarMateriasPorOpcionCarrera(int opcionCarrera) {
        ArrayList<Carrera> carreras = universidad.getCarreras();
        int indice = opcionCarrera - 1;

        if (indice < 0 || indice >= carreras.size()) {
            System.out.println("Carrera no valida.");
            return false;
        }

        Carrera carrera = carreras.get(indice);
        System.out.println("Materias de " + carrera.getNombre() + ":");

        ArrayList<Materia> materias = carrera.getMaterias();
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + ". " + materias.get(i).getNombre());
        }
        return true;
    }

    public void mostrarAlumnosPorMateriaYEstado(int opcionCarrera, int opcionMateria, int tipoEstado) {
        ArrayList<Carrera> carreras = universidad.getCarreras();
        int idxCarrera = opcionCarrera - 1;

        if (idxCarrera < 0 || idxCarrera >= carreras.size()) {
            System.out.println("Carrera no valida.");
            return;
        }

        Carrera carrera = carreras.get(idxCarrera);
        ArrayList<Materia> materias = carrera.getMaterias();
        int idxMateria = opcionMateria - 1;

        if (idxMateria < 0 || idxMateria >= materias.size()) {
            System.out.println("Materia no valida.");
            return;
        }

        Materia materia = materias.get(idxMateria);
        boolean encontrado = false;

        System.out.println();
        if (tipoEstado == 1) {
            System.out.println("===== ALUMNOS CURSANDO =====");
            for (Alumno alumno : universidad.getAlumnos()) {
                if (alumno.getCarrera() == carrera) {
                    Inscripcion inscripcion = alumno.buscarInscripcion(materia);
                    if (inscripcion != null && !inscripcion.isFinalizado()) {
                        System.out.println("Legajo: " + alumno.getLegajo() + " | " + alumno.getNombre() + " " + alumno.getApellido());
                        encontrado = true;
                    }
                }
            }
        } else if (tipoEstado == 2) {
            System.out.println("===== ALUMNOS QUE FINALIZARON =====");
            for (Alumno alumno : universidad.getAlumnos()) {
                if (alumno.getCarrera() == carrera) {
                    Inscripcion inscripcion = alumno.buscarInscripcion(materia);
                    if (inscripcion != null && inscripcion.isFinalizado()) {
                        System.out.println("Legajo: " + alumno.getLegajo() + " | " + alumno.getNombre() + " " + alumno.getApellido() +
                                " | Situacion: " + inscripcion.getSituacionFinal() + " | Inasistencias: " + inscripcion.getInasistencias());
                        encontrado = true;
                    }
                }
            }
        } else {
            System.out.println("Opcion no valida.");
            return;
        }

        if (!encontrado) {
            System.out.println("No hay alumnos para mostrar.");
        }
    }

    public void imprimirResumenMatriculacion(int legajo, String nombre, String apellido, int opcionCarrera) {
        ArrayList<Carrera> carreras = universidad.getCarreras();
        int idx = opcionCarrera - 1;
        if (idx >= 0 && idx < carreras.size()) {
            System.out.println("\nAlumno matriculado correctamente.");
            System.out.println("Alumno: " + nombre + " " + apellido);
            System.out.println("Carrera: " + carreras.get(idx).getNombre());
        }
    }

    public void imprimirInasistencias(int legajo, int opcionInscripcion) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno != null) {
            int idx = opcionInscripcion - 1;
            if (idx >= 0 && idx < alumno.getInscripciones().size()) {
                Inscripcion ins = alumno.getInscripciones().get(idx);
                System.out.println("Total de inasistencias: " + ins.getInasistencias());
            }
        }
    }

    public void imprimirResumenSituacionFinal(int legajo, int opcionInscripcion) {
        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);
        if (alumno != null) {
            int idx = opcionInscripcion - 1;
            if (idx >= 0 && idx < alumno.getInscripciones().size()) {
                Inscripcion ins = alumno.getInscripciones().get(idx);
                System.out.println("\nSituacion final cargada correctamente.");
                System.out.println("Alumno: " + alumno.getNombre() + " " + alumno.getApellido());
                System.out.println("Materia: " + ins.getMateria().getNombre());
                System.out.println("Situacion: " + ins.getSituacionFinal());
                System.out.println("Inasistencias: " + ins.getInasistencias());
            }
        }
    }
}