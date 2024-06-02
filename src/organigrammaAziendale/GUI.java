package organigrammaAziendale;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    final String TITOLO = "Organigramma Aziendale";
    JPanel finestra = new JPanel();
    JScrollPane finestraScroll = new JScrollPane(finestra, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private ZoomManager zoomManager;

    // Costruttore, inizializza la finestra
    public GUI() {
        super();
        this.setTitle(TITOLO);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Termina il programma quando chiuso
        this.setResizable(true);
        // Setto di base a schermo intero la finestra
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Massimizza la finestra
        this.setVisible(true);

        // Aggiungo il JScrollPane al JFrame
        this.getContentPane().add(finestraScroll); // Rendo la finestra scrollabile se necessario

        // Setto un layout manager
        finestra.setLayout(new BorderLayout());

        // Aggiungo la barra del menu
        creaMenu();
    }

    private void creaMenu() {
        JMenuBar menuBar = new JMenuBar();
        ZoomMenu zoomMenu = new ZoomMenu();
        JMenu file = new JMenu("File");
        // Aggiungi i menu alla barra del menu
        menuBar.add(file);  //da creare il menu che gestisce i file
        menuBar.add(zoomMenu);

        // Imposta la barra del menu nel JFrame
        this.setJMenuBar(menuBar);
        zoomManager = new ZoomManager(finestra);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
