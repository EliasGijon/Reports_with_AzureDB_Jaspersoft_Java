package sql_Querypack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import createReports.ConnectionManager;
import createReports.Employee;

public class Inventory_Availability {

	public Inventory_Availability() {
		// TODO Auto-generated constructor stub
	}
	 public List<Employee> generateQuery(){
	    	List<Employee> listItems = new ArrayList<Employee>();
	    	Connection connection =null;
			try {
				connection = ConnectionManager.getConnection();
		
		        // Consulta SQL
		        String sql = "select * from productos_proveedor";
		
		        // Preparar la declaración SQL con parámetros
		        PreparedStatement statement = connection.prepareStatement(sql);
		       
		        // Ejecutar la consulta
		        ResultSet resultSet = statement.executeQuery();
		        
	            // Create Employee objects with db info
	            while (resultSet.next()) {
	                Employee employee = new Employee();
	                employee.setColumn1(resultSet.getString(1));
	                employee.setColumn2(resultSet.getString(2));
	                employee.setColumn3(resultSet.getString(3));
	                employee.setColumn4(resultSet.getString(4));
	                listItems.add(employee);
	            }
			}catch(Exception e){
				
			}
		    return listItems;  
	    }
}
