/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author SENA
 */
public class CAlumnos {

    int codigo;
    String nombresAlumnos;
    String apellidosAlumnos;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombresAlumnos() {
        return nombresAlumnos;
    }

    public void setNombresAlumnos(String nombresAlumnos) {
        this.nombresAlumnos = nombresAlumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }
    
    public void InsertarAlumno(JTextField paramNombres, JTextField paramApellidos){
    
        setNombresAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        CConexion objetoConexion = new CConexion();
        String consulta = "insert into Alumnos (nombres, apellidos) values (?, ?);";
        
        try {
            CallableStatement cs = (CallableStatement) objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombresAlumnos());
            cs.setString(2, getApellidosAlumnos());
            
        cs.execute();
        
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el alumno, error: " +e.toString());
        }
    }    
        public void MostrarAlumno(JTable paramTablaTotalAlumnos){
            
            CConexion objetoConexion = new CConexion();
            
            DefaultTableModel modelo = new DefaultTableModel();
            
            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
            paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
            
            String sql="";
            
            modelo.addColumn("ID");
            modelo.addColumn("Nombres");
            modelo.addColumn("Apellidos");
            paramTablaTotalAlumnos.setModel(modelo);
            
            sql = "select * from Alumnos";
            
            String[] datos = new String[3];
            Statement st;
            
            try {
                
                st = objetoConexion.establecerConexion().createStatement();
                
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                
                modelo.addRow(datos);
                }
                
                paramTablaTotalAlumnos.setModel(modelo);
              
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
            }
 
            }
        public void SeleccionarAlumno(JTable paramTableAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos){
        
            try {
                int fila = paramTableAlumnos.getSelectedRow();
                
                if (fila>=0) {
                    
                    paramId.setText((String)(paramTableAlumnos.getValueAt(fila, 0)));
                    paramNombres.setText((String)(paramTableAlumnos.getValueAt(fila, 1)));
                    paramApellidos.setText((String)(paramTableAlumnos.getValueAt(fila, 2)));
                        }
                else{
                JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error : " + e.toString());
            }
        }    
            public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
                setCodigo(Integer.parseInt(paramCodigo.getText()));
                setNombresAlumnos(paramNombres.getText());
                setApellidosAlumnos(paramApellidos.getText());
            
                
                CConexion objetoConexion = new CConexion();
                
                String consulta = "UPDATE Alumnos SET alumnos = ?, alumnos.apellidos =? WHERE alumnos.id=?;";
                
                try {
                    
                    CallableStatement cs = (CallableStatement) objetoConexion.establecerConexion().prepareCall(consulta);
                    
                    cs.setString(1, getNombresAlumnos());
                    cs.setString(2, getApellidosAlumnos());
                    cs.setInt(3, getCodigo());
                    
                    cs.execute();
                    
                    JOptionPane.showMessageDialog(null, "Modificación Exitosa");
                    
                    
                } catch (Exception e) {
                    
                    JOptionPane.showMessageDialog(null, "Error al modificar Alumno, error :" + e.toString());
                }
            }
        
}
    
    

    
            

