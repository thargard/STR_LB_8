package by.iba.sfpetclinic.bootstrap;

import by.iba.sfpetclinic.model.*;
import by.iba.sfpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoad implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;


    public DataLoad(OwnerService ownerService, VetService vetService,
                    PetService petService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        //
        int count = petTypeService.findAll().size();
        if (count ==0 ) {
            loadData();
        }
    }
    private void loadData() {
    PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogType = petTypeService.save(dog);


        Owner ownerA = new Owner();

        ownerA.setFirstName("Olga");
        ownerA.setLastName("Star");
        ownerA.setAddress("Scorini, 55");
        ownerA.setCity("Minsk");
        ownerA.setTelephone("+37529152432");


        Pet newPet = new Pet();
        newPet.setPetType(dog);
        newPet.setBirthDate(LocalDate.now());
        newPet.setName("Roki");
        ownerA.getPets().add(newPet);
        newPet.setOwner(ownerA);
        ownerService.save(ownerA);

    PetType cat=new PetType();
        cat.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner ownerB = new Owner();

        ownerB.setFirstName("Ylia");
        ownerB.setLastName("Tote");
        ownerB.setAddress("Skolovo, 55");
        ownerB.setCity("Minsk");
        ownerB.setTelephone("+37529152222");


        Pet newPet2 = new Pet();
        newPet2.setPetType(cat);
        newPet2.setBirthDate(LocalDate.now());
        newPet2.setName("Kiti");
        ownerB.getPets().add(newPet2);
        newPet2.setOwner(ownerB);
        ownerService.save(ownerB);


        Visit dogVisit = new Visit();
        dogVisit.setPet(newPet);
        dogVisit.setDate(LocalDate.now());
        dogVisit.setDescription("bad mind");

        System.out.println(ownerService.findAll().size());

        Speciality radiology = new Speciality();
        radiology.setDescription("radiololgy");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality dentistry = new Speciality();
        radiology.setDescription("dentistry");
        Speciality savedDentisty = specialityService.save(dentistry);

        Vet vetA = new Vet();
        vetA.setFirstName("Dina");
        vetA.setLastName("Big");
        vetA.getSpecialities().add(savedRadiology);
        vetA.getSpecialities().add(savedDentisty);
        vetService.save(vetA);

        Vet vetB = new Vet();
        vetB.setFirstName("Paul");
        vetB.setLastName("Small");
        vetB.getSpecialities().add(savedRadiology);
        vetB.getSpecialities().add(savedDentisty);
        vetService.save(vetB);
        System.out.println(vetService.findAll().size());
    }
}