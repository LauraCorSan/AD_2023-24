/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticasAD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author laura
 */
public class Practica0 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            // Establecer la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3306/emple"; 
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            
            // Consulta SQL para obtener los empleados del departamento 30
            String sql = "SELECT e.employee_id, e.first_name, j.job_id, j.function " +
                     "FROM employee e " +
                     "JOIN job j ON e.job_id = j.job_id " +
                     "WHERE e.department_id = 30";

            ps = connection.prepareStatement(sql); // Aquí se crea el PreparedStatement con la consulta

            // Ejecutar la consulta
            rs = ps.executeQuery();
            
            // Mostrar los resultados
            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String nombre = rs.getString("first_name");
                String funcion = rs.getString("function");
                // Continuar con los campos adicionales que tengas en la tabla
                
                System.out.println("ID: " + id + "\t Nombre: " + nombre + "\t Funcion: " + funcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar conexiones y recursos
            try{
                rs.close();
                ps.close();
                connection.close();
            }catch(SQLException e ){
                e.printStackTrace();
            }
        }
    }
}
