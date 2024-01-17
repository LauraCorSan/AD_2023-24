/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticasAD;

import ciclo.CicloInterface;
import ciclo.FactoriaCiclos;

/**
 *
 * @author laura
 */
public class Practica2 {
    public static void main(String[] args) {

        CicloInterface objeto = FactoriaCiclos.getCicloDao();

        CicloInterface ciclo1 = objeto.getNuevoCiclo("DPI","Desarrollo de productos informaticos","MEDIO");
    }
}
