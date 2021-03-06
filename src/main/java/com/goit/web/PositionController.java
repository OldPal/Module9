package com.goit.web;

import com.goit.model.Position;
import com.goit.service.PositionService;
import com.goit.web.validators.PositionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PositionController {

    private PositionService positionService;

    @Autowired
    private PositionValidator positionValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(positionValidator);
    }

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public String showAllPositions(Model model) {
        model.addAttribute("positions", positionService.getAllPosition());
        return "positions/list_positions";
    }

    @RequestMapping(value = "/positions/{id}", method = RequestMethod.GET)
    public String showPosition(@PathVariable("id") int id, Model model) {
        Position position = positionService.findPositionById(id);

        if (position == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Position not found");
        }
        model.addAttribute("position", position);
        return "positions/show";
    }

    @RequestMapping(value = "/positions/{id}/delete", method = RequestMethod.POST)
    public String deletePosition(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        positionService.deletePosition(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Position is deleted!");

        return "redirect:/positions";
    }

    @RequestMapping(value = "/positions/{id}/update", method = RequestMethod.GET)
    public String showUpdatePositionForm(@PathVariable("id") int id, Model model) {
        Position position = positionService.findPositionById(id);
        model.addAttribute("position_form", position);

        return "positions/position_form";
    }

    @RequestMapping(value = "/positions/create", method = RequestMethod.GET)
    public String showCreatePositionForm(Model model) {
        Position position = new Position();
        model.addAttribute("position_form", position);

        return "positions/position_form";
    }

    @RequestMapping(value = "/positions", method = RequestMethod.POST)
    public String saveOrUpdatePosition(@ModelAttribute("position_form") @Validated Position position,
                                   BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "positions/position_form";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(position.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Position added successfully!");
                positionService.createPosition(position);
            }else{
                redirectAttributes.addFlashAttribute("msg", "Position updated successfully!");
                positionService.updatePositionTitle(position.getId(), position.getPositionTitle());
            }

            return "redirect:/positions/" + position.getId();
        }
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }
}
