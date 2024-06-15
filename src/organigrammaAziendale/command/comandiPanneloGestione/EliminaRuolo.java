package organigrammaAziendale.command.comandiPanneloGestione;

import organigrammaAziendale.command.Command;
import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.Organigramma;
import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.composite.UnitaOrganizzativa;
import organigrammaAziendale.interfacciaGrafica.PannelloDiGestione;

import javax.swing.*;

public class EliminaRuolo implements Command {

    private Organigramma organigramma;
    private JComboBox<Ruolo> ruoloComboBoxElimina;
    private PannelloDiGestione pannelloDiGestione;

    public EliminaRuolo(Organigramma organigramma, JComboBox<Ruolo> ruoloComboBoxElimina, PannelloDiGestione pannelloDiGestione) {
        this.organigramma = organigramma;
        this.ruoloComboBoxElimina = ruoloComboBoxElimina;
        this.pannelloDiGestione = pannelloDiGestione;
    }

    @Override
    public void execute() {
        Ruolo ruolo = (Ruolo) ruoloComboBoxElimina.getSelectedItem();
        if (ruolo != null) {
            UnitaOrganizzativa unitaDiAppartenenza = trovaUnitaDiAppartenenzaPerRuolo((UnitaOrganizzativa) organigramma.getRoot(), ruolo);
            if (unitaDiAppartenenza != null) {
                unitaDiAppartenenza.remove(ruolo);
                pannelloDiGestione.aggiornaComboBox();
            }
        }
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
