package organigrammaAziendale.command.comandiPanneloGestione;

import organigrammaAziendale.command.Command;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;
import organigrammaAziendale.interfacciaGrafica.PannelloDiGestione;

public class AggiungiUnita implements Command {

    private JTextField nomeElementoField;
    private JComboBox<UnitaOrganizzativa> unitaOrganizzativaDiAppartenenzaComboBox;
    private PannelloDiGestione pannelloDiGestione;

    public AggiungiUnita(JTextField nomeElementoField, JComboBox unitaOrganizzativaDiAppartenenzaComboBox, PannelloDiGestione pannelloDiGestione) {
        this.nomeElementoField = nomeElementoField;
        this.unitaOrganizzativaDiAppartenenzaComboBox = unitaOrganizzativaDiAppartenenzaComboBox;
        this.pannelloDiGestione = pannelloDiGestione;
    }

    @Override
    public void execute() {
        String nomeUnita = nomeElementoField.getText().trim();
        UnitaOrganizzativa unitaDiAppartenenza = (UnitaOrganizzativa) unitaOrganizzativaDiAppartenenzaComboBox.getSelectedItem();
        if (!nomeUnita.isEmpty() && unitaDiAppartenenza != null) {
            UnitaOrganizzativa nuovaUnita = new UnitaOrganizzativa(nomeUnita);
            unitaDiAppartenenza.add(nuovaUnita);
            pannelloDiGestione.aggiornaComboBox();
        }
    }
}
