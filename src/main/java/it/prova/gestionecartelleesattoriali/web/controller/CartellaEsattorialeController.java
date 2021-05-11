package it.prova.gestionecartelleesattoriali.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionecartelleesattoriali.model.*;
import it.prova.gestionecartelleesattoriali.service.CartellaEsattorialeService;
import it.prova.gestionecartelleesattoriali.service.ContribuenteService;

@Controller
@RequestMapping(value = "/cartella")
public class CartellaEsattorialeController {
	
	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;
	
	@Autowired
	private ContribuenteService contribuenteService;
	
	@GetMapping
	public ModelAndView listAllCartelle() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService.listAll();
		mv.addObject("cartelle_list_attribute", cartelle);
		mv.setViewName("cartella/list");
		return mv;
	}
	
	@GetMapping("/search")
	public String searchCartelle(Model model) {
		List<String> stati = Stream.of(Stato.values()).map(stato -> stato.name()).collect(Collectors.toList());
		
		model.addAttribute("contribuente_list_attribute", contribuenteService.listAll());
		model.addAttribute("stati_list_attribute", stati);
		
		
		return "cartella/search";
	}
	
	@PostMapping("/list")
	public String listCartelle(CartellaEsattoriale example, ModelMap model) {
		System.out.println(example);
		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService.findByExample(example);
		System.out.println(cartelle);
		model.addAttribute("cartelle_list_attribute", cartelle);
		return "cartella/list";
	}
	
	@GetMapping("/insert")
	public String createCartella(Model model) {
		CartellaEsattoriale cartella = new CartellaEsattoriale();
		cartella.setStatoCartella(Stato.CREATA);
		model.addAttribute("insert_cartella_attr", cartella);
		model.addAttribute("contribuente_list_attribute", contribuenteService.listAll());
		return "cartella/insert";
	}
	
	@PostMapping("/save")
    public String saveCartella(@Valid @ModelAttribute("insert_cartella_attr") CartellaEsattoriale cartellaEsattoriale,  BindingResult result,
			  RedirectAttributes redirectAttrs) {

       
		  if (cartellaEsattoriale.getContribuente() != null && cartellaEsattoriale.getContribuente() .getId() != null)
            cartellaEsattoriale.setContribuente(contribuenteService.caricaSingoloElementoEager(cartellaEsattoriale.getContribuente().getId()));
          else
        	result.rejectValue("contribuente", "contribuente.notnull");

        if (result.hasErrors()) 
            return "cartella/insert";
        
        cartellaEsattorialeService.inserisciNuovo(cartellaEsattoriale);

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/cartella";
    }
	
	@GetMapping("/show/{idCartella}")
    public String showCartellaEsattoriale(@PathVariable(required = true) Long idCartella, Model model) {
        model.addAttribute("show_cartellaesattoriale_attr", cartellaEsattorialeService.caricaSingolaCartellaEsattoriale(idCartella));

        return "cartella/show";
    }
	
	@GetMapping("/edit/{idCartella}")
	public String editFilm(@PathVariable(required = true) Long idCartella, Model model) {
		List<String> stati = Stream.of(Stato.values()).map(stato -> stato.name()).collect(Collectors.toList());
		
		model.addAttribute("update_cartella_attr",
				cartellaEsattorialeService.caricaSingolaCartellaEsattoriale(idCartella));
		model.addAttribute("contribuente_list_attribute", contribuenteService.listAll());
		model.addAttribute("stati_list_attribute", stati);

		return "cartella/edit";
	}
	
	@PostMapping("/edit/saveupdate")
	public String saveEditCartellaEsattoriale(
			@Valid @ModelAttribute("update_cartella_attr") CartellaEsattoriale cartellaEsattorialeInstance,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (cartellaEsattorialeInstance.getContribuente() != null
				&& cartellaEsattorialeInstance.getContribuente().getId() != null)
			cartellaEsattorialeInstance.setContribuente(contribuenteService
					.caricaSingoloElementoEager(cartellaEsattorialeInstance.getContribuente().getId()));
		else
			result.rejectValue("contribuente", "contribuente.notnull");

		if (result.hasErrors()) 
			return "cartella/edit";
		
		cartellaEsattorialeService.aggiorna(cartellaEsattorialeInstance);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartella";
	}
	
	@PostMapping("/cambiastato")
    public String eliminaCartellaEsattoriale(Long idCartellaEsattorialeForChangingStato) {
        CartellaEsattoriale cartellaEsattoriale = cartellaEsattorialeService.caricaSingolaCartellaEsattoriale(idCartellaEsattorialeForChangingStato);
        cartellaEsattorialeService.eliminaCartellaEsattoriale(cartellaEsattoriale);
        return "redirect:/cartella";
    }

}
