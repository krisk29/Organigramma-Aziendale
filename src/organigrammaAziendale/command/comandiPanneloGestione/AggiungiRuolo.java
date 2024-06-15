package organigrammaAziendale.command.comandiPanneloGestione;

import organigrammaAziendale.command.Command;
import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.composite.UnitaOrganizzativa;
import organigrammaAziendale.interfacciaGrafica.PannelloDiGestione;

import javax.swing.*;

public class AggiungiRuolo implements Command {

    private JTextField nomeElementoField;
    private JComboBox<UnitaOrganizzativa> unitaOrganizzativaDiAppartenenzaComboBox;
    private PannelloDiGestione pannelloDiGestione;

    public AggiungiRuolo(JTextField nomeElementoField, JComboBox unitaOrganizzativaDiAppartenenzaComboBox, PannelloDiGestione pannelloDiGestione) {
        this.nomeElementoField = nomeElementoField;
        this.unitaOrganizzativaDiAppartenenzaComboBox = unitaOrganizzativaDiAppartenenzaComboBox;
        this.pannelloDiGestione = pannelloDiGestione;
    }

    @Override
    public void execute() {
        String nomeRuolo = nomeElementoField.getText().trim();
        UnitaOrganizzativa unitaDiAppartenenza = (UnitaOrganizzativa) unitaOrganizzativaDiAppartenenzaComboBox.getSelectedItem();
        if (!nomeRuolo.isEmpty() && unitaDiAppartenenza != null) {
            Ruolo nuovoRuolo = new Ruolo(nomeRuolo);
            unitaDiAppartenenza.add(nuovoRuolo);
            pannelloDiGestione.aggiornaComboBox();
        }
    }

}
