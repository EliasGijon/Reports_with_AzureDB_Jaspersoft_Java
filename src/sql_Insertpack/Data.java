package sql_Insertpack;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import createReports.ConnectionManager;

public class Data  {
	private static Connection connection = ConnectionManager.getConnection();;
	private ResultSet resultSet;
	private int columnCount;
	private List<String[]> resultSetData = new ArrayList<>();
	
	
	
	
	// Consulta SQL
	private String initconsulta = "select * from ";
    StringBuffer sql =new StringBuffer(initconsulta);
	
    
    public Data(){
		System.out.println("Número de columnas en el ResultSet: " + columnCount);
    }
    
    
    
	public void queryTable(String table) {
		try {
			sql.replace(0, sql.length(), initconsulta + table);
			
	        // Preparar la declaración SQL con parámetros
	        PreparedStatement statement = connection.prepareStatement(sql.toString());
	       
	        // Ejecutar la consulta
	        resultSet = statement.executeQuery();
	        
	        //Delimitar Columnas
	    	ResultSetMetaData metaData;
	        metaData = resultSet.getMetaData();
			columnCount = metaData.getColumnCount();
			resultSetData = TupleStringList();
	    }catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	public List<String[]> TupleStringList() {
		List<String[]> rowsList = null;
		try {
			// Lista para almacenar filas
			rowsList= new ArrayList<>();
			while (resultSet.next()) {
			    // Arreglo para almacenar campos de una fila
			    String[] dynamicRow = new String[columnCount]; 
			    for (int i = 1; i <= columnCount; i++) {
			        String columnValue = resultSet.getString(i);
			        // -1 porque los índices de arreglo empiezan desde 0
			        dynamicRow[i - 1] = columnValue;
			    }
			    // Agrega la fila a la lista
			    rowsList.add(dynamicRow); 
			}
			// Ahora tienes todas las filas en rowsList, cada fila como un arreglo de strings
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsList;
	}
	
	public void printTuples()  {
		try {
			List<String[]> ColumnString=resultSetData;
			int i=0;
			while (i<ColumnString.size()) {
				for(String a:ColumnString.get(i)) {
					System.out.println(a);
				}
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public String[] getColumn(int n) {
	    List<String[]> columnString = resultSetData;
	    String[] columna = new String[columnString.size()];

	    try {
	        int i = 0;
	        while (i < columnString.size()) {
	            String[] currentRow = columnString.get(i); // Obtiene el arreglo de la posición i

	            if (n >= 0 && n < currentRow.length) {
	                String value = currentRow[n];
	                // Almacena el valor en el arreglo columna
	                columna[i] = value;
	            } else {
	                // Manejo de error si n está fuera de rango
	                throw new IllegalArgumentException("Número de columna fuera de rango");
	            }

	            i++;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	    return columna;
	}
	
	public List<String[]> getRows(){
		return resultSetData;
	}
}
