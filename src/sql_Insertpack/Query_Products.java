package sql_Insertpack;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Query_Products extends Frame_Insert_Data implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel panel_Contenido;
	private JComboBox<String> sucursal;
	private List<TextField> nTextFields;
	
	private JLabel lbl_Welcome;
    private JLabel lbl_Instruccion;
	
	private Data getData;
	
	
	public Query_Products(){
		super();
		panel_Contenido=getPanel_Contenido();
		
		lbl_Welcome=getlbl_Welcome();
	    lbl_Instruccion= getlbl_Instruccion();
	    
		addComponents();
		
		setPanel_Contenido(panel_Contenido);
	}
	
	private void addComponents() {
		nTextFields = new ArrayList<>();
	    getData=new Data();
	    
	    lbl_Welcome.setText("Inventario");
		lbl_Instruccion.setText("Te encuentras en la interfaz donde puedes ver todos tus productos");
	    
	    Data getSpecificData =new  Data("select p.product_id as ID, name as Nombre, price as Precio, existence as Existencias,  stationery_id as 'Numero de Tienda'"
	    		+" from product as p inner join existence_p_001 as ex"
	    		+" on p.product_id=ex.product_id");
	    
	    getSpecificData.queryTable("");
	    
	    
	    /*
	     * 
	    getSpecificData.printTuples();
	    String[] a =getSpecificData.getColumn(0);
	    String[] b =getSpecificData.getColumn(3);
	    
	    System.out.println(a.hashCode()+" || "+b.hashCode());
	    
	    print(a);
	    print(b);
	    
	    */
	    int numeroColumnasproductos=getSpecificData.getColumnCount();
	    
	    String[] columnNames = getSpecificData.getColumnName();
	    
	    List<String[]> data= getSpecificData.getRows();
	    
	    JTable table = new JTable(data.toArray(new String[data.size()][]), columnNames);
	    
	    table.setEnabled(false);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    // Add the scroll pane to your JFrame or JPanel where you want to display the table.
	
	    
	    
	    panel_Contenido.add(scrollPane,BorderLayout.CENTER);
	
	    
	    
	    JPanel Panel_Ubicacion = new JPanel(new GridLayout(2, 1, 10, 10));
	    
	    
	    getData.queryTable("stationery_shop");
	    String[] Stationary_shop = getData.getColumn(0);
	    
	    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(Stationary_shop);
	    sucursal=new JComboBox<String>(model);
	    sucursal.addActionListener(this);
	    
	    JLabel lbl_Sucursal = new JLabel("Seleccione su Sucursal:");
	    Panel_Ubicacion.add(lbl_Sucursal);
	    Panel_Ubicacion.add(sucursal);
	    
	    TextField dynamic;
	    JLabel lbl_dynamic;
	    String[] Sucursal={"Direccion:","Localizacion:","Numero de Telefono:"};
	
	    for(int i=0;i<getData.getRows().size();i++) {
	    	lbl_dynamic = new JLabel(Sucursal[i]);
	    	dynamic=new TextField(getData.getRows().get(i).toString());
	        dynamic.enable(false);
	        Panel_Ubicacion.add(lbl_dynamic);
	        Panel_Ubicacion.add(dynamic);
	        nTextFields.add(dynamic);
	    }
	    
	    panel_Contenido.add(Panel_Ubicacion,BorderLayout.SOUTH);
	
	    sucursal.setSelectedIndex(0);
	    
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Seleccion de de Sucursal
		if(e.getSource()==sucursal) {
			// Obtener la opción seleccionada
	        int selectedOption = sucursal.getSelectedIndex();
	        
	        String[] registro = getData.getRows().get(selectedOption);
	        for(int i=1;i<registro.length;i++) {	        	
	        	//Edicion de Textfields Dinamicos evitando desbordamiento de index
	        	nTextFields.get(i-1).setText(registro[i]);
	        	//panel_Contenido.repaint();
	        }
		}
	}
	
	public void print(String[] xd)  {	
		for(String a:xd) {
			//System.out.println(a);
			System.out.println(a);
		}
		//System.out.println("ya es toda");
	}
}