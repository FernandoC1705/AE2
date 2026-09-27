public class Inscripcion {

    private Alumno alumno;
    private Materia materia;

    private int inasistencias;
    private String situacionFinal;
    private boolean finalizado;

    public Inscripcion(Alumno alumno, Materia materia) {

        this.alumno = alumno;
        this.materia = materia;

        this.inasistencias = 0;
        this.situacionFinal = "";
        this.finalizado = false;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public int getInasistencias() {
        return inasistencias;
    }

    public String getSituacionFinal() {
        return situacionFinal;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void registrarAsistencia() {
        // La asistencia no modifica la cantidad de inasistencias.
    }

    public void registrarInasistencia() {
        inasistencias++;
    }

    public void setSituacionFinal(String situacionFinal) {

        if (situacionFinal.equalsIgnoreCase("Regular") ||
                situacionFinal.equalsIgnoreCase("Libre") ||
                situacionFinal.equalsIgnoreCase("Promocionado")) {

            this.situacionFinal = situacionFinal;
            this.finalizado = true;

        } else {

            System.out.println("Situación final no válida.");
            System.out.println("Debe ser: Regular, Libre o Promocionado.");
        }
    }

    @Override
    public String toString() {

        String resultado = "Alumno: " +
                alumno.getNombre() + " " +
                alumno.getApellido() +
                " | Legajo: " + alumno.getLegajo() +
                " | Inasistencias: " + inasistencias;

        if (finalizado) {
            resultado += " | Situación final: " + situacionFinal;
        } else {
            resultado += " | Estado: Cursando";
        }

        return resultado;
    }
}