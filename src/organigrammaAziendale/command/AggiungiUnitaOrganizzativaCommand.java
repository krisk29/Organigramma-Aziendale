package organigrammaAziendale.command;

import organigrammaAziendale.composite.UnitaOrganizzativa;

public class AggiungiUnitaOrganizzativaCommand implements Command {
    private UnitaOrganizzativa parent;
    private UnitaOrganizzativa child;

    public AggiungiUnitaOrganizzativaCommand(UnitaOrganizzativa parent, UnitaOrganizzativa child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void execute() {
        parent.add(child);
    }

}