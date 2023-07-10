package createReports;

import java.io.InputStream;
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
import sql_Querypack.Inventory_Availability;

public class Inventory_Suppliers {
	
	public void GenerarReportes() {
    	try {
            /* List to hold Items */
    		Inventory_Availability Inventary_query=new Inventory_Availability();
            List<Employee> listItems = Inventary_query.generateQuery();
                      
            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            String imagePath = System.getProperty("user.dir")+"/src/formatos/coffee.jpg";
            String imagePath2 = System.getProperty("user.dir")+"/src/formatos/coffee_stain.png";
            
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("CollectionBeanParam", itemsJRBean);
            parameters.put("REPORT_DIR", imagePath);
            parameters.put("REPORT_DIR2", imagePath2);
            
            //read jrxml file and creating jasperdesign object
            InputStream input = getClass().getClassLoader().getResourceAsStream("formatos/Inventario-Proveedores.jrxml");

            JasperDesign jasperDesign = JRXmlLoader.load(input);
  
            /*compiling jrxml with help of JasperReport class*/
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            /* Using jasperReport object to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            /*call jasper engine to display report in jasperviewer window*/
            JasperViewer.viewReport(jasperPrint);
            
            System.out.println("File Generated");	
        
        } catch (Exception e) {
            System.out.println("Failed to create the jasperreport."); 
            e.printStackTrace();
        }
    }
}
