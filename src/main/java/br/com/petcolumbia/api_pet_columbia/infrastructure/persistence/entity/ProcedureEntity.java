package br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity;

import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.association.EmployeeProcedureAssociationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "procedure")
public class ProcedureEntity {
    @Id
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "procedure")
    private List<EmployeeProcedureAssociationEntity> employeeProcedures;

    @OneToMany(mappedBy = "procedure")
    private List<ProcedurePriceAndDurationEntity> pricesAndDurations;

    public ProcedureEntity() {
    }

    public ProcedureEntity(Integer id, String name, String description, List<EmployeeProcedureAssociationEntity> employeeProcedures, List<ProcedurePriceAndDurationEntity> pricesAndDurations) {
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

    public List<EmployeeProcedureAssociationEntity> getEmployeeProcedures() {
        return employeeProcedures;
    }

    public void setEmployeeProcedures(List<EmployeeProcedureAssociationEntity> employeeProcedures) {
        this.employeeProcedures = employeeProcedures;
    }

    public List<ProcedurePriceAndDurationEntity> getPricesAndDurations() {
        return pricesAndDurations;
    }

    public void setPricesAndDurations(List<ProcedurePriceAndDurationEntity> pricesAndDurations) {
        this.pricesAndDurations = pricesAndDurations;
    }
}
