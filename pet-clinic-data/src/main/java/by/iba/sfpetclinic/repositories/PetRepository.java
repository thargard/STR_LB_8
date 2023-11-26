package by.iba.sfpetclinic.repositories;

import by.iba.sfpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository  extends CrudRepository<Pet, Long> {
}

