package br.com.petcolumbia.api_pet_columbia.models;
import java.util.List;

public class AvailableTimes {
    private EmployeeModel employeeModel;
    List<String> times;

    public EmployeeModel getEmployee() {
        return employeeModel;
    }

    public void setEmployee(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }
}
