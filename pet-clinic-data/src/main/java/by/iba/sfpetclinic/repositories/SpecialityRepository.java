package by.iba.sfpetclinic.repositories;

import by.iba.sfpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository  extends CrudRepository<Speciality,
        Long> {
}

