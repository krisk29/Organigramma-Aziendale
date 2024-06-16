package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisegnaOrganigramma extends JPanel {
    private ElementoOrganigramma root;
    private int maxX = 0;
    private int maxY = 0;
    private Map<ElementoOrganigramma, Integer> subtreeWidthMap = new HashMap<>();
    private static final int MAX_WIDTH = 100; // Larghezza massima del rettangolo
    private static final int INITIAL_BOX_HEIGHT = 30; // Altezza iniziale del rettangolo

    public DisegnaOrganigramma(ElementoOrganigramma root) {
        this.root = root;
        calcolaLarghezzeSottoalbero(root);
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
        int yOffset = 60;  // Distanza verticale tra i livelli
        int boxWidth = MAX_WIDTH;
        int boxHeight;

        // Suddividi il nome in più righe se è troppo lungo
        String nome = elemento.getNome();
        FontMetrics fm = g.getFontMetrics();
        List<String> lines = splitStringToLines(nome, fm, boxWidth);
        boxHeight = Math.max(INITIAL_BOX_HEIGHT, lines.size() * fm.getHeight());

        // Draw the rectangle
        g.setColor(Color.BLACK);
        g.drawRect(x - boxWidth / 2, y - boxHeight / 2, boxWidth, boxHeight);

        // Draw each line of the name inside the rectangle
        int textY = y - boxHeight / 2 + fm.getAscent();
        for (String line : lines) {
            g.drawString(line, x - fm.stringWidth(line) / 2, textY);
            textY += fm.getHeight();
        }

        // Aggiorna le dimensioni massime raggiunte
        maxX = Math.max(maxX, x + boxWidth / 2);
        maxY = Math.max(maxY, y + boxHeight / 2);

        if (elemento instanceof UnitaOrganizzativa) {
            List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
            int childY = y + yOffset + boxHeight / 2;
            int totalWidth = subtreeWidthMap.get(elemento);
            int startX = x - totalWidth / 2;
            for (ElementoOrganigramma figlio : figli) {
                int childSubtreeWidth = subtreeWidthMap.get(figlio);
                int childX = startX + childSubtreeWidth / 2;
                g.drawLine(x, y + boxHeight / 2, childX, childY - boxHeight / 2);
                drawOrganigramma(g, figlio, childX, childY, level + 1);
                startX += childSubtreeWidth;
            }
        }
    }

    public int calcolaLarghezzeSottoalbero(ElementoOrganigramma elemento) {
        int xOffset = 150;  // Distanza orizzontale tra i nodi figli
        int boxWidth = MAX_WIDTH;

        if (elemento instanceof UnitaOrganizzativa) {
            List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
            int totalWidth = 0;
            for (ElementoOrganigramma figlio : figli) {
                totalWidth += calcolaLarghezzeSottoalbero(figlio);
            }
            totalWidth = Math.max(totalWidth, boxWidth + xOffset);
            subtreeWidthMap.put(elemento, totalWidth);
            return totalWidth;
        } else {
            subtreeWidthMap.put(elemento, boxWidth + xOffset);
            return boxWidth + xOffset;
        }
    }

    public void calcolaDimensioniMassime(ElementoOrganigramma elemento, int x, int y, int level) {
        int yOffset = 60;  // Distanza verticale tra i livelli
        int boxWidth = MAX_WIDTH;
        int boxHeight = INITIAL_BOX_HEIGHT;

        // Aggiorna le dimensioni massime raggiunte
        maxX = Math.max(maxX, x + boxWidth / 2);
        maxY = Math.max(maxY, y + boxHeight / 2);

        if (elemento instanceof UnitaOrganizzativa) {
            List<ElementoOrganigramma> figli = ((UnitaOrganizzativa) elemento).getElementi();
            int childY = y + yOffset + boxHeight / 2;
            int totalWidth = subtreeWidthMap.get(elemento);
            int startX = x - totalWidth / 2;
            for (ElementoOrganigramma figlio : figli) {
                int childSubtreeWidth = subtreeWidthMap.get(figlio);
                int childX = startX + childSubtreeWidth / 2;
                calcolaDimensioniMassime(figlio, childX, childY, level + 1);
                startX += childSubtreeWidth;
            }
        }
    }

    private List<String> splitStringToLines(String text, FontMetrics fm, int maxWidth) {
        StringBuilder line = new StringBuilder();
        java.util.List<String> lines = new java.util.ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (fm.stringWidth(line.toString() + c) < maxWidth) {
                line.append(c);
            } else {
                lines.add(line.toString());
                line = new StringBuilder();
                line.append(c);
            }
        }
        lines.add(line.toString());
        return lines;
    }
}
