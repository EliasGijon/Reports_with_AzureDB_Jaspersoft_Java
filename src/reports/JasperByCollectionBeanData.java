/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reports;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;




public class JasperByCollectionBeanData {
	//static void main(String[] args) throws JRException, FileNotFoundException
    String month_start;
    String year_start;
    String month_end;
    String year_end;
    String startDateFormat;
    String endDateFormat;
    public JasperByCollectionBeanData(String month_start, String year_start, String month_end, String year_end, String startDateFormat, String endDateFormat){
        this.month_start=month_start;
        this.year_start=year_start;
        this.month_end=month_end;
        this.year_end=year_end;
        this.startDateFormat=startDateFormat;
        this.endDateFormat=endDateFormat;
        //GenerarReportes();
    }
    
    public void GenerarReportes() {		
	Connection connection = null;
        try {
            String jdbcURL = "jdbc:sqlserver://zc.database.windows.net:1433;databaseName=tienda_virtual";
            String username = "zckeeper";
            String password = "SecurityBad21";
            connection = DriverManager.getConnection(jdbcURL, username, password);

            // Consulta SQL
            String sql = "SELECT product.product_id as ID, product.name as Producto, sale.sale_date as Fecha, product_sale.quantity as Cantidad " +
                    "FROM (SELECT * FROM sale_001 WHERE sale_date > ? AND sale_date < ?) as sale, " +
                    "(SELECT product_id, name FROM product) as product, " +
                    "(SELECT sale_id, product_id, quantity FROM product_sale_001) as product_sale " +
                    "WHERE sale.sale_id = product_sale.sale_id AND product_sale.product_id = product.product_id "+
                    "ORDER BY Fecha ASC";

            // Preparar la declaración SQL con parámetros
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, startDateFormat);  // Establecer el valor del primer parámetro
            statement.setString(2, endDateFormat);  // Establecer el valor del segundo parámetro
	
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
/*
            // Obtener el plazo el mes y año de las ventas
            java.sql.Date fecha = resultSet.getDate("Fecha");
                
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            
            DateFormatSymbols dfs = new DateFormatSymbols();
            String nombreMes = dfs.getMonths()[month];
            
            //Variables del Plazo
            System.out.print(year+ " " +nombreMes);
*/
            
            /* Output file location to create report in pdf form */
            String outputFile = "D:/COPIAS DE SEGURIDAD SERVER/" + "JasperReportExample.pdf";

            /* List to hold Items */
            List<Employee> listItems = new ArrayList<Employee>();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setID(resultSet.getString("ID"));
                employee.setProducto(resultSet.getString("Producto"));
                employee.setFecha(resultSet.getString("Fecha"));
                employee.setCantidad(String.valueOf(resultSet.getInt("Cantidad")));
                listItems.add(employee);
            }
            
            /*
            List<Employee> fechacampos = new ArrayList<Employee>();
            Employee xd = new Employee();
            xd.setMonthstart("NOVIEMBRE");
            xd.setYearstart(2020);
            xd.setMonthend("DICIEMBRE");
            xd.setYearend(2020);
            fechacampos.add(xd);

            /*
            // Create Employee objects
            Employee emp1 = new Employee();
            Employee emp2 = new Employee();
            Employee emp3 = new Employee();

            //first employee object
            emp1.setId(101);
            emp1.setFirstName("SAM");
            emp1.setLastName("Smith");
            emp1.setAddress("6th Avenue Dalton Road");
            emp1.setSalary(10000.0);


            //second employee object
            emp2.setId(101);
            emp2.setFirstName("JOHN");
            emp2.setLastName("Williams");
            emp2.setAddress("4th Square Down Town");
            emp2.setSalary(17000.0);

            //third employee object
            emp3.setId(101);
            emp3.setFirstName("JACOB");
            emp3.setLastName("Wilson");
            emp3.setAddress("19th Zygon Square, Middle Town");
            emp3.setSalary(22000.0);


            // Add Items to List 
            listItems.add(emp1);
            listItems.add(emp2);
            listItems.add(emp3);
    */
            /* Convert List to JRBeanCollectionDataSource */
//            JRBeanCollectionDataSource fechas = new JRBeanCollectionDataSource(fechacampos);
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            String imagePath = System.getProperty("user.dir")+"\\src\\formatos\\coffee.jpg";
            String imagePath2 = System.getProperty("user.dir")+"\\src\\formatos\\coffee_stain.png";
            
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            //parameters.put("CollectionBeanParam",fechas);
            parameters.put("CollectionBeanParam", itemsJRBean);
            parameters.put("month_start",month_start);
            parameters.put("year_start",year_start);
            parameters.put("month_end",month_end);
            parameters.put("year_end",year_end);
            parameters.put("REPORT_DIR", imagePath);
            parameters.put("REPORT_DIR2", imagePath2);
            
            //read jrxml file and creating jasperdesign object
            InputStream input = getClass().getResourceAsStream("../formatos/Coffee_Landscape_Table_Based.jrxml");
           
//cool be
            //InputStream input = new FileInputStream(new File("C:\\Users\\saske\\JaspersoftWorkspace\\MyReports\\Coffee_Landscape_Table_Based.jrxml"));

            JasperDesign jasperDesign = JRXmlLoader.load(input);

            /*compiling jrxml with help of JasperReport class*/
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            /* Using jasperReport object to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            /*call jasper engine to display report in jasperviewer window*/
            JasperViewer.viewReport(jasperPrint);


            /* outputStream to create PDF */
            //OutputStream outputStream = new FileOutputStream(new File(outputFile));


            /* Write content to PDF file */
            //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            System.out.println("File Generated");	
        
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