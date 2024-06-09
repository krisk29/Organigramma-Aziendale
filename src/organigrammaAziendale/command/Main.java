package organigrammaAziendale.command;
/*
import organigrammaAziendale.composite.*;
import organigrammaAziendale.command.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {
    private static UnitaOrganizzativa root;
    private static java.util.List<ElementoOrganigramma> organigramma;

    public static void main(String[] args) {
        // Crea un frame per visualizzare il disegno
        JFrame frame = new JFrame("Organigramma Aziendale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Inizializza l'unità organizzativa root
        root = new UnitaOrganizzativa("Azienda");

        // Crea una lista per tenere traccia degli elementi dell'organigramma
        organigramma = new ArrayList<>();
        organigramma.add(root);

        // Crea un pannello personalizzato per il disegno
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Disegna gli elementi dell'organigramma
                for (ElementoOrganigramma elemento : organigramma) {
                    DisegnaVisitor visitor = new DisegnaVisitor(g);
                    elemento.accept(visitor);
                }
            }
        };

        // Aggiungi un pulsante per aggiungere nuovi elementi
        JButton addButton = new JButton("Aggiungi Elemento");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                organigrammaAziendale.visitor.AddElementoDialog dialog = new organigrammaAziendale.visitor.AddElementoDialog(frame);
                dialog.setVisible(true);

                if (dialog.isConfirmed()) {
                    String nome = dialog.getNome();
                    String tipo = dialog.getTipo();

                    if (nome != null && !nome.trim().isEmpty()) {
                        if (tipo.equals("Unità Organizzativa")) {
                            UnitaOrganizzativa nuovaUnita = new UnitaOrganizzativa(nome);
                            nuovaUnita.setX(100);
                            nuovaUnita.setY(organigramma.size() * 60); // Posizionamento semplice per esempio

                            AggiungiUnitaOrganizzativaCommand command = new AggiungiUnitaOrganizzativaCommand(root, nuovaUnita);
                            command.execute();
                            organigramma.add(nuovaUnita);
                        } else if (tipo.equals("Ruolo")) {
                            Ruolo nuovoRuolo = new Ruolo(nome);
                            nuovoRuolo.setX(100);
                            nuovoRuolo.setY(organigramma.size() * 60); // Posizionamento semplice per esempio

                            AggiungiRuoloCommand command = new AggiungiRuoloCommand(root, nuovoRuolo);
                            command.execute();
                            organigramma.add(nuovoRuolo);
                        }
                        panel.repaint();
                    }
                }
            }
        });

        frame.add(addButton, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
*/