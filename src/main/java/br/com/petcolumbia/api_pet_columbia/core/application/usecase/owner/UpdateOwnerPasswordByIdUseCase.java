package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdatePasswordCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.old.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.old.exceptions.EntityUnauthorizedException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UpdateOwnerPasswordByIdUseCase {
    private final OwnerGateway ownerGateway;

    public UpdateOwnerPasswordByIdUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public Owner execute(Integer id, OwnerUpdatePasswordCommand command) {
        Owner owner = ownerGateway.getOwnerById(id);

        if(owner == null)
            throw new EntityNotFoundException("Usuário não encontrado");

        if(!matches(command.currentPassword(), owner.getPassword()))
            throw new EntityUnauthorizedException("Senha atual incorreta");

        owner.setPassword(hashPassword(command.newPassword()));

        return ownerGateway.updatePasswordById(id, owner);

        /*
        implementação spring

        public Owner updatePasswordById(Integer id, Owner owner) {
        OwnerModel ownerModel = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado pelo id: " + id));
        ownerModel.setPassword(owner.getPassword());
        ownerRepository.save(ownerModel);
        return mapToDomain(ownerModel);
    }
         */
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criar hash da senha", e);
        }
    }

    public static boolean matches(String rawPassword, String hashedPassword) {
        String hashedRawPassword = hashPassword(rawPassword);
        return hashedRawPassword.equals(hashedPassword);
    }
}
