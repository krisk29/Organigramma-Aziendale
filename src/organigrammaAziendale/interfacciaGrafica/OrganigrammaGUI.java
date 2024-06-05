package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;
import organigrammaAziendale.observer.Observer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;


// TODO : da cancellare


public class OrganigrammaGUI implements Observer {
    private JFrame frame;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private Organigramma organigramma;

    public OrganigrammaGUI(Organigramma organigramma) {
        this.organigramma = organigramma;
        this.organigramma.addObserver(this);
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        treeModel = new DefaultTreeModel(null);
        tree = new JTree(treeModel);
        JScrollPane treeView = new JScrollPane(tree);

        frame.getContentPane().add(treeView, BorderLayout.CENTER);
        frame.setVisible(true);

        update();  // Inizializza l'albero con i dati correnti
    }

    @Override
    public void update() {
        UnitaOrganizzativa root = organigramma.getRoot();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root.getNome());
        buildTree(root, rootNode);
        treeModel.setRoot(rootNode);
        treeModel.reload();
    }

    private void buildTree(ElementoOrganigramma element, DefaultMutableTreeNode treeNode) {
        for (ElementoOrganigramma child : element.getElementi()) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getNome());
            treeNode.add(childNode);
            if (child instanceof UnitaOrganizzativa) {
                buildTree(child, childNode);
            }
        }
    }
}
