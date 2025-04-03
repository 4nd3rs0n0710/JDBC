/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author SENA
 */
public class CConexion {
    Connection conectar = null;
    
    String usuario = "root";
    String Contrasenia = "12345";
    String bd = "bdJDBC";
    String ip = "127.0.0.1";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    

    public Connection establecerConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conectar = DriverManager.getConnection(cadena, usuario, Contrasenia);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La conexion se realizo con exito");
        }
        return conectar;
}
}
