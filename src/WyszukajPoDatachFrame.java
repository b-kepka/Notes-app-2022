import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

enum DateType {
    WPROWADZENIA, WAZNOSCI
}
public class WyszukajPoDatachFrame extends JFrame {
    Notatki notatkiFrame;
    DateType dateType;
    Baza baza;
    WyszukajPoDatachFrame(Notatki notatkiFrame, DateType dateType) {
        this.notatkiFrame = notatkiFrame;
        this.dateType = dateType;
        switch (dateType) {
            case WPROWADZENIA:
                setTitle("Wyszukaj po dacie dodania");
                break;
            case WAZNOSCI:
                setTitle("Wyszukaj po dacie ważności");
                break;
        }
        setSize(220,130);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        baza = new Baza();
        initComponents();
    }
    public void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JPanel line_panel = new JPanel();
        line_panel.setLayout(new BoxLayout(line_panel, BoxLayout.LINE_AXIS));
        JPanel year_panel = new JPanel();
        year_panel.setLayout(new BoxLayout(year_panel, BoxLayout.PAGE_AXIS));
        JLabel year_label = new JLabel("Rok:");
        JTextField year_field = new JTextField(4);
        year_label.setAlignmentX(Component.LEFT_ALIGNMENT);
        year_field.setAlignmentX(Component.LEFT_ALIGNMENT);
        year_panel.add(year_label);
        year_panel.add(year_field);
        JPanel month_panel = new JPanel();
        month_panel.setLayout(new BoxLayout(month_panel, BoxLayout.PAGE_AXIS));
        JLabel month_label = new JLabel("Miesiąc:");
        JTextField month_field = new JTextField(4);
        month_label.setAlignmentX(Component.LEFT_ALIGNMENT);
        month_field.setAlignmentX(Component.LEFT_ALIGNMENT);
        month_panel.add(month_label);
        month_panel.add(month_field);
        JPanel day_panel = new JPanel();
        JLabel day_label = new JLabel("Dzień:");
        JTextField day_field = new JTextField(4);
        day_label.setAlignmentX(Component.LEFT_ALIGNMENT);
        day_field.setAlignmentX(Component.LEFT_ALIGNMENT);
        day_panel.add(day_label);
        day_panel.add(day_field);
        day_panel.setLayout(new BoxLayout(day_panel, BoxLayout.PAGE_AXIS));
        year_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        month_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        day_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        line_panel.add(year_panel);
        line_panel.add(Box.createHorizontalGlue());
        line_panel.add(month_panel);
        line_panel.add(day_panel);
        line_panel.setBorder(BorderFactory.createEmptyBorder(10,5,30,5));
        JButton submit = new JButton("Szukaj");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setMaximumSize(new Dimension(210,40));
        panel.add(line_panel);
        submit.addActionListener(e -> {
            int year,month,day;
            LocalDate date;
            try {
                year = Integer.parseInt(year_field.getText());
                month = Integer.parseInt(month_field.getText());
                day = Integer.parseInt(day_field.getText());
                date = LocalDate.of(year,month,day);
            } catch (DateTimeException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Podaj poprawną datę!",
                        "Format daty niepoprawny!",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            ArrayList<String[]> wynik = baza.wyszukajNotatkebyDate(dateType, date.toString());
            notatkiFrame.setContentPane(Notatki.createNotatkiPanel(wynik));
            notatkiFrame.revalidate();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        panel.add(submit);
        setContentPane(panel);
    }
}
