import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

class UsunNotatkeFrame extends JFrame {

    Baza baza;

    UsunNotatkeFrame() {
        setTitle("Notatki - Bartłomiej Kępka");
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

        JLabel idLabel = new JLabel("Podaj ID notatki:");
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(idLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField idField = new JTextField(10);
        idField.setAlignmentX(Component.CENTER_ALIGNMENT);
        idField.setMaximumSize(idField.getPreferredSize());
        panel.add(idField);

        panel.add(Box.createVerticalGlue());

        JButton button = new JButton("Usuń");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
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
            baza.usunNotatke(id);
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        panel.add(button);


        setContentPane(panel);
    }
}
