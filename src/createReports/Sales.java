package createReports;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql_Querypack.Sales_by_Date;

public class Sales {
	//static void main(String[] args) throws JRException, FileNotFoundException
    String month_start;
    String year_start;
    String month_end;
    String year_end;
    String startDateFormat;
    String endDateFormat;
    public Sales(String month_start, String year_start, String month_end, String year_end, String startDateFormat, String endDateFormat){
        this.month_start=month_start;
        this.year_start=year_start;
        this.month_end=month_end;
        this.year_end=year_end;
        this.startDateFormat=startDateFormat;
        this.endDateFormat=endDateFormat;
    }
	public void GenerarReportes() {
    	Connection connection =null;
    	try {
    		connection = ConnectionManager.getConnection();

/*          String jdbcURL = "jdbc:sqlserver://zc.database.windows.net:1433;databaseName=tienda_virtual";
            String username = "zckeeper";
            String password = "SecurityBad21";
            connection = DriverManager.getConnection(jdbcURL, username, password);
*//*
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
*/
            /* List to hold Items */
            Sales_by_Date sales_query=new Sales_by_Date(startDateFormat,endDateFormat);
            List<Employee> listItems =sales_query.generateQuery();
            		//new ArrayList<Employee>();
/*
            // Create Employee objects with db info
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setID(resultSet.getString("ID"));
                employee.setProducto(resultSet.getString("Producto"));
                employee.setFecha(resultSet.getString("Fecha"));
                employee.setCantidad(String.valueOf(resultSet.getInt("Cantidad")));
                listItems.add(employee);
            }
*/                       
            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            String imagePath = System.getProperty("user.dir")+"/src/formatos/coffee.jpg";
            String imagePath2 = System.getProperty("user.dir")+"/src/formatos/coffee_stain.png";
            
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
            //InputStream input = getClass().getResourceAsStream("../formatos/Coffee_Landscape_Table_Based.jrxml");
            //InputStream input = getClass().getResourceAsStream("/formatos/Coffee_Landscape_Table_Based.jrxml");
            InputStream input = getClass().getClassLoader().getResourceAsStream("formatos/Ventas-periodo.jrxml");

            JasperDesign jasperDesign = JRXmlLoader.load(input);
  
            /*compiling jrxml with help of JasperReport class*/
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            /* Using jasperReport object to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Sayte Gijon/Documents/report.pdf");

            /*call jasper engine to display report in jasperviewer window*/
            JasperViewer.viewReport(jasperPrint);
            
            System.out.println("File Generated");	
        
        } catch (Exception e) {
            System.out.println("Failed to create the database connection. A"); 

            e.printStackTrace();

        } /*catch (SQLException e) {
            System.out.println("Failed to create the database connection. Ñ"); 

            e.printStackTrace();

        } */finally {
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
