package organigrammaAziendale.interfacciaGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//3 da sistemare, fare il menu per aggiungere e rimuovere
public class PannelloFunzioni extends JPanel{

    private JButton button1;
    private JButton button2;
    private JComboBox<String> comboBox;

    public PannelloFunzioni() {
        // Initialize components
        button1 = new JButton("Aggiungi");
        button2 = new JButton("Rimuovi");
        String[] comboBoxItems = {"Option 1", "Option 2", "Option 3"};
        comboBox = new JComboBox<>(comboBoxItems);

        // Set layout manager
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add components to the panel
        add(button1);
        add(button2);
        add(comboBox);

        // Add action listeners to buttons
        button1.addActionListener(new ButtonClickListener());
        button2.addActionListener(new ButtonClickListener());

        // Add action listener to ComboBox
        comboBox.addActionListener(new ComboBoxListener());
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            System.out.println(source.getText() + " clicked");
        }
    }

    private class ComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedItem = (String) comboBox.getSelectedItem();
            System.out.println("Selected: " + selectedItem);
        }
    }

}
