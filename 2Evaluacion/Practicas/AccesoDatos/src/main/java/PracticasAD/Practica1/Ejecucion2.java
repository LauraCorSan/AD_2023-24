/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticasAD.Practica1;

import alumno.AlumnoInterface;
import alumno.FactoriaAlumnos;
import java.util.Iterator;

/**
 *
 * @author laura
 */
public class Ejecucion2 {
    public static void main(String[] args) {
        String ciclo="DAM";
        AlumnoInterface objeto = FactoriaAlumnos.getAlumnoDao();
        
        java.util.Collection<AlumnoInterface> alumnosDAM = objeto.getAlumnoPorCiclo(ciclo);
        
        if(alumnosDAM == null || alumnosDAM.isEmpty()){
            System.out.println("WARNING: No hay alumnos matriculados en "+ciclo);
        }else{
            System.out.println("LISTADO ALUMNOS MATRICULADOS EN "+ciclo);
            
            Iterator <AlumnoInterface> iterador = alumnosDAM.iterator();
            
            while(iterador.hasNext()){
                AlumnoInterface alumno = iterador.next();
                System.out.println(alumno.getNombre());
            }
        }
    }
}
