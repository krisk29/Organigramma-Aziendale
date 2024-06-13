package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DisegnaOrganigramma extends JPanel {
    private ElementoOrganigramma root;

    public DisegnaOrganigramma(ElementoOrganigramma root) {
        this.root = root;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawOrganigramma(g, root, getWidth() / 2, 30, 0);
    }

    private void drawOrganigramma(Graphics g, ElementoOrganigramma elemento, int x, int y, int level) {
        int yOffset = 100;
        int xOffset = 200 / (level + 1);
        int boxWidth = 100;
        int boxHeight = 30;

        // Draw the rectangle and the name inside it
        g.setColor(Color.BLACK);
        g.drawRect(x - boxWidth / 2, y - boxHeight / 2, boxWidth, boxHeight);
        g.drawString(elemento.getNome(), x - g.getFontMetrics().stringWidth(elemento.getNome()) / 2, y + 5);

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


}
