package organigrammaAziendale.interfacciaGrafica;

import javax.swing.*;
import java.awt.*;

public class Prova {

    public static void main(String[] args) {
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creazione di un pannello con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());

        // Creazione di componenti da aggiungere al pannello
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        // Creazione dei vincoli di griglia per i componenti
        GridBagConstraints gbc = new GridBagConstraints();

        // Posizionamento e dimensionamento del primo pulsante
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button1, gbc);

        // Posizionamento e dimensionamento del secondo pulsante
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button2, gbc);

        // Posizionamento e dimensionamento del secondo pulsante
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button2, gbc);

        // Aggiunta del pannello al frame
        frame.getContentPane().add(panel);

        // Impostazione delle dimensioni del frame e visualizzazione
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

}
