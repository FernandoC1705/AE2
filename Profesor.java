public class Profesor {

    private String nombre;
    private String apellido;
    private String dni;
    private String email;

    public Profesor(String nombre, String apellido, String dni, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
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

    public void registrarAsistencia(Inscripcion inscripcion) {
        inscripcion.registrarAsistencia();
    }

    public void registrarInasistencia(Inscripcion inscripcion) {
        inscripcion.registrarInasistencia();
    }

    public void cargarSituacionFinal(Inscripcion inscripcion, String situacion) {

        if (situacion.equalsIgnoreCase("Regular") ||
                situacion.equalsIgnoreCase("Libre") ||
                situacion.equalsIgnoreCase("Promocionado")) {

            inscripcion.setSituacionFinal(situacion);

        } else {

            System.out.println("Situación final no válida.");
            System.out.println("Debe ser: Regular, Libre o Promocionado.");
        }
    }

    @Override
    public String toString() {

        return "Profesor: " + nombre + " " + apellido +
                " | DNI: " + dni +
                " | Email: " + email;
    }
}