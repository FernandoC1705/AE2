import java.util.ArrayList;

public class Universidad {

    private static Universidad instancia;

    private ArrayList<Carrera> carreras;
    private ArrayList<Alumno> alumnos;

    private Universidad() {
        carreras = new ArrayList<>();
        alumnos = new ArrayList<>();
        inicializarSistema();
    }

    public static Universidad getInstancia() {
        if (instancia == null) {
            instancia = new Universidad();
        }
        return instancia;
    }

    private void inicializarSistema() {

        Coordinador coordinador1 = new Coordinador(
                "Juan",
                "Perez",
                "30123456",
                "juan@universidad.com"
        );

        Coordinador coordinador2 = new Coordinador(
                "Laura",
                "Espindola",
                "30234567",
                "laura@universidad.com"
        );

        Profesor profesor1 = new Profesor(
                "Pedro",
                "Paez",
                "28123456",
                "pedro@universidad.com"
        );

        Profesor profesor2 = new Profesor(
                "Maria",
                "Lopez",
                "29234567",
                "maria@universidad.com"
        );

        Profesor profesor3 = new Profesor(
                "Pedro",
                "Gonzalez",
                "27345678",
                "pedro@universidad.com"
        );

        Carrera carrera1 = new Carrera(
                "Ingenieria en Sistemas",
                5,
                coordinador1,
                50000,
                80000
        );

        Carrera carrera2 = new Carrera(
                "Licenciatura en Administracion",
                4,
                coordinador2,
                45000,
                70000
        );

        Materia sistemas1 = new Materia(
                "Programacion",
                1,
                1,
                profesor1
        );

        Materia sistemas2 = new Materia(
                "Matematica",
                1,
                2,
                profesor2
        );

        Materia sistemas3 = new Materia(
                "Base de Datos",
                2,
                1,
                profesor1
        );

        Materia sistemas4 = new Materia(
                "Fisica",
                2,
                2,
                profesor3
        );

        carrera1.agregarMateria(sistemas1);
        carrera1.agregarMateria(sistemas2);
        carrera1.agregarMateria(sistemas3);
        carrera1.agregarMateria(sistemas4);

        Materia administracion1 = new Materia(
                "Contabilidad",
                1,
                1,
                profesor2
        );

        Materia administracion2 = new Materia(
                "Economia",
                1,
                2,
                profesor3
        );

        Materia administracion3 = new Materia(
                "Marketing",
                2,
                1,
                profesor2
        );

        Materia administracion4 = new Materia(
                "Estadistica",
                2,
                2,
                profesor1
        );

        carrera2.agregarMateria(administracion1);
        carrera2.agregarMateria(administracion2);
        carrera2.agregarMateria(administracion3);
        carrera2.agregarMateria(administracion4);

        carreras.add(carrera1);
        carreras.add(carrera2);
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public Alumno buscarAlumnoPorLegajo(int legajo) {

        for (Alumno alumno : alumnos) {
            if (alumno.getLegajo() == legajo) {
                return alumno;
            }
        }

        return null;
    }

    public Carrera buscarCarrera(String nombre) {

        for (Carrera carrera : carreras) {
            if (carrera.getNombre().equalsIgnoreCase(nombre)) {
                return carrera;
            }
        }

        return null;
    }

    public void mostrarCarreras() {

        if (carreras.isEmpty()) {
            System.out.println("No hay carreras cargadas.");
            return;
        }

        for (Carrera carrera : carreras) {
            System.out.println(carrera);
        }
    }

    public void mostrarAlumnos() {

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }
}