package GUI;
import javax.swing.*;

import createReports.Inventory_Suppliers;
import sql_Insertpack.Insert_Products;
import sql_Insertpack.Query_Products;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel lblWelcome;
    private JLabel lblInstruccion;
    private JButton btnOption1;
    private JButton btnOption2;
    private JButton btnOption3;
    private JButton btnOption4;
    private JPanel panel;
    private JPanel panelINST;

    public gui() {
        setTitle("Papeleria Tienda_Virtual");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());
        panelINST = new JPanel(new BorderLayout());

        lblWelcome = new JLabel("Bienvenido");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        panelINST.add(lblWelcome, BorderLayout.NORTH);
        
        lblInstruccion = new JLabel("<html><div style='text-align: center;'>Desde este apartado puedes realizar reportes, "
                + "selecciona la opciones que deses</div></html>");
        lblInstruccion.setFont(new Font("Arial", Font.PLAIN, 16));
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        panelINST.add(lblInstruccion, BorderLayout.CENTER);
        
        panel.add(panelINST,BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        btnOption1 = new JButton("Busqueda por Periodo");
        btnOption1.addActionListener(this);
        optionsPanel.add(btnOption1);

        btnOption2 = new JButton("Proveedor y Contacto");
        btnOption2.addActionListener(this);
        optionsPanel.add(btnOption2);

        btnOption3 = new JButton("Inventario");
        btnOption3.addActionListener(this);

        optionsPanel.add(btnOption3);

        btnOption4 = new JButton("Insertar Productos");
        btnOption4.addActionListener(this);
        optionsPanel.add(btnOption4);

        panel.add(optionsPanel, BorderLayout.CENTER);

        JLabel lblFooter = new JLabel("Programa elaborado por Elias Gijón");
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 12));
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblFooter, BorderLayout.SOUTH);

        add(panel);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnOption1) {
			showDateSelectionDialog selectOnSelectedDates=new showDateSelectionDialog();
			selectOnSelectedDates.setVisible(true);
		}
		if(e.getSource()==btnOption2) {
			Inventory_Suppliers reporteInventario=new Inventory_Suppliers();
			reporteInventario.GenerarReportes();
		}
		if(e.getSource()==btnOption3) {
			dispose();
			Query_Products queryProducts=new Query_Products();
			queryProducts.setVisible(true);
		}
		if(e.getSource()==btnOption4) {
			dispose();
			Insert_Products AddProducts=new Insert_Products();
			AddProducts.setVisible(true);
		}
	}
}
