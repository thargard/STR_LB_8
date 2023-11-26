package by.iba.sfpetclinic.services;

import by.iba.sfpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>{
    List<Owner> findAllByLastNameLike(String lastName);

}
