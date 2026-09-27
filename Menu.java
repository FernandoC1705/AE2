import java.util.Scanner;

public class Menu {

    private Scanner teclado;
    private UniversidadFacade facade;

    public Menu() {
        this.facade = new UniversidadFacade();
        this.teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("        SISTEMA UNIVERSITARIO         ");
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
                    facade.mostrarCarreras();
                    break;

                case 7:
                    mostrarMateriasCarrera();
                    break;

                case 8:
                    facade.mostrarAlumnos();
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

        if (facade.existeAlumno(legajo)) {
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
        facade.mostrarCarrerasDisponibles();

        System.out.print("Seleccione una carrera: ");
        int opcionCarrera = teclado.nextInt();
        teclado.nextLine();

        boolean exito = facade.matricularAlumno(legajo, nombre, apellido, dni, email, opcionCarrera);

        if (exito) {
            facade.imprimirResumenMatriculacion(legajo, nombre, apellido, opcionCarrera);
        } else {
            System.out.println("Ocurrio un error al matricular al alumno.");
        }
    }

    private void inscribirAlumnoMateria() {

        System.out.println();
        System.out.println("===== INSCRIBIR ALUMNO A MATERIA =====");

        System.out.print("Ingrese el legajo del alumno: ");
        int legajo = teclado.nextInt();
        teclado.nextLine();

        if (!facade.mostrarMateriasDeCarreraDelAlumno(legajo)) {
            return;
        }

        System.out.print("Seleccione una materia: ");
        int opcionMateria = teclado.nextInt();
        teclado.nextLine();

        boolean exito = facade.inscribirAlumnoMateria(legajo, opcionMateria);

        if (exito) {
            System.out.println("\nInscripcion realizada correctamente.");
        } else {
            System.out.println("No se pudo realizar la inscripcion (opcion invalida o alumno ya inscripto).");
        }
    }

    private void registrarAsistencia() {

        System.out.println();
        System.out.println("===== REGISTRAR ASISTENCIA =====");

        System.out.print("Ingrese el legajo del alumno: ");
        int legajo = teclado.nextInt();
        teclado.nextLine();

        if (!facade.mostrarInscripcionesAlumno(legajo)) {
            return;
        }

        System.out.print("Seleccione una materia: ");
        int opcion = teclado.nextInt();
        teclado.nextLine();

        System.out.print("¿El alumno estuvo presente? (S/N): ");
        String respuesta = teclado.nextLine();

        boolean presente;
        if (respuesta.equalsIgnoreCase("S")) {
            presente = true;
        } else if (respuesta.equalsIgnoreCase("N")) {
            presente = false;
        } else {
            System.out.println("Respuesta no valida.");
            return;
        }

        boolean exito = facade.registrarAsistencia(legajo, opcion, presente);

        if (exito) {
            if (presente) {
                System.out.println("Asistencia registrada.");
            } else {
                System.out.println("Inasistencia registrada.");
                facade.imprimirInasistencias(legajo, opcion);
            }
        } else {
            System.out.println("Opcion no valida.");
        }
    }

    private void cargarSituacionFinal() {

        System.out.println();
        System.out.println("===== CARGAR SITUACION FINAL =====");

        System.out.print("Ingrese el legajo del alumno: ");
        int legajo = teclado.nextInt();
        teclado.nextLine();

        if (!facade.mostrarInscripcionesAlumno(legajo)) {
            return;
        }

        System.out.print("Seleccione una materia: ");
        int opcionMateria = teclado.nextInt();
        teclado.nextLine();

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

        boolean exito = facade.cargarSituacionFinal(legajo, opcionMateria, situacion);

        if (exito) {
            facade.imprimirResumenSituacionFinal(legajo, opcionMateria);
        } else {
            System.out.println("Ocurrio un error al cargar la situacion final.");
        }
    }

    private void mostrarAlumnosMateria() {

        System.out.println();
        System.out.println("===== ALUMNOS DE UNA MATERIA =====");

        System.out.println("Carreras disponibles:");
        facade.mostrarCarrerasDisponibles();

        System.out.print("Seleccione una carrera: ");
        int opcionCarrera = teclado.nextInt();
        teclado.nextLine();

        if (!facade.mostrarMateriasPorOpcionCarrera(opcionCarrera)) {
            return;
        }

        System.out.print("Seleccione una materia: ");
        int opcionMateria = teclado.nextInt();
        teclado.nextLine();

        System.out.println();
        System.out.println("¿Que desea visualizar?");
        System.out.println("1. Alumnos que estan cursando");
        System.out.println("2. Alumnos que finalizaron");
        System.out.print("Opcion: ");

        int opcionEstado = teclado.nextInt();
        teclado.nextLine();

        facade.mostrarAlumnosPorMateriaYEstado(opcionCarrera, opcionMateria, opcionEstado);
    }

    private void mostrarMateriasCarrera() {

        System.out.println();
        System.out.println("===== MATERIAS DE UNA CARRERA =====");

        System.out.println("Carreras disponibles:");
        facade.mostrarCarrerasDisponibles();

        System.out.print("Seleccione una carrera: ");
        int opcion = teclado.nextInt();
        teclado.nextLine();

        facade.mostrarMateriasDeCarrera(opcion);
    }
}