package organigrammaAziendale.visitor;

import organigrammaAziendale.composite.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// per le operazioni di disegno

public class DisegnaVisitor implements ElementoOrganigrammaVisitor {
    private Graphics rettangolo; // l'oggetto da disegnare

    public DisegnaVisitor(Graphics rettangolo) {
        this.rettangolo = rettangolo;
    }

    @Override
    public void visit(UnitaOrganizzativa unita) {
        rettangolo.setColor(Color.BLUE);
        Rectangle bounds = getBounds(unita);
        rettangolo.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        rettangolo.drawString(unita.getNome(), bounds.x + 10, bounds.y + 25);
    }

    @Override
    public void visit(Ruolo ruolo) {
        rettangolo.setColor(Color.GREEN);
        Rectangle bounds = getBounds(ruolo);
        rettangolo.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        rettangolo.drawString(ruolo.getNome(), bounds.x + 10, bounds.y + 20);
    }

    //TODO
    private Rectangle getBounds(ElementoOrganigramma elemento) {
        // Calcola e restituisce i confini dell'elemento
        // Qui puoi implementare la logica per determinare la posizione e le dimensioni
        // dell'elemento basandoti su come vuoi disporre gli elementi nel pannello di disegno
        return new Rectangle(elemento.getX(), elemento.getY(), 150, 50); // Placeholder, dovrai personalizzarlo
    }

//main di prova
public static void main(String[] args) {
    // Crea un frame per visualizzare il disegno
    JFrame frame = new JFrame("Organigramma Aziendale");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    // Crea una lista per tenere traccia degli elementi dell'organigramma
    ArrayList<ElementoOrganigramma> organigramma = new ArrayList<>();

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



    // Aggiungi un MouseListener per ottenere le coordinate del click del mouse
    panel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

            // Crea un ruolo nella posizione del click del mouse
            Ruolo nuovoRuolo = new Ruolo("Nuovo Ruolo");
            nuovoRuolo.setX(e.getX());
            nuovoRuolo.setY(e.getY());

            // Aggiungi il nuovo ruolo all'organigramma
            organigramma.add(nuovoRuolo);

            // Richiama il metodo repaint() per disegnare nuovamente il pannello
            panel.repaint();
        }
    });

    frame.add(panel);
    frame.setVisible(true);
}


}
