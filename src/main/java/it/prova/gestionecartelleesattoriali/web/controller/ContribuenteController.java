package it.prova.gestionecartelleesattoriali.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.gestionecartelleesattoriali.model.Contribuente;
import it.prova.gestionecartelleesattoriali.service.ContribuenteService;

@Controller
@RequestMapping(value = "/contribuente")
public class ContribuenteController {
	
	@Autowired
	private ContribuenteService contribuenteService;
	
	@GetMapping
	public ModelAndView listAllContribuenti() {
		ModelAndView mv = new ModelAndView();
		
		List<Contribuente> contribuenti = contribuenteService.listAll();
		
		mv.addObject("contribuenti_list_attribute", contribuenti);
		mv.setViewName("contribuente/list");
		return mv;
	}
	
	@GetMapping("/search")
	public String searchContribuente() {
		return "contribuente/search";
	}
	
	@PostMapping("/list")
	public String listContribuenti(Contribuente example,  ModelMap model) {
		List<Contribuente> contribuenti = contribuenteService.findByExample(example);
		
		model.addAttribute("contribuenti_list_attribute", contribuenti);
		return "contribuente/list";
	}
	
	@GetMapping("/insert")
	public String createContribuente(Model model) {
		model.addAttribute("contribuente_insert_attribute", new Contribuente());
		return "contribuente/insert";
	}
	
	@PostMapping("/save")
	public String saveContribuente(@Valid @ModelAttribute("contribuente_insert_attribute") Contribuente contribuente, BindingResult result,
			RedirectAttributes redirectAttrs) {
		if(result.hasErrors())
			return "contribuente/insert";
		
		contribuenteService.inserisciNuovo(contribuente);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		
		return "redirect:/contribuente";
	}
	
	@GetMapping("/show/{idContribuente}")
	public String showContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("show_contribuente_attr", contribuenteService.caricaSingoloContribuente(idContribuente));

		return "contribuente/show";
	}
	
	@GetMapping("/edit/{idContribuente}")
	public String editContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("update_contribuente_attr", contribuenteService.caricaSingoloContribuente(idContribuente));
		
		return "contribuente/edit";
	}
	
	@PostMapping("/edit/saveupdate")
	public String saveEditContribuente(@Valid @ModelAttribute("update_contribuente_attr") Contribuente contribuente, BindingResult result,
								  RedirectAttributes redirectAttrs) {
		
		if (result.hasErrors()) 
			return "contribuente/edit";
		
		contribuenteService.aggiorna(contribuente);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");

		return "redirect:/contribuente";
	}
	
	@GetMapping("/delete/{idContribuente}")
	public String prepareDeleteContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("delete_contribuente_attr", contribuenteService.caricaSingoloElementoEager(idContribuente));
		
		return "contribuente/delete";
	}
	
	@PostMapping("/delete/executedelete")
	public String executeDeleteContribuente(@ModelAttribute("delete_contribuente_attr") Contribuente contribuente,  RedirectAttributes redirectAttrs) {
		try {
			contribuenteService.rimuovi(contribuenteService.caricaSingoloContribuente(contribuente.getId()));
		} catch (RuntimeException e) {
			redirectAttrs.addFlashAttribute("errorMessage", "Impossibile eliminare questo contribuente, ha delle cartelle ancora associate.");
			return "redirect:/contribuente";
		}
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");

		return "redirect:/contribuente";
	}
	
	@GetMapping(value = "/searchContribuentiAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchRegista(@RequestParam String term) {

		List<Contribuente> listaContribuenteByTerm = contribuenteService.cercaByCognomeENomeILike(term);
		return buildJsonResponse(listaContribuenteByTerm);
	}
	
	private String buildJsonResponse(List<Contribuente> listaContribuente) {
		JsonArray ja = new JsonArray();

		for (Contribuente contribuenteItem : listaContribuente) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", contribuenteItem.getId());
			jo.addProperty("label", contribuenteItem.getNome() + " " + contribuenteItem.getCognome());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}

}
