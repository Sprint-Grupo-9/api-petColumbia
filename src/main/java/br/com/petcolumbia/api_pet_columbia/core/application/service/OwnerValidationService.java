package br.com.petcolumbia.api_pet_columbia.core.application.service;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerValidationGateway;

public class OwnerValidationService {

    private final OwnerValidationGateway gateway;

    public OwnerValidationService(OwnerValidationGateway gateway) {
        this.gateway = gateway;
    }

    public boolean checkDuplicateFieldsOnCreate(String email,String cpf, String phoneNumber){
        return gateway.existsByEmail(email)
                || gateway.existsByCpf(cpf)
                || gateway.existsByPhoneNumber(phoneNumber);
    }

    public boolean checkDuplicateFieldsOnUpdate(String email,String cpf, String phoneNumber, Integer id){
        return gateway.existsByEmailAndIdNot(email, id)
                || gateway.existsByCpfAndIdNot(cpf, id)
                || gateway.existsByPhoneNumberAndIdNot(phoneNumber, id);
    }
}
