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

    private JTextField nomeElementoField;
    private JTextField nomePersonaField;
    private JTextArea personeTextArea;

    private JComboBox<Ruolo> ruoloComboBoxElimina;
    private JComboBox<Ruolo> ruoloComboBoxRuoli;
    private JComboBox<UnitaOrganizzativa> unitaOrganizzativaComboBox;
    private JComboBox<UnitaOrganizzativa> unitaOrganizzativaDiAppartenenzaComboBox;

    private DefaultComboBoxModel<UnitaOrganizzativa> unitaOrganizzativaModel;
    private DefaultComboBoxModel<UnitaOrganizzativa> unitaOrganizzativaDiAppartenenzaModel;
    private DefaultComboBoxModel<Ruolo> ruoloModelElimina;
    private DefaultComboBoxModel<Ruolo> ruoloModelRuoli;

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
        ruoloModelElimina = new DefaultComboBoxModel<>();
        ruoloModelRuoli = new DefaultComboBoxModel<>();
        unitaOrganizzativaDiAppartenenzaModel = new DefaultComboBoxModel<>();
        unitaOrganizzativaComboBox = new JComboBox<>(unitaOrganizzativaModel);
        unitaOrganizzativaDiAppartenenzaComboBox = new JComboBox<>(unitaOrganizzativaDiAppartenenzaModel);
        ruoloComboBoxElimina = new JComboBox<>(ruoloModelElimina);
        ruoloComboBoxElimina.setEnabled(false);

        aggiungiUnitaButton = new JRadioButton("Aggiungi Unità Organizzativa");
        aggiungiRuoloButton = new JRadioButton("Aggiungi Ruolo");
        ButtonGroup aggiungiGroup = new ButtonGroup();
        aggiungiGroup.add(aggiungiUnitaButton);
        aggiungiGroup.add(aggiungiRuoloButton);

        aggiungiUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitaOrganizzativaComboBox.setEnabled(false);
                ruoloComboBoxElimina.setEnabled(false);
                unitaOrganizzativaDiAppartenenzaComboBox.setEnabled(true);
                aggiornaRuoloComboBoxPerUnita((UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem());
            }
        });

        aggiungiRuoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitaOrganizzativaComboBox.setEnabled(true);
                ruoloComboBoxElimina.setEnabled(false);
                unitaOrganizzativaDiAppartenenzaComboBox.setEnabled(true);
                aggiornaRuoloComboBoxPerUnita((UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem());
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
                aggiornaRuoloComboBoxPerUnita((UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem());
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
                ruoloComboBoxElimina.setEnabled(false);
                aggiornaRuoloComboBoxPerUnita((UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem());
            }
        });

        eliminaRuoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitaOrganizzativaComboBox.setEnabled(true);
                ruoloComboBoxElimina.setEnabled(true);

                // Aggiorna la combobox dei ruoli solo se è selezionato il radiobutton per eliminare ruoli
                aggiornaRuoloComboBoxPerUnita((UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem());
            }
        });

        eliminaFormPanel.add(new JLabel("Seleziona Unità Organizzativa:"));
        eliminaFormPanel.add(unitaOrganizzativaComboBox);
        eliminaFormPanel.add(new JLabel("Seleziona Ruolo:"));
        eliminaFormPanel.add(ruoloComboBoxElimina);
        eliminaFormPanel.add(eliminaUnitaButton);
        eliminaFormPanel.add(eliminaRuoloButton);

        unitaOrganizzativaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaRuoloComboBoxPerUnita((UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem());
            }
        });

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
                aggiornaRuoloComboBoxPerUnita((UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem());
            }
        });

        eliminaButtonPanel.add(eliminaButton);

        eliminaPannello.add(eliminaFormPanel, BorderLayout.CENTER);
        eliminaPannello.add(eliminaButtonPanel, BorderLayout.SOUTH);

        // Pannello per gestire i ruoli
        JPanel ruoliPannello = new JPanel();
        ruoliPannello.setLayout(new BorderLayout());

        JPanel ruoliFormPanel = new JPanel(new GridLayout(3, 2, 5, 3));
        nomePersonaField = new JTextField();
        personeTextArea = new JTextArea(5, 20);
        personeTextArea.setEditable(false);
        ruoloComboBoxRuoli = new JComboBox<>(ruoloModelRuoli);

        ruoloComboBoxRuoli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaPersoneTextArea((Ruolo) ruoloComboBoxRuoli.getSelectedItem());
            }
        });

        ruoliFormPanel.add(new JLabel("Seleziona Ruolo:"));
        ruoliFormPanel.add(ruoloComboBoxRuoli);
        ruoliFormPanel.add(new JLabel("Scrivi il nome da aggiungere o eliminare:"));
        ruoliFormPanel.add(new JLabel(""));
        ruoliFormPanel.add(new JLabel("Nome Persona:"));
        ruoliFormPanel.add(nomePersonaField);


        JPanel ruoliButtonPanel = new JPanel(new FlowLayout());
        JButton aggiungiPersonaButton = new JButton("Aggiungi Persona");
        JButton rimuoviPersonaButton = new JButton("Rimuovi Persona");

        aggiungiPersonaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiPersonaAlRuolo();
            }
        });

        rimuoviPersonaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rimuoviPersonaDalRuolo();
            }
        });

        ruoliButtonPanel.add(aggiungiPersonaButton);
        ruoliButtonPanel.add(rimuoviPersonaButton);

        ruoliPannello.add(ruoliFormPanel, BorderLayout.NORTH);
        ruoliPannello.add(new JScrollPane(personeTextArea), BorderLayout.CENTER);
        ruoliPannello.add(ruoliButtonPanel, BorderLayout.SOUTH);

        // Aggiungi i pannelli alle tab
        tabbedPane.addTab("Aggiungi Elemento", aggiungiPannello);
        tabbedPane.addTab("Elimina Elemento", eliminaPannello);
        tabbedPane.addTab("Ruoli", ruoliPannello);

        this.add(tabbedPane, BorderLayout.CENTER);

        aggiornaComboBox();
    }

    private void aggiornaComboBox() {
        unitaOrganizzativaModel.removeAllElements();
        ruoloModelElimina.removeAllElements();
        ruoloModelRuoli.removeAllElements();
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
                ruoloModelElimina.addElement(ruolo);
                ruoloModelRuoli.addElement(ruolo);
            }
        }
    }

    private void aggiornaRuoloComboBoxPerUnita(UnitaOrganizzativa unitaSelezionata) {
        ruoloModelElimina.removeAllElements(); // Rimuovi tutti gli elementi dal modello
        if (unitaSelezionata != null) {
            for (ElementoOrganigramma elemento : unitaSelezionata.getElementi()) {
                if (elemento instanceof Ruolo) {
                    ruoloModelElimina.addElement((Ruolo) elemento);
                }
            }
        }
        ruoloComboBoxElimina.repaint(); // Forza il repaint della combobox
    }

    private void aggiornaPersoneTextArea(Ruolo ruolo) {
        personeTextArea.setText("");
        if (ruolo != null) {
            ArrayList<String> persone = organigramma.getPersonePerRuolo(ruolo);
            for (String persona : persone) {
                personeTextArea.append(persona + "\n");
            }
        }
    }

    private void aggiungiPersonaAlRuolo() {
        Ruolo ruolo = (Ruolo) ruoloComboBoxRuoli.getSelectedItem();
        String nomePersona = nomePersonaField.getText().trim();
        if (ruolo != null && !nomePersona.isEmpty()) {
            organigramma.aggiungiPersonaAlRuolo(ruolo, nomePersona);
            aggiornaPersoneTextArea(ruolo);
        }
    }

    private void rimuoviPersonaDalRuolo() {
        Ruolo ruolo = (Ruolo) ruoloComboBoxRuoli.getSelectedItem();
        String nomePersona = nomePersonaField.getText().trim();
        if (ruolo != null && !nomePersona.isEmpty()) {
            organigramma.rimuoviPersonaDalRuolo(ruolo, nomePersona);
            aggiornaPersoneTextArea(ruolo);
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
        Ruolo ruolo = (Ruolo) ruoloComboBoxElimina.getSelectedItem();
        if (ruolo != null) {
            UnitaOrganizzativa unitaDiAppartenenza = trovaUnitaDiAppartenenzaPerRuolo((UnitaOrganizzativa) organigramma.getRoot(), ruolo);
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
