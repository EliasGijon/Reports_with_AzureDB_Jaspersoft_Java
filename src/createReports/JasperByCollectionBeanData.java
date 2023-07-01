/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package createReports;

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

import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
//import net.sf.jasperreports.export.SimplePdfExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;


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
import net.sf.jasperreports.engine.export.PdfGlyphRenderer;
import com.lowagie.*;

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
    	Connection connection =null;
    	try {
    		connection = ConnectionManager.getConnection();

/*          String jdbcURL = "jdbc:sqlserver://zc.database.windows.net:1433;databaseName=tienda_virtual";
            String username = "zckeeper";
            String password = "SecurityBad21";
            connection = DriverManager.getConnection(jdbcURL, username, password);
*/
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
            
            /* Output file location to create report in pdf form */
            //String outputFile = System.getProperty("user.dir") + "\\JasperReportExample.pdf";

            /* List to hold Items */
            List<Employee> listItems = new ArrayList<Employee>();

            // Create Employee objects with db info
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setID(resultSet.getString("ID"));
                employee.setProducto(resultSet.getString("Producto"));
                employee.setFecha(resultSet.getString("Fecha"));
                employee.setCantidad(String.valueOf(resultSet.getInt("Cantidad")));
                listItems.add(employee);
            }
                       
            /* Convert List to JRBeanCollectionDataSource */
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
           
            //original form in that pc 
            //InputStream input = new FileInputStream(new File("C:\\Users\\saske\\JaspersoftWorkspace\\MyReports\\Coffee_Landscape_Table_Based.jrxml"));

            JasperDesign jasperDesign = JRXmlLoader.load(input);

            /*compiling jrxml with help of JasperReport class*/
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            /* Using jasperReport object to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Sayte Gijon/Documents/report.pdf");

            /*call jasper engine to display report in jasperviewer window*/
            JasperViewer.viewReport(jasperPrint);
            
            String doc=System.getProperty("user.dir") +"\\src\\Documents\\";
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Sayte Gijon/Documents/report.pdf");

            //System.getProperty("user.dir")+"\\src\\formatos\\coffee.jpg";
            
            /*// Save the report as PDF
            JRPdfExporter exporter = new JRPdfExporter();

            // Set the input report
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

            // Set the output file
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File("report.pdf")));

            // Configure the PDF export
            SimplePdfExporterConfiguration exporterConfiguration = new SimplePdfExporterConfiguration();
            exporterConfiguration.setMetadataAuthor("Your Name");
            exporterConfiguration.setMetadataTitle("Your Report");

            SimplePdfReportConfiguration reportConfiguration = new SimplePdfReportConfiguration();
            reportConfiguration.setSizePageToContent(true);
            reportConfiguration.setForceLineBreakPolicy(false);

            exporter.setConfiguration(exporterConfiguration);
            exporter.setConfiguration(reportConfiguration);

            exporter.exportReport();
            
            
            // Save the report as PDF
            JRPdfExporter exporter = new JRPdfExporter();

            // Set the input report and output file
            exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE, new File("report.pdf"));

            // Configure the PDF export
            SimplePdfExporterConfiguration exporterConfiguration = new SimplePdfExporterConfiguration();
            exporterConfiguration.setMetadataAuthor("Your Name");
            exporterConfiguration.setMetadataTitle("Your Report");

            SimplePdfReportConfiguration reportConfiguration = new SimplePdfReportConfiguration();
            reportConfiguration.setSizePageToContent(true);
            reportConfiguration.setForceLineBreakPolicy(false);

            exporter.setConfiguration(exporterConfiguration);
            exporter.setConfiguration(reportConfiguration);

            exporter.exportReport();
            */
            /* outputStream to create PDF */
            //OutputStream outputStream = new FileOutputStream(new File(outputFile));


            /* Write content to PDF file */
            //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            System.out.println("File Generated");	
        
        } catch (SQLException e) {
            System.out.println("Failed to create the database connection. A"); 

            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Failed to create the database connection. Ñ"); 

            e.printStackTrace();

        } finally {
            // Cerrar la conexión a la base de datos
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to create the database connection. C"); 

                    e.printStackTrace();
                }
            }
        }
    }
}