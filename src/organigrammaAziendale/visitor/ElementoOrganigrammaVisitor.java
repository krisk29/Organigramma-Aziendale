package organigrammaAziendale.visitor;

import organigrammaAziendale.composite.UnitaOrganizzativa;
import organigrammaAziendale.composite.Ruolo;

public interface ElementoOrganigrammaVisitor {
    void visit(UnitaOrganizzativa unita);
    void visit(Ruolo ruolo);
}
