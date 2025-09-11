package br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject;

public class Adress {
    private String cep;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;

    public Adress() {
    }

    public Adress(String cep, String neighborhood, String street, String number, String complement) {
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
