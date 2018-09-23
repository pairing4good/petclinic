package com.pairing4good.petclinic.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
public class OwnerController {

    public static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerController(OwnerRepository ownerRepository) {

        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/owners/new")
    public String setupSave(Map<String, Object> model) {
        model.put("owner", new Owner());
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/owners/new")
    public String save(@Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            ownerRepository.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/owners/find")
    public String setupFind(Map<String, Object> model) {
        model.put("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView findById(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);
        mav.addObject(optionalOwner.get());
        return mav;
    }
}
