package br.com.petcolumbia.api_pet_columbia.core.domain.procedure;

import br.com.petcolumbia.api_pet_columbia.core.domain.procedure_price_and_duration.ProcedurePriceAndDuration;
import br.com.petcolumbia.api_pet_columbia.core.domain.associations.EmployeeProcedureAssociation;

import java.util.List;

public class Procedure {
    private Integer id;
    private String name;
    private String description;
    private List<EmployeeProcedureAssociation> employeeProcedures;
    private List<ProcedurePriceAndDuration> pricesAndDurations;

    public Procedure() {
    }

    public Procedure(Integer id, String name, String description, List<EmployeeProcedureAssociation> employeeProcedures, List<ProcedurePriceAndDuration> pricesAndDurations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employeeProcedures = employeeProcedures;
        this.pricesAndDurations = pricesAndDurations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EmployeeProcedureAssociation> getEmployeeProcedures() {
        return employeeProcedures;
    }

    public void setEmployeeProcedures(List<EmployeeProcedureAssociation> employeeProcedures) {
        this.employeeProcedures = employeeProcedures;
    }

    public List<ProcedurePriceAndDuration> getPricesAndDurations() {
        return pricesAndDurations;
    }

    public void setPricesAndDurations(List<ProcedurePriceAndDuration> pricesAndDurations) {
        this.pricesAndDurations = pricesAndDurations;
    }
}
