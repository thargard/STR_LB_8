package by.iba.sfpetclinic.controller;

import by.iba.sfpetclinic.model.Owner;
import by.iba.sfpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
    @Controller
    public class OwnerController {

        private final OwnerService ownerService;
        private static final String OWNER_CREATE_OR_UPDATE_FORM =
            "owners/createOrUpdateOwnerForm";
    public OwnerController(OwnerService ownerService) {
            this.ownerService = ownerService;
        }

//        @RequestMapping({"","/","/index","/index.html"})
//        public String listOwners(Model model){
//            model.addAttribute("owners", ownerService.findAll());
//            return "owners/index";
//        }

        @RequestMapping("/find")
        public String findOwners(Model model){
            model.addAttribute("owner", Owner.builder().build());
            return "owners/findOwners";
        }


    @GetMapping("/{ownerId}")
        public ModelAndView showOwner(@PathVariable Long ownerId) {
            ModelAndView mav = new ModelAndView("owners/ownerDetails");
            mav.addObject(ownerService.findById(ownerId));
            return mav;
        }
        @InitBinder
        public void setAllowedFields(WebDataBinder dataBinder) {
            dataBinder.setDisallowedFields("id"); //что автоматический не нужно привязывать поле
        }
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model
            model){
        // GET request для /owners вернет все записи
        if (owner.getLastName() == null) {
            owner.setLastName(""); // пустая строка
        }
        // поиск по lastName
        List<Owner> results = ownerService.findAllByLastNameLike("%"+
                owner.getLastName() + "%");
        if (results.isEmpty()) {
            // не найдены
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // найден один результат
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // несколько
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }
    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return OWNER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return OWNER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult
            result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }}








