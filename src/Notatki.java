import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Notatki extends JFrame {
    Menu menu;
    Baza baza;

    public Notatki() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("LOOK AND FEEL EXCEPTION:");
            System.out.println(e);
        }
        setTitle("Notatki - Bartłomiej Kępka");
        setSize(500, 345);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    public void initComponents() {
        setContentPane(drawAddNotatka());
        menu = new Menu();
        menu.nowaNotatka.addActionListener(e -> {setContentPane(drawAddNotatka()); this.revalidate();});
        menu.usunNotatke.addActionListener(e -> new UsunNotatkeFrame());
        menu.szukajID.addActionListener(e -> new WyszukajPoIdFrame(this));
        menu.szukajDataDodania.addActionListener(e -> new WyszukajPoDatachFrame(this, DateType.WPROWADZENIA));
        menu.szukajDataWaznosci.addActionListener(e -> new WyszukajPoDatachFrame(this, DateType.WAZNOSCI));
        setJMenuBar(menu);
    }
    public JPanel drawAddNotatka() {
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        JPanel label_panel = new JPanel();
        label_panel.setLayout(new BoxLayout(label_panel, BoxLayout.X_AXIS));
        JPanel date_panel = new JPanel();
        date_panel.setLayout(new BoxLayout(date_panel, BoxLayout.X_AXIS));
        JLabel main_label = new JLabel("Nowa Notatka");
        main_label.setFont(new Font("Arial",Font.PLAIN, 20));
        main_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea textarea = new JTextArea(10,100);
        JScrollPane scrollpane = new JScrollPane(textarea);
        scrollpane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollpane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.add(Box.createVerticalGlue());
        JLabel year_label = new JLabel("Rok:");
        JLabel month_label = new JLabel("Miesiąc:");
        JLabel day_label = new JLabel("Dzień:");
        label_panel.add(year_label);
        label_panel.add(Box.createHorizontalGlue());
        label_panel.add(month_label);
        label_panel.add(Box.createHorizontalGlue());
        label_panel.add(day_label);
        label_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        label_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField year_field = new JTextField(4);
        JTextField month_field = new JTextField(4);
        JTextField day_field = new JTextField(4);
        year_field.setMaximumSize(year_field.getPreferredSize());
        month_field.setMaximumSize(month_field.getPreferredSize());
        day_field.setMaximumSize(day_field.getPreferredSize());
        date_panel.add(year_field);
        date_panel.add(Box.createHorizontalGlue());
        date_panel.add(month_field);
        date_panel.add(Box.createHorizontalGlue());
        date_panel.add(day_field);
        date_panel.setBorder(BorderFactory.createEmptyBorder(0,10,20,10));
        date_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton submit_btn = new JButton("Dodaj Notatkę");
        submit_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit_btn.setMaximumSize(new Dimension(480,40));
        submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textarea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Podaj notatkę!","Niewłaściwa notatka!", JOptionPane.ERROR_MESSAGE);
                } else if (year_field.getText().isEmpty() || year_field.getText().matches("[a-z A-Z]") || Integer.parseInt(year_field.getText())>9999) {
                    JOptionPane.showMessageDialog(null,"Podaj rok!","Niewłaściwy rok!", JOptionPane.ERROR_MESSAGE);
                } else if (month_field.getText().isEmpty() || month_field.getText().matches("[a-z A-Z]") || Integer.parseInt(month_field.getText())>12) {
                    JOptionPane.showMessageDialog(null,"Podaj miesiąc!","Niewłaściwy miesiąc!", JOptionPane.ERROR_MESSAGE);
                } else if (day_field.getText().isEmpty() || day_field.getText().matches("[a-z A-Z]") || Integer.parseInt(day_field.getText())>31) {
                    JOptionPane.showMessageDialog(null,"Podaj dzień!","Niewłaściwy dzień!", JOptionPane.ERROR_MESSAGE);
                } else {
                    baza = new Baza();
                    baza.dodajNotatke(textarea.getText(),year_field.getText()+"-"+month_field.getText()+"-"+day_field.getText());
                    JOptionPane.showMessageDialog(null,"Pomyślnie dodano notatkę!","Notatka została dodana!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        main_panel.add(main_label);
        main_panel.add(scrollpane);
        main_panel.add(label_panel);
        main_panel.add(date_panel);
        main_panel.add(submit_btn);
        main_panel.add(Box.createVerticalGlue());
        return main_panel;
    }
    public static JScrollPane createNotatkiPanel(ArrayList<String[]> notatki) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < notatki.size(); i++) {
            JPanel notatka_panel = new JPanel();
            notatka_panel.setLayout(new BoxLayout(notatka_panel, BoxLayout.Y_AXIS));
            notatka_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText("ID: "+notatki.get(i)[0]+"\nData wprowadzenia: "+notatki.get(i)[2]+"\nData ważności: "+notatki.get(i)[3]+"\nNotatka:\n"+notatki.get(i)[1]);
            notatka_panel.add(textArea);
            notatka_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.add(notatka_panel);
        }

        return new JScrollPane(panel);
    }
}
