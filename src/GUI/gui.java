package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import reports.JasperByCollectionBeanData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

        btnOption2 = new JButton("Opción 2");
        optionsPanel.add(btnOption2);

        btnOption3 = new JButton("Opción 3");
        optionsPanel.add(btnOption3);

        btnOption4 = new JButton("Opción 4");
        optionsPanel.add(btnOption4);

        panel.add(optionsPanel, BorderLayout.CENTER);

        JLabel lblFooter = new JLabel("Computo en la nube equipo cinco");
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
	}
}
