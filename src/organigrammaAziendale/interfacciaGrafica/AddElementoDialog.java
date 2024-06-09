package organigrammaAziendale.interfacciaGrafica;

import javax.swing.*;
import java.awt.*;

public class AddElementoDialog extends JDialog {
    private JTextField nomeField;
    private JComboBox<String> tipoComboBox;
    private JButton okButton;
    private boolean confirmed;

    public AddElementoDialog(Frame owner) {
        super(owner, "Aggiungi Elemento", true);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Tipo:"));
        tipoComboBox = new JComboBox<>(new String[]{"Unità Organizzativa", "Ruolo"});
        add(tipoComboBox);

        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });
        add(okButton);

        pack();
    }

    public String getNome() {
        return nomeField.getText();
    }

    public String getTipo() {
        return (String) tipoComboBox.getSelectedItem();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
