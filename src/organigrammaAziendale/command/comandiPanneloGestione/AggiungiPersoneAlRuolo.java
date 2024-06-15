package organigrammaAziendale.command.comandiPanneloGestione;

import organigrammaAziendale.command.Command;
import organigrammaAziendale.composite.Organigramma;
import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.interfacciaGrafica.PannelloDiGestione;

import javax.swing.*;

public class AggiungiPersoneAlRuolo implements Command {

    private Organigramma organigramma;
    private JComboBox<Ruolo> ruoloComboBoxRuoli;
    private JTextField nomePersonaField;
    private PannelloDiGestione pannelloDiGestione;

    public AggiungiPersoneAlRuolo(Organigramma organigramma, JComboBox<Ruolo> ruoloComboBoxRuoli, JTextField nomePersonaField, PannelloDiGestione pannelloDiGestione){
        this.organigramma = organigramma;
        this.ruoloComboBoxRuoli = ruoloComboBoxRuoli;
        this.nomePersonaField = nomePersonaField;
        this.pannelloDiGestione = pannelloDiGestione;
    }

    @Override
    public void execute() {
        Ruolo ruolo = (Ruolo) ruoloComboBoxRuoli.getSelectedItem();
        String nomePersona = nomePersonaField.getText().trim();
        if (ruolo != null && !nomePersona.isEmpty()) {
            organigramma.aggiungiPersonaAlRuolo(ruolo, nomePersona);
            pannelloDiGestione.aggiornaPersoneTextArea(ruolo);
        }
    }
}
