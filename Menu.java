import java.util.Scanner;

public class Menu {

    private Scanner teclado;
    private Universidad universidad;

    public Menu(Universidad universidad) {
        this.universidad = universidad;
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("        SISTEMA UNIVERSITARIO");
            System.out.println("======================================");
            System.out.println("1. Matricular alumno a una carrera");
            System.out.println("2. Inscribir alumno a una materia");
            System.out.println("3. Registrar asistencia");
            System.out.println("4. Cargar situacion final");
            System.out.println("5. Mostrar alumnos de una materia");
            System.out.println("6. Mostrar carreras");
            System.out.println("7. Mostrar materias de una carrera");
            System.out.println("8. Mostrar alumnos registrados");
            System.out.println("0. Salir");
            System.out.println("======================================");
            System.out.print("Ingrese una opcion: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    matricularAlumno();
                    break;

                case 2:
                    inscribirAlumnoMateria();
                    break;

                case 3:
                    registrarAsistencia();
                    break;

                case 4:
                    cargarSituacionFinal();
                    break;

                case 5:
                    mostrarAlumnosMateria();
                    break;

                case 6:
                    universidad.mostrarCarreras();
                    break;

                case 7:
                    mostrarMateriasCarrera();
                    break;

                case 8:
                    universidad.mostrarAlumnos();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    private void matricularAlumno() {

        System.out.println();
        System.out.println("===== MATRICULAR ALUMNO =====");

        System.out.print("Legajo: ");
        int legajo = teclado.nextInt();
        teclado.nextLine();

        if (universidad.buscarAlumnoPorLegajo(legajo) != null) {
            System.out.println("Ya existe un alumno con ese legajo.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Apellido: ");
        String apellido = teclado.nextLine();

        System.out.print("DNI: ");
        String dni = teclado.nextLine();

        System.out.print("Email: ");
        String email = teclado.nextLine();

        System.out.println();
        System.out.println("Carreras disponibles:");

        for (int i = 0; i < universidad.getCarreras().size(); i++) {

            System.out.println(
                    (i + 1) + ". " +
                            universidad.getCarreras().get(i).getNombre()
            );
        }

        System.out.print("Seleccione una carrera: ");
        int opcionCarrera = teclado.nextInt();
        teclado.nextLine();

        if (opcionCarrera < 1 ||
                opcionCarrera > universidad.getCarreras().size()) {

            System.out.println("Carrera no valida.");
            return;
        }

        Carrera carrera =
                universidad.getCarreras().get(opcionCarrera - 1);

        Alumno alumno = new Alumno(
                legajo,
                nombre,
                apellido,
                dni,
                email
        );

        alumno.matricularEnCarrera(carrera);
        universidad.agregarAlumno(alumno);

        System.out.println();
        System.out.println("Alumno matriculado correctamente.");
        System.out.println("Alumno: " + nombre + " " + apellido);
        System.out.println("Carrera: " + carrera.getNombre());
    }

    private void inscribirAlumnoMateria() {

        System.out.println();
        System.out.println("===== INSCRIBIR ALUMNO A MATERIA =====");

        System.out.print("Ingrese el legajo del alumno: ");
        int legajo = teclado.nextInt();
        teclado.nextLine();

        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);

        if (alumno == null) {

            System.out.println("No existe un alumno con ese legajo.");
            return;
        }

        if (alumno.getCarrera() == null) {

            System.out.println(
                    "El alumno no esta matriculado en ninguna carrera."
            );

            return;
        }

        Carrera carrera = alumno.getCarrera();

        System.out.println();
        System.out.println("Carrera del alumno: " + carrera.getNombre());

        System.out.println();
        System.out.println("Materias disponibles:");

        for (int i = 0; i < carrera.getMaterias().size(); i++) {

            Materia materia = carrera.getMaterias().get(i);

            System.out.println(
                    (i + 1) + ". " +
                            materia.getNombre() +
                            " | Curso: " + materia.getCurso() +
                            " | Cuatrimestre: " + materia.getCuatrimestre() +
                            " | Profesor: " + materia.getProfesor().getNombre()
            );
        }

        System.out.print("Seleccione una materia: ");
        int opcionMateria = teclado.nextInt();
        teclado.nextLine();

        if (opcionMateria < 1 ||
                opcionMateria > carrera.getMaterias().size()) {

            System.out.println("Materia no valida.");
            return;
        }

        Materia materia =
                carrera.getMaterias().get(opcionMateria - 1);

        if (alumno.estaInscriptoEn(materia)) {

            System.out.println(
                    "El alumno ya esta inscripto en esta materia."
            );

            return;
        }

        Inscripcion inscripcion =
                new Inscripcion(alumno, materia);

        alumno.agregarInscripcion(inscripcion);

        System.out.println();
        System.out.println("Inscripcion realizada correctamente.");
        System.out.println("Alumno: " +
                alumno.getNombre() + " " +
                alumno.getApellido());

        System.out.println("Materia: " +
                materia.getNombre());
    }

    private void registrarAsistencia() {

        System.out.println();
        System.out.println("===== REGISTRAR ASISTENCIA =====");

        System.out.print("Ingrese el legajo del alumno: ");
        int legajo = teclado.nextInt();
        teclado.nextLine();

        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);

        if (alumno == null) {

            System.out.println("Alumno no encontrado.");
            return;
        }

        if (alumno.getInscripciones().isEmpty()) {

            System.out.println(
                    "El alumno no esta inscripto en ninguna materia."
            );

            return;
        }

        System.out.println();
        System.out.println("Materias del alumno:");

        for (int i = 0; i < alumno.getInscripciones().size(); i++) {

            Inscripcion inscripcion =
                    alumno.getInscripciones().get(i);

            System.out.println(
                    (i + 1) + ". " +
                            inscripcion.getMateria().getNombre()
            );
        }

        System.out.print("Seleccione una materia: ");
        int opcion = teclado.nextInt();
        teclado.nextLine();

        if (opcion < 1 ||
                opcion > alumno.getInscripciones().size()) {

            System.out.println("Opcion no valida.");
            return;
        }

        Inscripcion inscripcion =
                alumno.getInscripciones().get(opcion - 1);

        System.out.print(
                "¿El alumno estuvo presente? (S/N): "
        );

        String respuesta = teclado.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {

            inscripcion.registrarAsistencia();

            System.out.println("Asistencia registrada.");

        } else if (respuesta.equalsIgnoreCase("N")) {

            inscripcion.registrarInasistencia();

            System.out.println("Inasistencia registrada.");
            System.out.println(
                    "Total de inasistencias: " +
                            inscripcion.getInasistencias()
            );

        } else {

            System.out.println("Respuesta no valida.");
        }
    }

    private void cargarSituacionFinal() {

        System.out.println();
        System.out.println("===== CARGAR SITUACION FINAL =====");

        System.out.print("Ingrese el legajo del alumno: ");
        int legajo = teclado.nextInt();
        teclado.nextLine();

        Alumno alumno = universidad.buscarAlumnoPorLegajo(legajo);

        if (alumno == null) {

            System.out.println("Alumno no encontrado.");
            return;
        }

        if (alumno.getInscripciones().isEmpty()) {

            System.out.println(
                    "El alumno no esta inscripto en ninguna materia."
            );

            return;
        }

        System.out.println();
        System.out.println("Materias del alumno:");

        for (int i = 0; i < alumno.getInscripciones().size(); i++) {

            Inscripcion inscripcion =
                    alumno.getInscripciones().get(i);

            System.out.println(
                    (i + 1) + ". " +
                            inscripcion.getMateria().getNombre()
            );
        }

        System.out.print("Seleccione una materia: ");
        int opcionMateria = teclado.nextInt();
        teclado.nextLine();

        if (opcionMateria < 1 ||
                opcionMateria > alumno.getInscripciones().size()) {

            System.out.println("Materia no valida.");
            return;
        }

        Inscripcion inscripcion =
                alumno.getInscripciones().get(opcionMateria - 1);

        System.out.println();
        System.out.println("Seleccione la situacion final:");
        System.out.println("1. Regular");
        System.out.println("2. Libre");
        System.out.println("3. Promocionado");
        System.out.print("Opcion: ");

        int opcionSituacion = teclado.nextInt();
        teclado.nextLine();

        String situacion;

        switch (opcionSituacion) {

            case 1:
                situacion = "Regular";
                break;

            case 2:
                situacion = "Libre";
                break;

            case 3:
                situacion = "Promocionado";
                break;

            default:
                System.out.println("Situacion no valida.");
                return;
        }

        Profesor profesor =
                inscripcion.getMateria().getProfesor();

        profesor.cargarSituacionFinal(
                inscripcion,
                situacion
        );

        System.out.println();
        System.out.println("Situacion final cargada correctamente.");
        System.out.println("Alumno: " +
                alumno.getNombre() + " " +
                alumno.getApellido());

        System.out.println("Materia: " +
                inscripcion.getMateria().getNombre());

        System.out.println("Situacion: " +
                inscripcion.getSituacionFinal());

        System.out.println("Inasistencias: " +
                inscripcion.getInasistencias());
    }

    private void mostrarAlumnosMateria() {

        System.out.println();
        System.out.println("===== ALUMNOS DE UNA MATERIA =====");

        // Primero se selecciona la carrera

        System.out.println("Carreras disponibles:");

        for (int i = 0; i < universidad.getCarreras().size(); i++) {

            System.out.println(
                    (i + 1) + ". " +
                            universidad.getCarreras().get(i).getNombre()
            );
        }

        System.out.print("Seleccione una carrera: ");
        int opcionCarrera = teclado.nextInt();
        teclado.nextLine();

        if (opcionCarrera < 1 ||
                opcionCarrera > universidad.getCarreras().size()) {

            System.out.println("Carrera no valida.");
            return;
        }

        Carrera carrera =
                universidad.getCarreras().get(opcionCarrera - 1);

        // Luego se selecciona una materia de esa carrera

        System.out.println();
        System.out.println(
                "Materias de " + carrera.getNombre() + ":"
        );

        for (int i = 0; i < carrera.getMaterias().size(); i++) {

            Materia materia = carrera.getMaterias().get(i);

            System.out.println(
                    (i + 1) + ". " +
                            materia.getNombre()
            );
        }

        System.out.print("Seleccione una materia: ");
        int opcionMateria = teclado.nextInt();
        teclado.nextLine();

        if (opcionMateria < 1 ||
                opcionMateria > carrera.getMaterias().size()) {

            System.out.println("Materia no valida.");
            return;
        }

        Materia materia =
                carrera.getMaterias().get(opcionMateria - 1);

        System.out.println();
        System.out.println("¿Que desea visualizar?");
        System.out.println("1. Alumnos que estan cursando");
        System.out.println("2. Alumnos que finalizaron");
        System.out.print("Opcion: ");

        int opcion = teclado.nextInt();
        teclado.nextLine();

        boolean encontrado = false;

        System.out.println();

        if (opcion == 1) {

            System.out.println("===== ALUMNOS CURSANDO =====");

            for (Alumno alumno : universidad.getAlumnos()) {

                if (alumno.getCarrera() == carrera) {

                    Inscripcion inscripcion =
                            alumno.buscarInscripcion(materia);

                    if (inscripcion != null &&
                            !inscripcion.isFinalizado()) {

                        System.out.println(
                                "Legajo: " +
                                        alumno.getLegajo() +
                                        " | " +
                                        alumno.getNombre() +
                                        " " +
                                        alumno.getApellido()
                        );

                        encontrado = true;
                    }
                }
            }

        } else if (opcion == 2) {

            System.out.println("===== ALUMNOS QUE FINALIZARON =====");

            for (Alumno alumno : universidad.getAlumnos()) {

                if (alumno.getCarrera() == carrera) {

                    Inscripcion inscripcion =
                            alumno.buscarInscripcion(materia);

                    if (inscripcion != null &&
                            inscripcion.isFinalizado()) {

                        System.out.println(
                                "Legajo: " +
                                        alumno.getLegajo() +
                                        " | " +
                                        alumno.getNombre() +
                                        " " +
                                        alumno.getApellido() +
                                        " | Situacion: " +
                                        inscripcion.getSituacionFinal() +
                                        " | Inasistencias: " +
                                        inscripcion.getInasistencias()
                        );

                        encontrado = true;
                    }
                }
            }

        } else {

            System.out.println("Opcion no valida.");
            return;
        }

        if (!encontrado) {

            System.out.println(
                    "No hay alumnos para mostrar."
            );
        }
    }

    private void mostrarMateriasCarrera() {

        System.out.println();
        System.out.println("===== MATERIAS DE UNA CARRERA =====");

        System.out.println("Carreras disponibles:");

        for (int i = 0; i < universidad.getCarreras().size(); i++) {

            System.out.println(
                    (i + 1) + ". " +
                            universidad.getCarreras().get(i).getNombre()
            );
        }

        System.out.print("Seleccione una carrera: ");
        int opcion = teclado.nextInt();
        teclado.nextLine();

        if (opcion < 1 ||
                opcion > universidad.getCarreras().size()) {

            System.out.println("Carrera no valida.");
            return;
        }

        Carrera carrera =
                universidad.getCarreras().get(opcion - 1);

        System.out.println();
        System.out.println(
                "Materias de " + carrera.getNombre()
        );

        carrera.mostrarMaterias();
    }
}