package by.iba.sfpetclinic.repositories;
import by.iba.sfpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findAllByLastNameLike(String lastName);

}
