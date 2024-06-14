package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.Organigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SchermataPrincipale extends JFrame {

    final String TITOLO = "Organigramma Aziendale";
    JPanel finestra = new JPanel();

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
        this.getContentPane().add(finestra);
        // Setto un layout manager
        finestra.setLayout(new BorderLayout());
        // Aggiungo la barra del menu
        creaMenu();
    }

    public SchermataPrincipale(Organigramma organigramma) {
        this();
        this.organigramma = organigramma;
        this.setTitle(TITOLO + " - " + organigramma.getRoot().getNome());
        aggiungiPannelloDiGestione(); // pannello di modifica
        aggiungiPannelloOrganigramma(); // pannello che disegna l'organigramma
        this.setVisible(true); // Impostare la visibilità alla fine
    }

    private void creaMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem nuovo = new JMenuItem("Nuovo");
        nuovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuovaSchermata();
            }
        });

        JMenuItem salva = new JMenuItem("Salva");
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaOrganigramma();
            }
        });

        JMenuItem carica = new JMenuItem("Carica");
        carica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caricaOrganigramma();
            }
        });

        JMenuItem esci = new JMenuItem("Esci");
        esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                esciProgramma();
            }
        });

        menuBar.add(nuovo);
        menuBar.add(salva);
        menuBar.add(carica);
        menuBar.add(esci);

        this.setJMenuBar(menuBar);
    }


    private void nuovaSchermata() {
        SchermataPrincipale schermata = new SchermataPrincipale();
        schermata.apriPopupNomeOrganigramma();

        // Aggiungi il controllo sul nome dell'organigramma
        if (schermata.getOrganigramma() != null) {
            schermata.setVisible(true);
            dispose();
        } else {
            // In caso di nome vuoto, non fare nulla
            schermata.dispose();  // Chiudi la finestra che non deve essere mostrata
        }
    }

    public Organigramma getOrganigramma() {
        return this.organigramma;
    }

    private void caricaOrganigramma() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("File di testo (.txt)", "txt");
        FileNameExtensionFilter filterCsv = new FileNameExtensionFilter("File CSV (.csv)", "csv");
        fileChooser.addChoosableFileFilter(filterTxt);
        fileChooser.addChoosableFileFilter(filterCsv);

        fileChooser.setDialogTitle("Seleziona il file da caricare");

        int selezione = fileChooser.showOpenDialog(this);

        if (selezione == JFileChooser.APPROVE_OPTION) {
            File fileSelezionato = fileChooser.getSelectedFile();
            caricaOrganigrammaDaFile(fileSelezionato);
        }
    }



    private void esciProgramma() {
        int scelta = JOptionPane.showConfirmDialog(this, "Vuoi salvare prima di uscire?", "Conferma Uscita", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (scelta == JOptionPane.YES_OPTION) {
            salvaOrganigramma();
            System.exit(0);
        } else if (scelta == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }



    public void apriPopupNomeOrganigramma() {
        String nomeOrganigramma = JOptionPane.showInputDialog(this, "Inserisci il nome dell'organigramma:");
        if (nomeOrganigramma != null && !nomeOrganigramma.isEmpty()) {
            this.setTitle(TITOLO + " - " + nomeOrganigramma);
            organigramma = new Organigramma();
            UnitaOrganizzativa root = new UnitaOrganizzativa(nomeOrganigramma); // Creazione della prima unità organizzativa con il nome inserito
            organigramma.setRoot(root);
            aggiungiPannelloDiGestione(); // pannello di modifica
            aggiungiPannelloOrganigramma(); // pannello che disegna l'organigramma
        } else {
            // Nome vuoto, mostra un messaggio di errore
            JOptionPane.showMessageDialog(this, "Il nome dell'organigramma non può essere vuoto.", "Errore", JOptionPane.ERROR_MESSAGE);
            // Non fare altro perché il nome dell'organigramma non è stato inserito correttamente
            organigramma = null; // Imposta organigramma a null per segnalare che non è stato creato correttamente
        }
    }



    public void aggiungiPannelloDiGestione() {
        pannelloDiGestione = new PannelloDiGestione(this, organigramma);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(pannelloDiGestione, BorderLayout.CENTER);
        finestra.add(rightPanel, BorderLayout.EAST);
    }

    public void aggiungiPannelloOrganigramma() {
        organigrammaPanel = new DisegnaOrganigramma(organigramma.getRoot());
        JScrollPane scrollPane = new JScrollPane(organigrammaPanel);  // Aggiunta del JScrollPane
        scrollPane.setPreferredSize(new Dimension(1200, 800));  // Imposta le dimensioni preferite del JScrollPane
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);  // Aggiunta del JScrollPane al centro
        finestra.add(centerPanel, BorderLayout.CENTER);
        finestra.revalidate();  // Aggiorna la finestra principale
        finestra.repaint();  // Ridisegna la finestra principale
    }



    public void salvaOrganigramma() {
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

    public void caricaOrganigrammaDaFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            organigramma = (Organigramma) objectInputStream.readObject();
            JOptionPane.showMessageDialog(this, "Organigramma caricato con successo.", "Caricamento", JOptionPane.INFORMATION_MESSAGE);
            refreshOrganigramma();

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento dell'organigramma:\n" + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }
    }

    public void refreshOrganigramma() {
        finestra.removeAll();
        aggiungiPannelloDiGestione();
        aggiungiPannelloOrganigramma();
        finestra.revalidate();
        finestra.repaint();
        organigramma.printOrganigramma(organigramma.getRoot(), 0); // Stampa l'organigramma nel terminale
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
