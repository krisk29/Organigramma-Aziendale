package organigrammaAziendale.command.comandiPanneloGestione;

import organigrammaAziendale.command.Command;
import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.Organigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;
import organigrammaAziendale.interfacciaGrafica.PannelloDiGestione;

import javax.swing.*;

public class EliminaUnita implements Command {

    private Organigramma organigramma;
    private JComboBox<UnitaOrganizzativa> unitaOrganizzativaComboBox;
    private PannelloDiGestione pannelloDiGestione;

    public EliminaUnita(Organigramma organigramma, JComboBox<UnitaOrganizzativa> unitaOrganizzativaComboBox, PannelloDiGestione pannelloDiGestione) {
        this.organigramma = organigramma;
        this.unitaOrganizzativaComboBox = unitaOrganizzativaComboBox;
        this.pannelloDiGestione = pannelloDiGestione;
    }

    @Override
    public void execute() {
        UnitaOrganizzativa unita = (UnitaOrganizzativa) unitaOrganizzativaComboBox.getSelectedItem();
        if (unita != null) {
            if (unita.equals(organigramma.getRoot())) {
                // Mostra un popup di errore
                JOptionPane.showMessageDialog(pannelloDiGestione,"Non puoi eliminare l'unità radice!","Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                UnitaOrganizzativa unitaDiAppartenenza = trovaUnitaDiAppartenenza((UnitaOrganizzativa) organigramma.getRoot(), unita);
                if (unitaDiAppartenenza != null) {
                    unitaDiAppartenenza.remove(unita);
                    pannelloDiGestione.aggiornaComboBox();
                }
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

}
