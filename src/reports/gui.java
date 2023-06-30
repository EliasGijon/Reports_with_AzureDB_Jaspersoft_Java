package reports;
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

public class gui extends JFrame {
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
        btnOption1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDateSelectionDialog();
            }
        });
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

    private void showDateSelectionDialog() {
        JFrame frame = new JFrame("Seleccionar fechas de inicio y fin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel calendarPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel lblStartDate = new JLabel("Fecha de inicio:");
        lblStartDate.setFont(new Font("Arial", Font.BOLD, 14));
        calendarPanel.add(lblStartDate);

        JDateChooser startDateChooser = new JDateChooser();
        startDateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        calendarPanel.add(startDateChooser);

        JLabel lblEndDate = new JLabel("Fecha de fin:");
        lblEndDate.setFont(new Font("Arial", Font.BOLD, 14));
        calendarPanel.add(lblEndDate);

        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        calendarPanel.add(endDateChooser);

        panel.add(calendarPanel, BorderLayout.CENTER);

        JButton btnOk = new JButton("OK");
        btnOk.setFont(new Font("Arial", Font.BOLD, 14));
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date startDate = startDateChooser.getDate();
                Date endDate = endDateChooser.getDate();

                 if (startDate != null && endDate != null) {
                    if (endDate.after(startDate)) {
                        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        //String startDateFormat = dateFormat.format(startDate);
                        //String endDateFormat = dateFormat.format(endDate);
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

                        
                        //System.out.println(month_start+" "+year_start+" "+" "+month_end+" "+year_end);
                        
                        /*Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDateFormat);

                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH) + 1;

                        

                        //Variables del Plazo
                        System.out.print(year+ " " +nombreMes);
                        
                        
                        JOptionPane.showMessageDialog(frame, "Fechas seleccionadas:\nInicio: " + startDateFormat
                                +"\nFin: " + endDateFormat);
                        */
                    } else {
                        JOptionPane.showMessageDialog(frame, "La fecha de fin debe ser posterior a la fecha de inicio");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor, seleccione fechas válidas");
                }
            }
        });
        panel.add(btnOk, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
