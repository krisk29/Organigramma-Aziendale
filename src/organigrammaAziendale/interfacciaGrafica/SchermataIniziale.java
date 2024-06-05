package organigrammaAziendale.interfacciaGrafica;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SchermataIniziale extends JFrame {

    final String TITOLO = "Organigramma Aziendale";
    private final String SFONDO_PATH = "images/schermata-iniziale.jpg";

    public SchermataIniziale() {
        super();
        this.setTitle(TITOLO);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Termina il programma quando chiuso
        this.setResizable(true);
        // Setto di base a schermo intero la finestra
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Massimizza la finestra
        this.setVisible(true);

        // Pannello per le funzioni + sfondo
        JPanel pannello = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica l'immagine di sfondo e la disegna sul pannello
                Image sfondo = new ImageIcon(SFONDO_PATH).getImage();
                g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();

        // Inserisco i bottoni nel pannello -> (nuovo organigramma - carica organigramma - esci)
        JButton nuovoOrganigramma = new JButton("Nuovo Organigramma");
        JButton caricaOrganigramma = new JButton("Carica Organigramma");
        JButton chiudiProgramma = new JButton("Chiudi Programma");

        // Dimensione bottoni
        Dimension dimensioneBottoni = new Dimension(200, 50);
        nuovoOrganigramma.setPreferredSize(dimensioneBottoni);
        caricaOrganigramma.setPreferredSize(dimensioneBottoni);
        chiudiProgramma.setPreferredSize(dimensioneBottoni);

        nuovoOrganigramma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuovaSchermata();
            }
        });

        caricaOrganigramma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caricaOrganigramma();
            }
        });

        chiudiProgramma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chiudiProgramma();
            }
        });

        // Posizionamento e dimensionamento del primo pulsante
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pannello.add(nuovoOrganigramma, gbc);

        // Posizionamento e dimensionamento del secondo pulsante
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pannello.add(caricaOrganigramma, gbc);

        // Posizionamento e dimensionamento del secondo pulsante
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pannello.add(chiudiProgramma, gbc);

        // Aggiunta del pannello al JFrame
        this.add(pannello);

        // Centra la finestra
        this.setLocationRelativeTo(null);

        // Mostra il JFrame
        this.setVisible(true);
    }

    public void nuovaSchermata() {
        // Crea un'istanza della seconda schermata
        GUI secondaSchermata = new GUI();   //da cambiare con la schermata che creerò

        // Rendi visibile la seconda schermata
        secondaSchermata.setVisible(true);

        // Chiudi la schermata corrente se necessario
        dispose();
    }

    public void caricaOrganigramma() {
        // Crea un selettore di file
        JFileChooser fileChooser = new JFileChooser();

        // Aggiungi filtri per i file con estensioni specifiche
        FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("File di testo (.txt)", "txt");
        FileNameExtensionFilter filterCsv = new FileNameExtensionFilter("File CSV (.csv)", "csv");
        fileChooser.addChoosableFileFilter(filterTxt);
        fileChooser.addChoosableFileFilter(filterCsv);

        // Imposta il titolo del selettore di file
        fileChooser.setDialogTitle("Seleziona il file da caricare");

        // Mostra il selettore di file
        int selezione = fileChooser.showOpenDialog(this);

        // Se l'utente ha selezionato un file
        if (selezione == JFileChooser.APPROVE_OPTION) {
            File fileSelezionato = fileChooser.getSelectedFile();
            // Qui puoi scrivere il codice per elaborare il file selezionato
            // Ad esempio, puoi leggere il file e caricare l'organigramma
            System.out.println("File selezionato: " + fileSelezionato.getAbsolutePath()); //operazione di esempio
        }
    }

    public void chiudiProgramma() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new SchermataIniziale();
    }
}
