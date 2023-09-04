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
	private JLabel lbl_Welcome;
    private JLabel lbl_Instruccion;
    private JLabel lbl_Footer;
    
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

        setPanel_Contenido(new JPanel(new BorderLayout()));
        getPanel_Contenido().setBorder(new EmptyBorder(10, 10, 10, 10));
        getPanel_Contenido().setBackground(c);
        getPanel_Contenido().setOpaque(false);
        
        panel_Inferior = new JPanel(new BorderLayout());
        panel_Inferior.setBackground(colors[0]);
        //panel_Inferior.setOpaque(false);
        
        lbl_Welcome = new JLabel("Bienvenido");
        lbl_Welcome.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_Welcome.setHorizontalAlignment(SwingConstants.CENTER);
        
        lbl_Instruccion = new JLabel("<html><div style='text-align: center;'>Desde este apartado puedes realizar reportes, "
                + "selecciona la opciones que deses</div></html>");
        lbl_Instruccion.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl_Instruccion.setHorizontalAlignment(SwingConstants.CENTER);

        
        lbl_Footer = new JLabel("Programa elaborado por Elias Gijón");
        lbl_Footer.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl_Footer.setHorizontalAlignment(SwingConstants.CENTER);

        panel_general.add(panel_Norte,BorderLayout.NORTH);
        panel_general.add(getPanel_Contenido(), BorderLayout.CENTER);
        panel_general.add(panel_Inferior,BorderLayout.SOUTH);
        
        panel_Norte.add(lbl_Welcome, BorderLayout.NORTH);
        panel_Norte.add(lbl_Instruccion, BorderLayout.CENTER);
        
        //panel_Contenido.add(sucursal,BorderLayout.CENTER);
        panel_Inferior.add(lbl_Footer, BorderLayout.SOUTH);
        

        JP_BG_IMG.add(panel_general,BorderLayout.CENTER);
        setVisible(true);
	}
	public void print(String[] xd)  {	
		for(String a:xd) {
			System.out.println(a);
		}			
	}
	
	
	public JLabel getlbl_Instruccion(){
		return lbl_Instruccion;
	}
	public void setlbl_Instruccion(JLabel lbl_Instruccion) {
		this.lbl_Instruccion = lbl_Instruccion;
	}
	public JLabel getlbl_Welcome(){
		return lbl_Welcome;
	}
	public void setlbl_Welcome(JLabel lbl_Welcome) {
		this.lbl_Welcome = lbl_Welcome;
	}
	
	public JPanel getPanel_Contenido(){
		return panel_Contenido;
	}
	public void setPanel_Contenido(JPanel panel_Contenido) {
		this.panel_Contenido = panel_Contenido;
	}
}
