package sql_Insertpack;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Insert_Products extends Frame_Insert_Data implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel panel_Contenido;
	private JComboBox<String> sucursal;
	private List<TextField> nTextFields;
	private Data getData;
	
	public Insert_Products() {
		super();
		panel_Contenido=getPanel_Contenido();
		
		addComponents();
		
		setPanel_Contenido(panel_Contenido);
	}
	
	private void addComponents() {
		nTextFields = new ArrayList<>();
        getData=new Data();
        
        getData.queryTable("product as p inner join existence_p_001 as ex "
        		+ "on  p.product_id=ex.product_id");
        
        getData.printTuples();
        String[] a =getData.getColumn(0);
        String[] b =getData.getColumn(3);
        
        System.out.println(a.hashCode()+" || "+b.hashCode());
        
        print(a);
        print(b);
        
        getData.queryTable("stationery_shop");
        String[] Stationary_shop = getData.getColumn(0);
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(Stationary_shop);
        sucursal=new JComboBox<String>(model);
        sucursal.addActionListener(this);

        panel_Contenido.add(sucursal,BorderLayout.CENTER);
        
        TextField dynamic;

        for(int i=0;i<getData.getRows().size();i++) {
        	dynamic=new TextField(getData.getRows().get(i).toString());
            dynamic.enable(false);
            panel_Contenido.add(dynamic,BorderLayout.CENTER);
            nTextFields.add(dynamic);
        }
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
            	System.out.println(registro[i-1]);
            	
            	//Edicion de Textfields Dinamicos evitando desbordamiento de index
            	nTextFields.get(i-1).setText(registro[i]);
            	//panel_Contenido.repaint();
            }
		}
	}
	
	public void print(String[] xd)  {	
		for(String a:xd) {
			//System.out.println(a);
			System.out.println(a.hashCode());
		}
		System.out.println("ya es toda");
	}
}
