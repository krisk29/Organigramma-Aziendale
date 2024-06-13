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
    private SchermataPrincipale schermataPrincipale;

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

    public PannelloDiGestione(SchermataPrincipale schermataPrincipale, Organigramma organigramma) {
        this.organigramma = organigramma;
        this.schermataPrincipale = schermataPrincipale;
        this.setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pannello per aggiungere elementi
        JPanel aggiungiPannello = new JPanel();
        aggiungiPannello.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 3));
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
                schermataPrincipale.refreshOrganigramma();
            }
        });

        buttonPanel.add(aggiungiButton);

        aggiungiPannello.add(formPanel, BorderLayout.CENTER);
        aggiungiPannello.add(buttonPanel, BorderLayout.SOUTH);

        // Pannello per eliminare elementi
        JPanel eliminaPannello = new JPanel();
        eliminaPannello.setLayout(new BorderLayout());

        JPanel eliminaFormPanel = new JPanel(new GridLayout(3, 2, 5, 3));
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
            }
        });

        eliminaFormPanel.add(eliminaUnitaButton);
        eliminaFormPanel.add(eliminaRuoloButton);
        eliminaFormPanel.add(new JLabel("Seleziona Unità Organizzativa:"));
        eliminaFormPanel.add(unitaOrganizzativaComboBox);
        eliminaFormPanel.add(new JLabel("Seleziona Ruolo:"));
        eliminaFormPanel.add(ruoloComboBox);

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
                schermataPrincipale.refreshOrganigramma();
            }
        });

        eliminaButtonPanel.add(eliminaButton);

        eliminaPannello.add(eliminaFormPanel, BorderLayout.CENTER);
        eliminaPannello.add(eliminaButtonPanel, BorderLayout.SOUTH);

        // Aggiungi i pannelli alle tab
        tabbedPane.addTab("Aggiungi Elemento", aggiungiPannello);
        tabbedPane.addTab("Elimina Elemento", eliminaPannello);

        // Aggiungi il tabbed pane al pannello principale
        this.add(tabbedPane, BorderLayout.CENTER);

        aggiornaComboBox();
    }

    private void aggiornaComboBox() {
        unitaOrganizzativaModel.removeAllElements();
        ruoloModel.removeAllElements();
        unitaOrganizzativaDiAppartenenzaModel.removeAllElements();

        UnitaOrganizzativa root = (UnitaOrganizzativa) organigramma.getRoot();
        if (root != null) {
            aggiungiElementiAlleComboBox(root);
        }
    }

    private void aggiungiElementiAlleComboBox(UnitaOrganizzativa unita) {
        unitaOrganizzativaModel.addElement(unita);
        unitaOrganizzativaDiAppartenenzaModel.addElement(unita);
        for (ElementoOrganigramma elemento : unita.getElementi()) {
            if (elemento instanceof UnitaOrganizzativa) {
                UnitaOrganizzativa unitaFiglia = (UnitaOrganizzativa) elemento;
                aggiungiElementiAlleComboBox(unitaFiglia);
            } else if (elemento instanceof Ruolo) {
                Ruolo ruolo = (Ruolo) elemento;
                ruoloModel.addElement(ruolo);
            }
        }
    }

    private void aggiungiUnita() {
        String nomeUnita = nomeElementoField.getText().trim();
        UnitaOrganizzativa unitaDiAppartenenza = (UnitaOrganizzativa) unitaOrganizzativaDiAppartenenzaComboBox.getSelectedItem();
        if (!nomeUnita.isEmpty() && unitaDiAppartenenza != null) {
            UnitaOrganizzativa nuovaUnita = new UnitaOrganizzativa(nomeUnita);
            unitaDiAppartenenza.add(nuovaUnita);
            aggiornaComboBox();

        }
    }

    private void aggiungiRuolo() {
        String nomeRuolo = nomeElementoField.getText().trim();
        UnitaOrganizzativa unitaDiAppartenenza = (UnitaOrganizzativa) unitaOrganizzativaDiAppartenenzaComboBox.getSelectedItem();
        if (!nomeRuolo.isEmpty() && unitaDiAppartenenza != null) {
            Ruolo nuovoRuolo = new Ruolo(nomeRuolo);
            unitaDiAppartenenza.add(nuovoRuolo);
            aggiornaComboBox();

        }
    }

    private void eliminaUnita() {
        UnitaOrganizzativa unita = (UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem();
        if (unita != null && !unita.equals(organigramma.getRoot())) {
            UnitaOrganizzativa unitaDiAppartenenza = trovaUnitaDiAppartenenza((UnitaOrganizzativa) organigramma.getRoot(), unita);
            if (unitaDiAppartenenza != null) {
                unitaDiAppartenenza.remove(unita);
                aggiornaComboBox();

            }
        }
    }

    private void eliminaRuolo() {
        Ruolo ruolo = (Ruolo) ruoloComboBox.getSelectedItem();
        if (ruolo != null) {
            UnitaOrganizzativa unitaDiAppartenenza = trovaUnitaDiAppartenenzaPerRuolo((UnitaOrganizzativa)organigramma.getRoot(), ruolo);
            if (unitaDiAppartenenza != null) {
                unitaDiAppartenenza.remove(ruolo);
                aggiornaComboBox();

            }
        }
    }

    private UnitaOrganizzativa trovaUnitaDiAppartenenza(UnitaOrganizzativa unitaCorrente, UnitaOrganizzativa unitaDaTrovare) {
        for (ElementoOrganigramma elemento : unitaCorrente.getElementi()) {
            if (elemento.equals(unitaDaTrovare)) {
                return unitaCorrente;
            } else if (elemento instanceof UnitaOrganizzativa) {
                UnitaOrganizzativa unita = trovaUnitaDiAppartenenza((UnitaOrganizzativa) elemento, unitaDaTrovare);
                if (unita != null) {
                    return unita;
                }
            }
        }
        return null;
    }

    private UnitaOrganizzativa trovaUnitaDiAppartenenzaPerRuolo(UnitaOrganizzativa unitaCorrente, Ruolo ruoloDaTrovare) {
        for (ElementoOrganigramma elemento : unitaCorrente.getElementi()) {
            if (elemento.equals(ruoloDaTrovare)) {
                return unitaCorrente;
            } else if (elemento instanceof UnitaOrganizzativa) {
                UnitaOrganizzativa unita = trovaUnitaDiAppartenenzaPerRuolo((UnitaOrganizzativa) elemento, ruoloDaTrovare);
                if (unita != null) {
                    return unita;
                }
            }
        }
        return null;
    }
}
