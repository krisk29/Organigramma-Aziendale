package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.command.*;
import organigrammaAziendale.command.comandiPanneloGestione.*;
import organigrammaAziendale.composite.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                // Aggiorna la schermata e le combobox dopo l'eliminazione
                schermataPrincipale.refreshOrganigramma();
                aggiornaComboBox();
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

    public void aggiornaComboBox() {
        Command aggiornaC = new AggiornaComboBox(organigramma, unitaOrganizzativaModel, unitaOrganizzativaDiAppartenenzaModel, ruoloModelElimina, ruoloModelRuoli, this);
        aggiornaC.execute();
    }

    public void aggiornaRuoloComboBoxPerUnita(UnitaOrganizzativa unitaSelezionata) {
        Command aggiorna = new AggiornaRuoloComboBoxPerUnita(ruoloModelElimina, unitaSelezionata, ruoloComboBoxElimina);
        aggiorna.execute();
    }

    public void aggiornaPersoneTextArea(Ruolo ruolo) {
        Command aggiorna = new AggiornaPersoneTextArea(ruolo, organigramma, personeTextArea);
        aggiorna.execute();
    }

    public void aggiungiPersonaAlRuolo() {
        Command aggiorna = new AggiungiPersoneAlRuolo(organigramma, ruoloComboBoxRuoli, nomePersonaField, this);
        aggiorna.execute();
    }

    public void rimuoviPersonaDalRuolo() {
        Command aggiorna = new RimuoviPersonaDalRuolo(organigramma, ruoloComboBoxRuoli, nomePersonaField, this);
        aggiorna.execute();
    }

    public void aggiungiUnita() {
        Command aggiungiU = new AggiungiUnita(nomeElementoField, unitaOrganizzativaDiAppartenenzaComboBox, this);
        aggiungiU.execute();
    }

    public void aggiungiRuolo() {
        Command aggiungiR = new AggiungiRuolo(nomeElementoField, unitaOrganizzativaDiAppartenenzaComboBox, this);
        aggiungiR.execute();
    }

    public void eliminaUnita() {
        Command eliminaU = new EliminaUnita(organigramma, unitaOrganizzativaComboBox, this);
        eliminaU.execute();
    }

    public void eliminaRuolo() {
        Command eliminaR = new EliminaRuolo(organigramma, ruoloComboBoxElimina,this);
        eliminaR.execute();
    }

}