import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

class WyszukajPoIdFrame extends JFrame {
    Baza baza;
    Notatki notatkiFrame;
    WyszukajPoIdFrame(Notatki notatkiFrame) {
        this.notatkiFrame = notatkiFrame;
        setTitle("Wyszukaj Notatkę po ID");
        setSize(200, 130);
        setResizable(false);
        setLocationRelativeTo(null);
        baza = new Baza();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel id_label = new JLabel("Podaj ID notatki:");
        id_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(id_label);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField id_field = new JTextField(10);
        id_field.setAlignmentX(Component.CENTER_ALIGNMENT);
        id_field.setMaximumSize(id_field.getPreferredSize());
        panel.add(id_field);

        panel.add(Box.createVerticalGlue());

        JButton button = new JButton("Szukaj");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(id_field.getText());
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Podaj poprawne id",
                        "Błędne id",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            ArrayList<String[]> wynik = baza.wyszukajNotatkebyID(id);

            notatkiFrame.setContentPane(Notatki.createNotatkiPanel(wynik));
            notatkiFrame.revalidate();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        panel.add(button);


        setContentPane(panel);
    }
}
