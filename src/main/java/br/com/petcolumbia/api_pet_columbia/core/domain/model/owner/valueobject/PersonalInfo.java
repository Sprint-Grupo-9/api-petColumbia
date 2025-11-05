package br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject;

public class PersonalInfo {
    private String cpf;
    private String phoneNumber;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PersonalInfo(String cpf, String phoneNumber) {
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }
}
