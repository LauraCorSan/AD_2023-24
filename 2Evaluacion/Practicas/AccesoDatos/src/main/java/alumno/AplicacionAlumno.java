/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumno;

/**
 *
 * @author laura
 */
public class AplicacionAlumno {
    public static void main(String[] args) {
        AlumnoInterface objeto = FactoriaAlumnos.getAlumnoDao();

        AlumnoInterface alumno1 = objeto.getNuevoAlumno("1234", "Carla Alonso Perez", "DAM", "03496081M");
        
    }
}