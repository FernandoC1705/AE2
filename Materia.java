public class Materia {

    private String nombre;
    private int curso;
    private int cuatrimestre;
    private Profesor profesor;

    public Materia(String nombre, int curso, int cuatrimestre, Profesor profesor) {
        this.nombre = nombre;
        this.curso = curso;
        this.cuatrimestre = cuatrimestre;
        this.profesor = profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCurso() {
        return curso;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {

        return "Materia: " + nombre +
                " | Curso: " + curso +
                "° año" +
                " | Cuatrimestre: " + cuatrimestre +
                " | Profesor: " + profesor.getNombre();
    }
}