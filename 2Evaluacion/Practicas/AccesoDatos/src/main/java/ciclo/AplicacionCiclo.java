/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo;

/**
 *
 * @author laura
 */
public class AplicacionCiclo {
    public static void main(String[] args) {

        CicloInterface objeto = FactoriaCiclos.getCicloDao();

        CicloInterface ciclo1 = objeto.getNuevoCiclo("EI", "Educacion Infantil", "SUPERIOR");
    }
}
