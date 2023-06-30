/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package reports;

// @author saske

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Reports{
    public static void main(String[] args) {
        gui inicio=new gui();
        inicio.setVisible(true);
    }
}
        /* Conectar a la base de datos
        Connection connection = null;
        
        String fechaInicio = "2020-11-01";
        String fechaFin = "2021-01-01";
        
        try {
            String jdbcURL = "jdbc:sqlserver://zc.database.windows.net:1433;databaseName=tienda_virtual";
            String username = "zckeeper";
            String password = "SecurityBad21";
            connection = DriverManager.getConnection(jdbcURL, username, password);

            // Ruta al archivo de diseño del informe (.jrxml)
            String reportPath = "C:/Users/saske/JaspersoftWorkspace/Tienda_Virtual.jrxml";

            // Parámetros del informe
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("fechaInicioParam", fechaInicio);
            parameters.put("fechaFinParam", fechaFin);

            // Compilar el archivo de diseño del informe
            JasperCompileManager.compileReportToFile(reportPath);

            // Llenar el informe con los datos de la consulta
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, connection);

            // Mostrar el informe en la ventana de visualización
            JasperViewer.viewReport(jasperPrint);

            // Exportar el informe a PDF
            String pdfPath = "D:/COPIAS DE SEGURIDAD SERVER/informe.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
            System.out.println("Informe generado: " + pdfPath);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión a la base de datos
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
*/