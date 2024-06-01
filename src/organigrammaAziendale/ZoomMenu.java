package organigrammaAziendale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomMenu extends JMenu {
    private ZoomManager zoomManager;

    public ZoomMenu() {
        super("Visualizza");
        JMenuItem zoomInItem = new JMenuItem("Zoom In");
        JMenuItem zoomOutItem = new JMenuItem("Zoom Out");

        zoomInItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(zoomManager != null)
                    zoomManager.zoom(1.2); // Incrementa il fattore di zoom del 20%
            }
        });

        zoomOutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (zoomManager != null)
                    zoomManager.zoom(0.8); // Decrementa il fattore di zoom del 20%
            }
        });

        add(zoomInItem);
        add(zoomOutItem);
    }

    public void setZoomManager(ZoomManager zoomManager) {
        this.zoomManager = zoomManager;
    }
}
