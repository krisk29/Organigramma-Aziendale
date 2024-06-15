package organigrammaAziendale.command.comandiPanneloGestione;

import organigrammaAziendale.command.Command;
import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.Organigramma;
import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.composite.UnitaOrganizzativa;
import organigrammaAziendale.interfacciaGrafica.PannelloDiGestione;

import javax.swing.*;

public class AggiornaComboBox implements Command {

    private Organigramma organigramma;
    private DefaultComboBoxModel<UnitaOrganizzativa> unitaOrganizzativaModel;
    private DefaultComboBoxModel<UnitaOrganizzativa> unitaOrganizzativaDiAppartenenzaModel;
    private DefaultComboBoxModel<Ruolo> ruoloModelElimina;
    private DefaultComboBoxModel<Ruolo> ruoloModelRuoli;
    private PannelloDiGestione pannelloDiGestione;

    public AggiornaComboBox(Organigramma organigramma, DefaultComboBoxModel<UnitaOrganizzativa> unitaOrganizzativaModel, DefaultComboBoxModel<UnitaOrganizzativa> unitaOrganizzativaDiAppartenenzaModel, DefaultComboBoxModel<Ruolo> ruoloModelElimina, DefaultComboBoxModel<Ruolo> ruoloModelRuoli, PannelloDiGestione pannelloDiGestione) {
        this.organigramma = organigramma;
        this.unitaOrganizzativaModel = unitaOrganizzativaModel;
        this.unitaOrganizzativaDiAppartenenzaModel = unitaOrganizzativaDiAppartenenzaModel;
        this.ruoloModelElimina = ruoloModelElimina;
        this.ruoloModelRuoli = ruoloModelRuoli;
        this.pannelloDiGestione = pannelloDiGestione;
    }

    @Override
    public void execute() {
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

}
