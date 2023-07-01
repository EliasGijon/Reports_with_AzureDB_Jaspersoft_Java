package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import reports.JasperByCollectionBeanData;

public class showDateSelectionDialog extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnOk;
	private JDateChooser endDateChooser;
	private JDateChooser startDateChooser;
	
	public showDateSelectionDialog() {
		setTitle("Seleccionar fechas de inicio y fin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel calendarPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel lblStartDate = new JLabel("Fecha de inicio:");
        lblStartDate.setFont(new Font("Arial", Font.BOLD, 14));
        calendarPanel.add(lblStartDate);

        startDateChooser = new JDateChooser();
        startDateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        calendarPanel.add(startDateChooser);

        JLabel lblEndDate = new JLabel("Fecha de fin:");
        lblEndDate.setFont(new Font("Arial", Font.BOLD, 14));
        calendarPanel.add(lblEndDate);

        endDateChooser = new JDateChooser();
        endDateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        calendarPanel.add(endDateChooser);

        panel.add(calendarPanel, BorderLayout.CENTER);

        btnOk = new JButton("OK");
        btnOk.setFont(new Font("Arial", Font.BOLD, 14));
        btnOk.addActionListener(this);
        panel.add(btnOk, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnOk) {
			Date startDate = startDateChooser.getDate();
            Date endDate = endDateChooser.getDate();

             if (startDate != null && endDate != null) {
                if (endDate.after(startDate)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    SimpleDateFormat year = new SimpleDateFormat("yyyy");
                    SimpleDateFormat month = new SimpleDateFormat("MM");
                    String startDateFormat = dateFormat.format(startDate);
                    String endDateFormat = dateFormat.format(endDate);
                    
                    DateFormatSymbols dfs = new DateFormatSymbols();
                    
                    String month_start = dfs.getMonths()[Integer.parseInt(month.format(startDate))-1];
                    String year_start = year.format(startDate);
                    String month_end = dfs.getMonths()[Integer.parseInt(month.format(endDate))-1];
                    String year_end = year.format(endDate);
                    month_start=month_start.toUpperCase();
                    month_end=month_end.toUpperCase();
                    
                    JasperByCollectionBeanData reportes = new JasperByCollectionBeanData(month_start, year_start, month_end, year_end, startDateFormat,endDateFormat );
                    reportes.GenerarReportes();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "La fecha de fin debe ser posterior a la fecha de inicio");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione fechas válidas");
            }
        }	
	}
}
