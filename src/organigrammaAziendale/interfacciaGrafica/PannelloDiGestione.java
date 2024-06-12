package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.Organigramma;
import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PannelloDiGestione extends JPanel {

    private Organigramma organigramma;

    private JTextField nomeElementoField;                               //dove metti il nome

    private JComboBox<Ruolo> ruoloComboBox;                            //seleziona quale ruolo
    private JComboBox<UnitaOrganizzativa> unitaOrganizzativaComboBox;               //seleziona quale unita
    private JComboBox<UnitaOrganizzativa> unitaOrganizzativaDiAppartenenzaComboBox; //seleziona a quale unita appartiene

    private DefaultComboBoxModel<UnitaOrganizzativa> unitaOrganizzativaModel;
    private DefaultComboBoxModel<UnitaOrganizzativa> unitaOrganizzativaDiAppartenenzaModel;
    private DefaultComboBoxModel<Ruolo> ruoloModel;

    private JRadioButton aggiungiUnitaButton;
    private JRadioButton aggiungiRuoloButton;
    private JRadioButton eliminaUnitaButton;
    private JRadioButton eliminaRuoloButton;

    public PannelloDiGestione(Organigramma organigramma) {
        this.organigramma = organigramma;
        this.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Pannello per aggiungere elementi
        JPanel aggiungiPannello = new JPanel();
        aggiungiPannello.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        nomeElementoField = new JTextField();
        unitaOrganizzativaModel = new DefaultComboBoxModel<>();
        ruoloModel = new DefaultComboBoxModel<>();
        unitaOrganizzativaDiAppartenenzaModel = new DefaultComboBoxModel<>();

        unitaOrganizzativaComboBox = new JComboBox<>(unitaOrganizzativaModel);
        unitaOrganizzativaDiAppartenenzaComboBox = new JComboBox<>(unitaOrganizzativaDiAppartenenzaModel);
        ruoloComboBox = new JComboBox<>(ruoloModel);
        ruoloComboBox.setEnabled(false);

        aggiungiUnitaButton = new JRadioButton("Aggiungi Unità Organizzativa");
        aggiungiRuoloButton = new JRadioButton("Aggiungi Ruolo");
        ButtonGroup aggiungiGroup = new ButtonGroup();
        aggiungiGroup.add(aggiungiUnitaButton);
        aggiungiGroup.add(aggiungiRuoloButton);

        aggiungiUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitaOrganizzativaComboBox.setEnabled(false);
                ruoloComboBox.setEnabled(false);
                unitaOrganizzativaDiAppartenenzaComboBox.setEnabled(true);
            }
        });

        aggiungiRuoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitaOrganizzativaComboBox.setEnabled(true);
                ruoloComboBox.setEnabled(false);
                unitaOrganizzativaDiAppartenenzaComboBox.setEnabled(true);
            }
        });

        formPanel.add(new JLabel("Nome elemento:"));
        formPanel.add(nomeElementoField);
        formPanel.add(new JLabel("Unità Organizzativa di Appartenenza:"));
        formPanel.add(unitaOrganizzativaDiAppartenenzaComboBox);
        formPanel.add(aggiungiUnitaButton);
        formPanel.add(aggiungiRuoloButton);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton aggiungiButton = new JButton("Aggiungi");
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aggiungiUnitaButton.isSelected()) {
                    aggiungiUnita();
                } else if (aggiungiRuoloButton.isSelected()) {
                    aggiungiRuolo();
                }
            }
        });

        buttonPanel.add(aggiungiButton);

        aggiungiPannello.add(formPanel, BorderLayout.CENTER);
        aggiungiPannello.add(buttonPanel, BorderLayout.SOUTH);

        // Pannello per eliminare elementi
        JPanel eliminaPannello = new JPanel();
        eliminaPannello.setLayout(new BorderLayout());

        JPanel eliminaFormPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        eliminaUnitaButton = new JRadioButton("Elimina Unità Organizzativa");
        eliminaRuoloButton = new JRadioButton("Elimina Ruolo");
        ButtonGroup eliminaGroup = new ButtonGroup();
        eliminaGroup.add(eliminaUnitaButton);
        eliminaGroup.add(eliminaRuoloButton);

        eliminaUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitaOrganizzativaComboBox.setEnabled(true);
                ruoloComboBox.setEnabled(false);
            }
        });

        eliminaRuoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitaOrganizzativaComboBox.setEnabled(true);
                ruoloComboBox.setEnabled(true);
                aggiornaRuoloComboBox();
            }
        });

        eliminaFormPanel.add(new JLabel("Unità Organizzativa:"));
        eliminaFormPanel.add(unitaOrganizzativaComboBox);
        eliminaFormPanel.add(new JLabel("Ruolo:"));
        eliminaFormPanel.add(ruoloComboBox);
        eliminaFormPanel.add(eliminaUnitaButton);
        eliminaFormPanel.add(eliminaRuoloButton);

        JPanel eliminaButtonPanel = new JPanel(new FlowLayout());
        JButton eliminaButton = new JButton("Elimina");
        eliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eliminaUnitaButton.isSelected()) {
                    eliminaUnita();
                } else if (eliminaRuoloButton.isSelected()) {
                    eliminaRuolo();
                }
            }
        });

        eliminaButtonPanel.add(eliminaButton);

        eliminaPannello.add(eliminaFormPanel, BorderLayout.CENTER);
        eliminaPannello.add(eliminaButtonPanel, BorderLayout.SOUTH);

        tabbedPane.add("Aggiungi", aggiungiPannello);
        tabbedPane.add("Elimina", eliminaPannello);

        this.add(tabbedPane, BorderLayout.CENTER);

        aggiornaComboBox();

        // Aggiorna ruoloComboBox quando viene selezionata una nuova unità organizzativa
        unitaOrganizzativaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaRuoloComboBox();
            }
        });
    }   //costruttore


    private void aggiungiUnita() {
        String nome = nomeElementoField.getText();
        UnitaOrganizzativa unitaSelezionata = (UnitaOrganizzativa) unitaOrganizzativaDiAppartenenzaComboBox.getSelectedItem();
        UnitaOrganizzativa root = (UnitaOrganizzativa) organigramma.getRoot();
        if (root != null && nome != null && !nome.isEmpty() && unitaSelezionata != null) {
            if (root.getNome().equals(unitaSelezionata.getNome())) {
                UnitaOrganizzativa nuovaUnita = new UnitaOrganizzativa(nome);
                //organigramma.getRoot().getElementi().add(nuovaUnita);
                organigramma.aggiungiElemento(root, nuovaUnita);
            } else {
                for (ElementoOrganigramma elem : root.getElementi()) {
                    if (elem instanceof UnitaOrganizzativa && elem.getNome().equals(unitaSelezionata.getNome())) {
                        UnitaOrganizzativa nuovaUnita = new UnitaOrganizzativa(nome);
                        organigramma.aggiungiElemento((UnitaOrganizzativa) elem, nuovaUnita);
                        organigramma.getSottoAlbero(elem).getElementi().add(nuovaUnita);
                        break;
                    }
                }
            }
            aggiornaComboBox();
        }
    }

    private void aggiungiRuolo() {
        String nome = nomeElementoField.getText();
        UnitaOrganizzativa unitaSelezionata = (UnitaOrganizzativa) unitaOrganizzativaDiAppartenenzaComboBox.getSelectedItem();
        UnitaOrganizzativa root = (UnitaOrganizzativa)organigramma.getRoot();
        if (root != null && nome != null && !nome.isEmpty()) {
            if (unitaSelezionata == null || root.getNome().equals(unitaSelezionata.getNome())) {
                organigramma.aggiungiElemento(root, new Ruolo(nome));
            } else {
                for (ElementoOrganigramma elem : root.getElementi()) {
                    if (elem instanceof UnitaOrganizzativa && elem.getNome().equals(unitaSelezionata.getNome())) {
                        organigramma.aggiungiElemento((UnitaOrganizzativa) elem, new Ruolo(nome));
                        organigramma.getSottoAlbero(elem).getElementi().add(new Ruolo(nome));
                        break;
                    }
                }
            }
            aggiornaComboBox();
        }
    }

    private void eliminaUnita() {
        UnitaOrganizzativa unitaSelezionata = (UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem();
        UnitaOrganizzativa root = (UnitaOrganizzativa) organigramma.getRoot();

        if (root != null && unitaSelezionata != null) {
            if (root.getNome().equals(unitaSelezionata.getNome())) {
                JOptionPane.showMessageDialog(this, "Non è possibile eliminare la radice dell'organigramma.", "Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean removed = removeUnitRecursive(root, unitaSelezionata);
                if (removed) {
                    aggiornaComboBox();
                } else {
                    JOptionPane.showMessageDialog(this, "Unità Organizzativa non trovata.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private boolean removeUnitRecursive(UnitaOrganizzativa parent, UnitaOrganizzativa target) {
        for (ElementoOrganigramma elem : new ArrayList<>(parent.getElementi())) { // Use a copy of the list to avoid concurrent modification
            if (elem instanceof UnitaOrganizzativa) {
                UnitaOrganizzativa unita = (UnitaOrganizzativa) elem;
                if (unita.getNome().equals(target.getNome())) {
                    organigramma.rimuoviElemento(parent, unita);
                    return true;
                } else {
                    boolean removed = removeUnitRecursive(unita, target);
                    if (removed) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void eliminaRuolo() {
        Ruolo ruoloSelezionato = (Ruolo) ruoloComboBox.getSelectedItem();
        UnitaOrganizzativa unitaSelezionata = (UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem();
        UnitaOrganizzativa root = (UnitaOrganizzativa) organigramma.getRoot();

        if (root != null && ruoloSelezionato != null && unitaSelezionata != null) {
            if (root.getNome().equals(unitaSelezionata.getNome())) {
                rimuoviRuoloDaUnita(root, ruoloSelezionato);
            } else {
                rimuoviRuoloRicorsivo(root, unitaSelezionata, ruoloSelezionato);
            }
            aggiornaComboBox();
        }
    }

    private void rimuoviRuoloRicorsivo(UnitaOrganizzativa corrente, UnitaOrganizzativa targetUnita, Ruolo ruoloSelezionato) {
        for (ElementoOrganigramma elem : corrente.getElementi()) {
            if (elem instanceof UnitaOrganizzativa) {
                UnitaOrganizzativa unita = (UnitaOrganizzativa) elem;
                if (unita.getNome().equals(targetUnita.getNome())) {
                    rimuoviRuoloDaUnita(unita, ruoloSelezionato);
                    return;
                } else {
                    rimuoviRuoloRicorsivo(unita, targetUnita, ruoloSelezionato);
                }
            }
        }
    }

    private void rimuoviRuoloDaUnita(UnitaOrganizzativa unita, Ruolo ruoloSelezionato) {
        for (ElementoOrganigramma elem : new ArrayList<>(unita.getElementi())) {
            if (elem instanceof Ruolo && elem.getNome().equals(ruoloSelezionato.getNome())) {
                organigramma.rimuoviElemento(unita, elem);
                return;
            }
        }
    }

    private void aggiornaComboBox() {
        unitaOrganizzativaModel.removeAllElements();
        unitaOrganizzativaDiAppartenenzaModel.removeAllElements();
        ruoloModel.removeAllElements();

        UnitaOrganizzativa root = (UnitaOrganizzativa) organigramma.getRoot();
        if (root != null) {
            aggiornaComboBoxRecursivo(root);
        }
    }

    private void aggiornaComboBoxRecursivo(UnitaOrganizzativa unita) {
        unitaOrganizzativaModel.addElement(unita);
        unitaOrganizzativaDiAppartenenzaModel.addElement(unita);

        for (ElementoOrganigramma elem : unita.getElementi()) {
            if (elem instanceof UnitaOrganizzativa) {
                aggiornaComboBoxRecursivo((UnitaOrganizzativa) elem);
            }
        }
    }

    private void aggiornaRuoloComboBox() {
        ruoloModel.removeAllElements();
        UnitaOrganizzativa unitaSelezionata = (UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem();
        if (unitaSelezionata != null) {
            for (ElementoOrganigramma elem : unitaSelezionata.getElementi()) {
                if (elem instanceof Ruolo) {
                    ruoloModel.addElement((Ruolo) elem);
                }
            }
        }
    }
}



/*
    private void eliminaUnita() {
        UnitaOrganizzativa unitaSelezionata = (UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem();
        UnitaOrganizzativa root = (UnitaOrganizzativa)organigramma.getRoot();
        ElementoOrganigramma elementoPrecedente = null;
        if (root != null && unitaSelezionata != null) {
            if (root.getNome().equals(unitaSelezionata.getNome())) {
                JOptionPane.showMessageDialog(this, "Non è possibile eliminare la radice dell'organigramma.", "Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                for (ElementoOrganigramma elem : root.getElementi()) {
                    if (elem instanceof UnitaOrganizzativa && elem.getNome().equals(unitaSelezionata.getNome())) {
                        organigramma.rimuoviElemento(elementoPrecedente, (UnitaOrganizzativa) elem);
                        organigramma.getSottoAlbero(elem).getElementi().remove(elem);
                        break;
                    }
                    elementoPrecedente = elem;
                }
            }
            aggiornaComboBox();
        }
    }*/ //vecchio eliminaUnita
/*
    private void eliminaRuolo() {
        Ruolo ruoloSelezionato = (Ruolo) ruoloComboBox.getSelectedItem();
        UnitaOrganizzativa unitaSelezionata = (UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem();
        UnitaOrganizzativa root = (UnitaOrganizzativa) organigramma.getRoot();

        if (root != null && ruoloSelezionato != null && unitaSelezionata != null) {
            for (ElementoOrganigramma elem : root.getElementi()) {
                if (elem instanceof UnitaOrganizzativa && elem.getNome().equals(unitaSelezionata.getNome())) {
                    organigramma.rimuoviElemento(elem, ruoloSelezionato);
                    break;
                }
            }
            aggiornaComboBox();
        }
    }*/ //vecchio eliminaRuolo