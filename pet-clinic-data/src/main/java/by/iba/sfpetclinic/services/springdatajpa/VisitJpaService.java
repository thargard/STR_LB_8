package by.iba.sfpetclinic.services.springdatajpa;

import by.iba.sfpetclinic.model.Visit;
import by.iba.sfpetclinic.repositories.VisitRepository;
import by.iba.sfpetclinic.services.VisitService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@Profile("springdatajpa")
public class VisitJpaService implements VisitService {
    private final VisitRepository visitRepository;
    public VisitJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }
    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }
    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }
    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }
    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }
    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }
}

