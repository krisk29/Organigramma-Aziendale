package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.Organigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SchermataPrincipale extends JFrame {

    final String TITOLO = "Organigramma Aziendale";
    JPanel finestra = new JPanel();
    JScrollPane finestraScroll = new JScrollPane(finestra, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    PannelloDiGestione pannelloDiGestione;
    DisegnaOrganigramma organigrammaPanel;

    Organigramma organigramma;

    public SchermataPrincipale() {
        super();
        this.setTitle(TITOLO);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Termina il programma quando chiuso
        this.setResizable(true);
        // Setto di base a schermo intero la finestra
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Massimizza la finestra
        // Aggiungo il JScrollPane al JFrame
        this.getContentPane().add(finestraScroll); // Rendo la finestra scrollabile se necessario
        // Setto un layout manager
        finestra.setLayout(new BorderLayout());
        // Aggiungo la barra del menu
        creaMenu();
        // Mostra il popup per inserire il nome dell'organigramma
        apriPopupNomeOrganigramma();
    }

    private void creaMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");

        // Aggiungi il menu "Salva"
        JMenuItem salvaMenuItem = new JMenuItem("Salva");
        salvaMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaOrganigramma();
            }
        });
        file.add(salvaMenuItem);

        menuBar.add(file);
        this.setJMenuBar(menuBar);
    }

    private void apriPopupNomeOrganigramma() {
        String nomeOrganigramma = JOptionPane.showInputDialog(this, "Inserisci il nome dell'organigramma:");
        if (nomeOrganigramma != null && !nomeOrganigramma.isEmpty()) {
            this.setTitle(TITOLO + " - " + nomeOrganigramma);
            organigramma = new Organigramma();
            UnitaOrganizzativa root = new UnitaOrganizzativa(nomeOrganigramma); // Creazione della prima unità organizzativa con il nome inserito
            organigramma.setRoot(root);
            aggiungiPannelloDiGestione(); // pannello di modifica
            aggiungiPannelloOrganigramma(); // pannello che disegna l'organigramma
            this.setVisible(true); // Impostare la visibilità alla fine
        } else {
            // Inserire gestione nel caso in cui il nome inserito sia vuoto o annullato
            JOptionPane.showMessageDialog(this, "Il nome dell'organigramma non può essere vuoto.", "Errore", JOptionPane.ERROR_MESSAGE);
            apriPopupNomeOrganigramma(); // Richiama il popup per inserire nuovamente il nome
        }
    }

    private void aggiungiPannelloDiGestione() {
        pannelloDiGestione = new PannelloDiGestione(this, organigramma);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(pannelloDiGestione, BorderLayout.CENTER);
        finestra.add(rightPanel, BorderLayout.EAST);
    }

    private void aggiungiPannelloOrganigramma() {
        organigrammaPanel = new DisegnaOrganigramma(organigramma.getRoot());
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(organigrammaPanel, BorderLayout.CENTER);
        finestra.add(centerPanel, BorderLayout.CENTER);
    }

    private void salvaOrganigramma() {
        JFileChooser fileChooser = new JFileChooser();
        int scelta = fileChooser.showSaveDialog(this);

        if (scelta == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

                objectOutputStream.writeObject(organigramma);
                JOptionPane.showMessageDialog(this, "Organigramma salvato con successo.", "Salvataggio", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                //JOptionPane.showMessageDialog(this, "Errore durante il salvataggio dell'organigramma:\n" + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                System.err.println(e);
            }
        } else if (scelta == JFileChooser.CANCEL_OPTION) {
            // caso in cui si clicca cancel
        }
    }



    public void refreshOrganigramma() {
        finestra.remove(organigrammaPanel);
        aggiungiPannelloOrganigramma();
        finestra.revalidate();
        finestra.repaint();
        organigramma.printOrganigramma(organigramma.getRoot(),0); // Stampa l'organigramma nel terminale
        System.out.println(""); //spazio fra un organigramma stampato
    }

    public static void main(String[] args) {
        // da controllare questa cosa
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SchermataPrincipale();
            }
        });
    }
}
