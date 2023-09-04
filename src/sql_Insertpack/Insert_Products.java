package sql_Insertpack;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Insert_Products extends Frame_Insert_Data implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel panel_Contenido;
	
	private JLabel lbl_Welcome;
    private JLabel lbl_Instruccion;


	private JButton btnNuevaFila;
	private JButton btnRegistrar;
	private JButton btnRemoverFila;
	
	DefaultTableModel model;
	
	private Data getSpecificData;
	
	public Insert_Products() {
		super();
		panel_Contenido=getPanel_Contenido();
		
		lbl_Welcome=getlbl_Welcome();
	    lbl_Instruccion= getlbl_Instruccion();
		
		addComponents();
	}
	
	private void addComponents() {
		lbl_Welcome.setText("Productos");
		lbl_Instruccion.setText("Te encuentras en la interfaz de insercion de productos");
		
        getSpecificData =new  Data("SELECT TOP 1 p.product_id AS ID, name AS Nombre, price AS Precio, existence AS Existencias, stationery_id AS 'Numero de Tienda'"
										+" FROM product AS p INNER JOIN existence_p_001 AS ex"
										+" ON p.product_id = ex.product_id"
										+" ORDER BY p.product_id DESC");	
        
        getSpecificData.queryTable("");
        
        
        
        int numeroColumnasproductos=getSpecificData.getColumnCount();
                
        String[] columnNames = getSpecificData.getColumnName();
        
        List<String[]> data= getSpecificData.getRows();
        
        model = new DefaultTableModel(columnNames,0) {
	    	@Override
		    public boolean isCellEditable(int row, int column) {
		        // Si quieres que la columna 4 sea no editable (columna índice 3):
		        return column != numeroColumnasproductos-1; // Devuelve true para celdas editables, false para no editables
		    }
        };
        
        JTable table = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        panel_Contenido.add(scrollPane,BorderLayout.CENTER);

        
        JPanel Panel_Ubicacion = new JPanel(new GridLayout(1, 2, 10, 10));
        
        
        btnNuevaFila =new JButton("Nueva Fila");
        btnRemoverFila = new JButton("Eliminar Fila");
        btnRegistrar =new JButton("Registrar");
        
        btnNuevaFila.addActionListener(this);
        btnRegistrar.addActionListener(this);
        btnRemoverFila.addActionListener(this);
        
        
        Panel_Ubicacion.add(btnNuevaFila);
        Panel_Ubicacion.add(btnRemoverFila);
        Panel_Ubicacion.add(btnRegistrar);
        
        btnNuevaFila.doClick();
        
        panel_Contenido.add(Panel_Ubicacion,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Seleccion de de Sucursal
		if(e.getSource() == btnNuevaFila) {
	        model.addRow(new String[] {null,null,null,null,"001"});
		}
		if(e.getSource() == btnRemoverFila) {
			if(model.getRowCount()==1) {
	            JOptionPane.showMessageDialog(null, "Para Insertar contenido almenos debes tener una Fila", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			else {
				model.removeRow(model.getRowCount()-1);
			}
		}
		if(e.getSource() == btnRegistrar) {
			boolean canInsert=insertMetod();
			if(canInsert) {
				
				List<Products> parametros= new ArrayList<>();
				Products dynamic;
				for(int i=0;i<model.getRowCount();i++) {
					dynamic=new Products();
					for(int j=0;j<model.getColumnCount();j++) {
						switch (j) {
							case 0:	dynamic.setProductId(model.getValueAt(i, j).toString());
								break;
							case 1: dynamic.setName((String) model.getValueAt(i, j));
								break;
							case 2: dynamic.setPrice(Float.parseFloat((String) model.getValueAt(i, j)));
								break;
							case 3: dynamic.setExistence(Integer.parseInt((String) model.getValueAt(i,j)));
								break;
							case 4: dynamic.setStationeryId((String) model.getValueAt(i, j));
								break;
						}
					}
					parametros.add(dynamic);
				}
				
				getSpecificData.InsertInBD(parametros);;
				
				
				//Limpieza
				if(model.getRowCount()>1) {
					for(int i=0;i<model.getRowCount();i++) {
						model.removeRow(model.getRowCount()-1);
					}
				}
				model.removeRow(model.getRowCount()-1);
				btnNuevaFila.doClick();
			}
			else {
	            JOptionPane.showMessageDialog(null, "Necesitas rellenar correctamente todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
	
	public void pruebas(List<Products> productData)  {	
		for(Products product : productData) {
			System.out.println(product.getProductId());
			System.out.println(product.getName());
			System.out.println(product.getPrice());
			System.out.println(product.getExistence());
			System.out.println(product.getStationeryId());
		}
	}
	
	public boolean insertMetod()  {
		boolean isvalid=true;	
		
		String id, nombre, numeroDeTienda;
		float precio;
		int existencias;
		boolean campos[][] = new boolean [model.getRowCount()][model.getColumnCount()-1];
		
		for(int i=0;i<campos.length;i++) {
			for(int j=0;j<campos[i].length;j++) {
				switch (j) {
					case 0:	campos[i][j] = isString(i,j);
						break;
					case 1: campos[i][j] = isString(i,j);
						break;
					case 2:	campos[i][j] = isFloat(i,j);
						break;
					case 3: campos[i][j] = isInt(i,j);
						break;
					//case 4: isvalid = isString(i,j);
						//break;
				}
				if (!campos[i][j]) {
		            isvalid = false;
		        }
			}
		}
		return isvalid;
	}
	
	public boolean isString(int fila, int columna) {
        boolean isString=false;
		try {
			String value =(String) model.getValueAt(fila, columna);
			isString=!validarVacio(value);
			
			if(!isString) {
	            JOptionPane.showMessageDialog(null, "Valor en la fila " + (fila + 1) + ", columna " + (columna + 1) + " no es una cadena.", "Error", JOptionPane.ERROR_MESSAGE);
			} 
		
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valor en la fila " + (fila + 1) + ", columna " + (columna + 1) + " no es una cadena.", "Error", JOptionPane.ERROR_MESSAGE);
             // No es una cadena
        }
        return isString;
    }
	
	public boolean validarVacio(String cadena){
		return cadena.trim().isEmpty();
	}

	public boolean isInt(int fila, int columna) {
	    boolean isInt = false;
	    try {
	        int value =Integer.parseInt((String) model.getValueAt(fila, columna));
            isInt = true; // Es un entero
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Valor en la fila " + (fila + 1) + ", columna " + (columna + 1) + " no es un entero.", "Error", JOptionPane.ERROR_MESSAGE);
	        // No es un entero
	    }
	    return isInt;
	}

	public boolean isFloat(int fila, int columna) {
	    boolean isFloat = false;
	    try {
	        float value =Float.parseFloat((String) model.getValueAt(fila, columna));
            isFloat = true; // Es un flotante
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Valor en la fila " + (fila + 1) + ", columna " + (columna + 1) + " no es un flotante.", "Error", JOptionPane.ERROR_MESSAGE);
	        // No es un flotante
	    }
	    return isFloat;
	}	
}
