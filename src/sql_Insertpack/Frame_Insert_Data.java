package sql_Insertpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import createReports.ConnectionManager;
import createReports.Employee;
import backimg.JP_IMG;

public class Frame_Insert_Data extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblWelcome;
    private JLabel lblInstruccion;
    private JLabel lblFooter;
    
    private JPanel panel_general;
    
    private JPanel panel_Norte;
    private JPanel panel_Contenido=null;
    private JPanel panel_Inferior;
    
    //private JPanel panel_Central;

    
    // Define an array of Color objects
    private Color[] colors = {
    		new Color(0x77, 0x7C, 0x74),
    		new Color(52, 73, 94),
    		new Color(44, 62, 80),
    		new Color(236, 240, 241),
    		new Color(51, 51, 51), 
    		new Color(242, 242, 242), 
    		new Color(0, 136, 255),
    		new Color(255, 102, 0),
    		Color.WHITE};
    private Color c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");

    
	public Frame_Insert_Data() {
		setTitle("Papeleria Tienda_Virtual");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel JP_BG_IMG = new JP_IMG();
        JP_BG_IMG.setBorder(new EmptyBorder(0, 0, 0, 0));
		JP_BG_IMG.setLayout(new BorderLayout(0, 0));
        setContentPane(JP_BG_IMG);
        
        panel_general = new JPanel(new BorderLayout());
        panel_general.setBackground(c);
        panel_general.setOpaque(false);
        
        panel_Norte = new JPanel(new BorderLayout());
        panel_Norte.setBackground(colors[2]);
        panel_Norte.setOpaque(false);

        setPanel_Contenido(new JPanel(new GridLayout(2, 1, 10, 10)));
        getPanel_Contenido().setBorder(new EmptyBorder(10, 10, 10, 10));
        getPanel_Contenido().setBackground(c);
        getPanel_Contenido().setOpaque(false);
        
        panel_Inferior = new JPanel(new BorderLayout());
        panel_Inferior.setBackground(colors[0]);
        //panel_Inferior.setOpaque(false);
        
        lblWelcome = new JLabel("Bienvenido");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblInstruccion = new JLabel("<html><div style='text-align: center;'>Desde este apartado puedes realizar reportes, "
                + "selecciona la opciones que deses</div></html>");
        lblInstruccion.setFont(new Font("Arial", Font.PLAIN, 16));
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);

        
        lblFooter = new JLabel("Computo en la nube equipo cinco");
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 12));
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        //lblFooter.repaint(); // Repaint the component
       

        /*
        
        Data getData=new Data();
        getData.queryTable("stationery_shop");
        getData.printTuples();
        
        String[] a = getData.getColumn(0);
        
        getData.queryTable("service");
        getData.printTuples();
        
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(a);
       
        JComboBox<String> sucursal=new JComboBox<String>(model);
        
       
        
        
        String[] b = getData.getColumn(0);
        print(a);
        print(b);
        
        getData.queryTable("supplier");
        getData.printTuples();           
 */

        panel_general.add(panel_Norte,BorderLayout.NORTH);
        panel_general.add(getPanel_Contenido(), BorderLayout.CENTER);
        panel_general.add(panel_Inferior,BorderLayout.SOUTH);
        
        panel_Norte.add(lblWelcome, BorderLayout.NORTH);
        panel_Norte.add(lblInstruccion, BorderLayout.CENTER);
        
        //panel_Contenido.add(sucursal,BorderLayout.CENTER);
        panel_Inferior.add(lblFooter, BorderLayout.SOUTH);
        

        JP_BG_IMG.add(panel_general,BorderLayout.CENTER);
        setVisible(true);
	}
	public void print(String[] xd)  {	
		for(String a:xd) {
			System.out.println(a);
		}			
	}
	
	public JPanel getPanel_Contenido(){
		return panel_Contenido;
	}
	public void setPanel_Contenido(JPanel panel_Contenido) {
		this.panel_Contenido = panel_Contenido;
	}
}
