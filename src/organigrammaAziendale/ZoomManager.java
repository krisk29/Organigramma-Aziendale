package organigrammaAziendale;

import javax.swing.*;
import java.awt.*;

public class ZoomManager {
    private double zoomFactor = 1.0; // Fattore di zoom iniziale
    private JPanel finestra;

    public ZoomManager(JPanel finestra) {
        this.finestra = finestra;
    }

    public void zoom(double factor) {
        zoomFactor *= factor;
        for (Component comp : finestra.getComponents()) {
            if (comp instanceof JComponent) {
                JComponent jComp = (JComponent) comp;
                Dimension originalSize = jComp.getPreferredSize();
                int newWidth = (int) (originalSize.width * factor);
                int newHeight = (int) (originalSize.height * factor);
                jComp.setPreferredSize(new Dimension(newWidth, newHeight));
            }
        }
        finestra.revalidate();
        finestra.repaint();
    }
}
