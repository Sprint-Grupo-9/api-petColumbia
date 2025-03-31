package br.com.petcolumbia.api_pet_columbia.domain.models;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;

import java.util.List;

public class AvailableTimesModel {
    private EmployeeModel employee;
    List<String> times;

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employeeModel) {
        this.employee = employeeModel;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }
}
