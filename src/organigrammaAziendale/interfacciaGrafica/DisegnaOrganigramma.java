/*package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class DisegnaOrganigramma extends JPanel {
    private ElementoOrganigramma root;
    private int maxX = 0;
    private int maxY = 0;

    public DisegnaOrganigramma(ElementoOrganigramma root) {
        this.root = root;
        calcolaDimensioniMassime(root, getWidth() / 2, 30, 0);

        // Aggiungi un ComponentListener per rilevare cambiamenti nella dimensione del pannello
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni massime e rivedi il disegno quando la finestra viene ridimensionata
                calcolaDimensioniMassime(root, getWidth() / 2, 30, 0);
                repaint();  // Ridisegna il pannello
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        // Aggiunge uno spazio extra per migliorare la visualizzazione
        return new Dimension(maxX + 50, maxY + 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Calcola il centro orizzontale della finestra
        int centerX = getWidth() / 2;
        int initY = 30;  // Posizione iniziale del disegno
        // Ricalcola l'organigramma centrato rispetto al nuovo centro
        drawOrganigramma(g, root, centerX, initY, 0);
    }

    public void drawOrganigramma(Graphics g, ElementoOrganigramma elemento, int x, int y, int level) {
        int yOffset = 100;
        int xOffset = 200;
        int boxWidth = 100;
        int boxHeight = 30;

        // Draw the rectangle and the name inside it
        g.setColor(Color.BLACK);
        g.drawRect(x - boxWidth / 2, y - boxHeight / 2, boxWidth, boxHeight);
        g.drawString(elemento.getNome(), x - g.getFontMetrics().stringWidth(elemento.getNome()) / 2, y + 5);

        // Aggiorna le dimensioni massime raggiunte
        maxX = Math.max(maxX, x + boxWidth / 2);
        maxY = Math.max(maxY, y + boxHeight / 2);

        if (elemento instanceof UnitaOrganizzativa) {
            List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
            int totalWidth = figli.size() * xOffset;
            int startX = x - totalWidth / 2 + xOffset / 2;  // Calcolo del punto di inizio

            // Calcola lo spaziamento tra i genitori in base al numero totale di nodi nel livello corrente
            int parentSpacing = calculateParentSpacing(level, figli.size());

            int childY = y + yOffset;  // Posizione verticale dei figli

            for (ElementoOrganigramma figlio : figli) {
                g.drawLine(x, y + boxHeight / 2, startX, childY - boxHeight / 2);
                drawOrganigramma(g, figlio, startX, childY, level + 1);
                startX += xOffset;  // Mantieni lo spaziamento tra i figli costante
            }
        }
    }

    // Metodo ausiliario per calcolare lo spaziamento tra genitori in base al livello e al numero di figli
    private int calculateParentSpacing(int level, int numChildren) {
        // Aumenta il fattore di moltiplicazione per ottenere un buon spaziamento tra i genitori
        int parentSpacing = 300 + level * 150;

        // Aggiungi spazio basato sulla larghezza totale dei nodi figli
        int totalWidth = numChildren * 200; // Assumiamo xOffset = 200 come nel tuo codice
        parentSpacing += totalWidth;

        return parentSpacing;
    }


    public void calcolaDimensioniMassime(ElementoOrganigramma elemento, int x, int y, int level) {
        int yOffset = 100;
        int xOffset = 200 / (level + 1);
        int boxWidth = 100;
        int boxHeight = 30;

        // Aggiorna le dimensioni massime raggiunte
        maxX = Math.max(maxX, x + boxWidth / 2);
        maxY = Math.max(maxY, y + boxHeight / 2);

        if (elemento instanceof UnitaOrganizzativa) {
            List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
            int childY = y + yOffset;
            int totalWidth = figli.size() * xOffset;
            for (ElementoOrganigramma figlio : figli) {
                int childX = x - (totalWidth / 2) + figli.indexOf(figlio) * xOffset + xOffset / 2;
                calcolaDimensioniMassime(figlio, childX, childY, level + 1);
            }
        }
    }

}
*/


package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class DisegnaOrganigramma extends JPanel {
    private ElementoOrganigramma root;
    private int maxX = 0;
    private int maxY = 0;

    public DisegnaOrganigramma(ElementoOrganigramma root) {
        this.root = root;
        calcolaDimensioniMassime(root, getWidth() / 2, 30, 0);

        // Aggiungi un ComponentListener per rilevare cambiamenti nella dimensione del pannello
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni massime e rivedi il disegno quando la finestra viene ridimensionata
                calcolaDimensioniMassime(root, getWidth() / 2, 30, 0);
                repaint();  // Ridisegna il pannello
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        // Aggiunge uno spazio extra per migliorare la visualizzazione
        return new Dimension(maxX + 50, maxY + 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Calcola il centro orizzontale della finestra
        int centerX = getWidth() / 2;
        int initY = 30;  // Posizione iniziale del disegno
        // Ricalcola l'organigramma centrato rispetto al nuovo centro
        drawOrganigramma(g, root, centerX, initY, 0);
    }

    public void drawOrganigramma(Graphics g, ElementoOrganigramma elemento, int x, int y, int level) {
        int yOffset = 100;
        int xOffset = 200;
        int boxWidth = 100;
        int boxHeight = 30;

        // Draw the rectangle and the name inside it
        g.setColor(Color.BLACK);
        g.drawRect(x - boxWidth / 2, y - boxHeight / 2, boxWidth, boxHeight);
        g.drawString(elemento.getNome(), x - g.getFontMetrics().stringWidth(elemento.getNome()) / 2, y + 5);

        // Aggiorna le dimensioni massime raggiunte
        maxX = Math.max(maxX, x + boxWidth / 2);
        maxY = Math.max(maxY, y + boxHeight / 2);

        if (elemento instanceof UnitaOrganizzativa) {
            List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
            int childY = y + yOffset;
            int totalWidth = figli.size() * xOffset;
            int startX = x - totalWidth / 2 + xOffset / 2;
            for (ElementoOrganigramma figlio : figli) {
                g.drawLine(x, y + boxHeight / 2, startX, childY - boxHeight / 2);
                drawOrganigramma(g, figlio, startX, childY, level + 1);
                startX += xOffset;
            }
        }
    }

    public void calcolaDimensioniMassime(ElementoOrganigramma elemento, int x, int y, int level) {
        int yOffset = 100;
        int xOffset = 200;
        int boxWidth = 100;
        int boxHeight = 30;

        // Aggiorna le dimensioni massime raggiunte
        maxX = Math.max(maxX, x + boxWidth / 2);
        maxY = Math.max(maxY, y + boxHeight / 2);

        if (elemento instanceof UnitaOrganizzativa) {
            List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
            int childY = y + yOffset;
            int totalWidth = figli.size() * xOffset;
            int startX = x - totalWidth / 2 + xOffset / 2;
            for (ElementoOrganigramma figlio : figli) {
                calcolaDimensioniMassime(figlio, startX, childY, level + 1);
                startX += xOffset;
            }
        }
    }

}


/*
   public void drawOrganigramma(Graphics g, ElementoOrganigramma elemento, int x, int y, int level) {
       int yOffset = 100;
       int xOffset = 200 / (level + 1);
       int boxWidth = 100;
       int boxHeight = 30;

       // Draw the rectangle and the name inside it
       g.setColor(Color.BLACK);
       g.drawRect(x - boxWidth / 2, y - boxHeight / 2, boxWidth, boxHeight);
       g.drawString(elemento.getNome(), x - g.getFontMetrics().stringWidth(elemento.getNome()) / 2, y + 5);

       // Aggiorna le dimensioni massime raggiunte
       maxX = Math.max(maxX, x + boxWidth / 2);
       maxY = Math.max(maxY, y + boxHeight / 2);

       if (elemento instanceof UnitaOrganizzativa) {
           List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
           int childY = y + yOffset;
           int totalWidth = figli.size() * xOffset;
           for (ElementoOrganigramma figlio : figli) {
               int childX = x - (totalWidth / 2) + figli.indexOf(figlio) * xOffset + xOffset / 2;
               g.drawLine(x, y + boxHeight / 2, childX, childY - boxHeight / 2);
               drawOrganigramma(g, figlio, childX, childY, level + 1);
           }
       }
   }


    public void calcolaDimensioniMassime(ElementoOrganigramma elemento, int x, int y, int level) {
        int yOffset = 100;
        int xOffset = 200 / (level + 1);
        int boxWidth = 100;
        int boxHeight = 30;

        // Aggiorna le dimensioni massime raggiunte
        maxX = Math.max(maxX, x + boxWidth / 2);
        maxY = Math.max(maxY, y + boxHeight / 2);

        if (elemento instanceof UnitaOrganizzativa) {
            List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
            int childY = y + yOffset;
            int totalWidth = figli.size() * xOffset;
            for (ElementoOrganigramma figlio : figli) {
                int childX = x - (totalWidth / 2) + figli.indexOf(figlio) * xOffset + xOffset / 2;
                calcolaDimensioniMassime(figlio, childX, childY, level + 1);
            }
        }
    }
*/  //funzione vecchia - funzionante

