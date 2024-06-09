package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;

public class SchermataDiLavoro extends JFrame {

    final String TITOLO = "Organigramma Aziendale";
    JPanel finestra = new JPanel();
    JScrollPane finestraScroll = new JScrollPane(finestra, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    PannelloDiGestione pannelloDiGestione;
    Organigramma organigramma;

    public SchermataDiLavoro() {
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
        // Aggiungi i menu alla barra del menu
        menuBar.add(file);  //da creare il menu che gestisce i file
        // Imposta la barra del menu nel JFrame
        this.setJMenuBar(menuBar);
    }

    private void apriPopupNomeOrganigramma() {
        String nomeOrganigramma = JOptionPane.showInputDialog(this, "Inserisci il nome dell'organigramma:");
        if (nomeOrganigramma != null && !nomeOrganigramma.isEmpty()) {
            organigramma = new Organigramma();
            UnitaOrganizzativa root = new UnitaOrganizzativa(nomeOrganigramma); // Creazione della prima unità organizzativa con il nome inserito
            organigramma.setRoot(root);
            aggiungiPannelloDiGestione();
            this.setVisible(true); // Impostare la visibilità alla fine
        } else {
            // Inserire gestione nel caso in cui il nome inserito sia vuoto o annullato
            JOptionPane.showMessageDialog(this, "Il nome dell'organigramma non può essere vuoto.", "Errore", JOptionPane.ERROR_MESSAGE);
            apriPopupNomeOrganigramma(); // Richiama il popup per inserire nuovamente il nome
        }
    }


    private void aggiungiPannelloDiGestione() {
        pannelloDiGestione = new PannelloDiGestione(organigramma);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(pannelloDiGestione, BorderLayout.CENTER);
        finestra.add(rightPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SchermataDiLavoro();
            }
        });
    }
}
