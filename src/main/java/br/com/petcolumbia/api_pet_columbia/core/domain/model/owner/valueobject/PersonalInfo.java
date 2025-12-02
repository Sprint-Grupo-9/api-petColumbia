package br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalInfo {
    private String cpf;
    private String phoneNumber;

    public PersonalInfo() {
    }

    public PersonalInfo(String cpf, String phoneNumber) {
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }

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
}
