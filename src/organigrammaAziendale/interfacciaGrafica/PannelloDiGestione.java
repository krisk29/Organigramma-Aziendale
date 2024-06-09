package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PannelloDiGestione extends JPanel {

    private Organigramma organigramma;
    private JTextField nomeElementoField;
    private JComboBox<String> unitaOrganizzativaComboBox;
    private DefaultComboBoxModel<String> unitaOrganizzativaModel;
    private JRadioButton aggiungiUnitaButton;
    private JRadioButton aggiungiRuoloButton;
    private JRadioButton eliminaUnitaButton;
    private JRadioButton eliminaRuoloButton;
    private JComboBox<String> ruoloComboBox;
    private DefaultComboBoxModel<String> ruoloModel;

    public PannelloDiGestione(Organigramma organigramma) {
        this.organigramma = organigramma;
        this.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Pannello per aggiungere elementi
        JPanel aggiungiPannello = new JPanel();
        aggiungiPannello.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        nomeElementoField = new JTextField();
        unitaOrganizzativaModel = new DefaultComboBoxModel<>();
        ruoloModel = new DefaultComboBoxModel<>();

        unitaOrganizzativaComboBox = new JComboBox<>(unitaOrganizzativaModel);
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
            }
        });

        aggiungiRuoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitaOrganizzativaComboBox.setEnabled(true);
                ruoloComboBox.setEnabled(false);
            }
        });

        formPanel.add(new JLabel("Nome elemento:"));
        formPanel.add(nomeElementoField);
        formPanel.add(new JLabel("Unità Organizzativa:"));
        formPanel.add(unitaOrganizzativaComboBox);
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
    }

    private void aggiornaComboBox() {
        unitaOrganizzativaModel.removeAllElements();
        UnitaOrganizzativa root = organigramma.getRoot();
        if (root != null) {
            for (ElementoOrganigramma elem : root.getElementi()) {
                if (elem instanceof UnitaOrganizzativa) {
                    unitaOrganizzativaModel.addElement(elem.getNome());
                }
            }
        }
        aggiornaRuoloComboBox();
    }

    private void aggiornaRuoloComboBox() {
        ruoloModel.removeAllElements();
        String selectedUnita = (String) unitaOrganizzativaComboBox.getSelectedItem();
        UnitaOrganizzativa root = organigramma.getRoot();
        if (root != null && selectedUnita != null) {
            for (ElementoOrganigramma elem : root.getElementi()) {
                if (elem instanceof UnitaOrganizzativa && elem.getNome().equals(selectedUnita)) {
                    for (ElementoOrganigramma ruolo : elem.getElementi()) {
                        if (ruolo instanceof Ruolo) {
                            ruoloModel.addElement(ruolo.getNome());
                        }
                    }
                }
            }
        }
    }

    private void aggiungiUnita() {
        String nome = nomeElementoField.getText();
        UnitaOrganizzativa root = organigramma.getRoot();
        if (root != null && nome != null && !nome.isEmpty()) {
            UnitaOrganizzativa nuovaUnita = new UnitaOrganizzativa(nome);
            organigramma.addElemento(root, nuovaUnita);
            aggiornaComboBox();
        }
    }

    private void aggiungiRuolo() {
        String nome = nomeElementoField.getText();
        String selectedUnita = (String) unitaOrganizzativaComboBox.getSelectedItem();
        UnitaOrganizzativa root = organigramma.getRoot();
        if (root != null && nome != null && !nome.isEmpty() && selectedUnita != null) {
            for (ElementoOrganigramma elem : root.getElementi()) {
                if (elem instanceof UnitaOrganizzativa && elem.getNome().equals(selectedUnita)) {
                    Ruolo nuovoRuolo = new Ruolo(nome);
                    organigramma.addElemento(elem, nuovoRuolo);
                    break;
                }
            }
            aggiornaComboBox();
        }
    }

    private void eliminaUnita() {
        String selectedUnita = (String) unitaOrganizzativaComboBox.getSelectedItem();
        UnitaOrganizzativa root = organigramma.getRoot();
        if (root != null && selectedUnita != null) {
            for (ElementoOrganigramma elem : root.getElementi()) {
                if (elem instanceof UnitaOrganizzativa && elem.getNome().equals(selectedUnita)) {
                    organigramma.removeElemento(root, elem);
                    break;
                }
            }
            aggiornaComboBox();
        }
    }

    private void eliminaRuolo() {
        String selectedUnita = (String) unitaOrganizzativaComboBox.getSelectedItem();
        String selectedRuolo = (String) ruoloComboBox.getSelectedItem();
        UnitaOrganizzativa root = organigramma.getRoot();
        if (root != null && selectedUnita != null && selectedRuolo != null) {
            for (ElementoOrganigramma elem : root.getElementi()) {
                if (elem instanceof UnitaOrganizzativa && elem.getNome().equals(selectedUnita)) {
                    for (ElementoOrganigramma ruolo : elem.getElementi()) {
                        if (ruolo instanceof Ruolo && ruolo.getNome().equals(selectedRuolo)) {
                            organigramma.removeElemento(elem, ruolo);
                            break;
                        }
                    }
                    break;
                }
            }
            aggiornaComboBox();
        }
    }
}
