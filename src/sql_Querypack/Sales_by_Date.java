package sql_Querypack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import createReports.ConnectionManager;
import createReports.Employee;

public class Sales_by_Date {
    String startDateFormat;
    String endDateFormat;
    
    public Sales_by_Date(String startDateFormat, String endDateFormat){
        this.startDateFormat=startDateFormat;
        this.endDateFormat=endDateFormat;
    }
    public List<Employee> generateQuery(){
    	List<Employee> listItems = new ArrayList<Employee>();
    	Connection connection =null;
		try {
			connection = ConnectionManager.getConnection();
	
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
	        
            // Create Employee objects with db info
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setColumn1(resultSet.getString("ID"));
                employee.setColumn2(resultSet.getString("Producto"));
                employee.setColumn3(resultSet.getString("Fecha"));
                employee.setColumn4(String.valueOf(resultSet.getInt("Cantidad")));
                listItems.add(employee);
            }
		}catch(Exception e){
			
		}
	    return listItems;  
    }
}
