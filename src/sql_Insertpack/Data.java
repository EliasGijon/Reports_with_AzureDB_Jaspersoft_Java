package sql_Insertpack;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import createReports.ConnectionManager;

public class Data  {
	private static Connection connection = ConnectionManager.getConnection();;
	private ResultSet resultSet;
	private int columnCount;
	private Set<String[]> resultSetData = new LinkedHashSet<>();	
	private String[] columnName; 
	
	
	// Consulta SQL
	private String initconsulta;
    StringBuffer sql;
	
    
    public Data(){
    	initconsulta = "select * from ";
    	sql=new StringBuffer(initconsulta);
    }
    
    public Data(String querybase){
    	initconsulta=querybase;
    	sql=new StringBuffer(initconsulta);
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
	        columnName = getColumnASName(metaData);
			resultSetData = TupleStringList();
	    }catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Set<String[]>TupleStringList() {
		Set<String[]> rowsList = null;
		//Vertificar columnas no duplicadas por hashcode
		Set<String> uniqueRows = new HashSet<>();
		try {
			// Lista para almacenar filas
			rowsList= new LinkedHashSet<>();
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
			List<String[]> ColumnString = getRows();
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
	    List<String[]> columnString = getRows();
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
	
	public String[] getColumnASName(ResultSetMetaData metaData) {
		String [] columns = new String[columnCount];
		for (int i = 1; i <= columnCount; i++) {
        	try {
				columns[i-1] = metaData.getColumnName(i);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return columns;
	}
	
	public void InsertInBD(List<Products> productData) {
		StringBuffer insertProduct = new StringBuffer("INSERT INTO product (product_id, name, price)");
		insertProduct.append(" VALUES (?, ?, ?)");
		
		StringBuffer insertExistence = new StringBuffer("INSERT INTO existence_p_001 (product_id, existence, stationery_id)"); 
		insertExistence.append(" VALUES (?, ?, ?)");
		
		try {
			connection.setAutoCommit(false); // Desactiva el modo de autocommit

	        // Preparar las declaraciones SQL
	        PreparedStatement insertProductStatement = connection.prepareStatement(insertProduct.toString());
	        PreparedStatement insertExistenceStatement = connection.prepareStatement(insertExistence.toString());

	        for(Products product : productData) {
	            // Llenar los valores para la tabla de producto
	            insertProductStatement.setString(1, product.getProductId()); // product_id
	            insertProductStatement.setString(2, product.getName()); // name
	            insertProductStatement.setFloat(3, product.getPrice()); // price

	            // Llenar los valores para la tabla de existencia
	            insertExistenceStatement.setString(1, product.getProductId()); // product_id
	            insertExistenceStatement.setInt(2, product.getExistence()); // existence
	            insertExistenceStatement.setString(3, product.getStationeryId()); // stationery_id

	            // Ejecutar las inserciones
	            insertProductStatement.executeUpdate();
	            insertExistenceStatement.executeUpdate();
	        }
	        // Confirmar la transacción
            connection.commit();
    	}catch (SQLException e) {
            // En caso de error, revertir la transacción
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } 
        finally {
            try {
                if (connection != null) {
                    // Restablecer el modo de autocommit
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	public void pruebas(List<Products> productData)  {	
		for(Products product : productData) {
			//System.out.println(a);
			System.out.println(product.getProductId());
			System.out.println(product.getName());
			System.out.println(product.getPrice());
			System.out.println(product.getExistence());
			System.out.println(product.getStationeryId());

		}
		//System.out.println("ya es toda");
	}
	
	public String[] getColumnName() {
		 return columnName;
	}
	
	
	public List<String[]> getRows(){
		return new ArrayList<>(resultSetData);
	}
	
	public int getColumnCount() {
		return columnCount;
	}
	
}
