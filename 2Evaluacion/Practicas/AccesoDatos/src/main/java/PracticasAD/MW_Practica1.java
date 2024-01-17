package PracticasAD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author laura
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

public class MW_Practica1 {
    public static Connection connection = null;
    //PreparedStatement ps = null;
    //ResultSet rs = null;
    
    public static void main(String[] args) {

        try {
            // Establecer la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3306/alumno"; 
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            
            /*NOTA: No puedes ejecutar todo a la vez, debe hacerlo por partes, 
            primero darDeAltaAlumno y despues en otra ejecucuion modificarAlumno*/

            // Dar de alta un nuevo alumno
            darDeAltaAlumno("105", "ENRIQUETA", "3434343", "ASIR");
            consultarTablaAlumno();

            //Modificar los datos del alumno
            modificarDatosAlumno("105", "BLANCA", "55555555", "DAM");

            // Consultar la tabla alumno y mostrar los resultados
            consultarTablaAlumno();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void darDeAltaAlumno(String numExpediente, String nombre, String dni, String ciclo) throws SQLException {
        String sql = "INSERT INTO alumno (numexpdte, nombre, dni, ciclo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, numExpediente);
            ps.setString(2, nombre);
            ps.setString(3, dni);
            ps.setString(4, ciclo);

            ps.executeUpdate();
        }
    }


    private static void modificarDatosAlumno(String numExpediente, String nuevoNombre, String nuevoDni, String nuevoCiclo) throws SQLException {

        String sql = "UPDATE alumno SET nombre = ?, dni = ?, ciclo = ? WHERE numexpdte = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoDni);
            ps.setString(3, nuevoCiclo);
            ps.setString(4, numExpediente);

            ps.executeUpdate();
        }
    }


    private static void consultarTablaAlumno() throws SQLException {
        String sql = "SELECT * FROM alumno";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("NUMEXPDTE\tNOMBRE\tDNI\tCICLO");
                while (rs.next()) {
                    System.out.print(rs.getString("numexpdte"));
                    System.out.print("\t" + rs.getString("nombre"));
                    System.out.print("\t" + rs.getString("dni"));
                    System.out.print("\t" + rs.getString("ciclo")+"\n");
                    System.out.println("---------------------------------------------------------------------------------");
                }
            }
        }
    }
    
}
